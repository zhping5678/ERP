package dataservice.messagedataservice;

/*
 *@Name: MessageDataService
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/5 15:15
 */
import po.messagepo.MessagePO;

import java.util.ArrayList;

public interface MessageDataService {
    void write(MessagePO messagePO);
    MessagePO read(long ID);
    void update(MessagePO mpo);
    ArrayList<MessagePO> showList(String userID);
    boolean remove(long ID);
}
