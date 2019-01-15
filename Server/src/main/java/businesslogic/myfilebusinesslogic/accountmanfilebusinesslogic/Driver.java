package businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic;

import java.rmi.RemoteException;

public class Driver {
    public static void main(String args[])throws RemoteException{
        AccountManBusinessLogicController a=new AccountManBusinessLogicController();
        for(String s:a.showDraft()){
            System.out.println(s);
        }
    }

}
