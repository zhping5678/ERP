package pkg.unclassified;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RecoverWindowButtonPane extends StackPane {
    public RecoverWindowButtonPane(){
        Button recoverWindowButton=new Button();
        Rectangle rectangle=new Rectangle(Parameters.WINDOW_BUTTON_WIDTH,Parameters.LEFT_BUTTON_HEIGHT);
        this.getChildren().add(rectangle);
        this.getChildren().add(recoverWindowButton);
        recoverWindowButton.getStylesheets().add("pkg/stylesheet/RecoverWindowButton.css");
        rectangle.setFill(Color.grayRgb(255, 0));
        recoverWindowButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();
            }
        });

        recoverWindowButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(191));
                fillTransition.play();

            }
        });
        recoverWindowButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(255, 0));
                fillTransition.play();

            }
        });
        recoverWindowButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();

            }
        });
        recoverWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              //  PrimaryStage.getInstance().setMaximized(false);
                PrimaryStage.getInstance().setX(TitleBar.getInstance().recoverX);
                PrimaryStage.getInstance().setY(TitleBar.getInstance().recoverY);
                PrimaryStage.getInstance().setWidth(TitleBar.getInstance().recoverWidth);
                PrimaryStage.getInstance().setHeight(TitleBar.getInstance().recoverHeight);

                PrimaryStage.getInstance().movable=true;
                PrimaryStage.getInstance().resizable=true;

                TitleBar.getInstance().getChildren().remove(TitleBar.getInstance().recoverWindowButtonPane);
                TitleBar.getInstance().getChildren().add(1,TitleBar.getInstance().maxWindowButtonPane);



            }
        });


    }
}