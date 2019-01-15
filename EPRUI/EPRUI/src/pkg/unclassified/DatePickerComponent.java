package pkg.unclassified;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class DatePickerComponent extends HBox {
    public PrettyDatePicker startDatePicker;
    public PrettyDatePicker endDatePicker;





    public DatePickerComponent(){

        startDatePicker =new PrettyDatePicker();
        startDatePicker.setPromptText("Start Date");
        endDatePicker =new PrettyDatePicker();
        endDatePicker.setPromptText("End Date");
        startDatePicker.setMinWidth(120);
        endDatePicker.setMinWidth(120);




        this.getChildren().addAll(startDatePicker, endDatePicker);
        this.setSpacing(30);
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
