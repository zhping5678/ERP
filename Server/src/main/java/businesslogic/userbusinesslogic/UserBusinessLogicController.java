package businesslogic.userbusinesslogic;

import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.userdataservice.UserDataService;
import util.HibernateUtil;
import vo.uservo.UserVO;
import vo.utilityvo.ResultMessage;
import vo.uservo.Position;
import po.userpo.UserPO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/*
 * @Name UserBusinessLogicController
 * @Description 用户管理的业务逻辑实现
 * @author zhangping
 * @date 2017/12/1 0001 0:15
 */
public class UserBusinessLogicController extends UnicastRemoteObject implements UserBusinessLogicControllerAccess,UserBusinessLogicService{

    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private UserDataService userDataService;
    private UserTransVoPo trans;

    public UserBusinessLogicController()throws RemoteException{
        userDataService= DataFactoryImpl.getInstance().getUserDataService();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        trans=new UserTransVoPo();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("UserBusinessLogicService is connected.\n");
    }

    @Override
    public ResultMessage login(String id, String password) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        //先验证用户名是否正确，即该用户是否存在
        UserPO po=userDataService.read(id);
        if(po==null){
            return ResultMessage.IDError;
        }else if(po.getIsBan()){//检查该用户是否已经被禁用或删除，如果是，则不能登录
            return ResultMessage.Fail;
        }else if(!password.equals(po.getPassword())) {//检查密码是否正确
            return ResultMessage.PasswordError;
        }else {//之前一切流程均正确
            po.setState(true);
            userDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.loginSuccess;
        }
    }

    @Override
    public ResultMessage logout(String id) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(id);
        po.setState(false);
        userDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.logoutSuccess;
    }

    @Override
    public ResultMessage reset(String oldID,String newID, String oldPass, String newPass,String name, String confirmPass) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(oldID);
        if(oldPass.equals(po.getPassword())){//输入密码正确
            if(!oldID.equals(newID)) {//要更改ID，就要检查新的id是否存在
                UserPO isExist = userDataService.read(newID);
                if (isExist != null) {
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    return ResultMessage.exist;
                }
            }
            if(newPass.equals(confirmPass)){//检验新密码与确认密码是否一致
                po.setID(newID);
                po.setPassword(newPass);
                po.setName(name);
                userDataService.remove(oldID);
                userDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.modiSuccess;
            }else{
                return ResultMessage.differPass;
            }
        }else{//原密码输入错误
            return ResultMessage.PasswordError;
        }
    }

    @Override
    public ResultMessage userManageAdd(String adminID,UserVO vo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO isExist=userDataService.read(vo.getID());
        if(isExist!=null){//用户已存在
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
            return ResultMessage.exist;

        }else{
            UserPO po=new UserPO(vo.getID(),vo.getName(),vo.getPassword(),vo.getPosition(),vo.getState(),vo.getRight(),vo.isBan(),true,false);
            userDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(adminID,"系统管理员增加用户 "+vo.toString());
            return ResultMessage.addSuccess;

        }
    }

    @Override
    public ResultMessage userManageBan(String adminID,String id) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(id);
        if(po.getIsOn()){
            return ResultMessage.onDeal;
        }else{
            po.setIsBan(true);
            userDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(adminID,"系统管理员禁用用户 "+trans.transPoToVo(po).toString());
            return ResultMessage.banSuccess;
        }
    }

    @Override
    public ResultMessage userManageIniPass(String adminID,String id) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(id);
        po.setPassword(id);//系统管理员可以将用户的密码初始化为id
        userDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(adminID,"系统管理员初始化密码 "+trans.transPoToVo(po).toString());
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage userManageGiveRight(String adminID,String id, boolean right) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(id);
        po.setRight(right);
        userDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(adminID,"系统管理员修改权限 "+trans.transPoToVo(po).toString());
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage userManageRecover(String adminID,String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(ID);
        po.setIsBan(false);
        userDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(adminID,"系统管理员还原用户 "+trans.transPoToVo(po).toString());
        return ResultMessage.recoverSuccess;

    }

    @Override
    public ResultMessage userManageChangePos(String adminID,String id, Position newPosition) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO po=userDataService.read(id);
        po.setPosition(newPosition);
        userDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(adminID,"系统管理员职位变更 "+"ID:"+id+"为"+newPosition.getPosition());
        return ResultMessage.modiSuccess;
    }

    @Override
    public UserVO trace(String id) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO p=userDataService.read(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(p==null){
            return null;
        }else{
            return trans.transPoToVo(p);
        }

    }

    @Override
    public ArrayList<UserVO> list() throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<UserPO> list=userDataService.list();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.transPoToVos(list);
    }

    @Override
    public ArrayList<String> searchByKey(String keywords) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<UserPO> list=userDataService.searchByKeywords(keywords);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        ArrayList<String > result=new ArrayList<>();
        for(UserPO upo:list){
            result.add(upo.getID());
        }
        return result;
    }

    @Override
    public boolean getUserRight(String userID) {
        HibernateUtil.getCurrentSession().beginTransaction();
        UserPO userPO=userDataService.read(userID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return userPO.getRight();
    }

    private ArrayList<UserPO> getOnUser() {
        ArrayList<UserPO> list=new ArrayList<>();
        HibernateUtil.getCurrentSession().beginTransaction();
        for(UserPO upo:userDataService.list()){
            if(!upo.getIsBan()){
                list.add(upo);
            }
        }
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return list;
    }

    @Override
    public ArrayList<String> getUserList(){
        ArrayList<String> re=new ArrayList<>();
        for(UserPO upo:this.getOnUser()){
            re.add(upo.getID());
        }
        return re;
    }
    @Override
    public ArrayList<String> getWarehousemanList() {
        ArrayList<String> re=new ArrayList<>();
        for(UserPO upo:this.getOnUser()){
            if(upo.getPosition()==Position.Warehouseman){
                re.add(upo.getID());
            }
        }
        return re;
    }

    @Override
    public ArrayList<String> getSalesmanList() {
        ArrayList<String> re=new ArrayList<>();
        for(UserPO upo:this.getOnUser()){
            if(upo.getPosition()==Position.Salesman){
                re.add(upo.getID());
            }
        }
        return re;
    }

    @Override
    public ArrayList<String> getFinancialOfficerList() {
        ArrayList<String> re=new ArrayList<>();
        for(UserPO upo:this.getOnUser()){
            if(upo.getPosition()==Position.FinancialOfficer){
                re.add(upo.getID());
            }
        }
        return re;
    }

    @Override
    public String getManageID(){
        for(UserPO upo:this.getOnUser()){
            if(upo.getPosition()==Position.Manager){
                return upo.getID();
            }
        }
        return null;
    }

}
