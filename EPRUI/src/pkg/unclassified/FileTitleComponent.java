package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pkg.unclassified.Parameters;
import pkg.unclassified.PrettyTextField;


public class FileTitleComponent extends HBox {
    public PrettyTextField purpleTitle;
    private Label space=new Label("");
    public PrettyTextField blackTitle;
    private Label leftLabel;
    private Label rightLabel;//used to show warning information.
    private Image leftImage;
    private Image rightImage;

    public FileTitleComponent(String purpleWord, String blackWord){
        leftLabel=new Label("");
        rightLabel=new Label("");
  //      leftLabel.setStyle("-fx-background-color: red");
        leftLabel.setPrefWidth(120);
  //      rightLabel.setStyle("-fx-background-color: red");
        rightLabel.setPrefWidth(120);


        purpleTitle=new PrettyTextField(purpleWord);
        blackTitle=new PrettyTextField(blackWord);
        purpleTitle.setAlignment(Pos.CENTER);
        blackTitle.setAlignment(Pos.CENTER);
        purpleTitle.setPrefWidth(Math.max(computeTextWidth(purpleTitle.getText()),20));
        blackTitle.setPrefWidth(Math.max(computeTextWidth(blackTitle.getText()),20));
        purpleTitle.setStyle(
                purpleTitle.getStyle()+
                "-fx-text-fill: "+ Parameters.CSS_NJU_PURPLE+";"
                +"-fx-font-size: "+Parameters.CSS_FILE_TITLE_COMPONENT_FONT_SIZE+";"
                +"-fx-background-color: white;"
                +"-fx-padding:0 0 0 0;"
                +"-fx-border-color: null;"

        );
        purpleTitle.setAlignment(Pos.CENTER_RIGHT);
        space.setStyle("-fx-font-size: "+Parameters.CSS_FILE_TITLE_COMPONENT_FONT_SIZE+";");
        blackTitle.setStyle(
                blackTitle.getStyle()+
                "-fx-text-fill: BLACK;"
                +"-fx-font-size: "+Parameters.CSS_FILE_TITLE_COMPONENT_FONT_SIZE+";"
                +"-fx-padding:0 0 0 0;"
                +"-fx-background-color: white;"
                +"-fx-border-color: null;"

        );



        purpleTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                purpleTitle.setPrefWidth(Math.max(computeTextWidth(purpleTitle.getText()),20));
            }
        });
        blackTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                blackTitle.setPrefWidth(Math.max(computeTextWidth(blackTitle.getText()),20));
            }
        });

        this.setStyle("-fx-background-color: WHITE");
        this.getChildren().addAll(leftLabel,purpleTitle,space,blackTitle,rightLabel);
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(Parameters.FILE_TITLE_COMPONENT_HEIGHT);
        this.setMinHeight(Parameters.FILE_TITLE_COMPONENT_HEIGHT);
        this.setMaxHeight(Parameters.FILE_TITLE_COMPONENT_HEIGHT);
        this.setPrefWidth(Integer.MAX_VALUE);

    }

    private double computeTextWidth(String s){
        double result;
        Label label=new Label(s);
        label.setStyle( "-fx-text-fill: null;"
                +"-fx-font-size: "+Parameters.CSS_FILE_TITLE_COMPONENT_FONT_SIZE+";"
              //  +"-fx-border-color:red;"
                +"-fx-background-color: transparent;"
        );
        StackPane stackPane=new StackPane();
        stackPane.setStyle("-fx-background-color: transparent;");
        stackPane.getChildren().add(label);
        Stage stage=new Stage();
        Scene scene=new Scene(stackPane);
        stage.setScene(scene);
        scene.setFill(null);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        result=label.getWidth();
        System.out.println(result);
        stage.close();
        return result+10;

    }
}
