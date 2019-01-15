package data.logdata;

import dataservice.logdataservice.LogDataService;
import org.hibernate.Query;

import po.logpo.LogPO;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class LogDataController implements LogDataService{

    private static final String logList="from LogPO";
    private static final String searchHQL="from LogPO as l where l.time like :keywords or l.id like :keywords or l.event like :keywords";

    @Override
    public ArrayList<LogPO> Search(String keywords) {
        Query query=HibernateUtil.getCurrentSession().createQuery(searchHQL);
        query.setString("keywords","%"+keywords+"%");
        List<LogPO> list=query.list();
        return new ArrayList<>(list);
    }

    @Override
    public ArrayList<LogPO> list() {
        Query query=HibernateUtil.getCurrentSession().createQuery(logList);
        return new ArrayList<LogPO>(query.list());
    }

    @Override
    public void write(LogPO logPO) {
        HibernateUtil.getCurrentSession().save(logPO);
    }
}
