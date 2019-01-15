package MockObject;

import Start.RemoteHelper;
import vo.utilityvo.ResultMessage;
import vo.accountvo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Accountbl {
    /************把accountVO改成String****************/
    public ArrayList<String> showAccountList(){
        return null;
    }

    //增加一个账户，参数为当前登录人员的ID和新增账户的内容
    public ResultMessage addAccount(String userID, AccountVO vo){
        return ResultMessage.addSuccess;
    }

    //删除一个账户，参数为当前登录人员的ID和要删除的账户的ID
    public ResultMessage deleteAccount(String userID,String ID){
        return null;
    }

    //禁用一个账户，参数为当前登录人员的ID和要禁用的账户ID
    public ResultMessage banAccount(String userID,String ID){
        return null;
    }

    public ResultMessage recoverAccount(String userID,String ID){
        return null;
    }

    //修改账户信息，参数为当前登录人员的ID和要修改的账户的ID以及修改的内容
    public ResultMessage modifyAccountID(String userID,String oldID,String newID){
        return null;
    }

    //根据关键字查找返回账户的列表
    public ArrayList<String> findAccountsByKeywords(String keywords){
        ArrayList<String> array=new ArrayList<>();
        String[] s={"abcdefg","abcdef","abcde","abcd","abc","ab","a","YYYYYYYYYYYY"};
        for(int i=0;i<s.length;i++){
            if(s[i].contains(keywords)){
                array.add(s[i]);
            }
        }
        return array;
    }

    //根据ID来返回唯一指定的账户对象
    public AccountVO findAccountByID(String ID){
        /*
        AccountVO v1=new AccountVO("YYYYYYYYYYYY","bbb1",2.5,true,true);
        AccountVO v2=new AccountVO("aaa2","bbb2",2.5,true,true);
        if(ID.equals("YYYYYYYYYYYY")){
            return v1;
        }
        else if(ID.equals("aaa2")){
            return v2;
        }
        else {
            return null;
        }
        */
        return null;
    }

}
