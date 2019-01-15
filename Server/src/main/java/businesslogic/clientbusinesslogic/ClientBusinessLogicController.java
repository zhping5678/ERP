package businesslogic.clientbusinesslogic;

/*
 * @Name ClientBusinessLogicController
 * @Description 实现逻辑层的客户管理职能
 * @author zhangping
 * @date 2017/11/29 0029 14:26
 */

import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicController;
import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicControllerAccess;
import businesslogicservice.clientbusinesslogicservice.ClientBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.clientdataservice.ClientDataService;
import util.HibernateUtil;
import vo.clientvo.ClientIdentity;
import vo.utilityvo.ResultMessage;
import po.clientpo.ClientPO;
import vo.clientvo.ClientVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientBusinessLogicController extends UnicastRemoteObject implements ClientBusinessLogicControllerAccess,ClientBusinessLogicService {


    private ClientDataService clientDataService;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private ClientTransVoPo trans;
    private StrategyBusinessLogicControllerAccess strategyBusinessLogicControllerAccess;

    public ClientBusinessLogicController() throws RemoteException{
        clientDataService= DataFactoryImpl.getInstance().getClientDataService();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        strategyBusinessLogicControllerAccess=new StrategyBusinessLogicController();
        trans=new ClientTransVoPo();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("ClientBusinessLogicService is connected.\n");
    }

    @Override
    public ArrayList<ClientPO> list(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<ClientPO> allClient=clientDataService.list();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return allClient;
    }

    @Override
    public ArrayList<ClientVO> listSaler() {
        ArrayList<ClientVO> re=new ArrayList<>();
        for(ClientPO cpo:this.list()){
            if((!cpo.getIsBan())&&(cpo.getIsVisible())&&(cpo.getCategory()==ClientIdentity.Seller)){
                re.add(trans.transPoToVo(cpo));
            }
        }
        return re;
    }

    @Override
    public ArrayList<ClientVO> listSupplier() {
        ArrayList<ClientVO> re=new ArrayList<>();
        for(ClientPO cpo:this.list()){
            if((!cpo.getIsBan())&&(cpo.getIsVisible())&&(cpo.getCategory()==ClientIdentity.Supplier)){
                re.add(trans.transPoToVo(cpo));
            }
        }
        return re;
    }

    @Override
    public ArrayList<ClientVO> listBoth() {
        ArrayList<ClientVO> re=new ArrayList<>();
        for(ClientPO cpo:this.list()){
            if((!cpo.getIsBan())&&(cpo.getIsVisible())&&(cpo.getCategory()==ClientIdentity.SellerAndSupplier)){
                re.add(trans.transPoToVo(cpo));
            }
        }
        return re;
    }

    @Override
    public ClientPO findByID(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO p= clientDataService.read(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return p;
    }

    @Override
    public ArrayList<String> showSellerList() throws RemoteException {
        ArrayList<ClientPO> all=this.list();
        if(all==null){
            return null;
        }else{
            ArrayList<String> sellers=new ArrayList<>();
            for(ClientPO po:all){
                if((po.getCategory()== ClientIdentity.Seller||po.getCategory()==ClientIdentity.SellerAndSupplier)&&(po.getIsVisible())) {
                    sellers.add(po.getID());
                }
            }
            return sellers;
        }
    }

    @Override
    public ArrayList<String> showSupplierList() throws RemoteException{
        ArrayList<ClientPO> all=this.list();
        if(all==null){
            return null;
        }else{
            ArrayList<String> suppliers=new ArrayList<>();
            for(ClientPO po:all){
                if((po.getCategory()== ClientIdentity.Supplier||po.getCategory()==ClientIdentity.SellerAndSupplier)&&(po.getIsVisible())) {
                    suppliers.add(po.getID());
                }
            }
            return suppliers;
        }

    }

    @Override
    public ResultMessage addClient(String userID,ClientVO vo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        //判断该客户是否已经存在，如果存在，则不能添加
        ClientPO isExist=clientDataService.read(vo.getID());
        if(isExist!=null){//ID已经存在
            //System.out.println(ResultMessage.exist.getExpression());
            return ResultMessage.exist;
        }else{
            ClientPO p=new ClientPO(vo.getID(),vo.getName(),vo.getCategory(),vo.getLevel(),vo.getPhoneNum(),vo.getAddress(),vo.getPostcode(),vo.getEmail(),vo.getNote(),vo.getQuota(),vo.getToPay(),vo.getToCollect(),vo.getTotalAmount(),vo.getStaff(),true,vo.getIsBan(),true,false);
            clientDataService.write(p);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"增加客户 "+vo.toString());
            return ResultMessage.addSuccess;
        }
    }

    @Override
    public ResultMessage deleteClient(String userID,String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        if((po.getCanDel())&&(!po.getIsOn())){//通过是否能彻底删除，决定调用数据层的remove来彻底删除客户还是write来修改客户的状态
            clientDataService.remove(ID);
            logBusinessLogicControllerAccess.addLog(userID,"删除客户 "+ID+" "+" "+po.getCategory().getIdentity());
            return ResultMessage.delSuccess;
        }else{//不能彻底删除，只能从列表中移除，实际是修改isVisible
            //判断是否正有业务往来，如果有，则不能删除
            if(po.getIsOn()){
                return ResultMessage.onDeal;
            }else if(po.getLevel()>=strategyBusinessLogicControllerAccess.getLeastLevelToDel()){//当客户级别一定高时不能删除
                return ResultMessage.noPower;
            }else{//条件均满足，可以将isVisible设为false了
                po.setIsVisible(false);
                clientDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"删除客户 "+ID+" "+" "+po.getCategory().getIdentity());
                return ResultMessage.delSuccess;
            }
        }
    }

    @Override
    public ResultMessage ban(String userID,String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        if(po.getIsOn()){
            return ResultMessage.onDeal;
        }else if(po.getLevel()>= strategyBusinessLogicControllerAccess.getLeastLevelToDel()){//当客户级别一定高时不能禁用
            return ResultMessage.noPower;
        }else{
            po.setIsBan(true);
            clientDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"禁用客户 "+ID+" "+" "+po.getCategory().getIdentity());
            return ResultMessage.banSuccess;
        }
    }

    @Override
    public ResultMessage recover(String userID,String ID) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        po.setIsBan(false);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(userID,"恢复客户 "+trans.transPoToVo(po).toString());
        return ResultMessage.recoverSuccess;
    }

    /*
     *@Name: traceClient
     *@Description: 精确查找客户
     *@Author: Jane
     @Date: 2017/12/14 19:36
     */
    @Override
    public ClientVO traceClient(String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO p= clientDataService.read(ID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(p!=null&&p.getIsVisible()){
            return trans.transPoToVo(p);
        }else{
            return null;
        }
    }

    /*
     *@Name: findClient
     *@Description: 模糊查找客户.根据关键字得到客户的ID
     *@Author: Jane
     @Date: 2017/12/14 19:36
     */
    @Override
    public ArrayList<String> findClient(String keywords) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<ClientPO> ps=clientDataService.searchByKeywords(keywords);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(ps==null){
            return null;
        }else{
            ArrayList<String> vs=new ArrayList<>();
            for(ClientPO po:ps){
                if(po.getIsVisible()){
                    vs.add(po.getID());
                }
            }
            return vs;
        }
    }

    /*
     *@Name: modifyClient
     *@Description: 
     *@Author: Jane
     @Date: 2017/12/15 14:17
     */
    @Override
    public ResultMessage modifyClient(String userID,String oldID,ClientVO vo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        if(oldID.equals(vo.getID())){//没有更改ID
            ClientPO po=clientDataService.read(oldID);
            po.setName(vo.getName());
            po.setCategory(vo.getCategory());
            po.setLevel(vo.getLevel());
            po.setPhoneNum(vo.getPhoneNum());
            po.setAddress(vo.getAddress());
            po.setPostcode(vo.getPostcode());
            po.setEmail(vo.getEmail());
            po.setNote(vo.getNote());
            po.setQuota(vo.getQuota());
            po.setStaff(vo.getStaff());
            clientDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"修改客户 "+oldID+"为"+vo.toString());
            return ResultMessage.modiSuccess;
        }else{
            ClientPO isExist=clientDataService.read(vo.getID());
            if(isExist!=null){//与已有客户的ID重复
                return ResultMessage.exist;
            }else{
                ClientPO po=clientDataService.read(oldID);
                ClientPO client=new ClientPO(vo.getID(),vo.getName(),vo.getCategory(),vo.getLevel(),vo.getPhoneNum(),vo.getAddress(),vo.getPostcode(),vo.getEmail(),vo.getNote(),vo.getQuota(),vo.getToPay(),vo.getToCollect(),vo.getTotalAmount(),vo.getStaff(),po.getIsVisible(),vo.getIsBan(),po.getCanDel(),po.getIsOn());
                clientDataService.remove(oldID);
                clientDataService.write(client);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"修改客户 "+oldID+"为"+vo.toString());
                return ResultMessage.modiSuccess;
            }
        }
    }

    @Override
    public void modifyClientCanDel(String ID, boolean canDel) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        po.setCanDel(canDel);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void modifyIsOnService(String ID, boolean isOnService) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        po.setIsOn(isOnService);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();

    }

    @Override
    public void modifyClientLevel(String ID) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        int level=strategyBusinessLogicControllerAccess.checkLevel(po.getTotalAmount());
        po.setLevel(level);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

    @Override
    public void modifyReceiveMoney(String ID,double money) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        po.setToCollect(po.getToCollect()+money);
        po.setTotalAmount(po.getTotalAmount()+money);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();

    }

    @Override
    public void modifyPayMoney(String ID,double money) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ClientPO po=clientDataService.read(ID);
        po.setToPay(po.getToPay()+money);
        po.setTotalAmount(po.getTotalAmount()+money);
        clientDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

}
