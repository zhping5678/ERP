package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class GiftsTableComponent extends VBox {
    ObservableList<CommoditiesTableComponentItem> dataList = FXCollections.observableArrayList();




    public GiftsTableComponent(){

        /**for test**/
        dataList.add(new CommoditiesTableComponentItem("cz.mi","Neon",23,234,"No Remarks"));
        dataList.add(new CommoditiesTableComponentItem("nj.lamp.studentlamp.philips.a720","Lamp",1,34.00,"No Remarks"));


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
       // commodityIdColumn.setReorderable(false);
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
        double quantityColumnWidthRate=0.1;
        quantityColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(quantityColumnWidthRate));
        quantityColumn.setSortable(false);
        quantityColumn.setResizable(false);
      //  quantityColumn.setReorderable(false);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        quantityColumn.getStyleClass().add("pretty-table-column");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));


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
       // warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");

        TableColumn remarkColumn=new TableColumn("Remark");
        double remarkColumnWidthRate= 1
                -commodityIdColumnWidthRate
                -nameColumnWidthRate
                -quantityColumnWidthRate
                -deleteButtonColumnWidthRate
                -warningColumnWidthRate
                -emptyColumnWidthRate
                -0.005;
        remarkColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(remarkColumnWidthRate));
        remarkColumn.setSortable(false);
        remarkColumn.setResizable(false);
        //remarkColumn.setReorderable(false);
        remarkColumn.getStyleClass().add("pretty-table-column");
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));

        tableView.getColumns().addAll(emptyColumn,commodityIdColumn,nameColumn,quantityColumn,remarkColumn,deleteButtonColumn,warningColumn);


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