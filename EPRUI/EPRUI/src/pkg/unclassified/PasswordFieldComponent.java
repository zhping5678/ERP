package pkg.unclassified;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;

public class PasswordFieldComponent extends HBox{
    Label leftLabel;
    Label rightLabel;

    PasswordField passwordField;
    public PasswordFieldComponent(String s){
        this.setAlignment(Pos.CENTER);
        leftLabel=new Label(s+"   ");
        leftLabel.setAlignment(Pos.CENTER_RIGHT);
        leftLabel.setPrefWidth(200);

        rightLabel=new Label();
        rightLabel.setPrefWidth(200);

        passwordField =new PasswordField();
        passwordField.setPrefWidth(350);
        passwordField.getStylesheets().add("pkg/stylesheet/PrettyPasswordField.css");


        this.getChildren().addAll(leftLabel, passwordField,rightLabel);
        this.setPrefHeight(33);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );


    }
}
