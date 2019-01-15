package pkg.unclassified;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;




public class CheckBoxesComponent extends HBox {
    ArrayList<CheckBox> checkBoxesArrayList=new ArrayList<>();
    public CheckBoxesComponent(){
        this.setAlignment(Pos.CENTER);

        for (int i=0;i<5;i++){
            CheckBox checkBox=new CheckBox("Level "+String.valueOf(i+1));
            checkBoxesArrayList.add(checkBox);
            checkBox.getStylesheets().add("pkg/stylesheet/PrettyCheckBox.css");

        }
        this.setSpacing(30);
        this.getChildren().addAll(checkBoxesArrayList);
        this.setPrefHeight(65);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"

        );


    }
}
