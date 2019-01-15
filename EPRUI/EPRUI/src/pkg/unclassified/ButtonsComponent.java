package pkg.unclassified;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ButtonsComponent extends HBox {
    ArrayList<Button> buttonArrayList=new ArrayList<>();

    public ButtonsComponent(String... strings){
        this.setAlignment(Pos.CENTER);
        for (String string:strings){
            Button button=new Button(string);
            button.getStylesheets().add("/pkg/stylesheet/PrettyButton.css");
            buttonArrayList.add(button);

        }
        this.setSpacing(30);
        this.getChildren().addAll(buttonArrayList);
        this.setPrefHeight(65);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );


    }
}
