package Others;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;

import java.io.IOException;
import java.net.URL;

public class CreateTitledPane {
    public TitledPane getTitledPane(String titlename, Node node){
        TitledPane t=new TitledPane("",node);
        Label title=new Label(titlename);
        title.setPrefHeight(38);
        title.setAlignment(Pos.CENTER);
        t.setGraphic(title);
        return t;
    }
}
