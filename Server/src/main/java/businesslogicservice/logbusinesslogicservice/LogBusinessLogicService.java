package businesslogicservice.logbusinesslogicservice;

import vo.logvo.LogVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LogBusinessLogicService extends Remote {

    void connectionTest() throws RemoteException;
    ArrayList<LogVO> LogView() throws RemoteException;
    ArrayList<LogVO> LogSearch(String keywords) throws RemoteException;

}
