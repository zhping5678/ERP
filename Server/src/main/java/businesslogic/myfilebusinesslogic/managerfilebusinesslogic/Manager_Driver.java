package businesslogic.myfilebusinesslogic.managerfilebusinesslogic;

import businesslogicservice.myfilebusinesslogicservice.ManagerBusinessLogicService;
import infor.Infor;

import java.rmi.RemoteException;

public class Manager_Driver {
    public static void main(String[] args) throws RemoteException{
        ManagerBusinessLogicService m=new ManagerBusinessLogicController();
//        for(String s:m.showWaitReview()){
//            System.out.println(s);
//        }
        Infor i=new Infor();
        i.setCheckerID("aaaa");
        i.setCheckerTime("xxxx");
        i.setRemark("ssss");
        i.setResult("dis");
        m.approveSalesman("zp","pr-20180105-7",false,i);

    }
}
