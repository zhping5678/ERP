package businesslogic.accountbusinesslogic;

/*
 *@Name: AccountBusinessLogicController
 *@Description: 实现逻辑层的账户管理功能。账户的增删查改只有最高权限才可以进行这些操作。
 *@author: Jane
 *@Date: Created in 2017/11/29 19:20
 */
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogicservice.accountbusinesslogicservice.AccountBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.accountdataservice.AccountDataService;
import util.HibernateUtil;
import vo.utilityvo.ResultMessage;
import po.accountpo.AccountPO;
import vo.accountvo.AccountVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AccountBusinessLogicController extends UnicastRemoteObject implements AccountBusinessLogicControllerAccess,AccountBusinessLogicService {

    private AccountDataService accountDataService;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private AccountTransVOPO trans;

    public AccountBusinessLogicController() throws RemoteException{
        accountDataService= DataFactoryImpl.getInstance().getAccountDataService();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        trans=new AccountTransVOPO();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("AccountBusinessLogicService is connected.\n");
    }

    /*
     *@Name: showAccountList()
     *@Description: 展示所有账户列表，属性有账户ID，余额，是否被禁用。
     *@Author: Jane
     *@Date: 2017/11/30 1:08
     */
    @Override
    public ArrayList<AccountPO> list(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<AccountPO> list=accountDataService.showList();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return list;
    }

    @Override
    public ArrayList<AccountVO> listAccountVO() {
        ArrayList<AccountVO> re=new ArrayList<>();
        for(AccountPO po:this.list()){
            if(!po.getIsBan()){
                re.add(trans.transPoToVo(po));
            }
        }
        return re;
    }

    @Override
    public ArrayList<String> showAccountList() throws RemoteException {
        ArrayList<String> list=new ArrayList<>();
        for(AccountPO p:this.list()){
            list.add(p.getID());
        }
        return list;
    }

    /*
     *@Name: addAccount
     *@Description: 增加一个账户。当账户ID已经存在时，则不能增加。
     *@Author: Jane
     *@Date: 2017/11/30 12:35
     */
    @Override
    public ResultMessage addAccount(String userID,AccountVO vo) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(accountDataService.read(vo.getID())==null){
                AccountPO newAccount=new AccountPO(vo.getID(),vo.getName(),vo.getMoney(),vo.getIsBan(),true,false);
                accountDataService.write(newAccount);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"增加账户 "+vo.getID());
                //System.out.println(ResultMessage.addSuccess.getExpression());
                return ResultMessage.addSuccess;
            }else{
                //System.out.println(ResultMessage.exist.getExpression());
                return ResultMessage.exist;
            }
        }catch (Exception e){
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return ResultMessage.Fail;
        }

    }

    @Override
    public ResultMessage banAccount(String userID,String ID) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        AccountPO po=accountDataService.read(ID);
        if(po.getIsOnService()){
            return ResultMessage.onDeal;
        }else{
            po.setIsBan(true);
            accountDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"禁用账户 "+ID);
            return ResultMessage.banSuccess;
        }
    }

    @Override
    public ResultMessage recoverAccount(String userID, String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        AccountPO po=accountDataService.read(ID);
        po.setIsBan(false);
        accountDataService.update(po);
        //accountDataService.remove(ID);
        //accountDataService.write(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(userID,"还原账户 "+ID);
        return ResultMessage.recoverSuccess;
    }

    @Override
    public ResultMessage modifyAccount(String userID,String oldID,String newID,String newName) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        AccountPO po=accountDataService.read(oldID);
        if(oldID.equals(newID)){//id不变化，只要设置name
            po.setName(newName);
            accountDataService.update(po);
            //accountDataService.remove(oldID);
            //accountDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.modiSuccess;
        }else{//id修改
            AccountPO isExist=accountDataService.read(newID);
            if(isExist!=null){
                return ResultMessage.exist;
            }else{
                po.setID(newID);
                po.setName(newName);
                accountDataService.remove(oldID);
                accountDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"账户名"+" "+oldID+" "+"修改成"+" "+newID+".");
                return ResultMessage.modiSuccess;
            }
        }
    }

    /*
     *@Name: findAccountsByKeywords
     *@Description: 模糊查找，返回的是AccountVO列表
     *@Author: Jane
     *@Date: 2017/11/30 1:42
     */
    @Override
    public ArrayList<String> findAccountsByKeywords(String keywords) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<AccountPO> ap = accountDataService.findByKeywords(keywords);
        //System.out.println(ap.size());
        HibernateUtil.getCurrentSession().getTransaction().commit();
        ArrayList<String> accountNameList=new ArrayList<>();
        for(AccountPO p: ap){
            accountNameList.add(p.getID());
        }
        return accountNameList;
    }

    @Override
    public AccountVO findAccountByID(String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        AccountPO po=accountDataService.read(ID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(po==null){
            return null;
        }else{
            return trans.transPoToVo(po);
        }
    }

    @Override
    public ResultMessage modifyIsOnService(String ID, boolean isOnService){
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            AccountPO po=accountDataService.read(ID);
            po.setIsOnService(isOnService);
            accountDataService.update(po);
            //accountDataService.remove(ID);
            //accountDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.modiSuccess;
        }catch (Exception e){
            HibernateUtil.getCurrentSession().beginTransaction().rollback();
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage modifyCanDel(String ID, boolean canDel){
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            AccountPO po=accountDataService.read(ID);
            po.setCanDel(canDel);
            accountDataService.update(po);
            //accountDataService.remove(ID);
            //accountDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.modiSuccess;
        }catch (Exception e){
            HibernateUtil.getCurrentSession().beginTransaction().rollback();
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage modifyAccountMoney(String ID, double money) {
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            AccountPO po=accountDataService.read(ID);
            po.setMoney(po.getMoney()+money);
            accountDataService.update(po);
            //accountDataService.remove(ID);
            //accountDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.modiSuccess;
        }catch (Exception e){
            HibernateUtil.getCurrentSession().beginTransaction().rollback();
            return ResultMessage.Fail;
        }
    }


}
