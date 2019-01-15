package businesslogic.accountbookbusinesslogic;

import businesslogic.accountbusinesslogic.AccountBusinessLogicController;
import businesslogic.accountbusinesslogic.AccountBusinessLogicControllerAccess;
import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
import businesslogic.clientbusinesslogic.ClientBusinessLogicControllerAccess;
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.userbusinesslogic.UserBusinessLogicController;
import businesslogic.userbusinesslogic.UserBusinessLogicControllerAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.accountbookdataservice.AccountBookDataService;
import infor.*;
import po.accountpo.AccountPO;
import po.clientpo.ClientPO;
import po.warehousepo.CommodityPO;
import util.HibernateUtil;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountBookBusinessLogicController extends UnicastRemoteObject implements AccountBookBusinessLogicService {

    private AccountBookDataService accountBookDataService;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private ClientBusinessLogicControllerAccess clientBusinessLogicControllerAccess;
    private AccountBusinessLogicControllerAccess accountBusinessLogicControllerAccess;
    private WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private UserBusinessLogicControllerAccess userBusinessLogicControllerAccess;

    public AccountBookBusinessLogicController() throws RemoteException{
        accountBookDataService= DataFactoryImpl.getInstance().getAccountBookDataService();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        clientBusinessLogicControllerAccess=new ClientBusinessLogicController();
        accountBusinessLogicControllerAccess=new AccountBusinessLogicController();
        userBusinessLogicControllerAccess=new UserBusinessLogicController();
        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("AccountBookBusinessLogicService is connected.\n");
    }

    @Override
    public ArrayList<String> accountIniList() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> accountBookList=new ArrayList<>();
        for(AccountBookInfor accountBookPO :accountBookDataService.list()){
            accountBookList.add(accountBookPO.getTime());
        }
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return accountBookList;
    }

    @Override
    public AccountBookInfor read(String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        AccountBookInfor re=accountBookDataService.read(ID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return re;
    }

    @Override
    public ResultMessage accountBegin(String userID) throws RemoteException {
        //生成一条message语句，通知开始期初建账
        messageBusinessLogicControllerAccess.addMessage(userID,"开始期初建账",userBusinessLogicControllerAccess.getUserList(),true);
        logBusinessLogicControllerAccess.addLog(userID,"开始期初建账");
        return null;
    }

    //财务人员选择停止建账，那么当前数据库的信息就将单独存储起来，并不能改变
    @Override
    public ResultMessage accountStop(String userID) throws RemoteException {
        //新建一套账本
        AccountBookInfor newAccountBook=new AccountBookInfor();
        //建账时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        //存储账户信息
        Set<AccountInitInfor> accounts=new HashSet<>();
        ArrayList<AccountPO> allAccount=accountBusinessLogicControllerAccess.list();
        for(AccountPO p:allAccount){
            if(p.getIsBan()){
                accounts.add(new AccountInitInfor(p.getID(),p.getName(),p.getMoney(), InitState.BAN));
            }else{
                accounts.add(new AccountInitInfor(p.getID(),p.getName(),p.getMoney(),InitState.ON));
            }
        }
        //存储客户信息
        Set<ClientInitInfor> clients=new HashSet<>();
        ArrayList<ClientPO> allClient=clientBusinessLogicControllerAccess.list();
        for(ClientPO p:allClient){
            if(p.getIsVisible()){
                if(p.getIsBan()){
                    clients.add(new ClientInitInfor(p.getID(),p.getName(),p.getCategory(),p.getLevel(), p.getAddress(),p.getPhoneNum(),p.getPostcode(),p.getEmail(),p.getQuota(),p.getToPay(),p.getToCollect(),p.getTotalAmount(),p.getStaff(),InitState.BAN));
                }else{
                    clients.add(new ClientInitInfor(p.getID(),p.getName(),p.getCategory(),p.getLevel(), p.getAddress(),p.getPhoneNum(),p.getPostcode(),p.getEmail(),p.getQuota(),p.getToPay(),p.getToCollect(),p.getTotalAmount(),p.getStaff(),InitState.ON));
                }
            }
        }
        //存储商品信息
        Set<CommodityIniInfor> commodity=new HashSet<>();
        ArrayList<CommodityPO> allCommodity = warehouseBusinessLogicAccess.list();
        for(CommodityPO p:allCommodity){
            //添加商品信息（商品分类，某一商品的名称，类别，型号，进价/售价(默认为上年平均)，最近进价和最近售价留空）
            if(p.getIsVisible()){
                if(p.getIsBan()){
                    commodity.add(new CommodityIniInfor(p.getID(),p.getName(),p.getSize(),p.getAmountOfInventory(),p.getBuyingPrice(),p.getMarketPrice(),p.getTheRecentBuyingPrice(),p.getTheRecentMarketPrice(),InitState.BAN));
                }else{
                    commodity.add(new CommodityIniInfor(p.getID(),p.getName(),p.getSize(),p.getAmountOfInventory(),p.getBuyingPrice(),p.getMarketPrice(),p.getTheRecentBuyingPrice(),p.getTheRecentMarketPrice(),InitState.ON));
                }
            }
        }
        newAccountBook.setTime(time);
        newAccountBook.setAccounts(accounts);
        newAccountBook.setClients(clients);
        newAccountBook.setCommodity(commodity);
        HibernateUtil.getCurrentSession().beginTransaction();
        accountBookDataService.write(newAccountBook);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        messageBusinessLogicControllerAccess.addMessage(userID,"期初建账结束",userBusinessLogicControllerAccess.getUserList(),false);
        logBusinessLogicControllerAccess.addLog(userID,"期初建账期结束");
        return ResultMessage.addSuccess;
    }
}
