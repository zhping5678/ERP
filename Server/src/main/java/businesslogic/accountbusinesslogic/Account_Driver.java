package businesslogic.accountbusinesslogic;
/*
 *@Name:
 *@Description: 驱动
 *@Author: Jane
 @Date: 2017/12/12 20:48
 */
import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
import vo.accountvo.AccountVO;

import java.rmi.RemoteException;

public class Account_Driver {
    public static void main(String args[]) throws RemoteException {
        AccountBusinessLogicService a=new AccountBusinessLogicController();
        //AccountBusinessLogicControllerAccess b=new AccountBusinessLogicController();
        AccountVO vo1=new AccountVO("黄鹏","ZJ",1000.0,false);
        AccountVO vo2=new AccountVO("钟洁","DN",15000.0,false);
        AccountVO vo3=new AccountVO("王旭","nju",1000.0,false);
        AccountVO vo4=new AccountVO("张萍","ZP",100.0,false);
        a.addAccount("zp",vo4);
//        a.addAccount("zp",vo2);
//        a.addAccount("zp",vo3);
//        a.findAccountsByKeywords("ss");
        //a.findAccountByID("ss");
        //a.modifyAccount("ping","zd","zd","ZJ");
    }

}
