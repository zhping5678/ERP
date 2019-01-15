package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ResizePane extends Pane{
    private boolean isRight;// 是否处于右边界调整窗口状态
    private boolean isBottomRight;// 是否处于右下角调整窗口状态
    private boolean isBottom;// 是否处于下边界调整窗口状态
    public ResizePane(){
        this.setStyle(this.getStyle()
                +"-fx-background-color:transparent;"
           //     +"-fx-pref-width: 100000;"
            //    +"-fx-pref-height: 12;"
                +"-fx-min-width: 10000;"
                +"-fx-min-height: 14;"
        );
        AnchorPane.setBottomAnchor(this,0.0);
        AnchorPane.setRightAnchor(this,0.0);

        this.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (PrimaryStage.getInstance().resizable) {
                    double RESIZE_WIDTH = Parameters.RESIZE_WIDTH;
                    double x = event.getSceneX();
                    double y = event.getSceneY();
/*
                System.out.print("x:");
                System.out.print(x);
                System.out.print("y:");
                System.out.println(y);
*/

                    double height = PrimaryStage.getInstance().getHeight();
                    double width = PrimaryStage.getInstance().getWidth();
                    Cursor cursorType = Cursor.DEFAULT;// 鼠标光标初始为默认类型，若未进入调整窗口状态，保持默认类型
                    // 先将所有调整窗口状态重置
                    isRight = isBottomRight = isBottom = false;

                    if (y >= height - RESIZE_WIDTH) {
                        if (x >= width - RESIZE_WIDTH) {
                            isBottomRight = true;
                            cursorType = Cursor.SE_RESIZE;
                        } else {
                            isBottom = true;
                            cursorType = Cursor.S_RESIZE;
                        }
                    } else {
                        if (x >= width - RESIZE_WIDTH) {
                            isRight = true;
                            cursorType = Cursor.E_RESIZE;
                            //           System.out.println("sdfsdlfkjsdlkfjklsjdflkdsjflksjfldsjfldsjflksjfl");
                        }

                    }

                    ((Pane) (event.getSource())).setCursor(cursorType);
                }
            }


        });

        this.setOnMouseDragged((MouseEvent event) -> {
            if (PrimaryStage.getInstance().resizable) {


                double x = event.getSceneX();
                double y = event.getSceneY();
                // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
                double nextX = PrimaryStage.getInstance().getX();
                double nextY = PrimaryStage.getInstance().getY();
                double nextWidth = PrimaryStage.getInstance().getWidth();
                double nextHeight = PrimaryStage.getInstance().getHeight();
                if (isRight || isBottomRight) {// 所有右边调整窗口状态
                    nextWidth = x;
                    System.out.println("MouseDragged to resize");
                }
                if (isBottomRight || isBottom) {// 所有下边调整窗口状态
                    nextHeight = y;
                    System.out.println("MouseDragged to resize");

                }
                if (nextWidth <= Parameters.WINDOWS_WIDTH) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                    nextWidth = Parameters.WINDOWS_WIDTH;

                }
                if (nextHeight <= Parameters.WINDOWS_HEIGHT) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                    nextHeight = Parameters.WINDOWS_HEIGHT;
                }
                // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
                PrimaryStage.getInstance().setX(nextX);
                PrimaryStage.getInstance().setY(nextY);
                PrimaryStage.getInstance().setWidth(nextWidth);
                PrimaryStage.getInstance().setHeight(nextHeight);
                TitleBar.getInstance().recoverHeight = PrimaryStage.getInstance().getHeight();
                TitleBar.getInstance().recoverWidth = PrimaryStage.getInstance().getWidth();
            }

        });

    }
}
