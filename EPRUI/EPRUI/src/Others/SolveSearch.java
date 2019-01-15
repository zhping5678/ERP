package Others;

import AccountUI.MakeAccountList;
import ClientUI.MakeClientList;
import MockObject.Accountbl;
import MockObject.Clientbl;
import MockObject.MyFilebl;
import MockObject.Warehousebl;
import MyFileUI.MyFile;
import vo.accountvo.AccountVO;
import vo.clientvo.ClientVO;
import vo.uservo.Position;
import vo.warehousevo.CommodityVO;
import WarehouseUI.Management.MakeWarehouseTree;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class SolveSearch {
    private HBox hb;
    private Position p;
    private String state;
    private String keywords;
    private ArrayList<String> array;
    public SolveSearch(HBox hb, Position p, String state, String keywords){
        this.hb=hb;
        this.p=p;
        this.state=state;
        this.keywords=keywords;
    }

    public ListView<String> getList(){
        initialArray();
        ListView<String> list=null;
        if(state.equals("Message")){
            list=getMessageList();
        }
        else if(state.equals("MyFile")){
            list=getMyFileList();
        }
        else if(state.equals("File")){
            list=getFileList();
        }
        else if(state.equals("Warehouse")){
            list=getWarehosueList();
        }
        else if(state.equals("Client")){
            list=getClientList();
        }
        else if(state.equals("accountvo")){
            list=getAccountList();
        }
        return list;
    }

    public void initialArray(){
        if(state.equals("Message")){
            ;
        }
        else if(state.equals("MyFile")){
            ;
        }
        else if(state.equals("File")){
            ;
        }
        else if(state.equals("Warehouse")){
           ;
        }
        else if(state.equals("Start.Client")){
            Clientbl bl=new Clientbl();
            array=bl.findClient(keywords);
        }
        else if(state.equals("accountvo")){
            Accountbl bl=new Accountbl();
            array=bl.findAccountsByKeywords(keywords);
        }
    }

    public ListView<String> getWarehosueList(){
        Warehousebl bl=new Warehousebl();
        ListView<String> list=new PrettyListView<>();
        ArrayList<ArrayList<String>> a=bl.findByKeyWords(keywords);
        ArrayList<String> array1=a.get(0);
        ArrayList<String> array2=a.get(1);

        array=new ArrayList<>();
        if(array1!=null){
            for(int i=0;i<array1.size();i++){
                array.add(array1.get(i));
            }
        }

        if(array2!=null){
            for(int i=0;i<array2.size();i++){
                array.add(array2.get(i));
            }
        }

        if(array.size()==0){
            return null;
        }

        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        list.setItems(items);
        list.setFixedCellSize(50);
        list.setPrefSize(350,items.size()*50);
        list.setMaxSize(350,items.size()*50);
        list.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());

        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String interNode=event.getPickResult().getIntersectedNode().toString();
                if(interNode.contains(",")){
                    interNode = interNode.substring(11, interNode.indexOf(","));
                    interNode = interNode.substring(0, interNode.length()-1);
                }
                else{
                    interNode = interNode.substring(interNode.indexOf("]"));
                    interNode = interNode.substring(2, interNode.length()-1);
                }

                String path=interNode.substring(0,interNode.indexOf(" "));
                CommodityVO vo=bl.getCommodity(path);

                MakeWarehouseTree maketree=new MakeWarehouseTree("",hb);
                Parent root=maketree.getPane(vo);
                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });

        return list;

    }

    public ListView<String> getAccountList(){
        MakeAccountList makelist=new MakeAccountList(hb);
        Accountbl bl=new Accountbl();
        ListView<String> list=makelist.getList(array);
        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String interNode =event.getPickResult().getIntersectedNode().toString();
                String account=makelist.getClickedName(interNode);
                AccountVO vo=bl.findAccountByID(account);
                Parent root=makelist.getPane(vo);
                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });
        return list;
    }

    public ListView<String> getClientList(){
        MakeClientList makelist=new MakeClientList(hb,true);
        Clientbl bl=new Clientbl();
        ArrayList<String> array=bl.findClient(keywords);

        if(array==null){
            return null;
        }

        ListView<String> list=makelist.getList(array);
        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String interNode =event.getPickResult().getIntersectedNode().toString();
                String client=makelist.getClickedName(interNode);
                ClientVO vo=bl.traceClient(client);
                Parent root=makelist.getPane(vo);
                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });
        return list;
    }

    public ListView<String> getMyFileList(){
        ListView<String> list=new PrettyListView<>();
        MyFile myfile=new MyFile(hb);
        MyFilebl bl=new MyFilebl();
        ArrayList<String> array=bl.findByKeyWords(keywords,p);

        if(array.size()==0){
            return null;
        }

        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        list.setItems(items);
        list.setFixedCellSize(50);
        list.setPrefSize(350,items.size()*50);
        list.setMaxSize(350,items.size()*50);
        list.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        list.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String interNode=event.getPickResult().getIntersectedNode().toString();
                if(interNode.contains(",")){
                    interNode = interNode.substring(11, interNode.indexOf(","));
                    interNode = interNode.substring(0, interNode.length()-1);
                }
                else{
                    interNode = interNode.substring(interNode.indexOf("]"));
                    interNode = interNode.substring(2, interNode.length()-1);
                }
                Parent root=null;
                if(interNode.contains("报溢单")) {
                    root = myfile.openOverflow(interNode, 0);
                }
                else if(interNode.contains("报损单")){
                    root=myfile.openLack(interNode,0);
                }
                else if(interNode.contains("销售单")){
                    root=myfile.openSell(interNode,0);
                }
                else if(interNode.contains("销售退货单")){
                    root=myfile.openSellBack(interNode,0);
                }
                else if(interNode.contains("进货单")){
                    root=myfile.openReplenish(interNode,0);
                }
                else if(interNode.contains("进货退货单")){
                    root=myfile.openReplenishBack(interNode,0);
                }
                else if(interNode.contains("赠送单")){
                    //root=myfile.openGift(interNode,0);
                }
                else if(interNode.contains("收款单")){
                    //root=myfile.openPaied(interNode,0);
                }
                else if(interNode.contains("付款单")){
                    //root=myfile.openPay(interNode,0);
                }
                else if(interNode.contains("现金费用单")){
                    //root=myfile.openCash(interNode,0);
                }

                hb.getChildren().clear();
                hb.getChildren().add(root);
            }
        });
        return list;
    }

    public ListView<String> getFileList(){
        return null;
    }

    public ListView<String> getMessageList(){
        return null;
    }
}
