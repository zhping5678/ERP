package AccountUI;

import Login.CurrentState;
import MockObject.Accountbl;
import vo.accountvo.AccountVO;
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

public class MakeAccountList {
    private HBox hb;
    private static String account;
    public MakeAccountList(HBox hb){
        this.hb=hb;
    }
    public ListView<String> getList(){
        //1.设置列表视图
        Accountbl bl=new Accountbl();
        ArrayList<String> array=bl.showAccountList();
        ListView<String> list=getList(array);

        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String interNode =event.getPickResult().getIntersectedNode().toString();
                account = getClickedName(interNode);
                AccountVO vo=bl.findAccountByID(account);
                Parent root=getPane(vo);
                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });

        //2.设置右键面板
        ContextMenu menu=new ContextMenu();
        MenuItem item1=new MenuItem("删除账户");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(bl.deleteAccount(CurrentState.getLoginID(),list.getSelectionModel().getSelectedItem())== ResultMessage.delSuccess){
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

    //生成list列表
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

    //生成最右边的版面
    public Parent getPane(AccountVO vo){
        URL url=getClass().getResource("accountvo.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> fxmlNamespace = loader.getNamespace();

        TextField name= (TextField) fxmlNamespace.get("name");
        TextField id= (TextField) fxmlNamespace.get("id");
        Label balance= (Label) fxmlNamespace.get("balance");

        name.setText(vo.getName());
        id.setText(vo.getID());
        balance.setText("余额：￥"+vo.getMoney());
        return root;
    }

    //生成选中框格的名称
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


    public static String getAccount(){
        return account;
    }
}
