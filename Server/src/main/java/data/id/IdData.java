package data.id;

import dataservice.idservice.IdDataService;
import infor.IndexInfor;
import util.HibernateUtil;

public class IdData implements IdDataService {

    @Override
    public IndexInfor read(int ID) {
        return (IndexInfor) HibernateUtil.getCurrentSession().get(IndexInfor.class,ID);
    }

    @Override
    public void update(IndexInfor indexInfor) {
        HibernateUtil.getCurrentSession().update(indexInfor);
    }
}
