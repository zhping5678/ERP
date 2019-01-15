package data.userdata;


import dataservice.userdataservice.UserDataService;
import org.hibernate.Query;
import po.userpo.UserPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name UserDataController
 * @Description
 * @author zhangping
 * @date 2017/12/2 0002 0:28
 */
public class UserDataController implements UserDataService{

    private static final String listUser="from UserPO";
    private static final String searchByKey="from UserPO as u where u.ID like :keywords or u.name like :keywords";
    @Override
    public void write(UserPO userPO) {
        HibernateUtil.getCurrentSession().save(userPO);
    }

    @Override
    public UserPO read(String ID) {
        return (UserPO)HibernateUtil.getCurrentSession().get(UserPO.class,ID);
    }

    @Override
    public void remove(String ID){
        HibernateUtil.getCurrentSession().delete(this.read(ID));
    }

    @Override
    public void update(UserPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }


    @Override
    public ArrayList<UserPO> list(){
        Query query=HibernateUtil.getCurrentSession().createQuery(listUser);
        List<UserPO> list=query.list();
        return new ArrayList<>(list);
    }

    @Override
    public ArrayList<UserPO> searchByKeywords(String keywords) {
        Query query=HibernateUtil.getCurrentSession().createQuery(searchByKey);
        query.setString("keywords","%"+keywords+"%");
        List<UserPO> list=query.list();
        return new ArrayList<>(list);
    }
}
