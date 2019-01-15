package WarehouseUI.View;

import MockObject.Warehousebl;
import Start.RemoteHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vo.warehousevo.CheckInventoryCommodityVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class ViewController {
    //1.View界面需要的各种容器
    @FXML public VBox VBoxR1;
    @FXML public VBox VBoxR2;
    @FXML public TextField Year1;
    @FXML public TextField Month1;
    @FXML public TextField Day1;
    @FXML public TextField Year2;
    @FXML public TextField Month2;
    @FXML public TextField Day2;
    @FXML public Label SubTitle1;
    @FXML public Label SubTitle2;


    public void TextChangedAction(KeyEvent keyEvent) {
        String time=Year1.getText()+"-"+Month1.getText()+"-"+Day1.getText()+"  "+Year2.getText()+"-"+Month2.getText()+"-"+Day2.getText();
        Warehousebl w=new Warehousebl();
        if(w.checkTime(time)){
            VBoxR1.getChildren().remove(4,VBoxR1.getChildren().size());
            SubTitle1.setText("出入库明细("+time+")");
            SubTitle2.setText("合计("+time+")");


            ArrayList<String[]> array=w.checkInventory(time,"");
            for(int i=0;i<array.size();i++){
                String frame;
                if(i%2==0){
                    frame="View001.fxml";
                }
                else{
                    frame="View002.fxml";
                }

                try {
                    Parent root = FXMLLoader.load(getClass().getResource(frame));
                    HBox hb= (HBox) root.lookup("HBox");

                    Label l1=getLabel001();
                    l1.setText(array.get(i)[0]);
                    hb.getChildren().add(l1);
                    Label l2=getLabel001();
                    l2.setText(array.get(i)[1]);
                    hb.getChildren().add(l2);
                    Label l3=getLabel001();
                    l3.setText(array.get(i)[2]);
                    hb.getChildren().add(l3);
                    Label l4=getLabel001();
                    l4.setText(array.get(i)[3]);
                    hb.getChildren().add(l4);
                    Label l5=getLabel001();
                    l5.setText(array.get(i)[4]);
                    hb.getChildren().add(l5);
                    Label l6=getLabel001();
                    l6.setText(array.get(i)[5]);
                    hb.getChildren().add(l6);
                    Label l7=getLabel001();
                    l7.setPrefWidth(260);
                    l7.setMaxWidth(260);
                    l7.setText(array.get(i)[6]);
                    hb.getChildren().add(l7);

                    VBoxR1.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            /*
            String[] temp=time.split("  ");
            ArrayList<ArrayList<CheckInventoryCommodityVO>> info1= null;
            try {
                info1 = RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().checkInventory(temp[0],temp[1]);
                System.out.println(info1.size());
                System.out.println(info1.get(0).size());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for(int i=0;i<info1.size();i++){
                for(int j=0;j<info1.get(i).size();j++){
                    String frame;
                    if(j%2==0){
                        frame="View001.fxml";
                    }
                    else{
                        frame="View002.fxml";
                    }

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource(frame));
                        HBox hb= (HBox) root.lookup("HBox");

                        Label l1=getLabel001();
                        l1.setText(info1.get(i).get(j).getPassTime());
                        hb.getChildren().add(l1);
                        Label l2=getLabel001();
                        l2.setText(info1.get(i).get(j).getName());
                        hb.getChildren().add(l2);
                        Label l3=getLabel001();
                        l3.setText(info1.get(i).get(j).getInOrOut());
                        hb.getChildren().add(l3);
                        Label l4=getLabel001();
                        l4.setText(info1.get(i).get(i).getNum()+"");
                        hb.getChildren().add(l4);
                        Label l5=getLabel001();
                        l5.setText(info1.get(i).get(j).getMoney()+"");
                        hb.getChildren().add(l5);
                        Label l6=getLabel001();
                        l6.setText(info1.get(i).get(j).getWarehouseLeftNum()+"");
                        hb.getChildren().add(l6);
                        Label l7=getLabel001();
                        l7.setPrefWidth(260);
                        l7.setMaxWidth(260);
                        l7.setText(info1.get(i).get(j).getCommodityID());
                        hb.getChildren().add(l7);

                        VBoxR1.getChildren().add(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
            */


            VBoxR2.getChildren().remove(2,VBoxR2.getChildren().size());
            ArrayList<String[]> info2=w.calculateInventory(time,MakeViewList.getInventory());
            for(int i=0;i<info2.size();i++){
                String frame;
                if(i%2==0){
                    frame="View003.fxml";
                }
                else{
                    frame="View004.fxml";
                }
                try {
                    Parent root = FXMLLoader.load(getClass().getResource(frame));
                    HBox hb= (HBox) root.lookup("HBox");

                    Label l1=getLabel002();
                    l1.setText(info2.get(i)[0]);
                    hb.getChildren().add(l1);

                    Label l21=getLabel001();
                    l21.setText(info2.get(i)[1]);
                    l21.setPrefHeight(50);
                    Label l22=getLabel001();
                    l22.setText(info2.get(i)[2]);
                    l22.setPrefHeight(50);
                    VBox v=new VBox();
                    v.getChildren().add(l21);
                    v.getChildren().add(l22);
                    hb.getChildren().add(v);

                    Label l3=getLabel002();
                    l3.setText(info2.get(i)[3]);
                    hb.getChildren().add(l3);

                    Label l41=getLabel001();
                    l41.setText(info2.get(i)[4]);
                    l41.setPrefHeight(50);
                    Label l42=getLabel001();
                    l42.setText(info2.get(i)[5]);
                    l42.setPrefHeight(50);
                    VBox v4=new VBox();
                    v4.getChildren().add(l41);
                    v4.getChildren().add(l42);
                    hb.getChildren().add(v4);

                    Label l5=getLabel002();
                    l5.setText(info2.get(i)[6]);
                    hb.getChildren().add(l5);

                    Label l6=getLabel002();
                    l6.setText(info2.get(i)[7]);
                    hb.getChildren().add(l6);

                    Label l7=getLabel002();
                    l7.setPrefWidth(260);
                    l7.setMaxWidth(260);
                    l7.setText(info2.get(i)[8]);
                    hb.getChildren().add(l7);

                    VBoxR2.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Label getLabel001(){
        Label l=new Label("");
        l.setPrefWidth(100);
        l.setMaxWidth(100);
        l.setAlignment(Pos.CENTER);
        l.setStyle("-fx-font-size:18px");
        return l;
    }

    public Label getLabel002(){
        Label l=getLabel001();
        l.setPrefHeight(100);
        l.setMaxHeight(100);
        return l;
    }
}
