package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class LogsTableComponent extends VBox {
    ObservableList<LogsTableComponentItem> dataList = FXCollections.observableArrayList();




    public LogsTableComponent(){

        /**for test**/
        for (int i=0;i<10;i++) {

            dataList.add(new LogsTableComponentItem("paulwong", "2019-12-12 12:12:12", "sadfsdfdsfsfdsdfsfsdfsdfdsfsf"));
            dataList.add(new LogsTableComponentItem("paulwong", "2019-12-12 12:12:12", "sadfsdfdsfsfdsdfsfsdfsdfdsfsf"));
            dataList.add(new LogsTableComponentItem("paulwong", "2019-12-12 12:12:12", "sadfsdfdsfsfdsdfsfsdfsdfdsfsf"));
            dataList.add(new LogsTableComponentItem("paulwong", "2019-12-12 12:12:12", "sadfsdfdsfsfdsdfsfsdfsdfdsfsf"));
        }



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
       // emptyColumn.setReorderable(false);
        emptyColumn.getStyleClass().add("hidden-table-column");


        TableColumn userIdColumn=new TableColumn("ID");
        double userIdColumnWidthRate=0.14;
        userIdColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(userIdColumnWidthRate));
        userIdColumn.setResizable(false);
        userIdColumn.setSortable(false);
       // userIdColumn.setReorderable(false);
        userIdColumn.getStyleClass().add("pretty-table-column");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userIdColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new IdTableCell();
            }
        });

        TableColumn timeColumn=new TableColumn("Time");
        double timeColumnWidthRate=0.20;
        timeColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(timeColumnWidthRate));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
      //  timeColumn.setReorderable(false);
        timeColumn.getStyleClass().add("pretty-table-column");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));







        TableColumn warningColumn=new TableColumn("warning");
        double warningColumnWidthRate=emptyColumnWidthRate;
        warningColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warningColumnWidthRate));
        warningColumn.setSortable(false);
        warningColumn.setResizable(false);
       // warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");

        TableColumn eventColumn=new TableColumn("Event");
        double  eventColumnWidthRate= 1
                -userIdColumnWidthRate
                -timeColumnWidthRate
                -warningColumnWidthRate
                -emptyColumnWidthRate
                -0.005;
        eventColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(eventColumnWidthRate));
        eventColumn.setSortable(false);
        eventColumn.setResizable(false);
       // eventColumn.setReorderable(false);
        eventColumn.getStyleClass().add("pretty-table-column");
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));

        tableView.getColumns().addAll(emptyColumn,userIdColumn,timeColumn,eventColumn,warningColumn);


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