package MockObject;

import Login.CurrentState;
import Start.RemoteHelper;
import com.sun.org.apache.regexp.internal.RE;
import vo.uservo.Position;
import vo.filevo.*;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class MyFilebl {

    //occupation 可以是AccountManager Manager SellingManager WarehouseManager
    public ArrayList<String> getDraftFile(Position position){
        ArrayList<String> array=new ArrayList<String>();
        try {
            if(position==Position.Warehouseman){
                array=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().showDraft();
            }
            else{
                array=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().listDraft(CurrentState.getLoginID());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    public ArrayList<String> getWaitingCheckedFile(Position position){
        ArrayList<String> array=new ArrayList<String>();
        if(position==Position.Warehouseman){
            try {
                array=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().showWaitReview();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                array=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().listWaitToReview(CurrentState.getLoginID());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    public ArrayList<String> getCheckedFile(Position position){
        ArrayList<String> array=new ArrayList<String>();
        if(position==Position.Warehouseman){
            try {
                array=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().showReviewed();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                array= RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().listReviewed(CurrentState.getLoginID());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    public ArrayList<String> getTrash(Position position){
        ArrayList<String> array=new ArrayList<String>();
        if(position==Position.Warehouseman){
            try {
                array=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().showTrash();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                array=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().listDraft(CurrentState.getLoginID());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return array;
    }


    //以下用来得到单据，filename形式为：报溢单 PAP台灯
    public ExcessVO getOverflowFile(String filename){
        ExcessVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().findExcessOrLoss(CurrentState.getLoginID(),filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public ExcessVO getLackFile(String filename){
        ExcessVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().findExcessOrLoss(CurrentState.getLoginID(),filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /*
    public CashFileVO getCashFile(String filenmae){
        CashFileVO vo=new CashFileVO();
        return vo;
    }
    */

    public PurchaseVO getPurchaseFile(String filename){
        PurchaseVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().findPurchase(filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public SaleVO getSaleFile(String filename){
        SaleVO vo=null;
/*
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().findSale(filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
*/

        String[] s1={"nj/lamp/philips","飞利浦台灯","SX3500","25","60","1500","暂无"};
        String[] s2={"nj/lamp/NaSa","Nasa台灯","#669900","10","25","250","暂无"};
        String[] s3={"nj/lamp/Blue/P","P台灯","$s485","80","10","800","销量极好"};
        ArrayList<String[]> array=new ArrayList<>();
        array.add(s1);
        array.add(s2);
        array.add(s3);

        String[] g1={"nj/light/XAbc","Xabc台灯","-872564","5","10","50","暂无"};
        ArrayList<String[]> garray=new ArrayList<>();
        array.add(g1);



        vo=new SaleVO("sa2018-01-13-15",FileType.SALE,FileState.DRAFT,CurrentState.getLoginID(),"","this is a note","","","Client","NJ",array, "",garray,5.5,150,null,1000,0,7,null);

        return vo;
    }

    public PurchaseReturnVO getPurchaseReturnFile(String filename){
        PurchaseReturnVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().findPurchaseReturn(filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public SaleReturnVO getSaleReturnFile(String filename){
        SaleReturnVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().findSaleReturn(filename);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    //根据不同职业返回不同的模糊搜索结果
    public ArrayList<String> findByKeyWords(String keywords, Position p){
        ArrayList<String> array=new ArrayList<>();
        return array;
    }

    //新增
    public ResultMessage deleteFile(String file){
        if(CurrentState.getPosition()==Position.Warehouseman){
            ResultMessage re= null;
            try {
                RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().connectionTest();
                re = RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().delExcessOrLoss(CurrentState.getLoginID(),file);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return re;
        }
        else if(CurrentState.getPosition()==Position.Salesman){
            ResultMessage re= null;
            try {
                re = RemoteHelper.getInstance().getServiceFactory().getSalesmanFileBusinessLogicService().delete(CurrentState.getLoginID(),file);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return re;
        }
        else{
            return null;
        }
    }

}
