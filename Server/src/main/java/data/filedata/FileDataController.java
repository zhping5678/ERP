package data.filedata;


import data.databaseutility.DatabaseUtilityControllerAccess;
import dataservice.filedataservice.FileDataService;
import org.hibernate.Query;
import po.filepo.*;
import util.HibernateUtil;
import vo.filevo.FileState;

import java.util.ArrayList;

public class FileDataController implements FileDataService{
    DatabaseUtilityControllerAccess databaseUtilityControllerAccess;
    @Override
    public ArrayList<String> listExcessPO() {
        ArrayList<String> res=new ArrayList<>();
        String listExcessOrLoss="from ExcessPO";
        Query query= HibernateUtil.getCurrentSession().createQuery(listExcessOrLoss);
        ArrayList<ExcessPO> excessPOS=new ArrayList<ExcessPO>(query.list());
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getExcessID());
            }
        }
        return res;
    }

    @Override
    public ArrayList<String> listPaymentPO() {
        ArrayList<String> res=new ArrayList<>();
        String listPayment="from PaymentPO";
        Query query= HibernateUtil.getCurrentSession().createQuery(listPayment);
        ArrayList<PaymentPO> paymentPOS=new ArrayList<>(query.list());
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getPaymentID());
            }
        }
        return res;
    }

    @Override
    public ArrayList<String> listPurchasepo() {
        ArrayList<String> res=new ArrayList<>();
        String listPurchase="from PurchasePO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listPurchase);
        ArrayList<PurchasePO> purchasePOS=new ArrayList<>(query.list());
        for(PurchasePO po:purchasePOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getID());
            }
        }
        return res;
    }

    @Override
    public ArrayList<String> listReceiptPO() {
        ArrayList<String> res=new ArrayList<>();
        String listReceipt="from ReceiptPO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listReceipt);
        ArrayList<ReceiptPO> receiptPOS=new ArrayList<>(query.list());
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getId());
            }
        }
        return res;
    }

    @Override
    public ArrayList<String> listSalePO() {
        ArrayList<String> res=new ArrayList<>();
        String listSale="from SalePO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listSale);
        ArrayList<SalePO> salePOS=new ArrayList<>(query.list());
        for(SalePO po:salePOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getID());
            }
        }
        return res;
    }

    @Override
    public ArrayList<String> listSaleReturnPO() {
        ArrayList<String> res=new ArrayList<>();
        String listSaleReturn="from SaleReturnPO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listSaleReturn);
        ArrayList<SaleReturnPO> saleReturnPOS=new ArrayList<>(query.list());
        for(SaleReturnPO po:saleReturnPOS){
            if(po.getState().equals(FileState.REVIEWED)){
                res.add(po.getID());
            }
        }
        return res;
    }

    @Override
    public void remove(String ID) {


    }

    @Override
    public FilePO read(String ID) {
        return null;
    }

    @Override
    public void write(FilePO filePO) {

    }
}
