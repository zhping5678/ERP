package businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic;

import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import data.myfiledata.StrategyDataController;
import dataservice.myfiledataservice.StrategyDataService;
import infor.ProductItem;
import po.filepo.PurchasePO;

import po.filepo.PurchaseReturnPO;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;
import po.warehousepo.CommodityPO;
import util.HibernateUtil;
import vo.filevo.PurchaseReturnVO;
import vo.filevo.PurchaseVO;
import vo.filevo.SaleReturnVO;
import vo.filevo.SaleVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

class TransSaleAndPurchaseVoPo {
    private WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    private StrategyDataService strategyDataService;

    TransSaleAndPurchaseVoPo() throws RemoteException{
        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
        strategyDataService=new StrategyDataController();
    }

    PurchaseVO PurchasePoToVo(PurchasePO pPO){
        ArrayList<String []> productItems=new ArrayList<>();//id(0)，name(1)，型号(2)，数量(3)，进货单价(4),单项总额(5)，备注(6)
        for(ProductItem p:pPO.getCommodityList()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            if(cpo!=null){
                productItems.add(new String[]{cpo.getID(),cpo.getName(),cpo.getSize(),String.valueOf(p.getNum()),String.valueOf(p.getPrice()),""+p.getNum()*p.getPrice(),p.getNote()});
            }
        }
        return new PurchaseVO(pPO.getID(),pPO.getFileType(),pPO.getOperator(),pPO.getClerk(),pPO.getNote(),pPO.getCreateTime(),
                pPO.getPassTime(),pPO.getState(), pPO.getClient(),pPO.getWarehouse(),productItems,pPO.getTotal(),new ArrayList<>(pPO.getInformation()));
    }

    PurchaseReturnVO PurchaseReturnPoToVo(PurchaseReturnPO pPO){
        ArrayList<String []> productItems=new ArrayList<>();//id(0)，name(1)，型号(2)，数量(3)，进货单价(4),单项总额(5)，备注(6)
        for(ProductItem p:pPO.getCommodityList()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            productItems.add(new String[]{cpo.getID(),cpo.getName(),cpo.getSize(),String.valueOf(p.getNum()),String.valueOf(p.getPrice()),""+p.getNum()*p.getPrice(),p.getNote()});
        }
        return new PurchaseReturnVO(pPO.getID(),pPO.getFileType(),pPO.getOperator(),pPO.getClerk(),pPO.getNote(),pPO.getCreateTime(),
                pPO.getPassTime(),pPO.getState(), pPO.getClient(),pPO.getWarehouse(),productItems,pPO.getTotal(),new ArrayList<>(pPO.getInformation()));
    }

    SaleVO SalePoToVo(SalePO salePO){
        ArrayList<String[]> products=new ArrayList<>();
        System.err.println(salePO.getCommodityList().size());
        for(ProductItem p:salePO.getCommodityList()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            if(cpo!=null){
                products.add(new String[]{cpo.getID(),cpo.getName(),cpo.getSize(),String.valueOf(p.getNum()),String.valueOf(p.getPrice()),""+p.getNum()*p.getPrice(),p.getNote()});

            }
        }
        ArrayList<String []> gifts=new ArrayList<>();
        HibernateUtil.getCurrentSession().beginTransaction();
        if(salePO.getGiftStrategy()!=null){
            for(ProductItem p:strategyDataService.readGiftStrategy(salePO.getGiftStrategy()).getGifts()){
                gifts.add(new String[]{p.getCommodityID(),p.getSize(),String.valueOf(p.getNum()),p.getNote()});
            }
        }
        return new SaleVO(salePO.getID(),salePO.getFileType(),salePO.getState(),salePO.getOperator(),salePO.getClerk(),salePO.getNote(),
                salePO.getCreateTime(),salePO.getPassTime(),salePO.getClient(),salePO.getWarehouse(),products, salePO.getGiftStrategy(),gifts,
                salePO.getDiscount(),salePO.getVoucherStrategy(),salePO.getPricePack(),salePO.getAllowance(),salePO.getVoucher(),salePO.getTotal(),
                new ArrayList<>(salePO.getInformation()));
    }

    SaleReturnVO SaleReturnPoToVo(SaleReturnPO srpo){
        ArrayList<String[]> products=new ArrayList<>();
        for(ProductItem p:srpo.getCommodityList()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            products.add(new String[]{cpo.getID(),cpo.getName(),cpo.getSize(),String.valueOf(p.getNum()),String.valueOf(p.getPrice()),""+p.getNum()*p.getPrice(),p.getNote()});
        }
        return new SaleReturnVO(srpo.getID(),srpo.getFileType(),srpo.getState(),srpo.getOperator(),srpo.getClerk(),srpo.getNote(),
                srpo.getCreateTime(),srpo.getPassTime(),srpo.getWarehouse(),srpo.getClient(),products,srpo.getTotal(),new ArrayList<>(srpo.getInformation()));
    }


}
