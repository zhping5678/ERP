package WarehouseUI.Management;

import MockObject.Warehousebl;
import vo.warehousevo.CommodityVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class MakeWarehouseTree {
    TreeView<String> tree;
    public static HBox hbox;

    public MakeWarehouseTree(String root, HBox hb){
        hbox=hb;
        WarehouseTreeItem<String> rootitem=new WarehouseTreeItem<>(root);
        rootitem.setExpanded(false);
        ObservableList<TreeItem<String>> a=rootitem.getChildren();
        tree=new TreeView<>(rootitem);
        tree.getStylesheets().add(getClass().getResource("Tree.css").toExternalForm());
        tree.setEditable(true);
        tree.setCellFactory((TreeView<String> p) -> new TextFieldTreeCellImpl());
        //tree.setCellFactory((TreeView<String> p) -> new test());
        CheckProduct(hb);
    }

    public void CheckProduct(HBox hb){
        tree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null && newValue != oldValue){

                //0.先清空HBox上的内容
                hb.getChildren().clear();
                //1.得到商叶节点的路径
                String locate=newValue.getValue();
                TreeItem<String> temp=newValue;
                while(temp.getParent()!=null){
                    locate=temp.getParent().getValue()+"/"+locate;
                    temp=temp.getParent();
                }
                //2.判断叶节点是否为商品
                Warehousebl ware=new Warehousebl();
                boolean isProduct=ware.isCommodity(locate);
                //2.1若是商品则调出界面
                if(isProduct){
                    //2.1.1先调出商品的FXML
                    CommodityVO vo=ware.getCommodity(locate);
                    Parent root=getPane(vo);

                    //2.1.3将商品信息显示到界面中
                    hb.getChildren().add(root);
                }
                else{
                    Parent root=null;
                    try {
                        root=FXMLLoader.load(getClass().getResource("commodityType.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hb.getChildren().add(root);
                }
            }
        });
    }

    public Parent getPane(CommodityVO vo){
        URL url=getClass().getResource("product.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //2.1.2初始化商品界面
        Map<String, Object> product = loader.getNamespace();
        TextField ID= (TextField) product.get("ID");

        CurrentProduct.setProductID(vo.getID());
        CurrentProduct.setIsBan(vo.getIsBan());
        String[] c=vo.getID().split("/");
        ID.setText(c[c.length-1]);
        TextField NAME= (TextField) product.get("NAME");
        NAME.setText(vo.getName());
        TextField text1= (TextField) product.get("text1");
        text1.setText(vo.getID());
        TextField text2= (TextField) product.get("text2");
        text2.setText(vo.getSize());
        TextField text3= (TextField) product.get("text3");
        text3.setText(vo.getAmountOfInventory()+"");
        TextField text4= (TextField) product.get("text4");
        text4.setText(vo.getBuyingPrice()+"");
        TextField text5= (TextField) product.get("text5");
        text5.setText(vo.getMarketPrice()+"");
        TextField text6= (TextField) product.get("text6");
        text6.setText(vo.getTheRecentBuyingPrice()+"");
        TextField text7= (TextField) product.get("text7");
        text7.setText(vo.getTheRecentMarketPrice()+"");
        TextField text8= (TextField) product.get("text8");
        text8.setText(vo.getWarningNumber()+"");
        return root;
    }

    public TreeView<String> getTree() {
        return tree;
    }
}
