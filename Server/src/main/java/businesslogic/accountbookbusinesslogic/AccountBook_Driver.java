package businesslogic.accountbookbusinesslogic;


import businesslogicservice.accountbookbusinesslogicservice.AccountBookBusinessLogicService;

import java.rmi.RemoteException;

public class AccountBook_Driver {
    public static void main(String[] args) throws RemoteException{
        AccountBookBusinessLogicService a=new AccountBookBusinessLogicController();
        //测试开始、结束建账
        /*a.accountBegin("zp");
        a.accountStop("zhangping");*/
        for(String t:a.accountIniList()){
            System.out.println(t);
        }
        //测试根据时间读取期初信息
        /*AccountBookInfor accountBookInfor=a.read("2017-12-31 21:34:15");
        for(AccountInitInfor acc:accountBookInfor.getAccounts()){
            System.out.println(acc.toString());
        }
        for(ClientInitInfor cli:accountBookInfor.getClients()){
            System.out.println(cli.toString());
        }
        for(CommodityIniInfor com:accountBookInfor.getCommodity()){
            System.out.println(com.toString());
        }*/
    }
}
