package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ItemsTableComponent extends VBox {

    ObservableList<ItemsTableComponentItem> dataList= FXCollections.observableArrayList();
    TableView tableView=new TableView();

    public ItemsTableComponent(){





        //    dataList.clear();
        dataList.add(new ItemsTableComponentItem("asdfsdfsadf",2342.0));
        dataList.add(new ItemsTableComponentItem("asdfsfsdfasdfsfsf",12452.0));


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



        TableColumn valueColumn=new TableColumn("Value");
        double valueColumnWidthRate=0.40;
        valueColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(valueColumnWidthRate));
        valueColumn.setSortable(false);
        valueColumn.setResizable(false);
      //  valueColumn.setReorderable(false);
        valueColumn.getStyleClass().add("pretty-table-column");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));



        TableColumn warningColumn=new TableColumn("warning");
        double warningColumnWidthRate=emptyColumnWidthRate;
        warningColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warningColumnWidthRate));
        warningColumn.setSortable(false);
        warningColumn.setResizable(false);
       // warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");


        TableColumn itemColumn=new TableColumn("Item");
        double itemColumnWidthRate= 1
                -warningColumnWidthRate
                -emptyColumnWidthRate
                -valueColumnWidthRate
                -0.005;
        itemColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(itemColumnWidthRate));
        itemColumn.setSortable(false);
        itemColumn.setResizable(false);
      //  itemColumn.setReorderable(false);
        itemColumn.getStyleClass().add("pretty-table-column");
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));


        tableView.getColumns().addAll(emptyColumn,valueColumn,itemColumn,warningColumn);
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
