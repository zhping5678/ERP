package businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic;

import businesslogic.ServiceFactoryImpl;
import businesslogicservice.ServiceFactory;
import businesslogicservice.myfilebusinesslogicservice.WarehouseManBusinessLogicService;
import data.myfiledata.SalesmanDataController;
import data.myfiledata.WarehouseManDataController;
import dataservice.myfiledataservice.SalesmanDataService;
import dataservice.myfiledataservice.WarehouseManDataService;
import infor.CommodityItem;
import infor.Infor;
import vo.filevo.ExcessVO;
import vo.warehousevo.CheckInventoryCommodityVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public class Excess_Driver {
    public static void main(String args[]) throws RemoteException{
        WarehouseManBusinessLogicService aa= ServiceFactoryImpl.getInstance().getWarehouseManBusinessLogicService();
        WarehouseManBusinessLogicService a=new WarehouseManBusinessLogicController();
//        aa.addExcessOrLoss("Jane",1);
//        a.delExcessOrLoss("Jane","ec-20180105-9");
//        System.out.println(a.findExcessOrLoss("Jane","ec-20180105-11").getExcessID());

//        ArrayList<Infor> information=new ArrayList<>();
//        Infor infor1=new Infor();
//        infor1.setCheckerID("Paul");
//        infor1.setCheckerTime("xxxx-xxxx-xxxx");
//        infor1.setRemark("");
//        infor1.setResult("approve");
//        information.add(infor1);
//        ArrayList<CommodityItem> commodityItems=new ArrayList<>();
//        CommodityItem commodityItem1=new CommodityItem("lamp","lamp-a","xx",100,10,110);
//        commodityItems.add(commodityItem1);
//        ExcessVO excessVO=new ExcessVO("","ls-20180105-7","","NJU",commodityItems,1000,10000.0,information);
//        a.modExcessOrLoss("Jane",excessVO);
//        a.commit("Jane","ec-20180105-11");
//        ArrayList<String> array=a.showWaitReview();
//        for(String x:array){
//            System.out.println(x);
//        }


        String startTime="2018-01-05";
        String endTime="2018-01-06";
        ArrayList<ArrayList<CheckInventoryCommodityVO>> test=a.checkInventory(startTime,endTime);
        for(ArrayList<CheckInventoryCommodityVO> x:test){
            for(CheckInventoryCommodityVO vo:x){
                System.out.println(vo.getCommodityID()+"        AAAAAAAAAAAAA");
            }
        }


    }
}
