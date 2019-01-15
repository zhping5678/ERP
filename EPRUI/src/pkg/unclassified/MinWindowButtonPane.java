package pkg.unclassified;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MinWindowButtonPane extends StackPane {
    public MinWindowButtonPane(){
        Button minWindowButton=new Button();
        Rectangle rectangle=new Rectangle(Parameters.WINDOW_BUTTON_WIDTH,Parameters.LEFT_BUTTON_HEIGHT);
        this.getChildren().add(rectangle);
        this.getChildren().add(minWindowButton);
        minWindowButton.getStylesheets().add("pkg/stylesheet/MinWindowButton.css");
        rectangle.setFill(Color.grayRgb(255, 0));
        minWindowButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();
            }
        });

        minWindowButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(191));
                fillTransition.play();

            }
        });
        minWindowButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(255, 0));
                fillTransition.play();

            }
        });
        minWindowButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition=new FillTransition(Duration.millis(200),rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();

            }
        });
        minWindowButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

               // PrimaryStage.getInstance().toBack();
                PrimaryStage.getInstance().setIconified(true);

                System.out.println("min");
            }
        });

    }
}