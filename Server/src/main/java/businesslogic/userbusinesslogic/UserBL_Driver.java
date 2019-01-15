package businesslogic.userbusinesslogic;

import businesslogic.ServiceFactoryImpl;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
import vo.uservo.Position;
import vo.uservo.UserVO;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;

public class UserBL_Driver {

    public static void main(String args[]) throws RemoteException{

        UserBusinessLogicService users= ServiceFactoryImpl.getInstance().getUserBusinessLogicService();
        //UserBusinessLogicControllerAccess users=new UserBusinessLogicController();
        UserVO uvo=new UserVO("Paul","paul","001", Position.Manager,false,false,false);
        UserVO uvo1=new UserVO("Ping","ping","002", Position.Salesman,false,false,false);
        UserVO uvo2=new UserVO("Jane","jane","003", Position.FinancialOfficer,false,false,false);
        UserVO uvo3=new UserVO("Wang","wang","004", Position.Warehouseman,false,false,false);
        UserVO uvo4=new UserVO("admin","admin","admin", Position.Administrator,false,false,false);
        users.userManageAdd("admin",uvo);
        users.userManageAdd("admin",uvo1);
        users.userManageAdd("admin",uvo2);
        users.userManageAdd("admin",uvo3);
        users.userManageAdd("admin",uvo4);
       // users.userManageAdd("zhongjie",uvo);
        //System.out.println(users.userManageBan("zhangping","001"));
        //System.out.println(users.userManageGiveRight("zhangping","002",true));
        //System.out.println(users.userManageAdd("zhangping",new UserVO("02","name002","pass002",Position.Manager,false,false,false)));
        //System.out.println(users.userManageChangePos("zhangping","001",Position.Warehouseman));
        //System.out.println(users.reset("001","002","newpass001","pass001","name003","pass001"));
        //System.out.println(users.reset("001","001","pass002","newpass001","name001","newpass001"));
        //System.out.println(users.reset("001","002","pass001","newpass001","name001","newpass001"));
        //System.out.println(users.userManageIniPass("zhangping","03"));
//        System.out.println("用户列表");
//        for(UserVO v:users.list()){
//            System.out.println(v.toString());
//        }
        //System.out.println(users.trace("001").toString());
//        for(String s:users.getFinancialOfficerList()){
//            System.out.println(s);
//        }


    }
}
