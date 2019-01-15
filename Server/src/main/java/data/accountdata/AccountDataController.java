package data.accountdata;

import dataservice.accountdataservice.AccountDataService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import po.accountpo.AccountPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountDataController implements AccountDataService{

    private static final String listAccount="from AccountPO";
    private static final String searchByKey="from AccountPO as c where c.ID like :keywords or c.name like :keywords";

    @Override
    public AccountPO read(String ID) {
        return (AccountPO) HibernateUtil.getCurrentSession().get(AccountPO.class, ID);

    }

    @Override
    public void remove(String ID) {
        AccountPO po=this.read(ID);
        HibernateUtil.getCurrentSession().delete(po);
    }

    @Override
    public ArrayList<AccountPO> showList() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listAccount);
        return new ArrayList<AccountPO>(query.list());
    }

    @Override
    public void write(AccountPO po) {
       HibernateUtil.getCurrentSession().save(po);
    }

    @Override
    public ArrayList<AccountPO> findByKeywords(String keywords) {
        Query query=HibernateUtil.getCurrentSession().createQuery(searchByKey);
        query.setString("keywords","%"+keywords+"%");
        List<AccountPO> list=query.list();
        System.out.println("data"+list.size());
        return new ArrayList<>(list);

    }

    @Override
    public void update(AccountPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }

//    public ArrayList<String> test(String a){
//        String hql = "select ID FROM AccountPO E WHERE E.name ='zju'";
//        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
//        List<String> list=query.list();
//        return new ArrayList<>(list);
//    }


}
