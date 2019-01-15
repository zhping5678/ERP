package businesslogicservice.clientbusinesslogicservice;

import vo.utilityvo.ResultMessage;
import vo.clientvo.ClientVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @Name ClientBusinessLogicService
 * @Description
 * @author zhangping
 * @date 2017/11/29 0029 9:44
 */
public interface ClientBusinessLogicService extends Remote {

    void connectionTest() throws RemoteException;

    //显示客户列表，包括正在使用的和已经被禁用的
    ArrayList<String> showSellerList() throws RemoteException;

    ArrayList<String> showSupplierList() throws RemoteException;

    //参数为当前登陆用户的ID和要增加的客户信息
    ResultMessage addClient(String userID, ClientVO vo) throws RemoteException;

    //从界面删除该客户，但是后端根据客户的具体情况决定彻底从
    ResultMessage deleteClient(String userID, String ID) throws RemoteException;

    //还原已经被禁用的客户，参数为当前登录用户的ID和要还原的客户ID
    ResultMessage recover(String userID, String ID) throws RemoteException;

    //禁用客户，在界面显示为灰色，参数为当前登录用户的ID和要禁用的客户的ID
    ResultMessage ban(String userID, String ID) throws RemoteException;

    //根据客户ID返回特定的单一客户对象
    ClientVO traceClient(String ID) throws RemoteException;

    //根据关键字返回满足关键字的多个客户对象
    ArrayList<String> findClient(String keywords) throws RemoteException;

    //修改客户信息，参数为当前登录用户的ID和要修改的客户的原本ID和要修改的信息
    ResultMessage modifyClient(String ID, String oldId, ClientVO vo) throws RemoteException;

}
