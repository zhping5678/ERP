package businesslogicservice.myfilebusinesslogicservice;

import vo.filevo.ExcessVO;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CheckInventoryCommodityVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface WarehouseManBusinessLogicService extends Remote {
    void connectionTest() throws RemoteException;

    String addExcessOrLoss(String userID, int tag) throws RemoteException;

    ResultMessage delExcessOrLoss(String userID, String fileID) throws RemoteException;

    ResultMessage modExcessOrLoss(String userID, ExcessVO excessVO) throws RemoteException;

    ExcessVO findExcessOrLoss(String userID, String fileID) throws RemoteException;

    ArrayList<String> showTrash() throws RemoteException;

    ArrayList<String> showDraft() throws RemoteException;

    ArrayList<String> showWaitReview() throws RemoteException;

    ArrayList<String> showReviewed() throws RemoteException;

    ResultMessage commit(String userID, String fileID) throws RemoteException;

    ArrayList<ArrayList<CheckInventoryCommodityVO>> checkInventory(String startTime, String endTime) throws RemoteException;
//    ArrayList<String> test(String startTime,String endTime) throws RemoteException;

    ArrayList<String[]> stocking() throws RemoteException;
}
