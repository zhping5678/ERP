package pkg.unclassified;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CloseWindowButtonPane extends StackPane {
    public CloseWindowButtonPane(){
        Button closeWindowButton=new Button();
        Rectangle rectangle=new Rectangle(Parameters.WINDOW_BUTTON_WIDTH,Parameters.LEFT_BUTTON_HEIGHT);
        this.getChildren().add(rectangle);
        this.getChildren().add(closeWindowButton);
        closeWindowButton.getStylesheets().add("pkg/stylesheet/CloseWindowButton.css");
        rectangle.setFill(Color.grayRgb(255, 0));
        closeWindowButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.rgb(185, 19, 15));
                fillTransition.play();
            }
        });

        closeWindowButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.rgb(136, 14, 10));
                fillTransition.play();

            }
        });
        closeWindowButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(255, 0));
                fillTransition.play();

            }
        });
        closeWindowButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.rgb(185, 19, 15));
                fillTransition.play();

            }
        });
        closeWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PrimaryStage.getInstance().close();
            }
        });


    }
}
