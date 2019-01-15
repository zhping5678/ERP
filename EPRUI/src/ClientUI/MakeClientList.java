package ClientUI;

import Login.CurrentState;
import MockObject.Clientbl;
import vo.clientvo.ClientVO;
import vo.utilityvo.ResultMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MakeClientList {
    private HBox hb;
    private static String client;
    private boolean isSell;
    public MakeClientList(HBox hb, boolean isSell){
        this.hb=hb;
        this.isSell=isSell;
    }
    public ListView<String> getList(){
        Clientbl bl=new Clientbl();
        //如果是销售商
        ArrayList<String> array;
        if(isSell){
            array=bl.showSellerList();
        }
        //如果是供货商
        else{
            array=bl.showPayList();
        }

        ArrayList<String> strarray=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            strarray.add(array.get(i));
        }

        ListView<String> list=getList(strarray);

        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String internode =event.getPickResult().getIntersectedNode().toString();
                client=getClickedName(internode);
                ClientVO vo=bl.traceClient(client);
                Parent root=getPane(vo);
                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });

        //2.设置右键面板
        ContextMenu menu=new ContextMenu();
        MenuItem item1=new MenuItem("删除客户");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(bl.deleteClient(CurrentState.getLoginID(),list.getSelectionModel().getSelectedItem())== ResultMessage.delSuccess){
                    list.getItems().remove(list.getSelectionModel().getSelectedIndex());
                    list.setMaxSize(350,list.getItems().size()*50);
                }
                else{
                    //删除失败
                }
            }
        });
        menu.getItems().add(item1);
        list.setContextMenu(menu);
        return list;
    }

    public ListView<String> getList(ArrayList<String> array){
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        ListView<String> list=new PrettyListView<>();
        list.setItems(items);
        list.setFixedCellSize(50);
        list.setPrefSize(350,items.size()*50);
        list.setMaxSize(350,items.size()*50);
        list.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        return list;
    }

    public String getClickedName(String interNode){
        if(interNode.contains(",")){
            interNode = interNode.substring(11, interNode.indexOf(","));
            interNode = interNode.substring(0, interNode.length()-1);
        }
        else{
            interNode = interNode.substring(interNode.indexOf("]"));
            interNode = interNode.substring(2, interNode.length()-1);
        }
        return interNode;
    }

    public Parent getPane(ClientVO vo){
        URL url=getClass().getResource("client.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> fxmlNamespace = loader.getNamespace();
        TextField id= (TextField) fxmlNamespace.get("text1");
        TextField type= (TextField) fxmlNamespace.get("text2");
        TextField name= (TextField) fxmlNamespace.get("text3");
        TextField tele= (TextField) fxmlNamespace.get("text4");
        TextField address = (TextField) fxmlNamespace.get("text5");
        TextField post= (TextField) fxmlNamespace.get("text6");
        TextField rank= (TextField) fxmlNamespace.get("text7");
        TextField shouldGet= (TextField) fxmlNamespace.get("text8");
        TextField shouldPay= (TextField) fxmlNamespace.get("text9");
        TextField remark= (TextField) fxmlNamespace.get("text10");
        TextField Get= (TextField) fxmlNamespace.get("text11");
        TextField email= (TextField) fxmlNamespace.get("text12");
        TextField Sum= (TextField) fxmlNamespace.get("text13");
        TextField operator= (TextField) fxmlNamespace.get("text14");
        TextField ID= (TextField) fxmlNamespace.get("ID");
        TextField NAME= (TextField) fxmlNamespace.get("NAME");
        Label saveOrModify= (Label) fxmlNamespace.get("saveOrModify");
        Label banOrRecover= (Label) fxmlNamespace.get("banOrRecover");


        id.setText(vo.getID());
        id.setEditable(false);
        type.setText(vo.getCategory()+"");
        name.setText(vo.getName());
        name.setEditable(false);
        tele.setText(vo.getPhoneNum());
        tele.setEditable(false);
        address.setText(vo.getAddress());
        address.setEditable(false);
        post.setText(vo.getPostcode());
        post.setEditable(false);
        rank.setText(vo.getLevel()+"");
        rank.setEditable(false);
        shouldGet.setText(vo.getToCollect()+"");
        shouldPay.setText(vo.getToPay()+"");
        remark.setText(vo.getNote());
        remark.setEditable(false);
        Get.setText(vo.getQuota()+"");
        Get.setEditable(false);
        email.setText(vo.getEmail());
        email.setEditable(false);
        Sum.setText(vo.getTotalAmount()+"");
        Sum.setEditable(false);
        operator.setText(vo.getStaff());
        operator.setEditable(false);
        ID.setText(vo.getID());
        ID.setEditable(false);
        NAME.setText(vo.getName());
        NAME.setEditable(false);
        saveOrModify.setText("修改");
        if(vo.getIsBan()){
            banOrRecover.setText("启用该客户");
        }
        else{
            banOrRecover.setText("禁用该客户");
        }

        return root;
    }

    public static String getClient(){
        return client;
    }
}
