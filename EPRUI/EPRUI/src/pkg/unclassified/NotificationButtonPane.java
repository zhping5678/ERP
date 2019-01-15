package pkg.unclassified;

import javafx.animation.FillTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pkg.ui.messageui.MessageUIControllerAccess;
import pkg.ui.messageui.NotificationViewer;

public class NotificationButtonPane extends StackPane {

    Button notificationButton=new Button();
    Boolean isWithRedSpot=false;
    public NotificationButtonPane() {

        Rectangle rectangle = new Rectangle(Parameters.WINDOW_BUTTON_WIDTH, Parameters.LEFT_BUTTON_HEIGHT);
        this.getChildren().add(rectangle);
        this.getChildren().add(notificationButton);
        notificationButton.getStylesheets().add("pkg/stylesheet/NotificationButton.css");
        rectangle.setFill(Color.grayRgb(255, 0));
        notificationButton.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition = new FillTransition(Duration.millis(200), rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();
            }
        });
        addRedSpot();

        notificationButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition = new FillTransition(Duration.millis(200), rectangle);
                fillTransition.setToValue(Color.grayRgb(191));
                fillTransition.play();

            }
        });
        notificationButton.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition = new FillTransition(Duration.millis(200), rectangle);
                fillTransition.setToValue(Color.grayRgb(255, 0));
                fillTransition.play();

            }
        });
        notificationButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FillTransition fillTransition = new FillTransition(Duration.millis(200), rectangle);
                fillTransition.setToValue(Color.grayRgb(223));
                fillTransition.play();

            }
        });
        notificationButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //    NotificationBar.addAndShowNotificationBar(NotificationBar.NotificationBarType.NOTICE,"DONE",true,"CLOSE");
                /*
                if (!isWithRedSpot) {
                    addRedSpot();//For Test
                    isWithRedSpot=true;
                }else{
                    removeRedSpot();
                    isWithRedSpot=false;
                }
                */


                if (!NotificationViewer.getInstance().isVisible()) {
                    NotificationViewer.getInstance().loadData();
                    PrimaryStage.getInstance().showNotificationViewer();
                } else {
                    PrimaryStage.getInstance().hideNotificationViewer();
                }
                updateRedSpot();


            }
        });

    }

    public void updateRedSpot(){
        if(MessageUIControllerAccess.messageUIControllerAccess.getMessages().size()>0){
            addRedSpot();
        }else{
            removeRedSpot();
        }
    }

    private void addRedSpot(){
        notificationButton.setStyle(this.getStyle()
                +"-fx-background-image:url(/pkg/image/NotificationButtonIconWithRedSpot.png);"
        );

    }
    private void removeRedSpot(){
        notificationButton.setStyle(this.getStyle()
                +"-fx-background-image:url(/pkg/image/NotificationButtonIcon.png);"
        );

    }
}