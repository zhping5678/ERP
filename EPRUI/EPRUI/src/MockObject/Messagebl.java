package MockObject;

import Start.RemoteHelper;
import vo.messagevo.MessageVO;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Messagebl {
    //根据当前用户的ID，返回用户发起的消息列表
    public ArrayList<MessageVO> showMySendMessage(String userID){

        ArrayList<MessageVO> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getMessageBusinessLogicService().showMySendMessage(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    //根据当前用户的ID，返回用户要接收的消息列表
    public ArrayList<MessageVO> showMyReceiveMessage(String userID){
        ArrayList<MessageVO> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getMessageBusinessLogicService().showMyReceiveMessage(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    //根据消息的id删除
    public ResultMessage removeMessage(long messageID, String userID){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getMessageBusinessLogicService().removeMessage(messageID,userID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }
}
