package WarehouseUI.Inventory;

import MockObject.Warehousebl;
import WarehouseUI.View.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MakeInventoryList {
    private HBox hb;
    private String inventory;
    public MakeInventoryList(HBox hb){
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
                URL url=getClass().getResource("Inventory.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> fxmlNamespace = loader.getNamespace();
                Label title= (Label) fxmlNamespace.get("title");
                title.setText(inventory);

                Label timel= (Label) fxmlNamespace.get("timel");
                Date now=new Date();
                DateFormat d=DateFormat.getDateTimeInstance();
                String time=d.format(now);
                timel.setText("截止至"+time);

                VBox v1= (VBox) fxmlNamespace.get("VBoxR1");
                ViewController vc=new ViewController();
                Warehousebl wb=new Warehousebl();
                ArrayList<String[]> info=wb.makeInventory();
                for(int i=0;i<info.size();i++){
                    String frame="";
                    if(i%2==0){
                        frame="Inventory001.fxml";
                    }
                    else{
                        frame="Inventory002.fxml";
                    }
                    Parent inventoryRoot= null;
                    try {
                        inventoryRoot = FXMLLoader.load(getClass().getResource(frame));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    HBox inventoryHB= (HBox) inventoryRoot.lookup("HBox");
                    Label l1=vc.getLabel001();
                    l1.setText((i+1)+"");
                    inventoryHB.getChildren().add(l1);

                    Label l2=vc.getLabel001();
                    l2.setText(info.get(i)[0]);
                    inventoryHB.getChildren().add(l2);

                    Label l3=vc.getLabel001();
                    l3.setText(info.get(i)[1]);
                    inventoryHB.getChildren().add(l3);

                    Label l4=vc.getLabel001();
                    l4.setText(info.get(i)[2]);
                    inventoryHB.getChildren().add(l4);

                    Label l5=vc.getLabel001();
                    l5.setText(info.get(i)[3]);
                    inventoryHB.getChildren().add(l5);

                    Label l6=vc.getLabel001();
                    l6.setText(info.get(i)[4]);
                    inventoryHB.getChildren().add(l6);

                    Label l7=vc.getLabel001();
                    l7.setText(info.get(i)[5]);
                    inventoryHB.getChildren().add(l7);

                    Label l8=vc.getLabel001();
                    l8.setText(info.get(i)[6]);
                    l8.setMaxWidth(160);
                    l8.setPrefWidth(160);
                    inventoryHB.getChildren().add(l8);

                    v1.getChildren().add(inventoryRoot);

                }
                hb.getChildren().add(root);

            }
        });
        return list;
    }
}
