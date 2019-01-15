package MockObject;

import Start.RemoteHelper;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityTypeVO;
import vo.warehousevo.CommodityVO;
import vo.warehousevo.WarehouseVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Warehousebl {
    public int times=0;
    //库存查看
    public ArrayList<String[]> checkInventory(String time, String inventory){
        /*
        CommodityVO p1=new CommodityVO(0,"001","pro001","big", 0,0,0,0,0,true,true,true);
        CommodityVO p2=new CommodityVO(0,"002","pro002","small", 0,0,0,0,0,true,true,true);
        CommodityVO[] p=new CommodityVO[2];
        p[0]=p1;
        p[1]=p2;
        */
        String[] s1={"2018.01.01","Philips","入库","20","-40","60","NJ/lamp/Philips"};
        String[] s2={"2018.01.12","NANA","出库","40","-20","50","NJ/lamp/NANA"};
        String[] s3={"2018.01.12","SmallRed","出库","40","-20","50","NJ/RedLamp/SmallRed"};
        String[] s4={"2018.01.13","Lamp","出库","40","-20","50","NJ/lamp/lamp"};
        String[] s5={"2018.01.13","ABC","出库","40","-20","50","NJ/RedLamp/ABC"};
        String[] s6={"2018.01.13","Ye","出库","40","-20","50","NJ/lamp/Ye"};
        ArrayList<String[]> array=new ArrayList<String[]>();
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);

        return array;
    }

    public ArrayList<String[]> calculateInventory(String time, String inventory){
        String[] s1={"Philips","入库300","出库400","-100","-600","+1200","+600","600","NJ.lamp.new.Philips"};
        String[] s2={"Danaps","入库300","出库400","-100","-600","+1200","+600","600","NJ.lamp.new.Philips"};

        ArrayList<String[]> array=new ArrayList<String[]>();
        array.add(s1);
        array.add(s2);

        return array;
    }

    public boolean checkTime(String time){
        String[] temp=time.split("  ");
        if((temp[0].length()==10)&&(temp[1].length()==10)){
            String[] Time1=temp[0].split("-");
            String[] Time2=temp[1].split("-");
            for(int i=0;i<3;i++){
                if(Integer.parseInt(Time1[i])>Integer.parseInt(Time2[i])){
                    return false;
                }
                else if(Integer.parseInt(Time1[i])<Integer.parseInt(Time2[i])){
                    return true;
                }
                else{
                    continue;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    //库存盘点
    public ArrayList<String[]> makeInventory(){
        ArrayList<String[]> result=null;
        try {
            result=RemoteHelper.getInstance().getServiceFactory().getWarehouseManBusinessLogicService().stocking();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
    }


    //新接口：
    public ArrayList<String> getOnlyWarehouse(){
        ArrayList<WarehouseVO> array=null;
        try {
            array= RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().getOnlyWarehouse();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ArrayList<String> result=new ArrayList<>();
        for(int i=0;i<array.size();i++){
            result.add(array.get(i).getID());
        }
        return result;
    }

    public boolean isCommodity(String path){
        boolean isCommodity=false;
        try {
            isCommodity=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().isCommodity(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isCommodity;
    }

    public static int t=0;
    //判断该结点下有没有商品
    public boolean hasCommodity(String path){
        boolean hasCommodity=false;
        try {
            hasCommodity=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().hasCommodity(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hasCommodity;
    }

    //判断该节点下有没有商品分类
    public boolean hasCommodityType(String path){
        boolean hasCommodityType=false;
        try {
            hasCommodityType=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().hasCommodityType(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hasCommodityType;
    }

    public CommodityVO getCommodity(String path){
        CommodityVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().getCommodity(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    //建立树形结构
    //判断有没有孩子
    public boolean hasChildren(String str){
        boolean hasChildren=false;
        try {
            hasChildren=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().hasChildren(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return hasChildren;
    }

    public String[] getChildren(String str){
       ArrayList<String> children=null;
        try {
            children=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().getChildren(str);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        String[] result=new String[children.size()];
        for(int i=0;i<children.size();i++){
            result[i]=children.get(i);
        }
        return result;
    }

    public ResultMessage newWarehouse(String userID,WarehouseVO vo) {
        ResultMessage message = null;
        try {
            message = RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().newWarehouse(userID, vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage addNewCommodityID(String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().addNewCommodityID(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage newCommodity(String userID,CommodityVO vo){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().newCommodity(userID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage addNewCommodityTypeID(String path){
        ResultMessage message=null;
        try {
            System.out.println(path);
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().addNewCommodityTypeID(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage newCommodityType(String userID,CommodityTypeVO vo){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().newCommodityType(userID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage updateCommodity(String userID,String oldID,CommodityVO vo){
        ResultMessage message= null;
        try {
            message = RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().updateCommodity(userID,oldID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage updateCommodityType(String userID,String oldID,CommodityTypeVO vo){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().updateCommodityType(userID,oldID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage updateWarehouse(String userID,String oldID,WarehouseVO vo){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().updateWarehouse(userID,oldID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage modifyCommodityLocation(String userID,String oldID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().modifyCommodityLocation(userID,oldID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage modifyCommodityTypeLocation(String userID,String oldID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().modifyCommodityTypeLocation(userID,oldID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage deleteCommodity(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().deleteCommodity(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage deleteCommodityType(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().deleteCommodityType(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }
    public ResultMessage deleteWarehouse(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().deleteWarehouse(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage banCommodity(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().banCommodity(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage banCommodityType(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().banCommodityType(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage banWarehouse(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().banWarehouse(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage recoverCommodity(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().recoverCommodity(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;

    }

    public ResultMessage recoverCommodityType(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().recoverCommodityType(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public ResultMessage recoverWarehouse(String userID,String path){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().recoverWarehouse(userID,path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean isBanedCommodity(String path){
        boolean isBan=false;
        try {
            isBan=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().isBanedCommodity(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isBan;
    }

    public boolean isBanedCommodityType(String path){
        boolean isBan=false;
        try {
            isBan=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().isBanedCommodityType(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isBan;
    }

    public boolean isBanedWarehouse(String path){
        boolean isban=false;
        try {
            isban=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().isBanedWarehouse(path);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return isban;
    }

    //模糊查找 返回两个Arr,一个Arr为未被禁用的商品，一个Arr为已被禁用的商品
    public ArrayList<ArrayList<String>> findByKeyWords(String keyWords) {
        ArrayList<ArrayList<String>> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getWarehouseBusinessLogicService().findByKeyWords(keyWords);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }




}
