package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class BusinessHistoryTableComponent extends VBox {
    ObservableList<BusinessHistoryTableComponentItem> dataList = FXCollections.observableArrayList();




    public BusinessHistoryTableComponent (){

        /**for test**/
        dataList.add(new BusinessHistoryTableComponentItem("sa23424234","Neon","SDFSF","SDFSDF","2017-12-12"));
        dataList.add(new BusinessHistoryTableComponentItem("sa23424234","Neon","SDFSF","SDFSDF","2017-12-12"));


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


        TableColumn idColumn=new TableColumn("ID");
        double idColumnWidthRate=0.24;
        idColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(idColumnWidthRate));
        idColumn.setResizable(false);
        idColumn.setSortable(false);
     //   idColumn.setReorderable(false);
        idColumn.getStyleClass().add("pretty-table-column");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new IdTableCell();
            }
        });

        TableColumn clientColumn=new TableColumn("Client");
        double clientColumnWidthRate=0.13;
        clientColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(clientColumnWidthRate));
        clientColumn.setSortable(false);
        clientColumn.setResizable(false);
       // clientColumn.setReorderable(false);
        clientColumn.getStyleClass().add("pretty-table-column");
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));

        TableColumn clerkColumn=new TableColumn("Clerk");
        double clerkColumnWidthRate=0.13;
        clerkColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(clerkColumnWidthRate));
        clerkColumn.setSortable(false);
        clerkColumn.setResizable(false);
        //clerkColumn.setReorderable(false);
        clerkColumn.setCellValueFactory(new PropertyValueFactory<>("clerk"));
        clerkColumn.getStyleClass().add("pretty-table-column");


        TableColumn warehouseColumn=new TableColumn("Warehouse");
        double warehouseColumnWidthRate=0.13;
        warehouseColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warehouseColumnWidthRate));
        warehouseColumn.setSortable(false);
        warehouseColumn.setResizable(false);
      //  warehouseColumn.setReorderable(false);
        warehouseColumn.getStyleClass().add("pretty-table-column");
        warehouseColumn.setCellValueFactory(new PropertyValueFactory<>("warehouse"));









        TableColumn warningColumn=new TableColumn("warning");
        double warningColumnWidthRate=emptyColumnWidthRate;
        warningColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warningColumnWidthRate));
        warningColumn.setSortable(false);
        warningColumn.setResizable(false);
       // warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");


        TableColumn timeColumn=new TableColumn("Time");
        double timeColumnWidthRate= 1
                -idColumnWidthRate
                -clientColumnWidthRate
                -clerkColumnWidthRate
                -warehouseColumnWidthRate

                -warningColumnWidthRate
                -emptyColumnWidthRate
                -0.005;
        timeColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(timeColumnWidthRate));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
        //timeColumn.setReorderable(false);
        timeColumn.getStyleClass().add("pretty-table-column");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));


        tableView.getColumns().addAll(emptyColumn,idColumn,clientColumn,clerkColumn,warehouseColumn,timeColumn,warningColumn);
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
