package pkg.unclassified;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ChoiceBoxComponent extends HBox{
    Label leftLabel;
    Label rightLabel;
    ChoiceBox choiceBox=new ChoiceBox();
    public ObservableList dataList=FXCollections.observableArrayList();
    public ChoiceBoxComponent(String... strings){
        this.setAlignment(Pos.CENTER);
        leftLabel=new Label(strings[0]+"   ");
        leftLabel.setAlignment(Pos.CENTER_RIGHT);
        leftLabel.setPrefWidth(200);
        rightLabel=new Label();
        rightLabel.setPrefWidth(200);
        for (int i=1;i<strings.length;i++){
            dataList.add(strings[i]);
        }
        choiceBox=new ChoiceBox(dataList);
        choiceBox.getStylesheets().add("pkg/stylesheet/PrettyChoiceBox.css");

        this.getChildren().addAll(leftLabel,choiceBox,rightLabel);
        this.setPrefHeight(33);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );


    }
}
