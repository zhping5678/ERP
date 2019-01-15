package businesslogicservice.myfilebusinesslogicservice;

import infor.BusinessConditions;
import vo.filevo.PaymentVO;
import vo.filevo.ReceiptVO;
import vo.utilityvo.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountManBusinessLogicService extends Remote{
    void connectionTest() throws RemoteException;

    //收款单的增删查改
    String addReceipt(String userID) throws RemoteException;
    ResultMessage delReceipte(String userID, String ID) throws RemoteException;
    ResultMessage recoverReceipt(String userID, String ID) throws RemoteException;
    ResultMessage modReceipt(String userID, ReceiptVO receiptVO) throws RemoteException;
    ReceiptVO findReceipt(String receiptID) throws RemoteException;
    ResultMessage commitReceipt(String userID, String ID) throws RemoteException;

    //付款单的增删查改
    String addPayment(String userID, int tag) throws RemoteException;
    ResultMessage delPayment(String userID, String ID) throws RemoteException;
    ResultMessage recoverPayment(String userID, String ID) throws RemoteException;
    ResultMessage modPayment(String userID, PaymentVO paymentVO) throws RemoteException;
    PaymentVO findPayment(String receiptID) throws RemoteException;
    ResultMessage commitPayment(String userID, String ID) throws RemoteException;

    ArrayList<String> showTrash() throws RemoteException;
    ArrayList<String> showDraft() throws RemoteException;
    ArrayList<String> showWaitReview() throws RemoteException;
    ArrayList<String> showReviewed() throws RemoteException;

    public BusinessConditions showBusinessConditions(String startTime, String endTime) throws RemoteException;

}