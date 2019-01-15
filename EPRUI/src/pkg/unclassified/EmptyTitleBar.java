package pkg.unclassified;

import javafx.scene.layout.AnchorPane;

public class EmptyTitleBar extends AnchorPane {
    public EmptyTitleBar (){
        this.setPrefHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setPrefWidth(Integer.MAX_VALUE);
        this.setMaxHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setMinHeight(Parameters.LEFT_BUTTON_HEIGHT);
        this.setStyle("-fx-background-color: #ffffff;");
    }
}
