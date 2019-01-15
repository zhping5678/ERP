package data.myfiledata;

import dataservice.myfiledataservice.WarehouseManDataService;
import infor.ProductItem;
import org.hibernate.Query;
import po.filepo.*;
import util.HibernateUtil;
import vo.filevo.FileState;
import vo.warehousevo.CommodityVO;
import vo.warehousevo.CheckInventoryCommodityVO;

import java.util.ArrayList;
import java.util.List;

public class WarehouseManDataController implements WarehouseManDataService {
    @Override
    public void writeExcess(ExcessPO excessPO){
        HibernateUtil.getCurrentSession().save(excessPO);
    }

    @Override
    public ExcessPO readExcess(String fileID){
        return (ExcessPO) HibernateUtil.getCurrentSession().get(ExcessPO.class,fileID);
    }

    @Override
    public void updateExcess(ExcessPO excessPO){
        HibernateUtil.getCurrentSession().update(excessPO);
    }

    @Override
    public void removeExcess(String fileID) {
        HibernateUtil.getCurrentSession().delete(this.readExcess(fileID));
    }

    @Override
    public ArrayList<ExcessPO> showList(){
        String listExcess="from ExcessPO";
        Query query=HibernateUtil.getCurrentSession().createQuery(listExcess);
        return new ArrayList<>(query.list());
    }

    /*
     *@Name:
     *@Description: 根据指定日期返回报溢报损单据
     *@Author: Jane
     @Date: 2018/1/12 20:19
     */

    @Override
    public ArrayList<ExcessPO> searchExcessByDate(String startTime, String endTime) {
        ArrayList<ExcessPO> excessPOS=new ArrayList<>();
        String hql="from ExcessPO where passTime between '"+startTime+"' and '"+endTime+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
        List<ExcessPO> list=query.list();
        for(ExcessPO po:list){
            if(po.getState().equals(FileState.REVIEWED)){
                excessPOS.add(po);
            }
        }
        return excessPOS;
    }

    @Override
    public ArrayList<PaymentPO> searchPaymentByDate(String startTime, String endTime) {
        return null;
    }

    @Override
    public ArrayList<ReceiptPO> searchReceiptByDate(String startTime, String endTime) {
        return null;
    }

    @Override
    public ArrayList<PurchasePO> searchPurchaseByDate(String startTime, String endTime) {
        ArrayList<PurchasePO> purchasePOS=new ArrayList<>();
        String hql="from PurchasePO where date between '"+startTime+"' and '"+endTime+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
        List<PurchasePO> list=query.list();
        for(PurchasePO po:list){
            if(po.getState().equals(FileState.REVIEWED)){
                purchasePOS.add(po);
            }
        }
        return purchasePOS;
    }

    @Override
    public ArrayList<SalePO> searchSaleByDate(String startTime, String endTime) {
        ArrayList<SalePO> salePOS=new ArrayList<>();
        String hql="from SalePO where searchTime between '"+startTime+"' and '"+endTime+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(hql);
        List<SalePO> list=new ArrayList<>(query.list());
        for(SalePO po:list){
            if(po.getState().equals(FileState.REVIEWED)){
                salePOS.add(po);
            }
        }
        return salePOS;
    }

    @Override
    public ArrayList<SaleReturnPO> searchSaleReturnByDate(String startTime, String endTime) {
        ArrayList<SaleReturnPO> saleReturnPOS=new ArrayList<>();
        String sql="from SaleReturnPO where searchDate between'"+startTime+"' and '"+endTime+"'";
        Query query=HibernateUtil.getCurrentSession().createQuery(sql);
        List<SaleReturnPO> list=query.list();
        for(SaleReturnPO po:list){
            if(po.getState().equals(FileState.REVIEWED)){
                saleReturnPOS.add(po);
            }
        }
        return saleReturnPOS;
    }

}
