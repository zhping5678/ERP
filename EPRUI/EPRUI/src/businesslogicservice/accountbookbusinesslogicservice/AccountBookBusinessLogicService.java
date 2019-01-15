package businesslogicservice.accountbookbusinesslogicservice;

import infor.AccountBookInfor;
import vo.utilityvo.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountBookBusinessLogicService extends Remote {

    void connectionTest() throws RemoteException;
    ArrayList<String> accountIniList() throws RemoteException;
    AccountBookInfor read(String ID) throws RemoteException;
    ResultMessage accountBegin(String userID) throws RemoteException;
    ResultMessage accountStop(String userID) throws RemoteException;


}
