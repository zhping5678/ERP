package businesslogic.messagebusinesslogic;

import java.util.ArrayList;

/*
 *@Name: MessageBusinessLogicControllerAccess
 *@Description: 提供层间接口，生成一条messagevo
 *@Author: Jane
 *@Date: 2017/12/5 14:34
 */
public interface MessageBusinessLogicControllerAccess {
    void addMessage(String userID, String event, ArrayList<String> receivers,boolean canDel);
    void addMessage(String userID,String event,String receivers,boolean canDel);
}
