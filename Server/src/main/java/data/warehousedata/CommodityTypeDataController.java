package data.warehousedata;

import dataservice.warehousedataservice.CommodityTypeDataService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import po.warehousepo.CommodityTypePO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class CommodityTypeDataController implements CommodityTypeDataService {
    private static final String listCommodityType="from CommodityTypePO";
    @Override
    public void write(CommodityTypePO commodityTypePO) {
        HibernateUtil.getCurrentSession().save(commodityTypePO);
    }

    @Override
    public CommodityTypePO read(String ID) {
        try {
            CommodityTypePO po=(CommodityTypePO) HibernateUtil.getCurrentSession().get(CommodityTypePO.class, ID);
            return po;
        }catch (HibernateException he){
            return null;
        }
    }

    @Override
    public void remove(String ID) {
        CommodityTypePO po=this.read(ID);
        if(po!=null){
            HibernateUtil.getCurrentSession().delete(po);
        }else{
            System.out.println("Error");
        }
    }

    @Override
    public void update(CommodityTypePO commodityTypePO) {
        HibernateUtil.getCurrentSession().update(commodityTypePO);
    }

    @Override
    public ArrayList<CommodityTypePO> showList() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listCommodityType);
        return new ArrayList<CommodityTypePO>(query.list());
    }

    @Override
    public ArrayList<String> findSonIDByFatherID(String fatherID) {
        String hql = "select ID FROM CommodityTypePO WHERE father_id ='"+fatherID+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
        List<String> list=query.list();
        ArrayList<String> pos=new ArrayList<>(list);
        return pos ;
    }
}
