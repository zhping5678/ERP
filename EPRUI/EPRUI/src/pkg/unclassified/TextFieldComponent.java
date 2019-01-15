package pkg.unclassified;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TextFieldComponent extends HBox{
    Label leftLabel;
    Label rightLabel;
    PrettyTextField textField;
    public TextFieldComponent(String s){
        this(s,true);



    }

    public TextFieldComponent(String s,boolean b){
        this.setAlignment(Pos.CENTER);
        leftLabel=new Label(s+"   ");
        leftLabel.setAlignment(Pos.CENTER_RIGHT);
        leftLabel.setPrefWidth(400);

        rightLabel=new Label();
        rightLabel.setPrefWidth(400);

        textField=new PrettyTextField();
        textField.setPrefWidth(350);
        textField.setMinWidth(textField.getPrefWidth());

        this.getChildren().addAll(leftLabel,textField,rightLabel);
        this.setPrefHeight(33);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );
        if(!b){
            textField.setEditable(false);
            textField.setMouseTransparent(true);
        }

    }
}
