package Others;

import Login.CurrentState;
import Start.Client;
import Start.RemoteHelper;
import infor.CommodityItem;
import infor.Infor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.misc.VM;
import vo.filevo.*;
import vo.utilityvo.ResultMessage;


import java.rmi.RemoteException;
import java.util.ArrayList;

public class SolveUpdate {
    public void updateOverFlowAndLack(String id, String midPerson, String warehosue, VBox Vitems, double total, VBox VMessage){

        //整理信息
        ArrayList<Infor> arrayM=new ArrayList<>();
        for(int i=1;i<VMessage.getChildren().size();i++){
            HBox h= (HBox) VMessage.getChildren().get(i).lookup("HBox");
            TextField t= (TextField) h.getChildren().get(1).lookup("TextField");
            String[] part=t.getText().split("  ");
            Infor infor=new Infor();
            infor.setCheckerID(part[0]);
            infor.setCheckerTime(part[1]);
            infor.setRemark(part[2]);
            infor.setResult(part[3]);
            arrayM.add(infor);
        }

        //整理数据
        ArrayList<CommodityItem> arrayI=new ArrayList<>();
        for(int i=1;i<Vitems.getChildren().size();i++){
            HBox h= (HBox) Vitems.getChildren().get(i).lookup("HBox");
            CommodityItem item=new CommodityItem();
            Label l1= (Label) h.getChildren().get(1).lookup("Label");
            Label l2= (Label) h.getChildren().get(2).lookup("Label");
            Label l3= (Label) h.getChildren().get(3).lookup("Label");
            Label l4= (Label) h.getChildren().get(4).lookup("Label");
            TextField t1= (TextField) h.getChildren().get(5).lookup("TextField");
            Label l5= (Label) h.getChildren().get(6).lookup("Label");

            item.setCommodityID(l1.getText());
            item.setCommodityname(l2.getText());
            item.setCommoditySize(l3.getText());
            item.setInventory(Integer.parseInt(l4.getText()));
            if(t1.getText().equals("")||t1.getText()==null){
                item.setModiNum(0);
            }
            else{
                item.setModiNum(Integer.parseInt(t1.getText()));
            }
            item.setUpdateNum(Integer.parseInt(l5.getText()));

            arrayI.add(item);
        }

        ExcessVO vo=new ExcessVO("",id,midPerson,warehosue,arrayI,0,total,arrayM);
        try {
            ResultMessage r=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().modExcessOrLoss(CurrentState.getLoginID(),vo);
            System.out.println(r);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void updatePurchaseAndReturn(String id, String client, String clerk, String operator, VBox Vitems, double sum, VBox VMessage, String note){
        //整理信息
        ArrayList<Infor> arrayM=new ArrayList<>();
        for(int i=1;i<VMessage.getChildren().size();i++){
            HBox h= (HBox) VMessage.getChildren().get(i).lookup("HBox");
            TextField t= (TextField) h.getChildren().get(1).lookup("TextField");
            String[] part=t.getText().split("  ");
            Infor infor=new Infor();
            infor.setCheckerID(part[0]);
            infor.setCheckerTime(part[1]);
            infor.setRemark(part[2]);
            infor.setResult(part[3]);
            arrayM.add(infor);
        }

        //整理数据

        ArrayList<String[]> arrayI=new ArrayList<>();
        for(int i=1;i<Vitems.getChildren().size();i++){
            HBox h= (HBox) Vitems.getChildren().get(i).lookup("HBox");
            CommodityItem item=new CommodityItem();
            Label l1= (Label) h.getChildren().get(1).lookup("Label");
            Label l2= (Label) h.getChildren().get(2).lookup("Label");
            Label l3= (Label) h.getChildren().get(3).lookup("Label");
            TextField t1= (TextField) h.getChildren().get(4).lookup("TextField");
            TextField t2= (TextField) h.getChildren().get(5).lookup("TextField");
            Label l4= (Label) h.getChildren().get(6).lookup("Label");
            TextField t3= (TextField) h.getChildren().get(7).lookup("TextField");

            String[] array=new String[7];
            array[0]=l1.getText();
            array[1]=l2.getText();
            array[2]=l3.getText();
            if(t1.getText().equals("")||t1.getText()==null){
                array[3]="0";
            }
            else{
                array[3]=t1.getText();
            }

            if(t2==null){
                Label l= (Label) h.getChildren().get(5).lookup("Label");
                if(l.getText().equals("")||l.getText()==null){
                    array[4]="0";
                }
                else{
                    array[4]=l.getText();
                }
            }
            else{
                if(t2.getText().equals("")||t2.getText()==null){
                    array[4]="0";
                }
                else{
                    array[4]=t2.getText();
                }
            }

            array[5]=l4.getText();
            if(t3.getText().equals("")||t3.getText()==null){
                array[6]="";
            }
            else{
                array[6]=t3.getText();
            }

            arrayI.add(array);
        }

        if(id.contains("pc")){
            PurchaseVO vo=new PurchaseVO(id, FileType.PURCHASE,operator,clerk,note,"","", FileState.WAITREVIEW, client,"",arrayI,sum,arrayM);
            try {
                ResultMessage r=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().modifyPurchase(CurrentState.getLoginID(),id,vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else if(id.contains("pr")){
            PurchaseReturnVO vo=new PurchaseReturnVO(id, FileType.PURCHASERETURN,operator,clerk,note,"","", FileState.WAITREVIEW, client,"",arrayI,sum,arrayM);
            try {
                System.out.println(id);
                System.out.println(vo);
                ResultMessage r=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().modifyPurchaseReturn(CurrentState.getLoginID(),id,vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else if(id.contains("sr")){
            SaleReturnVO vo=new SaleReturnVO(id,FileType.SALERETURN,FileState.DRAFT,operator,clerk,note,"","","",client,arrayI,sum,arrayM);
            try {
                ResultMessage r=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().modifySaleReturn(CurrentState.getLoginID(),vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

}
