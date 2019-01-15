package businesslogic.warehousebusinesslogic;

import vo.warehousevo.CommodityTypeVO;
import vo.warehousevo.CommodityVO;
import vo.warehousevo.WarehouseVO;

import java.rmi.RemoteException;

public class Warehouse_Driver {
    public static void main(String args[]) throws RemoteException{
        WarehouseBusinessLogicController a=new WarehouseBusinessLogicController();
        CommodityVO vo1=new CommodityVO("NJU/lamp/blueLamp/1","lamp","XXL",5000,10.0,10.0,9.0,9.0,1000,false);
        CommodityVO vo2=new CommodityVO("NJU/lamp/blueLamp/2","lamp","XXL",5000,10.0,10.0,9.0,9.0,1000,false);
        CommodityVO vo3=new CommodityVO("ZJU/sun/1","lamp","XL",5000,10.0,10.0,9.0,9.0,1000,false);
        CommodityVO vo4=new CommodityVO("NJU/sun/2","lamp","XXL",5000,10.0,10.0,9.0,9.0,1000,false);

        CommodityTypeVO commodityTypeVO1=new CommodityTypeVO("NJU/lamp/blueLamp","lamp",false);
        CommodityTypeVO commodityTypeVO2=new CommodityTypeVO("NJU/lamb","lamp",false);
        CommodityTypeVO commodityTypeVO3=new CommodityTypeVO("NJU/www","",false);

        WarehouseVO warehouseVO=new WarehouseVO("ZJU","",false);

//        a.addNewWarehouseID("NJU");
//        a.addNewCommodityTypeID("NJU/lamp");
//        a.addNewCommodityTypeID("NJU/lamp/redLamp");
//        a.addNewCommodityTypeID("NJU/lamp/blueLamp");
//        a.addNewCommodityTypeID("NJU/sun");
//        a.addNewCommodityID("NJU/sun/a");
//        a.addNewCommodityID("NJU/sun/b");
//        a.deleteCommodityType("Jane","NJU/sun");
//        a.deleteCommodityType("Jane","NJU/lamp");
//        a.deleteWarehouse("Jane","NJU");
//        System.out.println(a.hasChildren("NJU/lamp/blueLamp"));
//        a.modifyCommodityLocation("Jane","NJU/lamp/redLamp/a","NJU/sun/a");
//        a.newWarehouse("Jane",warehouseVO);
//        a.newCommodityType("Jane",commodityTypeVO1);
//        a.newCommodityType("Jane",commodityTypeVO2);
//        a.newCommodityType("Jane",commodityTypeVO3);

//        a.newCommodity("Jane",vo1);
//        a.newCommodity("Jane",vo2);
//        a.newCommodity("Jane",vo3);
//        a.newCommodity("Jane",vo4);
//        a.updateCommodity("Jane","ZJU/sun/1",vo3);
//        a.updateWarehouse("Jane","ZJU",warehouseVO);
//        a.updateWarehouse("Jane","NJU",warehouseVO);
//        a.modifyCommodityTypeLocation("Jane","ZJU/lamp","NJU/lamp");
//        a.banCommodity("Paul","ZJU/sun/1");
//        a.recoverCommodity("Paul","ZJU/sun/1");
//        a.banCommodityType("Jane","ZJU/sun");
//        a.recoverCommodityType("Jane","ZJU/sun");
//        a.banWarehouse("Jane","NJU");
//        a.recoverWarehouse("Jane","NJU");
//        a.modifyAmountOfInventory("Jane","ZJU/sun/1",-4500);

    }
}
