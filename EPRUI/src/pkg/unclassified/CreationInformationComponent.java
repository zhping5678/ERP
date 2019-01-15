package pkg.unclassified;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;


public class CreationInformationComponent extends HBox{
    private Label creationInformationLabel;

    public CreationInformationComponent(String type,String username,String time){
        creationInformationLabel=new Label();
        creationInformationLabel.setText(type+"         \ncreated by "+username+" on "+time+"        ");
        creationInformationLabel.setStyle("-fx-text-fill: "+Parameters.CSS_CREATION_INFORMATION_COMPONENT_TEXT_FILL+";"
                +"-fx-font-size: "+Parameters.CSS_CREATION_INFORMATION_COMPONENT_FONT_SIZE+";"
        );
        creationInformationLabel.setWrapText(true);
        creationInformationLabel.setTextAlignment(TextAlignment.RIGHT);
        this.setStyle("-fx-background-color: WHITE;");
        this.getChildren().add(creationInformationLabel);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPrefHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setMinHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setMaxHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setPrefWidth(Integer.MAX_VALUE);

    }
    public CreationInformationComponent(String type){
        creationInformationLabel=new Label();
        creationInformationLabel.setText(type+"         \n");
        creationInformationLabel.setStyle("-fx-text-fill: "+Parameters.CSS_CREATION_INFORMATION_COMPONENT_TEXT_FILL+";"
                +"-fx-font-size: "+Parameters.CSS_CREATION_INFORMATION_COMPONENT_FONT_SIZE+";"
        );
        creationInformationLabel.setWrapText(true);
        creationInformationLabel.setTextAlignment(TextAlignment.RIGHT);
        this.setStyle("-fx-background-color: WHITE;");
        this.getChildren().add(creationInformationLabel);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPrefHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setMinHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setMaxHeight(2*Parameters.CREATION_INFORMATION_COMPONENT_HEIGHT);
        this.setPrefWidth(Integer.MAX_VALUE);

    }

}
