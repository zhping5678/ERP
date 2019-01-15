package dataservice.myfiledataservice;

import po.filepo.PurchasePO;
import po.filepo.PurchaseReturnPO;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;

import java.util.ArrayList;

public interface SalesmanDataService {
    //进货单的数据库操作
    ArrayList<PurchasePO> listPurchase();
    void writePurchase(PurchasePO purchasePO);
    PurchasePO readPurchase(String id);
    void removePurchase(String id);
    void updatePurchase(PurchasePO purchasePO);

    //进货退货单的数据库操作
    ArrayList<PurchaseReturnPO> listPurchaseReturn();
    void writePurchaseReturn(PurchaseReturnPO purchaseReturnPO);
    PurchaseReturnPO readPurchaseReturn(String id);
    void removePurchaseReturn(String id);
    void updatePurchaseReturn(PurchaseReturnPO purchaseReturnPO);

    //销售单
    ArrayList<SalePO> listSale();
    void writeSale(SalePO salePO);
    SalePO readSale(String id);
    void removeSale(String id);
    void updateSale(SalePO salePO);

    //销售退货单
    ArrayList<SaleReturnPO> listSaleReturn();
    void writeSaleReturn(SaleReturnPO saleReturnPO);
    SaleReturnPO readSaleReturn(String id);
    void removeSaleReturn(String id);
    void updateSaleReturn(SaleReturnPO saleReturn);


 }
