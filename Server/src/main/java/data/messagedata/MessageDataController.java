package data.messagedata;

/*
 *@Name: MessageDataController
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/5 15:15
 */

import dataservice.messagedataservice.MessageDataService;
import org.hibernate.Query;
import po.messagepo.MessagePO;
import util.HibernateUtil;

import java.util.ArrayList;

public class MessageDataController  implements MessageDataService{
    private static final String listMySender="from MessagePO";//" as m where m.sender =: senderid";
    @Override
    public void write(MessagePO messagePO) {
        HibernateUtil.getCurrentSession().save(messagePO);
    }

    @Override
    public MessagePO read(long ID) {
        return (MessagePO) HibernateUtil.getCurrentSession().get(MessagePO.class,ID);
    }

    @Override
    public void update(MessagePO mpo) {
        HibernateUtil.getCurrentSession().update(mpo);
    }

    @Override
    public ArrayList<MessagePO> showList(String userID) {
        Query query=HibernateUtil.getCurrentSession().createQuery(listMySender);
        return new ArrayList<>(query.list());
    }

    @Override
    public boolean remove(long ID) {
        HibernateUtil.getCurrentSession().delete(this.read(ID));
        return false;
    }
}
