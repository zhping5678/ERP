package data.myfiledata;

import dataservice.myfiledataservice.AccountManDataService;
import org.hibernate.Query;
import po.filepo.PaymentPO;
import po.filepo.ReceiptPO;
import util.HibernateUtil;
import vo.utilityvo.ResultMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountManDataController implements AccountManDataService {
    @Override
    public void writeReceipt(ReceiptPO receiptPO) {
        HibernateUtil.getCurrentSession().save(receiptPO);
    }

    @Override
    public ReceiptPO readReceipt(String ID){
//        String hql = "FROM ReceiptPO as a WHERE a.id ='"+ID+"'";
//        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
//        List<ReceiptPO> list=query.list();
//        ArrayList<ReceiptPO> pos=new ArrayList<>(list);
//        return pos.get(0);
        return (ReceiptPO)HibernateUtil.getCurrentSession().get(ReceiptPO.class,ID);
    }

    @Override
    public void updateReceipt(ReceiptPO receiptPO){
        HibernateUtil.getCurrentSession().update(receiptPO);
    }

    @Override
    public void removeReceipt(String ID){
        HibernateUtil.getCurrentSession().delete(this.readReceipt(ID));
    }

    @Override
    public ArrayList<ReceiptPO> listReceipt() {
        String listReceipt="from ReceiptPO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listReceipt);
        return new ArrayList<>(query.list());
    }

    @Override
    public void writePayment(PaymentPO paymentPO){
        HibernateUtil.getCurrentSession().save(paymentPO);
    }

    @Override
    public PaymentPO readPayment(String ID) {
//        String hql="FROM PaymentPO as a WHERE a.paymentID ='"+ID+"'";
//        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
//        List<PaymentPO> paymentPOS=query.list();
//        ArrayList<PaymentPO> paymentPOS1=new ArrayList<>(paymentPOS);
//        return paymentPOS1.get(0);
        return (PaymentPO)HibernateUtil.getCurrentSession().get(PaymentPO.class,ID);
    }

    @Override
    public void updatePayment(PaymentPO paymentPO) {
        HibernateUtil.getCurrentSession().merge(paymentPO);
//        HibernateUtil.getCurrentSession().update(paymentPO);
    }

    @Override
    public void removePayment(String ID) {
        HibernateUtil.getCurrentSession().delete(this.readPayment(ID));
    }

    @Override
    public ArrayList<PaymentPO> listPayment() {
        String listPayment="from PaymentPO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listPayment);
        return new ArrayList<>(query.list());
    }

    @Override
    public  ArrayList<String> dateRange(String start,String end){
//        Date beginDate=java.sql.Date.valueOf(start);
//        Date endDate=java.sql.Date.valueOf(end);
//        String hql = "from Uploadinfo info where info.dataTime <:endDate and info.dataTime >=:beginDate";
//        Query query = itemDaoImpl.getQuery(hql);
//        query.setDate("beginDate",beginDate);
//        query.setDate("endDate",endDate);
//        List<Uploadinfo> infolist = query.list();
//        return infolist;

        return null;
    }

}
