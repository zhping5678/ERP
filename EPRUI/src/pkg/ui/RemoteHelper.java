package pkg.ui;


import businesslogicservice.ServiceFactory;
import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;
import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
import businesslogicservice.filebusinesslogicservice.FileBusinessLogicService;
import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.AccountManBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.ManagerBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.SalesmanFileBusinessLogicService;
import businesslogicservice.myfilebusinesslogicservice.StrategyBusinessLogicService;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class RemoteHelper {
    private static String serverIp="172.28.134.219";
    private static ServiceFactory serviceFactory;
    private static ServiceFactory getServiceFactory(){
        if (serviceFactory==null){
            try{
                serviceFactory=(ServiceFactory) Naming.lookup("rmi://"+serverIp+":6666/ServiceFactory");
                System.out.println("serviceFactory is connected;");
            }catch(NotBoundException e){
                e.printStackTrace();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }
        return serviceFactory;
    }
    public static void main(String[]args) throws RemoteException{
        getServiceFactory();
        getAccountBookBusinessLogicService().connectionTest();
    }
    public static AccountBookBusinessLogicService getAccountBookBusinessLogicService() throws RemoteException{
        /*
        AccountBookBusinessLogicService accountBookBusinessLogicService=null;
        try{
            accountBookBusinessLogicService=getServiceFactory().getAccountBookBusinessLogicService();
        }catch(RemoteException e){
        }
        return accountBookBusinessLogicService;
        */
        return getServiceFactory().getAccountBookBusinessLogicService();
    }
    public static AccountBusinessLogicService getAccountBusinessLogicService() throws  RemoteException{
        return getServiceFactory().getAccountBusinessLogicService();
    }

    public static ClientBusinessLogicService getClientBusinessLogicService() throws RemoteException{
        return getServiceFactory().getClientBusinessLogicService();
    };
    //查看他人的单据
    public static FileBusinessLogicService getFileBusinessLogicService() throws RemoteException{
        return getServiceFactory().getFileBusinessLogicService();

    };
    public static LogBusinessLogicService getLogBusinessLogicService() throws RemoteException{
        return getLogBusinessLogicService();
    }
    public static MessageBusinessLogicService getMessageBusinessLogicService() throws RemoteException{
        return getServiceFactory().getMessageBusinessLogicService();
    }
    //各种单据的制定
    public static StrategyBusinessLogicService getStrategyBusinessLogicService() throws RemoteException{
        return getServiceFactory().getStrategyBusinessLogicService();
    }
    public static SalesmanFileBusinessLogicService getSalesmanFileBusinessLogicService() throws RemoteException{
        return getServiceFactory().getSalesmanFileBusinessLogicService();
    }

    public static UserBusinessLogicService getUserBusinessLogicService() throws RemoteException {
        return getServiceFactory().getUserBusinessLogicService();
    }
    public static WarehouseBusinessLogicService getWarehouseBusinessLogicService() throws RemoteException{
        return getServiceFactory().getWarehouseBusinessLogicService();
    }
    /*
    public static ManagerBusinessLogicService getManagerBusinessLogicService()throws RemoteException{
        return getServiceFactory().getManagerBusinessLogicService();
    }
    */
    public static AccountManBusinessLogicService getAccountManBusinessLogicService() throws RemoteException{
        return getServiceFactory().getAccountManBusinessLogicService();
    }
    public static ManagerBusinessLogicService getManagerBusinessLogicService()throws RemoteException{
        return getServiceFactory().getManagerBusinessLogicService();
    }



}
