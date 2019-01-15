package pkg.unclassified;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;

public class SeparatorWithTitleComponent extends StackPane {
    public SeparatorWithTitleComponent(String s){
        Separator separator=new Separator();
        separator.setStyle(separator.getStyle()
                +"-fx-padding:0 25 0 25;"
        );
        separator.getStylesheets().add("/pkg/stylesheet/SeparatorComponent.css");

        Label label=new Label("   "+s+"   ");
        label.setStyle(label.getStyle()
                +"-fx-background-color:white;"
                +"-fx-text-fill: #a3a3a3;"
        );
        label.getStylesheets().add("/pkg/stylesheet/LabelInSeparatorWithTitleComponent.css");
        this.setPrefHeight(40);
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(Integer.MAX_VALUE);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"

        );
        this.getChildren().addAll(separator,label);
    }
}
