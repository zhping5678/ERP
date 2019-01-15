package businesslogic.myfilebusinesslogic.managerfilebusinesslogic;

import businesslogic.accountbusinesslogic.AccountBusinessLogicController;
import businesslogic.accountbusinesslogic.AccountBusinessLogicControllerAccess;
import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
import businesslogic.clientbusinesslogic.ClientBusinessLogicControllerAccess;
import businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic.AccountManBusinessLogicController;
import businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic.AccountManBusinessLogicControllerAccess;
import businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic.SalesmanFileBusinessLogicAccess;
import businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic.SalesmanFileBusinessLogicController;
import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicController;
import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicControllerAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.myfilebusinesslogicservice.ManagerBusinessLogicService;
import infor.Infor;
import vo.accountvo.AccountVO;
import vo.clientvo.ClientVO;
import vo.filevo.*;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ManagerBusinessLogicController extends UnicastRemoteObject implements ManagerBusinessLogicService {

    AccountManBusinessLogicControllerAccess accountManBusinessLogicControllerAccess;
    WarehouseManBusinessLogicControllerAccess warehouseManBusinessLogicControllerAccess;
    SalesmanFileBusinessLogicAccess salesmanFileBusinessLogicAccess;
    WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    ClientBusinessLogicControllerAccess clientBusinessLogicControllerAccess;
    AccountBusinessLogicControllerAccess accountBusinessLogicControllerAccess;
    public ManagerBusinessLogicController() throws RemoteException{
        accountManBusinessLogicControllerAccess=new AccountManBusinessLogicController();
        warehouseManBusinessLogicControllerAccess=new WarehouseManBusinessLogicController();
        salesmanFileBusinessLogicAccess=new SalesmanFileBusinessLogicController();

        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
        clientBusinessLogicControllerAccess=new ClientBusinessLogicController();
        accountBusinessLogicControllerAccess=new AccountBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.println("ManagerBusinessLogicService is connected.");
    }


    @Override
    public ResultMessage approveWarehouseman(String userID, String fileID,boolean result,Infor infor) throws RemoteException {
        warehouseManBusinessLogicControllerAccess.approveExcessOrLoss(userID,fileID,result,infor);
        return ResultMessage.ReviewSuccess;
    }

    @Override
    public ResultMessage approveAccountMan(String userID, String fileID, boolean result, Infor infor) throws RemoteException {
        String type=fileID.substring(0,2);
        if(type.equals("rc")){
            accountManBusinessLogicControllerAccess.approveReceipt(userID,fileID,result,infor);
        }else{
            accountManBusinessLogicControllerAccess.approvePayment(userID,fileID,result,infor);
        }
        return ResultMessage.ReviewSuccess;
    }

    @Override
    public ResultMessage approveSalesman(String useID, String fileID, boolean result, Infor infor) throws RemoteException {
        String type=fileID.substring(0,2);
        switch (type){
            case "sa":
                salesmanFileBusinessLogicAccess.approvalSale(useID,fileID,result,infor);
            case "sr":
                salesmanFileBusinessLogicAccess.approvalSaleReturn(useID,fileID,result,infor);
            case "pc":
                salesmanFileBusinessLogicAccess.approvalPurchase(useID,fileID,result,infor);
            case "pr":
                salesmanFileBusinessLogicAccess.approvalPurchaseReturn(useID,fileID,result,infor);
        }
        return ResultMessage.ReviewSuccess;
    }

    @Override
    public ArrayList<CommodityVO> showCommodityList() throws RemoteException {
        return warehouseBusinessLogicAccess.listCommodityVO();

    }

    @Override
    public ArrayList<ClientVO> showSeller() throws RemoteException {
        return clientBusinessLogicControllerAccess.listSaler();
    }

    @Override
    public ArrayList<ClientVO> showSupplier() throws RemoteException {
        return clientBusinessLogicControllerAccess.listSupplier();
    }

    @Override
    public ArrayList<ClientVO> showBothClient() throws RemoteException {
        return clientBusinessLogicControllerAccess.listBoth();
    }

    @Override
    public ArrayList<AccountVO> showAccount() throws RemoteException {
        return accountBusinessLogicControllerAccess.listAccountVO();
    }

    @Override
    public ArrayList<String> showWaitReview() throws RemoteException{
        ArrayList<String> res=new ArrayList<>();
        ArrayList<String> a=accountManBusinessLogicControllerAccess.WaitReview();
        ArrayList<String> s=salesmanFileBusinessLogicAccess.waitReviewed();
        ArrayList<String> w=warehouseManBusinessLogicControllerAccess.waitReview();
        for(int i=0;i<a.size();i++){
            res.add(a.get(i));
        }
        for(int i=0;i<s.size();i++){
            res.add(s.get(i));
        }
        for(int i=0;i<w.size();i++){
            res.add(w.get(i));
        }
        return res;
    }


    public ExcessVO showExcess(String id){
        ExcessVO excessVO=warehouseManBusinessLogicControllerAccess.showExcessDetail(id);
        return excessVO;
    }

    @Override
    public ReceiptVO showReceipt(String id) throws RemoteException {
        ReceiptVO receiptVO=accountManBusinessLogicControllerAccess.showReceiptDetail(id);
        return receiptVO;
    }

    @Override
    public PaymentVO showPayment(String id) throws RemoteException {
        PaymentVO paymentVO=accountManBusinessLogicControllerAccess.showPaymentDetail(id);
        return paymentVO;
    }

    @Override
    public PurchaseVO showPurchase(String id) throws RemoteException {
        PurchaseVO purchaseVO=salesmanFileBusinessLogicAccess.showPurchaseDetail(id);
        return purchaseVO;
    }

    @Override
    public PurchaseReturnVO showPurchaseReturn(String id) throws RemoteException {
        PurchaseReturnVO purchaseReturnVO=salesmanFileBusinessLogicAccess.showPurchaseReturnDetail(id);
        return purchaseReturnVO;
    }

    @Override
    public SaleVO showSale(String id) throws RemoteException {
        SaleVO saleVO=salesmanFileBusinessLogicAccess.showSaleDetail(id);
        return saleVO;
    }

    @Override
    public SaleReturnVO showSaleReturn(String id) throws RemoteException {
        SaleReturnVO saleReturnVO=salesmanFileBusinessLogicAccess.showSaleReturnDetail(id);
        return saleReturnVO;
    }


}
