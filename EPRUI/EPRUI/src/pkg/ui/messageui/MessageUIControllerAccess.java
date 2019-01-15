package pkg.ui.messageui;


import vo.messagevo.MessageVO;

import java.util.ArrayList;

public interface MessageUIControllerAccess {
    public static MessageUIControllerAccess messageUIControllerAccess = MessageUIController.getInstance();
    public ArrayList<MessageVO> getMessages();
    public void removeMessage(long messageId);

}
