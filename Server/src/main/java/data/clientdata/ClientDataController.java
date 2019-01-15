package data.clientdata;


import dataservice.clientdataservice.ClientDataService;
import org.hibernate.Query;
import po.clientpo.ClientPO;
import util.HibernateUtil;

import java.util.ArrayList;

public class ClientDataController implements ClientDataService{

    private static final String listClient="from ClientPO";
    private static final String searchByKeywords="from ClientPO as c where c.ID like :keywords or c.name like :keywords";
    @Override
    public ArrayList<ClientPO> list() {
        Query query= HibernateUtil.getCurrentSession().createQuery(listClient);
        return new ArrayList<>(query.list());
    }

    @Override
    public ArrayList<ClientPO> searchByKeywords(String keywords) {
        Query query=HibernateUtil.getCurrentSession().createQuery(searchByKeywords);
        query.setString("keywords","%"+keywords+"%");
        return new ArrayList<>(query.list());
    }

    @Override
    public void remove(String ID) {
        HibernateUtil.getCurrentSession().delete(this.read(ID));
    }

    @Override
    public void write(ClientPO clientPO) {
        HibernateUtil.getCurrentSession().save(clientPO);
    }

    @Override
    public ClientPO read(String ID) {
        return (ClientPO)HibernateUtil.getCurrentSession().get(ClientPO.class,ID);
    }

    @Override
    public void update(ClientPO clientPO){
        HibernateUtil.getCurrentSession().update(clientPO);
    }
}
