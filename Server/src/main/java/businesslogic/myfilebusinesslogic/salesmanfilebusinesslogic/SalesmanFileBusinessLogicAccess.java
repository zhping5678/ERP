package businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic;

import infor.Infor;
import po.filepo.PurchasePO;
import po.filepo.PurchaseReturnPO;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;
import vo.filevo.PurchaseReturnVO;
import vo.filevo.PurchaseVO;
import vo.filevo.SaleReturnVO;
import vo.filevo.SaleVO;

import java.util.ArrayList;

public interface SalesmanFileBusinessLogicAccess {
    //总经理审批单据
    void approvalSale(String userID, String saleID, boolean toApprove, Infor infor);
    void approvalSaleReturn(String userID,String saleReturnID,boolean toApprove,Infor infor);
    void approvalPurchase(String userID,String purchaseID,boolean toApprove,Infor infor);
    void approvalPurchaseReturn(String userID,String purchaseReturnID,boolean toApprove,Infor infor);
    ArrayList<String> waitReviewed();

    SaleVO showSaleDetail(String id);
    SaleReturnVO showSaleReturnDetail(String id);
    PurchaseVO showPurchaseDetail(String id);
    PurchaseReturnVO showPurchaseReturnDetail(String id);
}
