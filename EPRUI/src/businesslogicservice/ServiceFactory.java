package businesslogicservice;

import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;
import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
import businesslogicservice.filebusinesslogicservice.FileBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.*;
import businesslogicservice.myfilebusinesslogicservice.AccountManBusinessLogicService;
import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface  ServiceFactory extends Remote{

    public AccountBookBusinessLogicService getAccountBookBusinessLogicService() throws RemoteException;
    public AccountBusinessLogicService getAccountBusinessLogicService() throws  RemoteException;
    public ClientBusinessLogicService getClientBusinessLogicService() throws RemoteException;
    //查看他人的单据
    public FileBusinessLogicService getFileBusinessLogicService() throws RemoteException;

    public LogBusinessLogicService getLogBusinessLogicService() throws RemoteException;
    public MessageBusinessLogicService getMessageBusinessLogicService() throws RemoteException;
    public UserBusinessLogicService getUserBusinessLogicService() throws RemoteException;

//    public CommodityBusinessLogicService getCommodityBusinessLogicService() throws RemoteException;
//    public CommodityTypeBusinessLogicService getCommodityTypeBusinessLogicService() throws RemoteException;
    public WarehouseBusinessLogicService getWarehouseBusinessLogicService() throws RemoteException;
    //各种单据的制定
    public ManagerBusinessLogicService getManagerBusinessLogicService() throws RemoteException;
    public AccountManBusinessLogicService getAccountManBusinessLogicService() throws RemoteException;
    public WarehouseManBusinessLogicService getWarehouseManBusinessLogicService() throws RemoteException;
    public StrategyBusinessLogicService getStrategyBusinessLogicService() throws RemoteException;
    public SalesmanFileBusinessLogicService getSalesmanFileBusinessLogicService() throws RemoteException;

}
