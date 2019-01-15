package WarehouseUI.View;

import MockObject.Warehousebl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import vo.warehousevo.WarehouseVO;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MakeViewList {
    private HBox hb;
    private static String inventory;
    public MakeViewList(HBox hb){
        this.hb=hb;
    }

    public ListView<String> getList(){
        Warehousebl bl=new Warehousebl();
        ArrayList<String> array=bl.getOnlyWarehouse();
        if(array==null){
            return null;
        }
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        ListView<String> list=new ListView<>();
        list.setItems(items);
        list.setFixedCellSize(50);
        list.setPrefSize(350,items.size()*50);
        list.setMaxSize(350,items.size()*50);
        list.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                inventory=event.getPickResult().getIntersectedNode().toString();
                if(inventory.contains(",")){
                    inventory=inventory.substring(11,inventory.indexOf(","));
                    inventory=inventory.substring(0,inventory.length()-1);
                }
                else{
                    inventory=inventory.substring(inventory.indexOf("]"));
                    inventory=inventory.substring(2,inventory.length()-1);
                }

                hb.getChildren().clear();
                try {
                    URL url=getClass().getResource("View.fxml");
                    FXMLLoader loader=new FXMLLoader(url);
                    Parent root= loader.load();
                    Map<String, Object> view = loader.getNamespace();
                    Label title= (Label) view.get("title");
                    title.setText(inventory);

                    hb.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return list;
    }

    public static String getInventory(){
        return inventory;
    }
}
