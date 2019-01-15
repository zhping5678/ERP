package pkg.ui.messageui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pkg.unclassified.Parameters;
import pkg.unclassified.TitleBar;

public class NotificationItemPane extends Pane{
    private String time;//消息产生时间
    private long id;//消息的ID，作为数据库中存储的唯一标识，对于用户没有实际意义，在界面不用显示
    private String sender;//消息的发起者
    private String event;//消息内容
    private boolean canDel;//消息是否为置顶消息，是否可以删除，因为在界面上删除就是真的删除了
    private Button clearButton;
    private Label timeAndSenderLabel;
    private Label eventLabel;


    public NotificationItemPane(String time,long id, String event, String sender ,boolean canDel){
        this.time=time;
        this.id=id;
        this.sender=sender;
        this.event=event;
        this.canDel=canDel;
        this.setStyle(this.getStyle()
                +"-fx-background-color:#ffffff;"
                +"-fx-background-radius: 7;"
              //  +"-fx-border-color:black;"
                +"-fx-border-radius: 7;"

        );
        this.setPrefWidth(Parameters.NOTIFICATION_VIEWER_WIDTH-20);
        this.setPrefHeight(76.0);
        this.setMaxWidth(Parameters.NOTIFICATION_VIEWER_WIDTH-20);


        clearButton=new Button();
        clearButton.setStyle(clearButton.getStyle()
                +"-fx-background-color:transparent;"
                +"-fx-background-image: url(/pkg/image/CloseWindowButtonIcon.png);"
                +"-fx-background-size: 16;"
                +"-fx-background-repeat: no-repeat;"

        );

        clearButton.setLayoutX(7);
        clearButton.setLayoutY(30);
        clearButton.setVisible(false);
        clearButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // System.out.println("clearbuton");
                MessageUIControllerAccess.messageUIControllerAccess.removeMessage(id);
                NotificationItemPane notificationItemPane=(NotificationItemPane)((Button)event.getSource()).getParent();
                VBox vBox=(VBox)notificationItemPane.getParent();
                vBox.getChildren().remove(notificationItemPane);
                NotificationViewer.getInstance().notificationItemPaneList.remove(notificationItemPane);
                TitleBar.getInstance().notificationButtonPane.updateRedSpot();



            }
        });
        this.getChildren().add(clearButton);



        timeAndSenderLabel=new Label(sender+"  "+time);
        timeAndSenderLabel.setPrefWidth(340);
     //   timeAndSenderLabel.setStyle("");
        timeAndSenderLabel.setLayoutX(29);
        timeAndSenderLabel.setLayoutY(52);
        timeAndSenderLabel.setTextFill(Color.grayRgb(199));
        this.getChildren().add(timeAndSenderLabel);

        eventLabel=new Label(event);
        eventLabel.setPrefWidth(340);
        eventLabel.setPrefHeight(40);
        eventLabel.setWrapText(true);
      //  eventLabel.setStyle("");
        eventLabel.setLayoutX(29);
        eventLabel.setLayoutY(6);
       // eventLabel.setTextFill(Color.grayRgb(140));
        this.getChildren().add(eventLabel);



        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearButton.setVisible(true);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearButton.setVisible(false);
            }
        });





    }

}
