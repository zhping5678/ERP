package pkg.unclassified;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class MaxWindowButtonPane extends StackPane {
    public MaxWindowButtonPane(){
        Button maxWindowButton=new Button();
        Rectangle rectangle=new Rectangle(Parameters.WINDOW_BUTTON_WIDTH,Parameters.LEFT_BUTTON_HEIGHT);
        this.getChildren().add(rectangle);
        this.getChildren().add(maxWindowButton);
        maxWindowButton.getStylesheets().add("pkg/stylesheet/MaxWindowButton.css");
        rectangle.setFill(Color.grayRgb(255, 0));
        maxWindowButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();
            }
        });

        maxWindowButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(191));
                fillTransition.play();

            }
        });
        maxWindowButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(255, 0));
                fillTransition.play();

            }
        });
        maxWindowButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();

            }
        });

        maxWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {



                TitleBar.getInstance().recoverX = PrimaryStage.getInstance().getX();
                TitleBar.getInstance().recoverY = PrimaryStage.getInstance().getY();
                TitleBar.getInstance().recoverHeight = PrimaryStage.getInstance().getHeight();
                TitleBar.getInstance().recoverWidth = PrimaryStage.getInstance().getWidth();


                //  PrimaryStage.getInstance().setMaximized(true);
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                PrimaryStage.getInstance().setX(bounds.getMinX());
                PrimaryStage.getInstance().setY(bounds.getMinY());
                PrimaryStage.getInstance().setWidth(bounds.getWidth());
                PrimaryStage.getInstance().setHeight(bounds.getHeight());



                PrimaryStage.getInstance().movable = false;
                PrimaryStage.getInstance().resizable = false;
                TitleBar.getInstance().getChildren().remove(TitleBar.getInstance().maxWindowButtonPane);
                TitleBar.getInstance().getChildren().add(1, TitleBar.getInstance().recoverWindowButtonPane);


            }
        });


    }
}