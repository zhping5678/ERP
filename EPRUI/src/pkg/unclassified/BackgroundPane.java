package pkg.unclassified;

import javafx.scene.layout.HBox;

public class BackgroundPane extends HBox {

    ReturnButton returnButton=new ReturnButton();

    TabButton currentTabButton;
    public BackgroundPane(){
        this.setPrefWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setMinWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setMaxWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setPrefHeight(100000);

        this.setStyle("-fx-background-color:"+Parameters.CSS_NJU_PURPLE+";");
        this.getChildren().addAll(returnButton);

    }
}
