package data;

import data.accountbookdata.AccountBookDataController;
import data.accountdata.AccountDataController;
import data.clientdata.ClientDataController;
import data.filedata.FileDataController;
import data.logdata.LogDataController;
import data.messagedata.MessageDataController;
import data.myfiledata.AccountManDataController;
import data.myfiledata.SalesmanDataController;
import data.myfiledata.StrategyDataController;
import data.myfiledata.WarehouseManDataController;
import data.userdata.UserDataController;
import data.warehousedata.CommodityDataController;
import data.warehousedata.CommodityTypeDataController;
import data.warehousedata.WarehouseDataController;
import dataservice.DataFactoryService;
import dataservice.accountbookdataservice.AccountBookDataService;
import dataservice.accountdataservice.AccountDataService;
import dataservice.clientdataservice.ClientDataService;
import dataservice.filedataservice.FileDataService;
import dataservice.logdataservice.LogDataService;
import dataservice.messagedataservice.MessageDataService;
import dataservice.myfiledataservice.AccountManDataService;
import dataservice.myfiledataservice.SalesmanDataService;
import dataservice.myfiledataservice.StrategyDataService;
import dataservice.myfiledataservice.WarehouseManDataService;
import dataservice.userdataservice.UserDataService;
import dataservice.warehousedataservice.CommodityDataService;
import dataservice.warehousedataservice.CommodityTypeDataService;
import dataservice.warehousedataservice.WarehouseDataService;

public class DataFactoryImpl implements DataFactoryService{

    private AccountBookDataService accountBookDataService;
    private AccountDataService accountDataService;
    private ClientDataService clientDataService;
    private FileDataService fileDataService;
    private LogDataService logDataService;
    private MessageDataService messageDataService;

    private StrategyDataService strategyDataService;
    private SalesmanDataService salesmanDataService;
    private AccountManDataService accountManDataService;
    private WarehouseManDataService warehouseManDataService;

    private UserDataService userDataService;
    private CommodityDataService commodityDataService;
    private CommodityTypeDataService commodityTypeDataService;
    private WarehouseDataService warehouseDataService;

    private static DataFactoryImpl instance;

    public static DataFactoryImpl getInstance(){
        if(instance==null){
            instance=new DataFactoryImpl();
        }
        return instance;
    }
    @Override
    public AccountBookDataService getAccountBookDataService() {
        if(accountBookDataService==null){
            accountBookDataService=new AccountBookDataController();
        }
        return accountBookDataService;
    }

    @Override
    public AccountDataService getAccountDataService() {
        if(accountDataService==null){
            accountDataService=new AccountDataController();
        }
        return accountDataService;
    }

    @Override
    public ClientDataService getClientDataService() {
        if(clientDataService==null){
            clientDataService=new ClientDataController();
        }
        return clientDataService;
    }

    @Override
    public FileDataService getFileDataService() {
        if(fileDataService==null){
            fileDataService=new FileDataController();
        }
        return fileDataService;
    }

    @Override
    public LogDataService getLogDataService() {
        if(logDataService==null){
            logDataService=new LogDataController();
        }
        return logDataService;
    }

    @Override
    public MessageDataService getMessageDataService() {
        if(messageDataService==null){
            messageDataService=new MessageDataController();
        }
        return messageDataService;
    }

    @Override
    public StrategyDataService getStrategyDataService() {
        if(strategyDataService==null){
            strategyDataService=new StrategyDataController();
        }
        return strategyDataService;
    }

    @Override
    public SalesmanDataService getSalesmanDataService() {
        if(salesmanDataService==null){
            salesmanDataService=new SalesmanDataController();
        }
        return salesmanDataService;
    }

    @Override
    public AccountManDataService getAccountManDataService() {
        if(accountManDataService==null){
            accountManDataService=new AccountManDataController();
        }
        return accountManDataService;
    }

    @Override
    public WarehouseManDataService getWarehouseManDataService(){
        if(warehouseManDataService==null){
            warehouseManDataService=new WarehouseManDataController();
        }
        return warehouseManDataService;
    }

    @Override
    public UserDataService getUserDataService() {
        if(userDataService==null){
            userDataService=new UserDataController();
        }
        return userDataService;
    }

    @Override
    public CommodityDataService getCommodityDataService() {
        if(commodityDataService==null){
            commodityDataService=new CommodityDataController();
        }
        return commodityDataService;
    }

    @Override
    public CommodityTypeDataService getCommodityTypeDataService() {
        if(commodityTypeDataService==null){
            commodityTypeDataService=new CommodityTypeDataController();
        }
        return commodityTypeDataService;
    }

    @Override
    public WarehouseDataService getWarehouseDataService() {
        if(warehouseDataService==null){
            warehouseDataService=new WarehouseDataController();
        }
        return warehouseDataService;
    }
}
