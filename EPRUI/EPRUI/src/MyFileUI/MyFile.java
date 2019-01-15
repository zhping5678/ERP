package MyFileUI;

import MockObject.MyFilebl;
import MyFileUI.OverFlowAndLack.Scenes;
import MyFileUI.OverFlowAndLack.startChoose;
import infor.CommodityItem;
import infor.Infor;
import vo.filevo.*;
import vo.uservo.Position;
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
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MyFile {
    private static HBox hb;
    MyFilebl myfile=new MyFilebl();

    public static ListView<String> draft;
    public static ListView<String> wait;
    public static ListView<String> done;
    public static ListView<String> trash;
    //初始化主界面中最右边的HBox容器
    public MyFile(HBox hb){
        this.hb=hb;
    }

    //得到某个职业的所有草稿文件
    public ListView<String> getDraft(Position position){
        ArrayList<String> array=myfile.getDraftFile(position);
        draft=new PrettyListView<>();
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        draft.setItems(items);
        draft.setFixedCellSize(50);
        draft.setPrefSize(350,items.size()*50);
        draft.setMaxSize(350,items.size()*50);
        draft.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        draft.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                //1.得到所选文件的名称
                String pickresult=event.getPickResult().getIntersectedNode().toString();
                if(pickresult.contains(",")){
                    pickresult=pickresult.substring(0,pickresult.indexOf(","));
                    pickresult=pickresult.substring(11);
                    pickresult=pickresult.substring(0,pickresult.length()-1);
                }
                else{
                    pickresult=pickresult.substring(pickresult.indexOf("]"));
                    pickresult=pickresult.substring(2,pickresult.length()-1);
                }

                Parent root=null;
                //2.根据名称调用方法
                if(pickresult.contains("ec")) {
                    root = openOverflow(pickresult, 0);
                }
                else if(pickresult.contains("ls")){
                    root=openLack(pickresult,0);
                }
                else if(pickresult.contains("sa")){
                    root=openSell(pickresult,0);
                }
                else if(pickresult.contains("sr")){
                    root=openSellBack(pickresult,0);
                }
                else if(pickresult.contains("pc")){
                    root=openReplenish(pickresult,0);
                }
                else if(pickresult.contains("pr")){
                    root=openReplenishBack(pickresult,0);
                }
                else if(pickresult.contains("赠送单")){
                   // root=openGift(pickresult,0);
                }
                else if(pickresult.contains("收款单")){
                   // root=openPaied(pickresult,0);
                }
                else if(pickresult.contains("付款单")){
                   // root=openPay(pickresult,0);
                }
                else if(pickresult.contains("现金费用单")){
                   // root=openCash(pickresult,0);
                }

                hb.getChildren().add(root);
            }
        });

        ContextMenu menu=new ContextMenu();
        MenuItem item=new MenuItem("删除");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyFilebl bl=new MyFilebl();
                if(bl.deleteFile(draft.getSelectionModel().getSelectedItem())== ResultMessage.delSuccess){
                    draft.getItems().remove(draft.getSelectionModel().getSelectedIndex());
                    draft.setMaxHeight(draft.getItems().size()*50);
                    draft.setPrefHeight(draft.getItems().size()*50);
                }

            }
        });
        menu.getItems().add(item);
        draft.setContextMenu(menu);
        return draft;
    }

    //得到某个职业的所有待审核文件
    public ListView<String> getWaitingCheckedFile(Position position){
        ArrayList<String> array=myfile.getWaitingCheckedFile(position);
        wait=new PrettyListView<>();
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        wait.setItems(items);
        wait.setFixedCellSize(50);
        wait.setPrefSize(350,items.size()*50);
        wait.setMaxSize(350,items.size()*50);
        wait.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        wait.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                //1.得到所选文件的名称
                String pickresult=event.getPickResult().getIntersectedNode().toString();
                if(pickresult.contains(",")){
                    pickresult=pickresult.substring(0,pickresult.indexOf(","));
                    pickresult=pickresult.substring(11);
                    pickresult=pickresult.substring(0,pickresult.length()-1);
                }
                else{
                    pickresult=pickresult.substring(pickresult.indexOf("]"));
                    pickresult=pickresult.substring(2,pickresult.length()-1);
                }

                Parent root=null;
                //2.根据名称调用方法
                if(pickresult.contains("ec")) {
                    root = openOverflow(pickresult, 1);
                }
                else if(pickresult.contains("ls")){
                    root=openLack(pickresult,1);
                }
                else if(pickresult.contains("sa")){
                    root=openSell(pickresult,1);
                }
                else if(pickresult.contains("sr")){
                    root=openSellBack(pickresult,1);
                }
                else if(pickresult.contains("pc")){
                    root=openReplenish(pickresult,1);
                }
                else if(pickresult.contains("pr")){
                    root=openReplenishBack(pickresult,1);
                }
                else if(pickresult.contains("赠送单")){
                   // root=openGift(pickresult,1);
                }
                else if(pickresult.contains("收款单")){
                    //root=openPaied(pickresult,1);
                }
                else if(pickresult.contains("付款单")){
                    //root=openPay(pickresult,1);
                }
                else if(pickresult.contains("现金费用单")){
                    //root=openCash(pickresult,1);
                }

                hb.getChildren().add(root);
            }
        });
        return wait;
    }

    //得到某个职业的所有已审核文件
    public ListView<String> getCheckedFile(Position position){
        ArrayList<String> array=myfile.getCheckedFile(position);
        done=new PrettyListView<>();
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        done.setItems(items);
        done.setFixedCellSize(50);
        done.setPrefSize(350,items.size()*50);
        done.setMaxSize(350,items.size()*50);
        done.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        done.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                //1.得到所选文件的名称
                String pickresult=event.getPickResult().getIntersectedNode().toString();
                if(pickresult.contains(",")){
                    pickresult=pickresult.substring(0,pickresult.indexOf(","));
                    pickresult=pickresult.substring(11);
                    pickresult=pickresult.substring(0,pickresult.length()-1);
                }
                else{
                    pickresult=pickresult.substring(pickresult.indexOf("]"));
                    pickresult=pickresult.substring(2,pickresult.length()-1);
                }

                Parent root=null;
                //2.根据名称调用方法
                if(pickresult.contains("ec")) {
                    root = openOverflow(pickresult, 1);
                }
                else if(pickresult.contains("ls")){
                    root=openLack(pickresult,1);
                }
                else if(pickresult.contains("sa")){
                    root=openSell(pickresult,1);
                }
                else if(pickresult.contains("sr")){
                    root=openSellBack(pickresult,1);
                }
                else if(pickresult.contains("pc")){
                    root=openReplenish(pickresult,1);
                }
                else if(pickresult.contains("pr")){
                    root=openReplenishBack(pickresult,1);
                }
                else if(pickresult.contains("赠送单")){
                    //root=openGift(pickresult,1);
                }
                else if(pickresult.contains("收款单")){
                    //root=openPaied(pickresult,1);
                }
                else if(pickresult.contains("付款单")){
                    //root=openPay(pickresult,1);
                }
                else if(pickresult.contains("现金费用单")){
                    //root=openCash(pickresult,1);
                }

                hb.getChildren().add(root);
            }
        });
        return done;
    }

    //得到某个职业的所有回收站中的文件
    public ListView<String> getTrash(Position position){
        ArrayList<String> array=myfile.getTrash(position);
        trash=new PrettyListView<>();
        ObservableList<String> items= FXCollections.observableArrayList();
        for(int i=0;i<array.size();i++){
            items.add(array.get(i));
        }
        trash.setItems(items);
        trash.setFixedCellSize(50);
        trash.setPrefSize(350,items.size()*50);
        trash.setMaxSize(350,items.size()*50);
        trash.getStylesheets().add(getClass().getResource("ListCell.css").toExternalForm());
        trash.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                //1.得到所选文件的名称
                String pickresult=event.getPickResult().getIntersectedNode().toString();
                if(pickresult.contains(",")){
                    pickresult=pickresult.substring(0,pickresult.indexOf(","));
                    pickresult=pickresult.substring(11);
                    pickresult=pickresult.substring(0,pickresult.length()-1);
                }
                else{
                    pickresult=pickresult.substring(pickresult.indexOf("]"));
                    pickresult=pickresult.substring(2,pickresult.length()-1);
                }

                Parent root=null;
                //2.根据名称调用方法
                if(pickresult.contains("ec")) {
                    root = openOverflow(pickresult, 1);
                }
                else if(pickresult.contains("ls")){
                    root=openLack(pickresult,1);
                }
                else if(pickresult.contains("sa")){
                    root=openSell(pickresult,1);
                }
                else if(pickresult.contains("sr")){
                    root=openSellBack(pickresult,1);
                }
                else if(pickresult.contains("pc")){
                    root=openReplenish(pickresult,1);
                }
                else if(pickresult.contains("pr")){
                    root=openReplenishBack(pickresult,1);
                }
                else if(pickresult.contains("赠送单")){
                   // root=openGift(pickresult,1);
                }
                else if(pickresult.contains("收款单")){
                   // root=openPaied(pickresult,1);
                }
                else if(pickresult.contains("付款单")){
                    //root=openPay(pickresult,1);
                }
                else if(pickresult.contains("现金费用单")){
                   // root=openCash(pickresult,1);
                }

                hb.getChildren().add(root);
            }
        });

        ContextMenu menu=new ContextMenu();
        MenuItem item=new MenuItem("删除");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyFilebl bl=new MyFilebl();
                if(bl.deleteFile(draft.getSelectionModel().getSelectedItem())== ResultMessage.delSuccess){
                    draft.getItems().remove(draft.getSelectionModel().getSelectedIndex());
                    draft.setMaxHeight(draft.getItems().size()*50);
                    draft.setPrefHeight(draft.getItems().size()*50);
                }

            }
        });
        menu.getItems().add(item);
        draft.setContextMenu(menu);

        return trash;
    }

    //打开报溢单 state为0表示草稿状态，state为1表示其他状态
    public Parent openOverflow(String filename, int state){
        //1.从后台获取文件
        ExcessVO vo=myfile.getOverflowFile(filename);

        URL url=getClass().getResource("OverFlowAndLack/Overflow.fxml");
        if(state==0){
            url=getClass().getResource("OverFlowAndLack/Overflow.fxml");
        }
        else{
            url=getClass().getResource("OverFlowAndLack/OverflowUneditable.fxml");
        }

        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> overflow = loader.getNamespace();
        TextField name=(TextField) overflow.get("name");
        TextField person=(TextField) overflow.get("person");
        ComboBox<String> warehouse=(ComboBox<String>) overflow.get("warehouse");
        VBox V=(VBox) overflow.get("V");
        Label AmountSum=(Label) overflow.get("AmountSum");
        Label MoneySum=(Label) overflow.get("MoneySum");
        VBox VBoxMessage=(VBox)overflow.get("VBoxMessage");

        //2.初始化文档
        name.setText(vo.getExcessID());
        person.setText(vo.getOperator());
        warehouse.setPromptText(vo.getWarehouseID());
        //AmountSum.setText("合计报溢数量 "+vo.getSumNum());
        MoneySum.setText("合计报溢金额 "+vo.getSumMoney());
        ArrayList<CommodityItem> array1=vo.getCommodityItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("OverFlowAndLack/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("OverFlowAndLack/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("OverFlowAndLAck/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            TextField textfield1= (TextField) addpart.get("textfield1");
            Label label7= (Label) addpart.get("label7");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i).getCommodityID());
            label3.setText(array1.get(i).getCommodityname());
            label4.setText(array1.get(i).getCommoditySize());
            label5.setText(array1.get(i).getInventory()+"");
            textfield1.setText(array1.get(i).getModiNum()+"");
            label7.setText(array1.get(i).getUpdateNum()+"");

            V.getChildren().add(subroot);
        }
        ArrayList<Infor> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("OverFlowAndLack/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("OverFlowAndLack/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("OverFlowAndLAck/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i).getCheckerID()+"  "+array2.get(i).getCheckerTime()+"  "+array2.get(i).getRemark()+"  "+array2.get(i).getResult());
            VBoxMessage.getChildren().add(subroot);
        }

        //3.设置草稿跳转界面
        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"报溢单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }
        return root;
    }

    //打开报损单
    public Parent openLack(String filename, int state){
        //1.从后台获取文件
        ExcessVO vo=myfile.getLackFile(filename);

        URL url=getClass().getResource("OverFlowAndLack/Lack.fxml");
        if(state==0){
            url=getClass().getResource("OverFlowAndLack/Lack.fxml");
        }
        else{
            url=getClass().getResource("OverFlowAndLack/LackUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> lack = loader.getNamespace();
        TextField name=(TextField) lack.get("name");
        TextField person=(TextField) lack.get("person");
        ComboBox warehouse= (ComboBox) lack.get("warehouse");
        VBox V=(VBox) lack.get("V");
        Label AmountSum=(Label) lack.get("AmountSum");
        Label MoneySum=(Label) lack.get("MoneySum");
        VBox VBoxMessage=(VBox)lack.get("VBoxMessage");

        //2.初始化文档
        name.setText(vo.getExcessID());
        person.setText(vo.getOperator());
        warehouse.setPromptText(vo.getWarehouseID());
        AmountSum.setText("合计报损数量 "+vo.getSumNum());
        MoneySum.setText("合计报损金额 "+vo.getSumMoney());
        ArrayList<CommodityItem> array1=vo.getCommodityItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("OverFlowAndLack/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("OverFlowAndLack/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("OverFlowAndLAck/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            TextField textfield1= (TextField) addpart.get("textfield1");
            Label label7= (Label) addpart.get("label7");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i).getCommodityID());
            label3.setText(array1.get(i).getCommodityname());
            label4.setText(array1.get(i).getCommoditySize());
            label5.setText(array1.get(i).getInventory()+"");
            textfield1.setText(array1.get(i).getModiNum()+"");
            label7.setText(array1.get(i).getUpdateNum()+"");

            V.getChildren().add(subroot);
        }
        ArrayList<Infor> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("OverFlowAndLack/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("OverFlowAndLack/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("OverFlowAndLAck/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i).getCheckerID()+"  "+array2.get(i).getCheckerTime()+"  "+array2.get(i).getRemark()+"  "+array2.get(i).getResult());
            VBoxMessage.getChildren().add(textfield);
        }

        //3.设置草稿跳转界面
        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"报损单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }
        return root;
    }

    //打开销售单
    public Parent openSell(String filename, int state){
        SaleVO vo=myfile.getSaleFile(filename);

        URL url=getClass().getResource("Sell/SellPane.fxml");
        if(state==0){
            url=getClass().getResource("Sell/SellPane.fxml");
        }
        else{
            url=getClass().getResource("Sell/SellPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> sell = loader.getNamespace();
        TextField name= (TextField) sell.get("name");
        TextField client= (TextField) sell.get("client");
        TextField worker= (TextField) sell.get("worker");
        Label operator= (Label) sell.get("operator");
        VBox V1= (VBox) sell.get("V1");
        VBox TJB= (VBox) sell.get("TJB");
        VBox V2= (VBox) sell.get("V2");
        Label DJQsystem= (Label) sell.get("DJQsystem");
        TextField DJQperson= (TextField) sell.get("DJQperson");
        TextField remark= (TextField) sell.get("remark");
        Label MoneySum= (Label) sell.get("MoneySum");
        VBox VBoxMessage= (VBox) sell.get("VBoxMessage");

        name.setText(vo.getID());
        client.setText(vo.getClient());
        worker.setText(vo.getClerk());
        operator.setText(vo.getOperator());
        MoneySum.setText("￥ "+vo.getTotal());
        DJQsystem.setText(vo.getVoucherStrategy()+"");
        DJQperson.setText(vo.getVoucher()+"");
        remark.setText(vo.getNote());
        ArrayList<String[]> array1=vo.getCommodityList();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Sell/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Sell/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            TextField textfield3= (TextField) addpart.get("textfield3");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i)[0]);
            label3.setText(array1.get(i)[1]);
            label4.setText(array1.get(i)[2]);
            textfield1.setText(array1.get(i)[3]);
            textfield2.setText(array1.get(i)[4]);
            label5.setText(array1.get(i)[5]);
            textfield3.setText(array1.get(i)[6]);
            V1.getChildren().add(subroot);
        }
        ArrayList<String[]> array2=vo.getGifts();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart003.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Sell/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Sell/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            label1.setText((i+1)+"");
            label2.setText(array2.get(i)[0]);
            label3.setText(array2.get(i)[1]);
            label4.setText(array2.get(i)[2]);
            textfield1.setText(array2.get(i)[3]);
            textfield2.setText(array2.get(i)[4]);
            V2.getChildren().add(subroot);
        }
        ArrayList<Infor> array3=vo.getInformation();
        if(array3!=null){
            for(int i=0;i<array3.size();i++){
                URL suburl=getClass().getResource("Sell/AddPart001.fxml");
                if(i%2==0){
                    suburl=getClass().getResource("Sell/AddPart005.fxml");
                }
                else{
                    suburl=getClass().getResource("Sell/AddPart006.fxml");
                }

                FXMLLoader subloader=new FXMLLoader(suburl);
                Parent subroot=null;
                try {
                    subroot=subloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> addpart = subloader.getNamespace();
                TextField textfield= (TextField) addpart.get("textfield");
                textfield.setText(array3.get(i).getCheckerID()+"  "+array3.get(i).getCheckerTime()+"  "+array3.get(i).getRemark()+"  "+array3.get(i).getResult());
                VBoxMessage.getChildren().add(subroot);
            }
        }


        Map<String, Integer> tjb=vo.getPricePack();
        int temp=0;
        if(tjb==null){

        }
        else{
            for(Map.Entry<String, Integer>entry: tjb.entrySet()){
                URL suburl=getClass().getResource("Sell/AddPart001.fxml");
                if(temp%2==0){
                    suburl=getClass().getResource("Sell/Vochour.fxml");
                }
                else{
                    suburl=getClass().getResource("Sell/Vochour.fxml");
                }

                FXMLLoader subloader=new FXMLLoader(suburl);
                Parent subroot=null;
                try {
                    subroot=subloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> addpart = subloader.getNamespace();
                Label id= (Label) addpart.get("id");
                Label content= (Label) addpart.get("content");
                TextField amount= (TextField) addpart.get("amount");
                id.setText(entry.getKey());
                content.setText("XXXXXXXXXXXXXXXXXXXXX");
                amount.setText(entry.getValue()+"");
                TJB.getChildren().add(subroot);
            }
        }


        //3.设置草稿跳转界面
        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"销售单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }

        return root;
    }

    //打开销售退货单
    public Parent openSellBack(String filename, int state){
        SaleReturnVO vo=myfile.getSaleReturnFile(filename);

        URL url=getClass().getResource("Replenish/ReplenishPane.fxml");
        if(state==0){
            url=getClass().getResource("Sell/SBP.fxml");
        }
        else{
            url=getClass().getResource("Sell/SBP.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> replenish = loader.getNamespace();
        TextField name= (TextField) replenish.get("name");
        TextField client= (TextField) replenish.get("client");
        TextField worker= (TextField) replenish.get("worker");
        Label operator= (Label) replenish.get("ope");
        ComboBox<String> warehouse= (ComboBox<String>) replenish.get("warehouse");
        Label MoneySum= (Label) replenish.get("MoneySum");
        Label AmountSum= (Label) replenish.get("AmountSum");
        VBox V1= (VBox) replenish.get("V1");
        VBox VBoxMessage= (VBox) replenish.get("VBoxMessage");

        name.setText(vo.getID());
        client.setText(vo.getClient());
        worker.setText(vo.getClerk());
        operator.setText(vo.getOperator());
        //warehouse.setPromptText(vo.getWarehouseID());
        MoneySum.setText("合计进货金额 ￥"+vo.getTotal());
        //AmountSum.setText("合计进货数量 "+vo.getAmountSum());
        ArrayList<String[]> array1=vo.getCommodityList();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            Label label6= (Label) addpart.get("label6");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i)[0]);
            label3.setText(array1.get(i)[1]);
            label4.setText(array1.get(i)[2]);
            textfield1.setText(array1.get(i)[3]);
            label5.setText(array1.get(i)[4]);
            label6.setText(array1.get(i)[5]);
            textfield2.setText(array1.get(i)[6]);
            V1.getChildren().add(subroot);
        }
        ArrayList<Infor> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i).getCheckerID()+"  "+array2.get(i).getCheckerTime()+"  "+array2.get(i).getRemark()+"  "+array2.get(i).getResult());
            VBoxMessage.getChildren().add(subroot);
        }

        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"销售退货单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }

        return root;
    }

    //打开进货单
    public Parent openReplenish(String fileID, int state){
        PurchaseVO vo=myfile.getPurchaseFile(fileID);

        URL url=getClass().getResource("Replenish/ReplenishPane.fxml");
        if(state==0){
            url=getClass().getResource("Replenish/ReplenishPane.fxml");
        }
        else{
            url=getClass().getResource("Replenish/ReplenishPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> replenish = loader.getNamespace();
        TextField name= (TextField) replenish.get("name");
        TextField client= (TextField) replenish.get("client");
        TextField worker= (TextField) replenish.get("worker");
        Label operator= (Label) replenish.get("ope");
        ComboBox<String> warehouse= (ComboBox<String>) replenish.get("warehouse");
        Label MoneySum= (Label) replenish.get("MoneySum");
        Label AmountSum= (Label) replenish.get("AmountSum");
        VBox V1= (VBox) replenish.get("V1");
        VBox VBoxMessage= (VBox) replenish.get("VBoxMessage");

        name.setText(vo.getID());
        client.setText(vo.getClientID());
        worker.setText(vo.getClerk());
        operator.setText(vo.getOperator());
        warehouse.setPromptText(vo.getWarehouseID());
        MoneySum.setText("合计进货金额 ￥"+vo.getTotal());
        //AmountSum.setText("合计进货数量 "+vo.getAmountSum());
        ArrayList<String[]> array1=vo.getProductItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            Label label6= (Label) addpart.get("label6");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i)[0]);
            label3.setText(array1.get(i)[1]);
            label4.setText(array1.get(i)[2]);
            textfield1.setText(array1.get(i)[3]);
            label5.setText(array1.get(i)[4]);
            label6.setText(array1.get(i)[5]);
            textfield2.setText(array1.get(i)[6]);
            V1.getChildren().add(subroot);
        }
        ArrayList<Infor> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i).getCheckerID()+"  "+array2.get(i).getCheckerTime()+"  "+array2.get(i).getRemark()+"  "+array2.get(i).getResult());
            VBoxMessage.getChildren().add(subroot);
        }

        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"进货单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }

        return root;
    }

    //打开进货退货单
    public Parent openReplenishBack(String filename, int state){
        PurchaseReturnVO vo=myfile.getPurchaseReturnFile(filename);

        URL url=getClass().getResource("Replenish/ReplenishBackPane.fxml");
        if(state==0){
            url=getClass().getResource("Replenish/ReplenishBackPane.fxml");
        }
        else{
            url=getClass().getResource("Replenish/ReplenishBackPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> replenish = loader.getNamespace();
        TextField name= (TextField) replenish.get("name");
        TextField client= (TextField) replenish.get("client");
        TextField worker= (TextField) replenish.get("worker");
        Label operator= (Label) replenish.get("ope");
        ComboBox<String> warehouse= (ComboBox<String>) replenish.get("warehouse");
        Label MoneySum= (Label) replenish.get("MoneySum");
        Label AmountSum= (Label) replenish.get("AmountSum");
        VBox V1= (VBox) replenish.get("V1");
        VBox VBoxMessage= (VBox) replenish.get("VBoxMessage");

        name.setText(vo.getID());
        client.setText(vo.getClientID());
        worker.setText(vo.getClerk());
        operator.setText(vo.getOperator());
        warehouse.setPromptText(vo.getWarehouseID());
        MoneySum.setText("合计退货金额 ￥"+vo.getTotal());
        //AmountSum.setText("合计退货数量 "+vo.getAmountSum());
        ArrayList<String[]> array1=vo.getProductItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            Label label2= (Label) addpart.get("label2");
            Label label3= (Label) addpart.get("label3");
            Label label4= (Label) addpart.get("label4");
            Label label5= (Label) addpart.get("label5");
            Label label6= (Label) addpart.get("label6");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            label1.setText((i+1)+"");
            label2.setText(array1.get(i)[0]);
            label3.setText(array1.get(i)[1]);
            label4.setText(array1.get(i)[2]);
            textfield1.setText(array1.get(i)[3]);
            label5.setText(array1.get(i)[4]);
            label6.setText(array1.get(i)[5]);
            textfield2.setText(array1.get(i)[6]);
            V1.getChildren().add(subroot);
        }
        ArrayList<Infor> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Replenish/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Replenish/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i).getCheckerID()+"  "+array2.get(i).getCheckerTime()+"  "+array2.get(i).getRemark()+"  "+array2.get(i).getResult());
            VBoxMessage.getChildren().add(subroot);
        }

        if(state==0){
            startChoose controller=new startChoose();
            controller.go(loader,root,"进货退货单");
            root=controller.getHBox();
            Scenes s=new Scenes();
            s.initialNumber(array1.size());
        }

        return root;
    }


    //打开赠送单
    /*
    public Parent openGift(String filename, int state){
        URL url=getClass().getResource("OverFlowAndLack/Overflow.fxml");
        System.out.println(url);
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    */

    /*

    //打开收款单
    public Parent openPaied(String filename, int state){
        BePaiedFileVO vo=myfile.getSaleFile(filename);
        URL url=getClass().getResource("Pay/BePaiedPane.fxml");
        if(state==0){
            url=getClass().getResource("Pay/BePaiedPane.fxml");
        }
        else{
            url=getClass().getResource("Pay/BePaiedPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> replenish = loader.getNamespace();
        TextField name= (TextField) replenish.get("name");
        TextField client= (TextField) replenish.get("client");
        Label operator= (Label) replenish.get("operator");
        Label MoneySum= (Label) replenish.get("MoneySum");
        VBox V1= (VBox) replenish.get("V1");
        VBox VBoxMessage= (VBox) replenish.get("VBoxMessage");

        name.setText(vo.getName());
        client.setText(vo.getClient());
        operator.setText(vo.getOperator());
        MoneySum.setText("总额汇总 ￥"+vo.getMoneySum());
        ArrayList<String[]> array1=vo.getItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Pay/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Pay/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            TextField textfield3= (TextField) addpart.get("textfield3");
            label1.setText(array1.get(i)[0]);
            textfield1.setText(array1.get(i)[1]);
            textfield2.setText(array1.get(i)[2]);
            textfield3.setText(array1.get(i)[3]);
            V1.getChildren().add(subroot);
        }
        ArrayList<String> array2=vo.getMessages();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Pay/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Pay/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i));
            VBoxMessage.getChildren().add(subroot);
        }
        return root;
    }

    //打开付款单
    public Parent openPay(String filename, int state){
        wrong vo=myfile.getPurchaseFile(filename);
        URL url=getClass().getResource("Pay/PayPane.fxml");
        if(state==0){
            url=getClass().getResource("Pay/PayPane.fxml");
        }
        else{
            url=getClass().getResource("Pay/PayPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> replenish = loader.getNamespace();
        TextField name= (TextField) replenish.get("name");
        TextField client= (TextField) replenish.get("client");
        Label operator= (Label) replenish.get("operator");
        Label MoneySum= (Label) replenish.get("MoneySum");
        VBox V1= (VBox) replenish.get("V1");
        VBox VBoxMessage= (VBox) replenish.get("VBoxMessage");

        name.setText(vo.getID());
        client.setText(vo.getClerk());
        operator.setText(vo.getOperator());
        MoneySum.setText("总额汇总 ￥"+vo.getTotal());
        ArrayList<String[]> array1=vo.getProductItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Pay/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Pay/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            TextField textfield3= (TextField) addpart.get("textfield3");
            label1.setText(array1.get(i)[0]);
            textfield1.setText(array1.get(i)[1]);
            textfield2.setText(array1.get(i)[2]);
            textfield3.setText(array1.get(i)[3]);
            V1.getChildren().add(subroot);
        }
        ArrayList<String> array2=vo.getInformation();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Pay/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Pay/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i));
            VBoxMessage.getChildren().add(subroot);
        }
        return root;
    }

    //打开现金费用单
    public Parent openCash(String filename, int state){
        CashFileVO vo=myfile.getCashFile(filename);
        URL url=getClass().getResource("Cash/CashPane.fxml");
        if(state==0){
            url=getClass().getResource("Cash/CashPane.fxml");
        }
        else{
            url=getClass().getResource("Cash/CashPaneUneditable.fxml");
        }
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> cash = loader.getNamespace();
        TextField name= (TextField) cash.get("name");
        ComboBox<String> account= (ComboBox<String>) cash.get("account");
        Label operator= (Label) cash.get("operator");
        Label MoneySum= (Label) cash.get("MoneySum");
        VBox V1= (VBox) cash.get("V1");
        VBox VBoxMessage= (VBox) cash.get("VBoxMessage");

        name.setText(vo.getName());
        account.setPromptText(vo.getAccount());
        operator.setText(vo.getOperator());
        MoneySum.setText("总额汇总 ￥"+vo.getMoneySum());
        ArrayList<String[]> array1=vo.getItems();
        for(int i=0;i<array1.size();i++){
            URL suburl=getClass().getResource("Sell/AddPArt001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Cash/AddPart001.fxml");
            }
            else{
                suburl=getClass().getResource("Cash/AddPart002.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            Label label1= (Label) addpart.get("label1");
            TextField textfield1= (TextField) addpart.get("textfield1");
            TextField textfield2= (TextField) addpart.get("textfield2");
            TextField textfield3= (TextField) addpart.get("textfield3");
            label1.setText(array1.get(i)[0]);
            textfield1.setText(array1.get(i)[1]);
            textfield2.setText(array1.get(i)[2]);
            textfield3.setText(array1.get(i)[3]);
            V1.getChildren().add(subroot);
        }
        ArrayList<String> array2=vo.getMessages();
        for(int i=0;i<array2.size();i++){
            URL suburl=getClass().getResource("Sell/AddPart001.fxml");
            if(i%2==0){
                suburl=getClass().getResource("Cash/AddPart003.fxml");
            }
            else{
                suburl=getClass().getResource("Cash/AddPart004.fxml");
            }

            FXMLLoader subloader=new FXMLLoader(suburl);
            Parent subroot=null;
            try {
                subroot=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> addpart = subloader.getNamespace();
            TextField textfield= (TextField) addpart.get("textfield");
            textfield.setText(array2.get(i));
            VBoxMessage.getChildren().add(subroot);
        }
        return root;
    }
*/

    public HBox getHBox(){
        return hb;
    }
}
