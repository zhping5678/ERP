package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class SalesDetailsTableComponent extends VBox {
    ObservableList<CommoditiesTableComponentItem> dataList = FXCollections.observableArrayList();




    public SalesDetailsTableComponent(){

        /**for test**/
        dataList.add(new CommoditiesTableComponentItem("cz.mi","Neon",23,234,"2017-12-12 "));
        dataList.add(new CommoditiesTableComponentItem("nj.lamp.studentlamp.philips.a720","Lamp",1,34.00,"2017-12-12 "));


        //    dataList.clear();

        TableView tableView=new TableView();
        Label labelNoCommodities =new Label("");
        labelNoCommodities.setStyle("-fx-text-fill:lightgrey");
        tableView.setPlaceholder(labelNoCommodities);
        tableView.setItems(dataList);////////////////////////////////////////////
        tableView.getStylesheets().add("pkg/stylesheet/PrettyTableView.css");
        tableView.setSelectionModel(null);//make it unable to be selected;

        TableColumn emptyColumn=new TableColumn("empty");
        double emptyColumnWidthRate=0.09;
        emptyColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(emptyColumnWidthRate));
        emptyColumn.setResizable(false);
        emptyColumn.setSortable(false);
      //  emptyColumn.setReorderable(false);
        emptyColumn.getStyleClass().add("hidden-table-column");


        TableColumn commodityIdColumn=new TableColumn("ID");
        double commodityIdColumnWidthRate=0.24;
        commodityIdColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(commodityIdColumnWidthRate));
        commodityIdColumn.setResizable(false);
        commodityIdColumn.setSortable(false);
      //  commodityIdColumn.setReorderable(false);
        commodityIdColumn.getStyleClass().add("pretty-table-column");
        commodityIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        commodityIdColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new IdTableCell();
            }
        });

        TableColumn nameColumn=new TableColumn("Name");
        double nameColumnWidthRate=0.09;
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(nameColumnWidthRate));
        nameColumn.setSortable(false);
        nameColumn.setResizable(false);
      //  nameColumn.setReorderable(false);
        nameColumn.getStyleClass().add("pretty-table-column");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn quantityColumn=new TableColumn("Quantity");
        double quantityColumnWidthRate=0.12;
        quantityColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(quantityColumnWidthRate));
        quantityColumn.setSortable(false);
        quantityColumn.setResizable(false);
       // quantityColumn.setReorderable(false);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.getStyleClass().add("pretty-table-column");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn unitPriceColumn=new TableColumn("Unit Price");
        double unitPriceColumnWidthRate=0.12;
        unitPriceColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(unitPriceColumnWidthRate));
        unitPriceColumn.setSortable(false);
        unitPriceColumn.setResizable(false);
       // unitPriceColumn.setReorderable(false);
        unitPriceColumn.getStyleClass().add("pretty-table-column");
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));



        TableColumn totalPriceColumn=new TableColumn("Total Price");
        double totalPriceColumnWidthRate=0.12;
        totalPriceColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(totalPriceColumnWidthRate));
        totalPriceColumn.setSortable(false);
        totalPriceColumn.setResizable(false);
       /// totalPriceColumn.setReorderable(false);
        totalPriceColumn.getStyleClass().add("pretty-table-column");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));




        TableColumn deleteButtonColumn=new TableColumn(" ");
        double deleteButtonColumnWidthRate=0.02;
        deleteButtonColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(deleteButtonColumnWidthRate));
        deleteButtonColumn.setSortable(false);
        deleteButtonColumn.setResizable(false);
       // deleteButtonColumn.setReorderable(false);
        deleteButtonColumn.getStyleClass().add("pretty-table-column");



        TableColumn warningColumn=new TableColumn("warning");
        double warningColumnWidthRate=emptyColumnWidthRate;
        warningColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warningColumnWidthRate));
        warningColumn.setSortable(false);
        warningColumn.setResizable(false);
      //  warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");


        TableColumn timeColumn=new TableColumn("Time");
        double timeColumnWidthRate= 1
                -commodityIdColumnWidthRate
                -nameColumnWidthRate
                -quantityColumnWidthRate
                -unitPriceColumnWidthRate
                -totalPriceColumnWidthRate
                -deleteButtonColumnWidthRate
                -warningColumnWidthRate
                -emptyColumnWidthRate
                -0.005;
        timeColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(timeColumnWidthRate));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
      //  timeColumn.setReorderable(false);
        timeColumn.getStyleClass().add("pretty-table-column");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));


        tableView.getColumns().addAll(emptyColumn,commodityIdColumn,nameColumn,quantityColumn,unitPriceColumn,totalPriceColumn,timeColumn,deleteButtonColumn,warningColumn);
        //    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
/*
        tableView.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if ((event.getDeltaX() != 0)||(event.getDeltaY() != 0)) {
                    event.consume();

                }
            }
        });
        */


        tableView.setFixedCellSize(60);
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(0.43)));
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());
        tableView.setFocusTraversable(false);


        this.getChildren().add(tableView);
        this.setStyle(this.getStyle()
                +"-fx-padding: 0 0 5 6;"
                +"-fx-background-color:white;"
        );
        this.setAlignment(Pos.CENTER);




    }

}
