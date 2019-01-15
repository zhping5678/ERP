package businesslogicservice.userbusinesslogicservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//import com.sun.org.apache.regexp.internal.RE;
import vo.utilityvo.ResultMessage;
import vo.uservo.Position;
import vo.uservo.*;

/**
 * @Name UserBusinessLogicService
 * @Description 业务逻辑层提供给展示层的接口类
 * @author zhangping
 * @date 2017/11/29 0029 21:55
 */
public interface UserBusinessLogicService extends Remote {

    public void connectionTest() throws RemoteException;

    //根据用户ID实现登录
    public ResultMessage login(String id, String password) throws RemoteException;

    //根据用户ID实现登出
    public ResultMessage logout(String id) throws RemoteException;

    //用户修改自己的ID和密码，参数分别为旧ID，新ID，旧密码，新密码，如果不修改，默认为原来的ID，密码
    public ResultMessage reset(String oldID, String newID, String oldPass, String newPass,String name, String confirm) throws RemoteException;

    //系统管理员查看用户信息
    public UserVO trace(String id) throws RemoteException;

    //系统管理员查看用户列表
    public ArrayList<UserVO> list() throws  RemoteException;

    public ArrayList<String> searchByKey(String keywords) throws RemoteException;

    //系统管理员增加一个用户
    public ResultMessage userManageAdd(String adminID,UserVO vo) throws RemoteException;

    //系统管理员从界面删除一个用户，具体实现时，根据参数决定彻底删除还是从界面移除
    //public ResultMessage userManageDel(String adminID,String id) throws RemoteException;

    //系统管理员为用户重置密码
    public ResultMessage userManageIniPass(String adminID,String id) throws RemoteException;

    //系统管理员禁用某个用户
    public ResultMessage userManageBan(String adminID,String id) throws RemoteException;

    //系统管理员还原被禁用的用户
    public ResultMessage userManageRecover(String adminID,String ID) throws RemoteException;

    //系统管理员变更用户的职位
    public ResultMessage userManageChangePos(String admin,String id, Position position) throws RemoteException;

    //系统管理员为用户赋予权限和取消权限
    public ResultMessage userManageGiveRight(String admin,String id, boolean right) throws RemoteException;

}
