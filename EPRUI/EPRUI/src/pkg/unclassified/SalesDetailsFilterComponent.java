package pkg.unclassified;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SalesDetailsFilterComponent extends HBox {

    public PrettyDatePicker startDatePicker;
    public PrettyDatePicker endDatePicker;

    public Label clientLabel;
    public PrettyTextField clientTextField;
    public Label pathLabel;
    public PrettyTextField pathTextField;

    public ChoiceBox<String> clerksChoiceBox;
    public ObservableList clerks;




    public SalesDetailsFilterComponent(){

        startDatePicker =new PrettyDatePicker();
        startDatePicker.setPromptText("Start Date");
        endDatePicker =new PrettyDatePicker();
        endDatePicker.setPromptText("End Date");
        clientLabel=new Label("Client");
        clientTextField=new PrettyTextField();
        clientTextField.setPrefWidth(80);
        clientTextField.setPrefHeight(28);
        clientTextField.setPromptText("All");

        pathLabel=new Label("Path");
        pathTextField=new PrettyTextField();
        pathTextField.setPrefWidth(160);
        pathTextField.setPrefHeight(28);
        pathTextField.setPromptText("All");






        clerksChoiceBox=new ChoiceBox();
        clerks=FXCollections.observableArrayList(
                "All Clerks"
                ,"sdfsdf"
                ,"sfdg"
                ,"ghdgxsfd"
                ,"hdhdgfdg"
                ,"werewr"
                ,"vcxvxcvxcv"

        );
        clerksChoiceBox.setItems(clerks);
        clerksChoiceBox.getSelectionModel().select(0);
        clerksChoiceBox.setMaxWidth(100);
        clerksChoiceBox.setStyle(clerksChoiceBox.getStyle()
                //    +"-fx-border-color:white"
        );
        clerksChoiceBox.getStylesheets().add("/pkg/stylesheet/PrettyChoiceBox.css");



        this.getChildren().addAll(pathLabel,pathTextField,clientLabel,clientTextField,clerksChoiceBox,startDatePicker, endDatePicker);
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);
        this.setPrefWidth(Integer.MAX_VALUE);
        this.setPrefHeight(40);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );


        if (startDatePicker.getValue()==null){
            System.out.println("date value null");
        }


    }

}
