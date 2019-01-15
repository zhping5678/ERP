package businesslogic;

import businesslogic.accountbookbusinesslogic.AccountBookBusinessLogicController;
import businesslogic.accountbusinesslogic.AccountBusinessLogicController;
import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
import businesslogic.filebusinesslogic.FileBusinessLogicController;
import businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic.AccountManBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.myfilebusinesslogic.managerfilebusinesslogic.ManagerBusinessLogicController;
import businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic.SalesmanFileBusinessLogicController;
import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicController;
import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicController;
import businesslogic.userbusinesslogic.UserBusinessLogicController;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.ServiceFactory;
import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;
import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
import businesslogicservice.filebusinesslogicservice.FileBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.*;
import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {

    private AccountBookBusinessLogicService accountBookBusinessLogicService;
    private AccountBusinessLogicService accountBusinessLogicService;
    private ClientBusinessLogicService clientBusinessLogicService;
    //有待修改file
    private FileBusinessLogicService fileBusinessLogicService;

    private LogBusinessLogicService logBusinessLogicService;
    private MessageBusinessLogicService messageBusinessLogicService;


    private UserBusinessLogicService userBusinessLogicService;
    //库存和商品管理
//    private CommodityBusinessLogicService commodityBusinessLogicService;
//    private CommodityTypeBusinessLogicService commodityTypeBusinessLogicService;
    private WarehouseBusinessLogicService warehouseBusinessLogicService;

    //MyFile中各种单据文件的接口
    private StrategyBusinessLogicService strategyBusinessLogicService;
    private SalesmanFileBusinessLogicService salesmanFileBusinessLogicService;
    private AccountManBusinessLogicService accountManBusinessLogicService;
    private WarehouseManBusinessLogicService warehouseManBusinessLogicService;
    private ManagerBusinessLogicService managerBusinessLogicService;

    private static ServiceFactoryImpl instance;

    private ServiceFactoryImpl() throws RemoteException {
    }

    public static ServiceFactoryImpl getInstance() throws RemoteException{
        if(instance==null){
            instance=new ServiceFactoryImpl();
        }
        System.out.println("Running");
        return instance;
    }

    @Override
    public AccountBookBusinessLogicService getAccountBookBusinessLogicService() throws RemoteException {
        if(accountBookBusinessLogicService==null){
            accountBookBusinessLogicService=new AccountBookBusinessLogicController();
        }
        return accountBookBusinessLogicService;
    }

    @Override
    public AccountBusinessLogicService getAccountBusinessLogicService() throws RemoteException {
        if(accountBusinessLogicService==null){
            accountBusinessLogicService=new AccountBusinessLogicController();
        }
        return accountBusinessLogicService;
    }

    @Override
    public ClientBusinessLogicService getClientBusinessLogicService() throws RemoteException {
        if(clientBusinessLogicService==null){
            clientBusinessLogicService=new ClientBusinessLogicController();
        }
        return clientBusinessLogicService;
    }

    @Override
    public FileBusinessLogicService getFileBusinessLogicService() throws RemoteException {
        if(fileBusinessLogicService==null){
            fileBusinessLogicService=new FileBusinessLogicController();
        }
        return fileBusinessLogicService;
    }

    @Override
    public LogBusinessLogicService getLogBusinessLogicService() throws RemoteException {
        if(logBusinessLogicService==null){
            logBusinessLogicService=new LogBusinessLogicController();
        }
        return logBusinessLogicService;
    }

    @Override
    public MessageBusinessLogicService getMessageBusinessLogicService() throws RemoteException {
        if(messageBusinessLogicService==null){
            messageBusinessLogicService=new MessageBusinessLogicController();
        }
        return messageBusinessLogicService;
    }

    @Override
    public StrategyBusinessLogicService getStrategyBusinessLogicService() throws RemoteException {
        if(strategyBusinessLogicService==null){
            strategyBusinessLogicService=new StrategyBusinessLogicController();
        }
        return strategyBusinessLogicService;
    }

    @Override
    public SalesmanFileBusinessLogicService getSalesmanFileBusinessLogicService() throws RemoteException {
        if(salesmanFileBusinessLogicService==null){
            salesmanFileBusinessLogicService=new SalesmanFileBusinessLogicController();
        }
        System.out.println("XXXXXXXXXX");
        return salesmanFileBusinessLogicService;
    }

    @Override
    public UserBusinessLogicService getUserBusinessLogicService() throws RemoteException {
        if (userBusinessLogicService==null){
            userBusinessLogicService=new UserBusinessLogicController();
        }
        return userBusinessLogicService;
    }

//    @Override
//    public CommodityBusinessLogicService getCommodityBusinessLogicService() throws RemoteException {
//        if(commodityBusinessLogicService==null){
//            commodityBusinessLogicService=new CommodityBusinessLogicController();
//        }
//        return commodityBusinessLogicService;
//    }

//    @Override
//    public CommodityTypeBusinessLogicService getCommodityTypeBusinessLogicService() throws RemoteException {
//        if(commodityTypeBusinessLogicService==null){
//            commodityTypeBusinessLogicService=new CommodityTypeBusinessLogicController();
//        }
//        return commodityTypeBusinessLogicService;
//    }

    @Override
    public WarehouseBusinessLogicService getWarehouseBusinessLogicService() throws RemoteException {
        if(warehouseBusinessLogicService==null){
            warehouseBusinessLogicService=new WarehouseBusinessLogicController();
        }
        return warehouseBusinessLogicService;
    }

    @Override
    public ManagerBusinessLogicService getManagerBusinessLogicService() throws RemoteException {
        if(managerBusinessLogicService==null){
            managerBusinessLogicService=new ManagerBusinessLogicController();
        }
        return managerBusinessLogicService;
    }

    @Override
    public AccountManBusinessLogicService getAccountManBusinessLogicService() throws RemoteException {
        if(accountManBusinessLogicService==null){
            accountManBusinessLogicService=new AccountManBusinessLogicController();
        }
        return accountManBusinessLogicService;
    }

    @Override
    public WarehouseManBusinessLogicService getWarehouseManBusinessLogicService() throws RemoteException {
        if(warehouseManBusinessLogicService==null){
            System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            warehouseManBusinessLogicService=new WarehouseManBusinessLogicController();
        }
        return warehouseManBusinessLogicService;
    }




}
