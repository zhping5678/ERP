package util;

import data.id.IdData;
import dataservice.idservice.IdDataService;
import infor.IndexInfor;
import vo.filevo.FileType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDUtil {
    private IdDataService idDataService;
    private IndexInfor indexInfor;

    public IDUtil(){
        HibernateUtil.getCurrentSession().beginTransaction();
        idDataService=new IdData();
        indexInfor=idDataService.read(1);
        this.updateIndexInfor();
    }
    private void updateIndexInfor(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String time = dateFormat.format(new Date());
        if(!time.equals(this.getTime())){//New Day
            indexInfor.setTime(time);
            indexInfor.setSale_num(0);
            indexInfor.setSaleReturn_num(0);
            indexInfor.setPurchase_num(0);
            indexInfor.setPurchaseReturn_num(0);
            indexInfor.setPayment_num(0);
            indexInfor.setReceipt_num(0);
            indexInfor.setLevel_num(0);
            indexInfor.setGift_num(0);
            indexInfor.setDiscount_num(0);
            indexInfor.setPricePack_num(0);
            indexInfor.setVoucher_num(0);
            indexInfor.setRb_num(0);
            indexInfor.setExcess_num(0);
            indexInfor.setLoss_num(0);
            HibernateUtil.getCurrentSession().beginTransaction();
            idDataService.update(indexInfor);
            HibernateUtil.getCurrentSession().getTransaction().commit();
        }
    }
    public int getID(FileType type){
        switch (type){
            case SALE:
                return indexInfor.getSale_num()+1;
            case PURCHASE:
                return indexInfor.getPurchase_num()+1;
            case SALERETURN:
                return indexInfor.getSaleReturn_num()+1;
            case PURCHASERETURN:
                return indexInfor.getPurchaseReturn_num()+1;
            case PAYMENT:
                return indexInfor.getPayment_num()+1;
            case RECEIPT:
                return indexInfor.getReceipt_num()+1;
            case REIMBURSEMENT:
                return indexInfor.getRb_num()+1;
            case LEVEL:
                return indexInfor.getLevel_num()+1;
            case GIFT:
                return indexInfor.getGift_num()+1;
            case DISCOUNT:
                return indexInfor.getDiscount_num()+1;
            case PRICEPACK:
                return indexInfor.getPricePack_num()+1;
            case VOUCHER:
                return indexInfor.getVoucher_num()+1;
            case EXCESS:
                return indexInfor.getExcess_num()+1;
            case LOSS:
                return indexInfor.getLoss_num()+1;
            default:
                return -1;
        }
    }

    public String getTime(){
        return this.indexInfor.getTime();
    }

    public void increment(FileType type){
        switch (type){
            case SALE:
                indexInfor.setSale_num(indexInfor.getSale_num()+1);
                break;
            case PURCHASE:
                indexInfor.setPurchase_num(indexInfor.getPurchase_num()+1);
                break;
            case SALERETURN:
                indexInfor.setSaleReturn_num(indexInfor.getSaleReturn_num()+1);
                break;
            case PURCHASERETURN:
                indexInfor.setPurchaseReturn_num(indexInfor.getPurchaseReturn_num()+1);
                break;
            case PAYMENT:
                indexInfor.setPayment_num(indexInfor.getPayment_num()+1);
                break;
            case RECEIPT:
                indexInfor.setReceipt_num(indexInfor.getReceipt_num()+1);
                break;
            case LEVEL:
                indexInfor.setLevel_num(indexInfor.getLevel_num()+1);
                break;
            case GIFT:
                indexInfor.setGift_num(indexInfor.getGift_num()+1);
                break;
            case DISCOUNT:
                indexInfor.setDiscount_num(indexInfor.getDiscount_num()+1);
                break;
            case PRICEPACK:
                indexInfor.setPricePack_num(indexInfor.getPricePack_num()+1);
                break;
            case VOUCHER:
                indexInfor.setVoucher_num(indexInfor.getVoucher_num()+1);
                break;
            case REIMBURSEMENT:
                indexInfor.setRb_num(indexInfor.getRb_num()+1);
                break;
            case EXCESS:
                indexInfor.setExcess_num(indexInfor.getExcess_num()+1);
                break;
            case LOSS:
                indexInfor.setLoss_num(indexInfor.getLoss_num()+1);
                break;
            default:
        }
        HibernateUtil.getCurrentSession().beginTransaction();
        idDataService.update(indexInfor);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }
}
