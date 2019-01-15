package pkg.unclassified;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import pkg.ui.messageui.NotificationViewer;
import pkg.ui.myfileui.MyFileTabContentPane;

public class PrimaryStage extends Stage {
    private static PrimaryStage primaryStage;
    public AnchorPane rootAnchorPane;
    public HBox primaryHBox;
    public LeftPane leftPane;
    public HBox tabContentPane;
    public BackgroundPane backgroundPane;
    public ResizePane resizePane;
    private NotificationViewer notificationViewer;
    public Scene scene;
    public boolean resizable=true;
    public boolean movable=true;
    public TitleBar titleBar=TitleBar.getInstance();
    private void launchCommonPart(){
        rootAnchorPane=new AnchorPane();
        primaryHBox=new HBox();
      //  backgroundPane=new BackgroundPane();
      //  rootAnchorPane.getChildren().add(backgroundPane);
        rootAnchorPane.getChildren().add(primaryHBox);
        primaryHBox.setPrefWidth(Parameters.WINDOWS_WIDTH);
        primaryHBox.setPrefHeight(Parameters.WINDOWS_HEIGHT);
        rootAnchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                primaryHBox.setPrefWidth(rootAnchorPane.getWidth());
            }
        });
        rootAnchorPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                primaryHBox.setPrefHeight(rootAnchorPane.getHeight());
            }
        });
        primaryHBox.setStyle(primaryHBox.getStyle()
                +"-fx-background-color:white;"
        );
        rootAnchorPane.setStyle(rootAnchorPane.getStyle()
                +"-fx-background-color:transparent;"
        );


        AnchorPane.setRightAnchor(titleBar,0.0);
        AnchorPane.setTopAnchor(titleBar,0.0);
        titleBar.prefWidthProperty().bind(PrimaryStage.getInstance().widthProperty().subtract(Parameters.LEFT_BUTTON_WIDTH+Parameters.MIDDLE_PREF_WIDTH));
        rootAnchorPane.getChildren().add(titleBar);



        notificationViewer=NotificationViewer.getInstance();
        AnchorPane.setTopAnchor(notificationViewer,45.0);
        AnchorPane.setRightAnchor(notificationViewer,20.0);
        rootAnchorPane.getChildren().add(notificationViewer);
        notificationViewer.setVisible(false);

        resizePane=new ResizePane();
        rootAnchorPane.getChildren().add(resizePane);

        Scene scene=new Scene(rootAnchorPane,Parameters.WINDOWS_WIDTH,Parameters.WINDOWS_HEIGHT);
        scene.setFill(null);
        this.setScene(scene);
        this.initStyle(StageStyle.TRANSPARENT);
      //  ResizeHelper.addResizeListener(this);
        scene.getStylesheets().add("/pkg/stylesheet/PrettyContextMenu.css");


    }



    public void showNotificationViewer(){
        notificationViewer.show();
        primaryHBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (notificationViewer.isVisible()) {
                    hideNotificationViewer();
                }
            }
        });
        TitleBar.getInstance().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (notificationViewer.isVisible()) {
                    hideNotificationViewer();
                }
            }
        });

        for (Node node:rootAnchorPane.getChildren()){
             /*Gaussian Blur Animation*/
            if (node!=NotificationViewer.getInstance()) {
                GaussianBlur gaussianBlur = new GaussianBlur();
                gaussianBlur.setRadius(0);
                node.setEffect(gaussianBlur);
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(), 11);
                Duration duration = Duration.millis(200);
                KeyFrame keyFrame = new KeyFrame(duration, keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }
        }




    }
    public void hideNotificationViewer(){
        notificationViewer.hide();

        for (Node node:rootAnchorPane.getChildren()){
             /*Gaussian Blur Animation*/
            if (node!=NotificationViewer.getInstance()) {
                GaussianBlur gaussianBlur = new GaussianBlur();
                gaussianBlur.setRadius(11);
                node.setEffect(gaussianBlur);
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(gaussianBlur.radiusProperty(), 0);
                Duration duration = Duration.millis(200);
                KeyFrame keyFrame = new KeyFrame(duration, keyValue);
                timeline.getKeyFrames().add(keyFrame);
                timeline.play();
            }
        }
    }
    public void launchForTest(){
        launchCommonPart();
        leftPane=LeftPaneForGeneralManager.getInstance();
        tabContentPane= MyFileTabContentPane.getInstance();

        primaryHBox.getChildren().add(leftPane);
        primaryHBox.getChildren().add(tabContentPane);

     //   NotificationBar.addAndShowNotificationBar(NotificationBar.NotificationBarType.WARNING,"SDFSDFSFS");
     //   NotificationBar.addAndShowNotificationBar(NotificationBar.NotificationBarType.ATTENTION,"SDFSDFSFS");
        NotificationBar.addAndShowNotificationBar(NotificationBar.NotificationBarType.NOTICE,"DONE",true,"CLOSE");
        this.show();

    }
    public void launchForGeneralManager(){
        launchCommonPart();
        leftPane=LeftPaneForGeneralManager.getInstance();
        tabContentPane=MyFileTabContentPane.getInstance();
        primaryHBox.getChildren().add(leftPane);
        primaryHBox.getChildren().add(tabContentPane);
        this.show();

    }
    public void launchForFinancialStaff(){
        launchCommonPart();
        leftPane=LeftPaneForFinancialStaff.getInstance();
        tabContentPane=MyFileTabContentPane.getInstance();
        primaryHBox.getChildren().add(leftPane);
        primaryHBox.getChildren().add(tabContentPane);
        this.show();

    }

    public static PrimaryStage getInstance(){
        if (primaryStage==null){
            primaryStage=new PrimaryStage();
        }
        return primaryStage;
    }
}
