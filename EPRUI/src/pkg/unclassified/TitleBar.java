package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TitleBar extends AnchorPane{
    private static TitleBar titleBar;
    public CloseWindowButtonPane closeWindowButtonPane;
    public MaxWindowButtonPane maxWindowButtonPane;
    public MinWindowButtonPane minWindowButtonPane;
    public RecoverWindowButtonPane recoverWindowButtonPane;
    public NotificationButtonPane notificationButtonPane;
    public Double recoverX;
    public Double recoverY;
    public Double recoverWidth;
    public Double recoverHeight;
    public Double xOffset;
    public Double yOffset;

    public TitleBar(){
        System.out.println("titlebar");
        this.setPrefHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setPrefWidth(Integer.MAX_VALUE);
        this.setMaxHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setMinHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setStyle("-fx-background-color: rgba(255,255,255,0);");

        closeWindowButtonPane =new CloseWindowButtonPane();
        AnchorPane.setTopAnchor(closeWindowButtonPane,0.0);
        AnchorPane.setRightAnchor(closeWindowButtonPane,0.0);



        maxWindowButtonPane=new MaxWindowButtonPane();
        AnchorPane.setTopAnchor(maxWindowButtonPane,0.0);
        AnchorPane.setRightAnchor(maxWindowButtonPane,Double.valueOf(Parameters.WINDOW_BUTTON_WIDTH));

        minWindowButtonPane=new MinWindowButtonPane();
        AnchorPane.setTopAnchor(minWindowButtonPane,0.0);
        AnchorPane.setRightAnchor(minWindowButtonPane,Double.valueOf(Parameters.WINDOW_BUTTON_WIDTH)*2);

        recoverWindowButtonPane=new RecoverWindowButtonPane();
        AnchorPane.setTopAnchor(recoverWindowButtonPane,0.0);
        AnchorPane.setRightAnchor(recoverWindowButtonPane,Double.valueOf(Parameters.WINDOW_BUTTON_WIDTH));

        notificationButtonPane=new NotificationButtonPane();
        AnchorPane.setTopAnchor( notificationButtonPane,0.0);
        AnchorPane.setRightAnchor( notificationButtonPane,3*Double.valueOf(Parameters.WINDOW_BUTTON_WIDTH));

        this.getChildren().addAll(closeWindowButtonPane,maxWindowButtonPane,minWindowButtonPane,notificationButtonPane);
      //  this.getChildren().addAll(closeWindowButtonPane,recoverWindowButtonPane,minWindowButtonPane);


        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // pressed=true;
                xOffset = PrimaryStage.getInstance().getX() - event.getScreenX();
                yOffset = PrimaryStage.getInstance().getY() - event.getScreenY();

            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              //  pressed=true;
            }
        });

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (PrimaryStage.getInstance().movable){
                    try {
                     //   System.out.println("MouseDragged to move");
                        PrimaryStage.getInstance().setX(event.getScreenX() + xOffset);
                        PrimaryStage.getInstance().setY(event.getScreenY() + yOffset);
                    }catch (NullPointerException e){

                    }
                }

            }
        });

    }
    public static TitleBar getInstance(){
        if (titleBar==null){
            titleBar=new TitleBar();
        }
        return titleBar;
    }



}
