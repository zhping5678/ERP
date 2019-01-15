package MyFileUI.Sell;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        URL suburl=getClass().getResource("SellPane.fxml");
        FXMLLoader subloader=new FXMLLoader(suburl);
        Parent subroot=null;
        try {
            subroot=subloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> addpart = subloader.getNamespace();
        VBox V1= (VBox) addpart.get("V1");
        Parent root=null;
        try {
            root=FXMLLoader.load(getClass().getResource("AddPArt003.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        V1.getChildren().add(root);
        Scene scne=new Scene(subroot);
        primaryStage.setScene(scne);
        primaryStage.show();
    }
}
