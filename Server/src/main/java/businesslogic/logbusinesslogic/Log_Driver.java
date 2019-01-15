package businesslogic.logbusinesslogic;

import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import vo.logvo.LogVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Log_Driver {
    public static void main(String args[]) throws RemoteException{
        LogBusinessLogicService a=new LogBusinessLogicController();
        LogBusinessLogicControllerAccess b=new LogBusinessLogicController();

        //b.addLog("Jane","增加账户");
        ArrayList<LogVO> l=a.LogView();
        for(LogVO lv:l){
            System.out.println(lv.toString());
        }
//        b.addLog("wang","删除商品2");
//        ArrayList<LogVO> search=a.LogSearch("增加");
//        for(LogVO lv:search){
//            System.out.println(lv.toString());
//        }
//        a.LogSearch("J");
    }
}
