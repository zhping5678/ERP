package dataservice;

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

public interface DataFactoryService {

    public AccountBookDataService getAccountBookDataService();
    public AccountDataService getAccountDataService();
    public ClientDataService getClientDataService();
    //有待修改
    public FileDataService getFileDataService();

    public LogDataService getLogDataService();
    public MessageDataService getMessageDataService();

    //各种单据的制定删除等数据库中的操作
    public StrategyDataService getStrategyDataService();
    public SalesmanDataService getSalesmanDataService();
    public AccountManDataService getAccountManDataService();
    public WarehouseManDataService getWarehouseManDataService();


    public UserDataService getUserDataService();
    //库存和商品管理
    public CommodityDataService getCommodityDataService();
    public CommodityTypeDataService getCommodityTypeDataService();
    public WarehouseDataService getWarehouseDataService();
}
