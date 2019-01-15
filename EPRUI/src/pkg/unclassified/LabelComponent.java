package pkg.unclassified;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class LabelComponent extends HBox{
    Label leftLabel;
    Label rightLabel;
    Label middleLabel;
    public LabelComponent(String s){
        this.setAlignment(Pos.CENTER);
        leftLabel=new Label(s+"   ");
        leftLabel.setAlignment(Pos.CENTER_RIGHT);
        leftLabel.setPrefWidth(100);

        rightLabel=new Label();
        rightLabel.setPrefWidth(100);

        middleLabel=new Label("");
        middleLabel.setPrefWidth(350);

        this.getChildren().addAll(leftLabel,middleLabel,rightLabel);
        this.setPrefHeight(33);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"

        );


    }
}
