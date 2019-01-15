package pkg.unclassified;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pkg.ui.messageui.NotificationViewer;

public class NotificationBar extends AnchorPane {
    public Button button;
    public Label label;
    public boolean stay;

    public static  NotificationBar addAndShowNotificationBar(NotificationBarType notificationBarType,String labelText, boolean stay,String buttonName){
        NotificationBar notificationBar=new NotificationBar();
        notificationBar.label=new Label(labelText);

        notificationBar.setPrefHeight(Parameters.LEFT_BUTTON_HEIGHT);
        notificationBar.prefWidthProperty().bind(PrimaryStage.getInstance().widthProperty().subtract(Parameters.LEFT_BUTTON_WIDTH+Parameters.MIDDLE_PREF_WIDTH));


        if (notificationBarType==NotificationBarType.NOTICE){
            notificationBar.setStyle(notificationBar.getStyle()
                    +"-fx-background-color:rgba(56,153,77,0.77);"
                    +"-fx-background-image: url(/pkg/image/NoticeIcon.png);"


            );
        }
        if (notificationBarType==NotificationBarType.ATTENTION){
            notificationBar.setStyle(notificationBar.getStyle()
                    +"-fx-background-color:rgba(222,144,43,0.76);"
                    +"-fx-background-image: url(/pkg/image/AttentionIcon.png);"


            );
        }
        if (notificationBarType==NotificationBarType.WARNING){
            notificationBar.setStyle(notificationBar.getStyle()
                    +"-fx-background-color:rgba(187,15,26,0.73);"
                    +"-fx-background-image: url(/pkg/image/WarningIcon.png);"


            );
        }

        notificationBar.setStyle(notificationBar.getStyle()
                +"-fx-background-repeat: no-repeat;"
                +"-fx-background-size: 16 16;"
                +"-fx-background-position: 7 7;"
        );

        notificationBar.getChildren().add(notificationBar.label);
        notificationBar.label.setTextFill(Color.WHITE);
        notificationBar.label.setLayoutX(30);
        notificationBar.label.setLayoutY(7);


        if (!buttonName.equals("CLOSE")) {
            notificationBar.button = new Button(buttonName);
            notificationBar.button.setTextFill(Color.WHITE);
            notificationBar.button.setStyle(notificationBar.button.getStyle()
                    + "-fx-background-color:transparent;"
                    + "-fx-font-size: 9;"
            );

        }else{
            notificationBar.button = new Button();
         //   notificationBar.button.setTextFill(Color.WHITE);
            notificationBar.button.setStyle(notificationBar.button.getStyle()
                    + "-fx-background-color:transparent;"
                    + "-fx-background-image: url(/pkg/image/CloseWindowButtonIconHover.png);"
                    + "-fx-background-repeat: no-repeat;"
                    +"-fx-background-size :13 13;"
                    +"-fx-background-position :2 9;"
          //          + "-fx-font-size: 9;"

            );
            notificationBar.button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    notificationBar.hide();
                }
            });


        }

        AnchorPane.setBottomAnchor(notificationBar.button, 5.0);
        AnchorPane.setRightAnchor(notificationBar.button, 5.0);
        notificationBar.getChildren().add(notificationBar.button);



        PrimaryStage.getInstance().rootAnchorPane.getChildren().add(PrimaryStage.getInstance().rootAnchorPane.getChildren().size()-1,notificationBar);
        if(NotificationViewer.getInstance().isVisible()){
            notificationBar.setEffect(new GaussianBlur(11));
        }

        AnchorPane.setRightAnchor(notificationBar,0.0);
        AnchorPane.setBottomAnchor(notificationBar,0.0);


        notificationBar.setTranslateY(3*Parameters.LEFT_BUTTON_HEIGHT);
        notificationBar.show();
        return notificationBar;
    }

    public void stay(){
        TranslateTransition translateTransition =new TranslateTransition(Duration.millis(5000),this);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!stay){
                    hide();
                }
            }
        });


    }

    public void show(){
        TranslateTransition translateTransition =new TranslateTransition(Duration.millis(700),this);
        translateTransition.setByY(-3*Parameters.LEFT_BUTTON_HEIGHT);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stay();
            }
        });
    }

    public void hide(){
        TranslateTransition translateTransition =new TranslateTransition(Duration.millis(700),this);
        translateTransition.setByY(3*Parameters.LEFT_BUTTON_HEIGHT);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                remove();
            }
        });
    }

    public void remove(){
        if(PrimaryStage.getInstance().rootAnchorPane.getChildren().indexOf(this)>=0){
            PrimaryStage.getInstance().rootAnchorPane.getChildren().remove(this);
            System.out.println("removed");
        }
    }



    public static NotificationBar addAndShowNotificationBar(NotificationBarType notificationBarType,String labelText){
        return addAndShowNotificationBar(notificationBarType,labelText,false,"CLOSE");
    }
    public enum NotificationBarType{
        WARNING,
        ATTENTION,
        NOTICE
    }

}
