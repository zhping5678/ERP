package dataservice.myfiledataservice;

import po.filepo.*;
import vo.warehousevo.CheckInventoryCommodityVO;
import vo.warehousevo.CommodityVO;

import java.util.ArrayList;
import java.util.Date;

public interface WarehouseManDataService {
    void writeExcess(ExcessPO excessPO);

    ExcessPO readExcess(String fileID);

    void updateExcess(ExcessPO excessPO);

    void removeExcess(String fileID);

    ArrayList showList();

    ArrayList<ExcessPO> searchExcessByDate(String startTime,String endTime);

    ArrayList<PaymentPO> searchPaymentByDate(String startTime,String endTime);

    ArrayList<ReceiptPO> searchReceiptByDate(String startTime, String endTime);

    ArrayList<PurchasePO> searchPurchaseByDate(String startTime, String endTime);

    ArrayList<SalePO> searchSaleByDate(String startTime,String endTime);

    ArrayList<SaleReturnPO> searchSaleReturnByDate(String startTime,String endTime);

}
