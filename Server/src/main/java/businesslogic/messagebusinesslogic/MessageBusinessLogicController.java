package businesslogic.messagebusinesslogic;
/*
 *@Name: MessageBusinessLogicController
 *@Description: 消息通知的实现。将消息的时间作为该消息的id.
 *@Author: Jane
 *@Date: 2017/12/5 15:15
 */

import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.messagedataservice.MessageDataService;
import org.hibernate.HibernateException;
import po.messagepo.MessagePO;
import util.HibernateUtil;
import vo.messagevo.MessageVO;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MessageBusinessLogicController extends UnicastRemoteObject implements MessageBusinessLogicControllerAccess,MessageBusinessLogicService {

    private MessageDataService messageDataService;
    private MessageTransVOPO trans;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    public MessageBusinessLogicController()throws RemoteException{
        messageDataService= DataFactoryImpl.getInstance().getMessageDataService();
        trans=new MessageTransVOPO();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("MessageBusinessLogicService is connected.\n");
    }

    /*
     *@Name: showMessage()
     *@Description: 把所有没被使用者移除的消息返回
     *@author: Jane
     *@Date: Created in 2017/12/5 16:38
     */
    public ArrayList<MessagePO> list(String userID){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<MessagePO> po=messageDataService.showList(userID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return po;
    }

    @Override
    public ArrayList<MessageVO> showMySendMessage(String userID) throws RemoteException {
        ArrayList<MessageVO> vo=new ArrayList<>();
        for(MessagePO p:this.list(userID)){
            if(p.getSender().equals(userID))
            vo.add(trans.transPoToVo(p));
        }
        return vo;
    }

    @Override
    public ArrayList<MessageVO> showMyReceiveMessage(String userID) throws RemoteException {
        ArrayList<MessageVO> vo=new ArrayList<>();
        for(MessagePO p:this.list(userID)){
            Set<String> receiver=new HashSet<>(p.getReceiver());
            if(receiver.contains(userID)){
                vo.add(trans.transPoToVo(p));
                //System.err.println();
            }
        }
        return vo;
    }

    /*
     *@Name: removeMessage
     *@Description: 从界面删除一条消息
     *@author: Jane
     *@Date: Created in 2017/12/5 16:35
     */
    @Override
    public ResultMessage removeMessage(long messageID,String userID) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            MessagePO mpo=messageDataService.read(messageID);
            Set<String> receivers=mpo.getReceiver();
            receivers.remove(userID);
            if(receivers.size()==0){
                messageDataService.remove(messageID);
            }else{
                mpo.setReceiver(receivers);
                messageDataService.update(mpo);
            }
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"删除"+mpo.getSender()+"发送的"+mpo.getEvent());
            return ResultMessage.delSuccess;
        }catch (HibernateException he){
            return ResultMessage.Fail;
        }

    }

    @Override
    public void addMessage(String senderID, String event,ArrayList<String> receivers,boolean candel){
        //根据系统时间自动生成
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        MessagePO mpo=new MessagePO();
        mpo.setTime(time);
        mpo.setEvent(event);
        mpo.setSender(senderID);
        Set<String> receive=new HashSet<>();
//        for(String s:receivers){
//            receive.add(s);
//        }
        receive.addAll(receivers);
        mpo.setReceiver(receive);
        mpo.setCanDel(candel);
        HibernateUtil.getCurrentSession().beginTransaction();
        messageDataService.write(mpo);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(senderID,"向"+changeArrayToString(receivers)+"发送消息“"+event+"”");

    }

    @Override
    public void addMessage(String userID, String event, String receivers, boolean canDel) {
        ArrayList<String> receiver=new ArrayList<>();
        receiver.add(receivers);
        this.addMessage(userID,event,receiver,canDel);
    }

    private String changeArrayToString(ArrayList<String> toChange){
        StringBuilder re= new StringBuilder();
        for(String a:toChange){
            re.append(a).append(" ");
        }
        return re.toString();
    }
}

