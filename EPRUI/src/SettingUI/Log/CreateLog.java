package SettingUI.Log;

import MockObject.Logbl;
import vo.logvo.LogVO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class CreateLog {
    public Parent getPane(){
        URL url=getClass().getResource("Log.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> log=loader.getNamespace();
        VBox V= (VBox) log.get("V");
        Logbl bl=new Logbl();
        ArrayList<LogVO> array=bl.LogView();

        for(int i=0;i<array.size();i++) {
            URL u = getClass().getResource("part001.fxml");
            if (i % 2 == 0) {
                u = getClass().getResource("part001.fxml");
            } else {
                u = getClass().getResource("part002.fxml");
            }
            FXMLLoader load = new FXMLLoader(u);
            Parent subroot = null;
            try {
                subroot = load.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> part = load.getNamespace();
            Label time = (Label) part.get("time");
            Label id = (Label) part.get("id");
            Label event = (Label) part.get("event");

            time.setText(array.get(i).getTime());
            id.setText(array.get(i).getId());
            event.setText(array.get(i).getEvent());

            V.getChildren().add(subroot);
        }

        return root;
    }
}
