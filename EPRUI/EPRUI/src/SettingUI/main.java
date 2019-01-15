package SettingUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox v=new VBox();
        TitledPane l=new TitledPane("AAA",new Label("XXX"));
        v.getChildren().add(l);
        v.getStylesheets().add(getClass().getResource("node.css").toExternalForm());
        primaryStage.setScene(new Scene(v));
        primaryStage.show();
    }
}
