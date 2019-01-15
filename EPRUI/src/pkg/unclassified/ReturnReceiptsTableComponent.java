package pkg.unclassified;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ReturnReceiptsTableComponent extends VBox {

    ObservableList<ReturnReceiptsTableComponentItem> dataList= FXCollections.observableArrayList();
    TableView tableView=new TableView();

    public ReturnReceiptsTableComponent(){





        //    dataList.clear();
        dataList.add(new ReturnReceiptsTableComponentItem("paulwong","2017/12/12 12:00:00","ok","approved"));
        dataList.add(new ReturnReceiptsTableComponentItem("paulwong","2017/12/12 12:00:00","ok","approved"));


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
        //emptyColumn.setReorderable(false);
        emptyColumn.getStyleClass().add("hidden-table-column");


        TableColumn staffIdColumn=new TableColumn("ID");
        double staffIdColumnWidthRate=0.24;
        staffIdColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(staffIdColumnWidthRate));
        staffIdColumn.setResizable(false);
        staffIdColumn.setSortable(false);
        //staffIdColumn.setReorderable(false);
        staffIdColumn.getStyleClass().add("pretty-table-column");
        staffIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        staffIdColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new IdTableCell();
            }
        });

        TableColumn timeColumn=new TableColumn("Time");
        double timeColumnWidthRate=0.13;
        timeColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(timeColumnWidthRate));
        timeColumn.setSortable(false);
        timeColumn.setResizable(false);
        //timeColumn.setReorderable(false);
        timeColumn.getStyleClass().add("pretty-table-column");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn resultColumn=new TableColumn("Result");
        double resultColumnWidthRate=0.09;
        resultColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(resultColumnWidthRate));
        resultColumn.setSortable(false);
        resultColumn.setResizable(false);
       // resultColumn.setReorderable(false);
        resultColumn.getStyleClass().add("pretty-table-column");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));


        TableColumn warningColumn=new TableColumn("warning");
        double warningColumnWidthRate=emptyColumnWidthRate;
        warningColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(warningColumnWidthRate));
        warningColumn.setSortable(false);
        warningColumn.setResizable(false);
        //warningColumn.setReorderable(false);
        warningColumn.getStyleClass().add("hidden-table-column");


        TableColumn remarkColumn=new TableColumn("Remark");
        double remarkColumnWidthRate= 1
                -staffIdColumnWidthRate
                -timeColumnWidthRate
                -warningColumnWidthRate
                -emptyColumnWidthRate
                -resultColumnWidthRate
                -0.005;
        remarkColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(remarkColumnWidthRate));
        remarkColumn.setSortable(false);
        remarkColumn.setResizable(false);
       // remarkColumn.setReorderable(false);
        remarkColumn.getStyleClass().add("pretty-table-column");
        remarkColumn.setCellValueFactory(new PropertyValueFactory<>("remark"));


        tableView.getColumns().addAll(emptyColumn,staffIdColumn,timeColumn,remarkColumn,resultColumn,warningColumn);
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
