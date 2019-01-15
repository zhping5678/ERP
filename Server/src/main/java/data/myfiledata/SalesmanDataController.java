package data.myfiledata;

import dataservice.myfiledataservice.SalesmanDataService;
import org.hibernate.Query;
import po.filepo.PurchasePO;
import po.filepo.PurchaseReturnPO;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;
import util.HibernateUtil;
import vo.filevo.FileType;

import java.util.ArrayList;

public class SalesmanDataController implements SalesmanDataService{
    private static final String listPurchase="from PurchasePO as p where p.fileType=:type";
    private static final String listPurchaseReturn="from PurchaseReturnPO as p where p.fileType=:type";
    private static final String listSale="from SalePO";
    private static final String listSaleReturn="from SaleReturnPO";

    //进货单
    @Override
    public ArrayList<PurchasePO> listPurchase() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listPurchase);
        query.setString("type", FileType.PURCHASE.toString());
        return new ArrayList<>(query.list());
    }
    @Override
    public void writePurchase(PurchasePO purchasePO) {
        HibernateUtil.getCurrentSession().save(purchasePO);
    }
    @Override
    public PurchasePO readPurchase(String id) {
        return (PurchasePO)HibernateUtil.getCurrentSession().get(PurchasePO.class,id);
    }
    @Override
    public void removePurchase(String id) {
        HibernateUtil.getCurrentSession().delete(this.readPurchase(id));
    }
    @Override
    public void updatePurchase(PurchasePO purchasePO) {
        HibernateUtil.getCurrentSession().update(purchasePO);
    }

    //进货退货单
    @Override
    public ArrayList<PurchaseReturnPO> listPurchaseReturn() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listPurchaseReturn);
        query.setString("type",FileType.PURCHASERETURN.toString());
        return new ArrayList<>(query.list());
    }
    @Override
    public void writePurchaseReturn(PurchaseReturnPO purchaseReturnPO) {
        HibernateUtil.getCurrentSession().save(purchaseReturnPO);
    }
    @Override
    public PurchaseReturnPO readPurchaseReturn(String id) {
        return (PurchaseReturnPO)HibernateUtil.getCurrentSession().get(PurchaseReturnPO.class,id);
    }
    @Override
    public void removePurchaseReturn(String id) {
        HibernateUtil.getCurrentSession().delete(this.readPurchaseReturn(id));
    }
    @Override
    public void updatePurchaseReturn(PurchaseReturnPO purchaseReturnPO) {
        HibernateUtil.getCurrentSession().merge(purchaseReturnPO);
    }

    //销售单
    @Override
    public ArrayList<SalePO> listSale() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listSale);
        return new ArrayList<>(query.list());
    }
    @Override
    public void writeSale(SalePO salePO) {
        HibernateUtil.getCurrentSession().save(salePO);
    }
    @Override
    public SalePO readSale(String id) {
        return (SalePO)HibernateUtil.getCurrentSession().get(SalePO.class,id);
    }
    @Override
    public void removeSale(String id) {
        HibernateUtil.getCurrentSession().delete(this.readSale(id));
    }
    @Override
    public void updateSale(SalePO salePO) {
        HibernateUtil.getCurrentSession().update(salePO);
    }

    //销售退货单
    @Override
    public ArrayList<SaleReturnPO> listSaleReturn() {
        Query query=HibernateUtil.getCurrentSession().createQuery(listSaleReturn);
        return new ArrayList<>(query.list());
    }
    @Override
    public void writeSaleReturn(SaleReturnPO saleReturnPO) {
        HibernateUtil.getCurrentSession().save(saleReturnPO);
    }
    @Override
    public SaleReturnPO readSaleReturn(String id) {
        return (SaleReturnPO)HibernateUtil.getCurrentSession().get(SaleReturnPO.class,id);
    }
    @Override
    public void removeSaleReturn(String id) {
        HibernateUtil.getCurrentSession().delete(this.readSaleReturn(id));
    }
    @Override
    public void updateSaleReturn(SaleReturnPO saleReturn) {
        HibernateUtil.getCurrentSession().update(saleReturn);
    }
}
