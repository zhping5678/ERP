package businesslogicservice.myfilebusinesslogicservice;

import infor.Infor;
import po.filepo.PurchaseReturnPO;
import vo.accountvo.AccountVO;
import vo.clientvo.ClientVO;
import vo.filevo.*;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ManagerBusinessLogicService extends Remote{
    void connectionTest() throws RemoteException;

    ResultMessage approveSalesman(String useID, String fileID, boolean result, Infor infor) throws RemoteException;

    ResultMessage approveWarehouseman(String userID, String fileID, boolean result, Infor infor) throws RemoteException;

    ResultMessage approveAccountMan(String userID,String fileID,boolean result,Infor infor) throws RemoteException;

    ArrayList<CommodityVO> showCommodityList() throws RemoteException;

    ArrayList<ClientVO> showSeller() throws RemoteException;
    ArrayList<ClientVO> showSupplier() throws RemoteException;
    ArrayList<ClientVO> showBothClient() throws RemoteException;

    ArrayList<AccountVO> showAccount() throws RemoteException;

    ArrayList<String> showWaitReview() throws RemoteException;

    //报溢单报损单公用
    ExcessVO showExcess(String id) throws RemoteException;

    ReceiptVO showReceipt(String id) throws RemoteException;

    PaymentVO showPayment(String id) throws RemoteException;

    PurchaseVO showPurchase(String id) throws RemoteException;

    PurchaseReturnVO showPurchaseReturn(String id) throws RemoteException;

    SaleVO showSale(String id) throws RemoteException;

    SaleReturnVO showSaleReturn(String id) throws RemoteException;

}
