package businesslogicservice.messagebusinesslogicservice;
/*
 *@Name: MessageBusinessLogicService
 *@Description: 消息处理的接口。有显示消息，生成消息，从界面移除消息。*注：没有从数据库删除消息的可能。
 *@author: Jane
 *@Date: Created in 2017/12/5 16:28
 */
import vo.messagevo.MessageVO;
import vo.utilityvo.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MessageBusinessLogicService extends Remote {
    void connectionTest() throws RemoteException;

    //根据当前用户的ID，返回用户发起的消息列表
    ArrayList<MessageVO> showMySendMessage(String userID) throws RemoteException;

    //根据当前用户的ID，返回用户要接收的消息列表
    ArrayList<MessageVO> showMyReceiveMessage(String userID) throws RemoteException;

    //根据消息的id删除
    ResultMessage removeMessage(long messageID, String userID) throws RemoteException;


}
