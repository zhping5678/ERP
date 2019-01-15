package pkg.ui.accountui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkg.ui.RemoteHelper;
import pkg.unclassified.ItemText;
import vo.accountvo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class AccountUIController implements AccountUIControllerAccess{
    private static AccountUIController accountUIController;

    private AccountUIController(){
    }


    @Override
    public ObservableList<ItemText> getAccountList() {
        ObservableList<ItemText> result =FXCollections.observableArrayList();
        try{
            ArrayList<String> stringArrayList= RemoteHelper.getAccountBusinessLogicService().showAccountList();
            for(int i=0;i<stringArrayList.size();i++){
                result.add(new ItemText(stringArrayList.get(i),stringArrayList.get(i),""));
            }

        }catch(RemoteException e){
            e.printStackTrace();
        }
        return result;
    }

    static AccountUIController getInstance(){
        if (accountUIController ==null){
            accountUIController =new AccountUIController();
        }
        return accountUIController;
    }
    public AccountVO getAccountVO(String id){
        AccountVO result=null;
        try{
            result=RemoteHelper.getAccountBusinessLogicService().findAccountByID(id);

        }catch(RemoteException e){
            e.printStackTrace();
        }

        return result;
    }

}
