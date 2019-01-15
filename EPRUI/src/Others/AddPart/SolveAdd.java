package Others.AddPart;

import Login.CurrentState;
import MockObject.Accountbl;
import MockObject.Clientbl;
import MockObject.MyFilebl;
import MockObject.Warehousebl;
import MyFileUI.OverFlowAndLack.Scenes;
import MyFileUI.OverFlowAndLack.startChoose;
import Start.RemoteHelper;
import javafx.scene.layout.VBox;
import org.omg.CORBA.Current;
import vo.clientvo.ClientIdentity;
import vo.clientvo.ClientVO;
import vo.filevo.FileType;
import vo.filevo.PricePackStrategyVO;
import vo.uservo.Position;
import vo.accountvo.AccountVO;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.WarehouseVO;
import WarehouseUI.Management.MakeWarehouseTree;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public class SolveAdd {
    private Position position;
    private String state;
    public SolveAdd(Position p, String state){
        position=p;
        this.state=state;
    }

    //新增账户
    public void getAccountPane(ListView<String> list){
        URL url=getClass().getResource("SetID.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> fxmlnamespace=loader.getNamespace();

        Label title= (Label) fxmlnamespace.get("title");
        Label type= (Label) fxmlnamespace.get("type");
        TextField id= (TextField) fxmlnamespace.get("id");
        Button confirm= (Button) fxmlnamespace.get("confirm");
        Button cancel= (Button) fxmlnamespace.get("cancel");
        Label attention= (Label) fxmlnamespace.get("attention");
        Label close= (Label) fxmlnamespace.get("close");


        title.setText("新 建 账 户");
        type.setText("新建账户ID");

        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(id.getText().equals("")){
                    attention.setText("账户ID不能为空");
                    attention.setTextFill(Paint.valueOf("red"));
                }
                else{
                    Accountbl bl=new Accountbl();
                    AccountVO vo=new AccountVO(id.getText(),"",0,true);
                    if(bl.addAccount(CurrentState.getLoginID(),vo)==ResultMessage.addSuccess){
                        stage.close();
                        list.getItems().add(id.getText());
                        list.setMaxHeight(list.getItems().size()*50);
                        list.setPrefHeight(list.getItems().size()*50);
                    }
                    else{
                        attention.setText("该目录下已存在相同ID的账户");
                        attention.setTextFill(Paint.valueOf("red"));
                    }
                }
            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }

    //新增客户
    public void getClientPane(ListView<String> listPay, ListView<String> listSell, HBox hb){
        URL url=getClass().getResource("SellOrPay.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> fxmlnamespace=loader.getNamespace();
        Button newPay= (Button) fxmlnamespace.get("newPay");
        Button newSell= (Button) fxmlnamespace.get("newSell");
        Label close= (Label) fxmlnamespace.get("close");

        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root));
        stage.show();

        newPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
                getInitialClientPane(listPay,hb, "进货商");
            }
        });
        newSell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
                getInitialClientPane(listSell,hb,"销售商");
            }
        });
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
    }

    //新增仓库
    public void getWarehosuePane(Accordion accoridion, HBox hb){
        URL url=getClass().getResource("/WarehouseUI/Management/Warehouse.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> fxmlnamespace=loader.getNamespace();

        hb.getChildren().clear();
        hb.getChildren().add(root);

        TextField ID= (TextField) fxmlnamespace.get("ID");
        TextField NAME= (TextField) fxmlnamespace.get("NAME");
        TextField id= (TextField) fxmlnamespace.get("id");
        TextField name= (TextField) fxmlnamespace.get("name");
        Label banOrRecover= (Label) fxmlnamespace.get("banOrRecover");
        Label saveOrModify= (Label) fxmlnamespace.get("saveOrModify");
        Label attention= (Label) fxmlnamespace.get("attention");

        saveOrModify.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(saveOrModify.getText().equals("修 改")){
                    ID.setEditable(true);
                    NAME.setEditable(true);
                    id.setEditable(true);
                    name.setEditable(true);
                    saveOrModify.setText("保 存");
                }
                else if(saveOrModify.getText().equals("保 存")){
                    Warehousebl bl=new Warehousebl();
                    boolean isban=false;
                    if(banOrRecover.getText().contains("禁用")){
                        isban=false;
                    }
                    else{
                        isban=true;
                    }
                    WarehouseVO vo=new WarehouseVO(id.getText(),name.getText(),isban);
                    ResultMessage message=bl.newWarehouse(CurrentState.getLoginID(),vo);

                    if(message==ResultMessage.exist){
                        attention.setTextFill(Paint.valueOf("red"));
                    }
                    else if(message==ResultMessage.addSuccess){
                        saveOrModify.setText("修 改");
                        MakeWarehouseTree maketree=new MakeWarehouseTree(id.getText(),hb);
                        TitledPane t=new TitledPane(id.getText(),maketree.getTree());
                        accoridion.getPanes().add(t);
                    }
                }
            }
        });

        /*
        URL url=getClass().getResource("SetID.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> fxmlnamespace=loader.getNamespace();

        Label title= (Label) fxmlnamespace.get("title");
        Label type= (Label) fxmlnamespace.get("type");
        TextField id= (TextField) fxmlnamespace.get("id");
        Button confirm= (Button) fxmlnamespace.get("confirm");
        Button cancel= (Button) fxmlnamespace.get("cancel");
        Label attention= (Label) fxmlnamespace.get("attention");
        Label close= (Label) fxmlnamespace.get("close");


        title.setText("新 建 仓 库");
        type.setText("新建仓库ID");

        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(id.getText().equals("")){
                    attention.setText("仓库ID不能为空");
                    attention.setTextFill(Paint.valueOf("red"));
                }
                else{
                    Warehousebl bl=new Warehousebl();
                    WarehouseVO vo=new WarehouseVO(id.getText(),null,false);
                    if(bl.newWarehouse(id.getText(),vo)==ResultMessage.addSuccess){
                        stage.close();
                        ResultMessage message=ResultMessage.Fail;
                        try {
                            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().addNewWarehouseID(id.getText());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        if(message==ResultMessage.exist){

                        }
                        MakeWarehouseTree make=new MakeWarehouseTree(id.getText(),hb);
                        TitledPane t=new TitledPane(id.getText(),make.getTree());
                        accoridion.getPanes().add(t);
                    }
                    else{
                        attention.setText("该目录下已存在相同ID的仓库");
                        attention.setTextFill(Paint.valueOf("red"));
                    }
                }
            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });

        */
    }

    private void getInitialClientPane(ListView<String> list, HBox hb, String type) {
        hb.getChildren().clear();
        URL url=getClass().getResource("/ClientUI/client.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> fxmlnamespace=loader.getNamespace();
        TextField text1= (TextField) fxmlnamespace.get("text1");
        TextField text2= (TextField) fxmlnamespace.get("text2");
        TextField text3= (TextField) fxmlnamespace.get("text3");
        TextField text4= (TextField) fxmlnamespace.get("text4");
        TextField text5= (TextField) fxmlnamespace.get("text5");
        TextField text6= (TextField) fxmlnamespace.get("text6");
        TextField text7= (TextField) fxmlnamespace.get("text7");
        TextField text8= (TextField) fxmlnamespace.get("text8");
        TextField text9= (TextField) fxmlnamespace.get("text9");
        TextField text10= (TextField) fxmlnamespace.get("text10");
        TextField text11= (TextField) fxmlnamespace.get("text11");
        TextField text12= (TextField) fxmlnamespace.get("text12");
        TextField text13= (TextField) fxmlnamespace.get("text13");
        TextField text14= (TextField) fxmlnamespace.get("text14");
        Label saveOrModify= (Label) fxmlnamespace.get("saveOrModify");
        Label banOrRecover= (Label) fxmlnamespace.get("banOrRecover");
        Label attention1= (Label) fxmlnamespace.get("attention1");
        TextField ID= (TextField) fxmlnamespace.get("ID");
        TextField NAME= (TextField) fxmlnamespace.get("NAME");

        ID.setEditable(true);
        NAME.setEditable(true);
        text1.setEditable(true);
        //text2.setEditable(false);
        text3.setEditable(true);
        text4.setEditable(true);
        text5.setEditable(true);
        text6.setEditable(true);
        text7.setEditable(true);
        //text8.setEditable(false);
        //text9.setEditable(false);
        if(CurrentState.getRight()){
            text10.setEditable(true);
        }
        else{
            text10.setEditable(false);
        }
        //text10.setEditable(false);
        text11.setEditable(true);
        text12.setEditable(true);
        text13.setEditable(true);
        text14.setEditable(true);

        text2.setText(type);
        saveOrModify.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Clientbl bl=new Clientbl();
                if(text1.getText().equals("")){
                    attention1.setText("客户ID/客户编号 为必填项");
                    attention1.setTextFill(Paint.valueOf("red"));
                }
                else{
                    ClientIdentity client=null;
                    if(text2.getText().equals("进货商")){
                        client=ClientIdentity.Supplier;
                    }
                    else if(text2.getText().equals("销售商")){
                        client=ClientIdentity.Seller;
                    }
                    Boolean isban=false;
                    if(banOrRecover.getText().equals("禁用该客户")){
                        isban=false;
                    }
                    else if(banOrRecover.getText().equals("启用该客户")){
                        isban=true;
                    }
                    double quota=0;
                    if(text11.getText().equals("")){
                        quota=0;
                    }
                    else {
                        quota=Double.parseDouble(text11.getText());
                    }
                    ClientVO vo=new ClientVO(text1.getText(),text3.getText(),client,Integer.parseInt(text7.getText()),text5.getText(),text4.getText(),text12.getText(),text6.getText(),text14.getText(),text10.getText(),quota,Double.parseDouble(text9.getText()),Double.parseDouble(text8.getText()),Double.parseDouble(text13.getText()),isban);
                    ResultMessage message=bl.addClient(CurrentState.getLoginID(),vo);
                    if(message== ResultMessage.exist){
                        attention1.setTextFill(Paint.valueOf("ffffff"));
                        attention1.setText("该客户ID已存在");
                        attention1.setTextFill(Paint.valueOf("red"));
                    }
                    else if(message==ResultMessage.Fail){
                        attention1.setTextFill(Paint.valueOf("ffffff"));
                        attention1.setText("新增客户失败");
                        attention1.setTextFill(Paint.valueOf("red"));
                    }
                    else if(message==ResultMessage.addSuccess){
                        attention1.setTextFill(Paint.valueOf("ffffff"));
                        attention1.setText("您已成功新增客户");
                        attention1.setTextFill(Paint.valueOf("#660099"));
                        list.getItems().add(text1.getText());
                        list.setMaxHeight(list.getItems().size()*50);
                        list.setPrefHeight(list.getItems().size()*50);
                    }

                }
            }
        });


        hb.getChildren().add(root);
        /*
        URL url=getClass().getResource("SetID.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> fxmlnamespace=loader.getNamespace();

        Label title= (Label) fxmlnamespace.get("title");
        Label type= (Label) fxmlnamespace.get("type");
        TextField id= (TextField) fxmlnamespace.get("id");
        Button confirm= (Button) fxmlnamespace.get("confirm");
        Button cancel= (Button) fxmlnamespace.get("cancel");
        Label attention= (Label) fxmlnamespace.get("attention");
        Label close= (Label) fxmlnamespace.get("close");


        title.setText("新 建 客 户");
        type.setText("新建客户ID");

        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(id.getText().equals("")){
                    attention.setText("客户ID不能为空");
                    attention.setTextFill(Paint.valueOf("red"));
                }
                else{
                    Clientbl bl=new Clientbl();
                    ClientVO vo=new ClientVO(id.getText(),null,null,0,null,null,null,null,null,null,0,0,0,0,false);
                    if(bl.addClient(CurrentState.getLoginID(),vo)==ResultMessage.addSuccess){
                        stage.close();
                        list.getProductItems().add(id.getText());
                        list.setMaxHeight(list.getProductItems().size()*50);
                        list.setPrefHeight(list.getProductItems().size()*50);
                    }
                    else{
                        attention.setText("该目录下已存在相同ID的客户");
                        attention.setTextFill(Paint.valueOf("red"));
                    }
                }
            }
        });
        cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        */
    }

    //新增单据
    public void getMyFilePane(ListView<String> draft, HBox hb){
        if(position==Position.Warehouseman){
            getWarehouseFilePane(draft,hb);
        }
        else if(position==Position.Salesman){
            getClientFilePane(draft,hb);
        }
    }
    private void getWarehouseFilePane(ListView<String> draft, HBox hb){
        URL url=getClass().getResource("warehouse.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> namespace=loader.getNamespace();
        Label close= (Label) namespace.get("close");
        Label newReplenish= (Label) namespace.get("newReplenish");
        Label newReplenishBack= (Label) namespace.get("newReplenishBack");

        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        newReplenish.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String id = null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().addExcessOrLoss(CurrentState.getLoginID(), 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL overflowURL = getClass().getResource("/MyFileUI/OverFlowAndLack/Overflow.fxml");
                FXMLLoader overloader = new FXMLLoader(overflowURL);
                Parent overroot = null;
                try {
                    overroot = overloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> overnamespace = overloader.getNamespace();
                TextField name = (TextField) overnamespace.get("name");
                name.setText(id);

                startChoose controller=new startChoose();
                controller.go(overloader,overroot,"报溢单");
                overroot=controller.getHBox();
                Scenes s=new Scenes();
                s.initialNumber(0);

                hb.getChildren().clear();
                hb.getChildren().add(overroot);


                stage.close();
                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size()*50);
                draft.setMaxHeight(draft.getItems().size()*50);
            }
        });
        newReplenishBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String id = null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().addExcessOrLoss(CurrentState.getLoginID(), 1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL overflowURL = getClass().getResource("/MyFileUI/OverFlowAndLack/Lack.fxml");
                FXMLLoader overloader = new FXMLLoader(overflowURL);
                Parent overroot = null;
                try {
                    overroot = overloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> overnamespace = overloader.getNamespace();
                TextField name = (TextField) overnamespace.get("name");
                name.setText(id);

                startChoose controller=new startChoose();
                controller.go(overloader,overroot,"报损单");
                overroot=controller.getHBox();
                Scenes s=new Scenes();
                s.initialNumber(0);

                stage.close();

                hb.getChildren().clear();
                hb.getChildren().add(overroot);

                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size()*50);
                draft.setMaxHeight(draft.getItems().size()*50);
            }
        });
    }
    private void getClientFilePane(ListView<String> draft, HBox hb){
        URL url=getClass().getResource("salesman.fxml");
        FXMLLoader loader=new FXMLLoader(url);
        Parent root=null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> namespace=loader.getNamespace();
        Label close= (Label) namespace.get("close");
        Label newPay= (Label) namespace.get("newPay");
        Label newPayBack= (Label) namespace.get("newPayBack");
        Label newSell= (Label) namespace.get("newSell");
        Label newSellBack= (Label) namespace.get("newSellBack");

        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        newPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyFilebl bl=new MyFilebl();
                String id= null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().createSaleAndPurchase(CurrentState.getLoginID(), FileType.PURCHASE);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL payurl=getClass().getResource("/MyFileUI/Replenish/ReplenishPane.fxml");
                FXMLLoader payloader=new FXMLLoader(payurl);
                Parent payroot = null;
                try {
                    payroot=payloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, Object> paynamespace=payloader.getNamespace();
                TextField name= (TextField) paynamespace.get("name");
                TextField client= (TextField) paynamespace.get("client");
                TextField worker= (TextField) paynamespace.get("worker");
                Label operator= (Label) paynamespace.get("ope");

                name.setText(id);
                operator.setText(CurrentState.getLoginID());


                startChoose controller=new startChoose();
                controller.go(payloader,payroot,"进货单");
                payroot=controller.getHBox();
                Scenes s=new Scenes();
                s.initialNumber(0);

                hb.getChildren().clear();
                hb.getChildren().add(payroot);

                stage.close();
                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size()*50);
                draft.setMaxHeight(draft.getItems().size()*50);
            }
        });
        newPayBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyFilebl bl=new MyFilebl();
                String id= null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().createSaleAndPurchase(CurrentState.getLoginID(), FileType.PURCHASERETURN);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL payurl=getClass().getResource("/MyFileUI/Replenish/ReplenishBackPane.fxml");
                FXMLLoader payloader=new FXMLLoader(payurl);
                Parent payroot = null;
                try {
                    payroot=payloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, Object> paynamespace=payloader.getNamespace();
                TextField name= (TextField) paynamespace.get("name");
                TextField client= (TextField) paynamespace.get("client");
                TextField worker= (TextField) paynamespace.get("worker");
                Label operator= (Label) paynamespace.get("ope");

                name.setText(id);
                operator.setText(CurrentState.getLoginID());


                startChoose controller=new startChoose();
                controller.go(payloader,payroot,"进货退货单");
                payroot=controller.getHBox();
                Scenes s=new Scenes();
                s.initialNumber(0);

                hb.getChildren().clear();
                hb.getChildren().add(payroot);

                stage.close();
                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size()*50);
                draft.setMaxHeight(draft.getItems().size()*50);
            }
        });
        newSell.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                String id = null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().createSaleAndPurchase(CurrentState.getLoginID(), FileType.SALE);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL saleurl = getClass().getResource("/MyFileUI/Sell/SellPane.fxml");
                FXMLLoader saleloader = new FXMLLoader(saleurl);
                Parent saleroot = null;
                try {
                    saleroot = saleloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> salenamespace = saleloader.getNamespace();
                TextField name = (TextField) salenamespace.get("name");
                Label operator = (Label) salenamespace.get("operator");
                VBox TJB = (VBox) salenamespace.get("TJB");
                Label sum = (Label) salenamespace.get("MoneySum");

                name.setText(id);
                operator.setText(CurrentState.getLoginID());
                ArrayList<PricePackStrategyVO> array = null;
                try {
                    array = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().listPricePackStrategy();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < array.size(); i++) {
                    URL TJBurl = getClass().getResource("/MyFileUI/Sell/Vochour.fxml");
                    FXMLLoader TJBloader = new FXMLLoader(TJBurl);
                    Parent TJBroot = null;
                    try {
                        TJBroot = TJBloader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Map<String, Object> TJBnamespace = TJBloader.getNamespace();
                    Label ID = (Label) TJBnamespace.get("id");
                    Label content = (Label) TJBnamespace.get("content");

                    ID.setText(array.get(i).getId());
                    ArrayList<String[]> con = array.get(i).getCommodityGroup();
                    content.setText(con.get(0)[0] + "+" + con.get(1)[0] + "+" + "......");

                    TJB.getChildren().add(TJBroot);
                }


                startChoose controller = new startChoose();
                controller.go(saleloader, saleroot, "销售单");
                saleroot = controller.getHBox();
                Scenes s = new Scenes();
                s.initialNumber(0);

                hb.getChildren().add(saleroot);
                stage.close();
                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size() * 50);
                draft.setMaxHeight(draft.getItems().size() * 50);
            }
        });
        newSellBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyFilebl bl=new MyFilebl();
                String id= null;
                try {
                    id = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().createSaleAndPurchase(CurrentState.getLoginID(), FileType.SALERETURN);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                URL saleurl = getClass().getResource("/MyFileUI/Sell/SBP.fxml");
                FXMLLoader saleloader = new FXMLLoader(saleurl);
                Parent saleroot = null;
                try {
                    saleroot = saleloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> salenamespace = saleloader.getNamespace();
                TextField name = (TextField) salenamespace.get("name");
                Label operator = (Label) salenamespace.get("ope");
                name.setText(id);
                operator.setText(CurrentState.getLoginID());

                startChoose controller = new startChoose();
                controller.go(saleloader, saleroot, "销售退货单");
                saleroot = controller.getHBox();
                Scenes s = new Scenes();
                s.initialNumber(0);

                hb.getChildren().add(saleroot);
                stage.close();

                draft.getItems().add(id);
                draft.setPrefHeight(draft.getItems().size()*50);
                draft.setMaxHeight(draft.getItems().size()*50);
            }
        });

    }


}
