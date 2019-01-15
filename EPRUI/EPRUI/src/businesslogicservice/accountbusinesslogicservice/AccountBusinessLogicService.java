package businesslogicservice.accountbusinesslogicservice;

/*
 *@Name: AccountBusinessLogicService
 *@Description: 接口 具体有显示，增，删，改，查
 *@author: Jane
 *@Date: Created in 2017/11/29 19:24
 */

import vo.utilityvo.ResultMessage;
import vo.accountvo.AccountVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AccountBusinessLogicService extends Remote {

    void connectionTest() throws RemoteException;

    //显示账户列表
    ArrayList<String> showAccountList() throws RemoteException;

    //增加一个账户，参数为当前登录人员的ID和新增账户的内容
    ResultMessage addAccount(String userID, AccountVO vo) throws RemoteException;

    //删除一个账户，参数为当前登录人员的ID和要删除的账户的ID
    //public ResultMessage deleteAccount(String userID,String ID)throws RemoteException;

    //禁用一个账户，参数为当前登录人员的ID和要禁用的账户ID
    ResultMessage banAccount(String userID, String ID) throws RemoteException;

    ResultMessage recoverAccount(String userID, String ID) throws RemoteException;

    //修改账户信息，参数为当前登录人员的ID和要修改的账户的ID,name以及修改的内容
    ResultMessage modifyAccount(String userID, String oldID, String newID, String newName) throws RemoteException;

    //根据关键字查找返回账户的列表
    ArrayList<String> findAccountsByKeywords(String keywords) throws RemoteException;

    //根据ID来返回唯一指定的账户对象
    AccountVO findAccountByID(String ID) throws RemoteException;

}
