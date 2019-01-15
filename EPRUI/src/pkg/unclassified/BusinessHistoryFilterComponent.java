package pkg.unclassified;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BusinessHistoryFilterComponent extends HBox {

    public PrettyDatePicker startDatePicker;
    public PrettyDatePicker endDatePicker;
    public Label clientLabel;
    public PrettyTextField clientTextField;
    public ChoiceBox warehouseChoiceBox;
    public ChoiceBox<String> fileTypesChoiceBox;
    public ObservableList fileTypes;
    public ObservableList warehouses;
    public ChoiceBox<String> clerksChoiceBox;
    public ObservableList clerks;
    public ChoiceBox warehousesChoiceBox;


    public BusinessHistoryFilterComponent(){

        startDatePicker =new PrettyDatePicker();
        startDatePicker.setPromptText("Start Date");
        endDatePicker =new PrettyDatePicker();
        endDatePicker.setPromptText("End Date");
        clientLabel=new Label("Client");
        clientTextField=new PrettyTextField();
        clientTextField.setPrefWidth(60);
        clientTextField.setPrefHeight(28);
        clientTextField.setPromptText("All");
        fileTypesChoiceBox=new ChoiceBox();

        fileTypes=FXCollections.observableArrayList(
                "All Types"
                ,"Sales Type"
                ,"Warehouse Type"
                ,"Financial Type"

        );
        fileTypesChoiceBox.setItems(fileTypes);
        fileTypesChoiceBox.getSelectionModel().select(0);
        fileTypesChoiceBox.setMaxWidth(100);
        fileTypesChoiceBox.setStyle(fileTypesChoiceBox.getStyle()
            //    +"-fx-border-color:white"
        );
        fileTypesChoiceBox.getStylesheets().add("/pkg/stylesheet/PrettyChoiceBox.css");




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

        warehousesChoiceBox=new ChoiceBox();
        warehouses=FXCollections.observableArrayList(
                "All Warehouses"
                ,"sdfsdf"
                ,"sfdg"
                ,"ghdgxsfd"
                ,"hdhdgfdg"
                ,"werewr"
                ,"vcxvxcvxcv"

        );
        warehousesChoiceBox.setItems(warehouses);
        warehousesChoiceBox.getSelectionModel().select(0);
        warehousesChoiceBox.setMaxWidth(120);
        warehousesChoiceBox.setStyle(warehousesChoiceBox.getStyle()
                //    +"-fx-border-color:white"
        );
        warehousesChoiceBox.getStylesheets().add("/pkg/stylesheet/PrettyChoiceBox.css");














        this.getChildren().addAll(fileTypesChoiceBox,clientLabel,clientTextField,clerksChoiceBox,warehousesChoiceBox,startDatePicker, endDatePicker);
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
