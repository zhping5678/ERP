package pkg.ui.messageui;

import pkg.ui.RemoteHelper;
import pkg.ui.settingui.SettingUIControllerAccess;
import vo.messagevo.MessageVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

class MessageUIController implements MessageUIControllerAccess{
    private static MessageUIController messageUIController;

    static MessageUIController getInstance(){
        if (messageUIController==null){
            messageUIController=new MessageUIController();
        }
        return messageUIController;
    }

    @Override
    public ArrayList<MessageVO> getMessages() {
        ArrayList<MessageVO> messageVOArrayList = null;
        try {
            System.out.println("username:"+SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());//////////////////
            messageVOArrayList=RemoteHelper.getMessageBusinessLogicService().showMyReceiveMessage(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());
        } catch (RemoteException e) {
            System.out.println("failed get messages");

        }

        System.out.println("messages numbers  "+messageVOArrayList.size());

        return messageVOArrayList;
    }
    public void removeMessage(long messageId){
        try{
            RemoteHelper.getMessageBusinessLogicService().removeMessage(messageId,SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());

        }catch (RemoteException e){

        }

    }
}
