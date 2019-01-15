package server;

import businesslogic.ServiceFactoryImpl;
//import businesslogic.accountbookbusinesslogic.AccountBookBusinessLogicController;
//import businesslogic.accountbusinesslogic.AccountBusinessLogicController;
//import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
//import businesslogic.logbusinesslogic.LogBusinessLogicController;
//import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
//import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicController;
//import businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic.AccountManBusinessLogicController;
//import businesslogic.myfilebusinesslogic.managerfilebusinesslogic.ManagerBusinessLogicController;
//import businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic.SalesmanFileBusinessLogicController;
//import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicController;
//import businesslogic.userbusinesslogic.UserBusinessLogicController;
//import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.ServiceFactory;
//import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;
//import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
//import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
//import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
//import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
//import businesslogicservice.myfilebusinesslogicservice.*;
//import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
//import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;


import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    ServiceFactory serviceFactory;

    public static void main(String[]args){
        Server server=new Server();
        server.launch();
    }
    public void launch(){
        try{

//            AccountBookBusinessLogicService accountBookBusinessLogicService=new AccountBookBusinessLogicController();
//            AccountBusinessLogicService accountBusinessLogicService=new AccountBusinessLogicController();
//            ClientBusinessLogicService clientBusinessLogicService=new ClientBusinessLogicController();
//            LogBusinessLogicService logBusinessLogicService=new LogBusinessLogicController();
//            MessageBusinessLogicService messageBusinessLogicService=new MessageBusinessLogicController();
//            UserBusinessLogicService userBusinessLogicService=new UserBusinessLogicController();
//            WarehouseBusinessLogicService warehouseBusinessLogicService=new WarehouseBusinessLogicController();
//            AccountManBusinessLogicService accountManBusinessLogicService=new AccountManBusinessLogicController();
//            ManagerBusinessLogicService managerBusinessLogicService=new ManagerBusinessLogicController();
//            SalesmanFileBusinessLogicService salesmanFileBusinessLogicService=new SalesmanFileBusinessLogicController();
//            StrategyBusinessLogicService strategyBusinessLogicService=new StrategyBusinessLogicController();
//            WarehouseManBusinessLogicService warehouseManBusinessLogicService=new WarehouseManBusinessLogicController();
            serviceFactory=ServiceFactoryImpl.getInstance();

            LocateRegistry.createRegistry(6666);
            Naming.bind("rmi://0.0.0.0:6666/ServiceFactory",serviceFactory);

        }catch(RemoteException e){
            e.printStackTrace();
        }catch(AlreadyBoundException e){
            e.printStackTrace();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }
}
