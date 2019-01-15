package pkg.ui.messageui;

import javafx.animation.*;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pkg.unclassified.Parameters;
import vo.messagevo.MessageVO;

import java.util.ArrayList;


public class NotificationViewer extends ScrollPane {

    public VBox vBox=new VBox();
    public ArrayList<NotificationItemPane> notificationItemPaneList = new ArrayList<NotificationItemPane>();


    public double WIDTH= Parameters.NOTIFICATION_VIEWER_WIDTH;
    public double HEIGHT=705;
    public Label noNotifications;
    private static NotificationViewer notificationViewer;
    public NotificationViewer(){

        this.setPrefSize(0,0);

        this.setEffect(new DropShadow(18, Color.grayRgb(190)));
        this.getStylesheets().add("pkg/stylesheet/NotificationViewer.css");
        vBox.setStyle(vBox.getStyle()
                +"-fx-padding:13 13  13 13;"
              //  +"-fx-background-color: pink"
        );
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10.0);

        noNotifications=new Label("No Notifications");
        noNotifications.setStyle(noNotifications.getStyle()
          //      +"-fx-background-color:pink;"
                +"-fx-font-size: 20;"
                +"-fx-text-fill: rgb(147,147,147)"
        );
        noNotifications.setAlignment(Pos.CENTER);
        noNotifications.setPrefWidth(WIDTH);
        noNotifications.setPrefHeight(540);
        this.setContent(noNotifications);

        vBox.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                if (vBox.getChildren().size()<=8){
                    vBox.setPrefWidth(Parameters.NOTIFICATION_VIEWER_WIDTH);

                }else{
                    vBox.setPrefWidth(Parameters.NOTIFICATION_VIEWER_WIDTH-12);
                }
                if (vBox.getChildren().size()==0){
                    notificationViewer.setContent(noNotifications);
                }else{
                    setContentAsVBox();
                }

            }
        });
        loadData();
        //addSomethingForTest();/*FOR TEST*/


    }
    public void setContentAsVBox(){
        this.setContent(vBox);/* Why did I have to split this method out?*/
    }

    public void show(){
        this.setVisible(true);
        this.setPrefSize(0,0);
        Timeline timeline = new Timeline();

        KeyValue keyValueWidth = new KeyValue(this.prefWidthProperty(), WIDTH);
        Duration durationWidth=Duration.millis(130);
        KeyFrame keyFrameWidth=new KeyFrame(durationWidth,keyValueWidth);
        timeline.getKeyFrames().add(keyFrameWidth);


        KeyValue keyValueHeight = new KeyValue(this.prefHeightProperty(), HEIGHT);
        Duration durationHeight=Duration.millis(200);
        KeyFrame keyFrameHeight=new KeyFrame(durationHeight,keyValueHeight);
        timeline.getKeyFrames().add(keyFrameHeight);

        FadeTransition fadeTransition=new FadeTransition(Duration.millis(100),this);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        timeline.play();





    }
    public void hide(){

        Timeline timeline = new Timeline();




        KeyValue keyValueHeight = new KeyValue(this.prefHeightProperty(), 0);
        Duration durationHeight=Duration.millis(170);
        KeyFrame keyFrameHeight=new KeyFrame(durationHeight,keyValueHeight);
        timeline.getKeyFrames().add(keyFrameHeight);

        KeyValue keyValueWidth = new KeyValue(this.prefWidthProperty(), 0);
        Duration durationWidth=Duration.millis(170);
        KeyFrame keyFrameWidth=new KeyFrame(durationWidth,keyValueWidth);
        timeline.getKeyFrames().add(keyFrameWidth);

        FadeTransition fadeTransition=new FadeTransition(Duration.millis(170),this);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        timeline.play();


        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notificationViewer.setVisible(false);
            }
        });

    }

    public void addSomethingForTest(){
        for (int i=0;i<10;i++) {
            NotificationItemPane notificationItemPane=new NotificationItemPane("2017-12-12 12:33:12"
                    , 234213123
                    , "sslf sldjflk sflj sdfljklksdf lskdfjlksj sf lksjd sdfsdf sldfjlsfdj sldfjlsjf"
                    , "sdfsnsd"
                    , true);
            notificationItemPaneList.add(notificationItemPane);

        }
        vBox.getChildren().addAll(notificationItemPaneList);
    }
    public void loadData(){
        ArrayList<MessageVO> messageVOArrayList= MessageUIControllerAccess.messageUIControllerAccess.getMessages();
      //  System.out.println("messages size"+messageVOArrayList.size());
        notificationItemPaneList.clear();
        vBox.getChildren().clear();

        for (int i=0;i<messageVOArrayList.size();i++){
            NotificationItemPane notificationItemPane=new NotificationItemPane(messageVOArrayList.get(i).getTime()
                    , messageVOArrayList.get(i).getId()
                    , messageVOArrayList.get(i).getEvent()
                    ,messageVOArrayList.get(i).getSender()
                    , messageVOArrayList.get(i).getCanDel());
            notificationItemPaneList.add(notificationItemPane);
        }
        vBox.getChildren().addAll(notificationItemPaneList);




    }

    public static NotificationViewer getInstance(){
        if (notificationViewer==null){
            notificationViewer=new NotificationViewer();
        }
        return notificationViewer;

    }
}
