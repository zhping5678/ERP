package data.warehousedata;

import dataservice.warehousedataservice.WarehouseDataService;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import po.warehousepo.WarehousePO;
import util.HibernateUtil;

import java.util.ArrayList;

public class WarehouseDataController implements WarehouseDataService {
    private static final String listWarehouse="from WarehousePO";
    @Override
    public void write(WarehousePO warehousePO) {
        HibernateUtil.getCurrentSession().save(warehousePO);
    }

    @Override
    public WarehousePO read(String ID) {
        try {
            WarehousePO po=(WarehousePO) HibernateUtil.getCurrentSession().get(WarehousePO.class, ID);
            return po;
        }catch (HibernateException he){
            return null;
        }
    }

    @Override
    public void remove(String ID) {
        WarehousePO po=this.read(ID);
        HibernateUtil.getCurrentSession().delete(po);
    }

    @Override
    public void update(WarehousePO warehousePO) {
        HibernateUtil.getCurrentSession().merge(warehousePO);
    }

    @Override
    public ArrayList<WarehousePO> showList() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listWarehouse);
        return new ArrayList<WarehousePO>(query.list());
    }

//    @Override
//    public ArrayList<String> findSonIDByFatherID(String fatherID) {
//        return null;
//    }
}
