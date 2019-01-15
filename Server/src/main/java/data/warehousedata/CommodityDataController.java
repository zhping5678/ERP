package data.warehousedata;

import data.databaseutility.DatabaseUtilityControllerAccess;
import dataservice.warehousedataservice.CommodityDataService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import po.warehousepo.CommodityPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CommodityDataController implements CommodityDataService{
    DatabaseUtilityControllerAccess databaseUtilityControllerAccess;
    private static final String listCommodity="from CommodityPO";
    private static final String searchByKey="from CommodityPO as c where c.ID like :keywords or c.name like :keywords";
    @Override
    public CommodityPO read(String ID) {
        try {
            CommodityPO po=(CommodityPO) HibernateUtil.getCurrentSession().get(CommodityPO.class, ID);
            return po;
        }catch (HibernateException he){
            return null;
        }
    }

    @Override
    public void remove(String ID) {
        CommodityPO po=this.read(ID);
        HibernateUtil.getCurrentSession().delete(po);
    }

    @Override
    public ArrayList<CommodityPO> showList() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listCommodity);
        return new ArrayList<CommodityPO>(query.list());
    }

    @Override
    public ArrayList<CommodityPO> findByKeywords(String keywords) {
        Query query=HibernateUtil.getCurrentSession().createQuery(searchByKey);
        query.setString("keywords","%"+keywords+"%");
        List<CommodityPO> list=query.list();
        return new ArrayList<>(list);
    }

    @Override
    public void write(CommodityPO commodityPO) {
        HibernateUtil.getCurrentSession().save(commodityPO);
    }

    @Override
    public void update(CommodityPO commodityPO){
        HibernateUtil.getCurrentSession().update(commodityPO);
    }

    @Override
    public ArrayList<String> findSonIDByFatherID(String fatherID) {
        String hql = "select ID FROM CommodityPO WHERE father_id ='"+fatherID+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
        List<String> list=query.list();
        ArrayList<String> pos=new ArrayList<>(list);
        return pos;
    }


}
