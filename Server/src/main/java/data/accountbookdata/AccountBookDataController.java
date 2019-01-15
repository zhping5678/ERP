package data.accountbookdata;

import dataservice.accountbookdataservice.AccountBookDataService;
import infor.AccountBookInfor;
import org.hibernate.Query;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class AccountBookDataController implements AccountBookDataService {

    private static final String listAccountBookInfor="from AccountBookInfor";
    @Override
    public AccountBookInfor read(String ID) {
        return (AccountBookInfor) HibernateUtil.getCurrentSession().get(AccountBookInfor.class,ID);
    }

    @Override
    public ArrayList<AccountBookInfor> list() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listAccountBookInfor);
        return new ArrayList<>(query.list());

    }

    @Override
    public void write(AccountBookInfor accountBookInfor) {
        HibernateUtil.getCurrentSession().save(accountBookInfor);
    }
}
