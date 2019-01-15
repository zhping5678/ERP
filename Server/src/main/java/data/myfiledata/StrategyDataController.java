package data.myfiledata;

import dataservice.myfiledataservice.StrategyDataService;
import org.hibernate.Query;
import po.filepo.StrategyPO.*;
import util.HibernateUtil;

import java.util.ArrayList;


public class StrategyDataController implements StrategyDataService {

    //分级方案
    @Override
    public LevelStrategyPO readLevelStrategy(String id) {
        return (LevelStrategyPO) HibernateUtil.getCurrentSession().get(LevelStrategyPO.class,id);
    }
    @Override
    public LevelStrategyPO readNowLevelStrategy() {
        for(LevelStrategyPO lpo:this.listLevelStrategy()){
            if(lpo.getIsUsing()){
                return lpo;
            }
        }
        return null;
    }
    @Override
    public void write(LevelStrategyPO po) {
        HibernateUtil.getCurrentSession().save(po);
    }
    @Override
    public void removeLevelStrategy(String id) {
        HibernateUtil.getCurrentSession().delete(this.readLevelStrategy(id));
    }
    @Override
    public void update(LevelStrategyPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }
    @Override
    public ArrayList<LevelStrategyPO> listLevelStrategy() {
        Query query=HibernateUtil.getCurrentSession().createQuery("from LevelStrategyPO");
        return new ArrayList<>(query.list());
    }

    //赠品策略
    @Override
    public GiftStrategyPO readGiftStrategy(String id) {
        return (GiftStrategyPO)HibernateUtil.getCurrentSession().get(GiftStrategyPO.class,id);
    }
    @Override
    public ArrayList<GiftStrategyPO> readNowGiftStrategy() {
        ArrayList<GiftStrategyPO> now=new ArrayList<>();
        for(GiftStrategyPO gpo:this.listGiftStrategy()){
            if(gpo.getIsUsing()){
                now.add(gpo);
            }
        }
        return now;
    }
    @Override
    public void write(GiftStrategyPO po) {
        HibernateUtil.getCurrentSession().save(po);
    }
    @Override
    public void update(GiftStrategyPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }
    @Override
    public ArrayList<GiftStrategyPO> listGiftStrategy() {
        Query query=HibernateUtil.getCurrentSession().createQuery("from GiftStrategyPO");
        return new ArrayList<>(query.list());
    }
    @Override
    public void removeGiftStrategy(String id) {
        HibernateUtil.getCurrentSession().delete(this.readGiftStrategy(id));
    }

    //折扣策略
    @Override
    public DiscountStrategyPO readDiscountStrategy(String id) {
        return (DiscountStrategyPO) HibernateUtil.getCurrentSession().get(DiscountStrategyPO.class,id);
    }
    @Override
    public DiscountStrategyPO readNowDiscountStrategy() {
        for(DiscountStrategyPO p:this.listDiscountStrategy()){
            if(p.getIsUsing()){
                return p;
            }
        }
        return null;
    }
    @Override
    public void write(DiscountStrategyPO po) {
        HibernateUtil.getCurrentSession().save(po);
    }
    @Override
    public void update(DiscountStrategyPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }
    @Override
    public ArrayList<DiscountStrategyPO> listDiscountStrategy() {
        Query query=HibernateUtil.getCurrentSession().createQuery("from DiscountStrategyPO");
        return new ArrayList<>(query.list());
    }
    @Override
    public void removeDiscountStrategy(String id) {
        HibernateUtil.getCurrentSession().delete(this.readDiscountStrategy(id));
    }

    //特价包策略
    @Override
    public PricePackStrategyPO readPricePackStrategy(String id) {
        return(PricePackStrategyPO)HibernateUtil.getCurrentSession().get(PricePackStrategyPO.class,id);
    }
    @Override
    public ArrayList<PricePackStrategyPO> readNowPricePackStrategy() {
        ArrayList<PricePackStrategyPO> now=new ArrayList<>();
        for(PricePackStrategyPO p:this.listPricePackStrategy()){
            if(p.getIsUsing()){
                now.add(p);
            }
        }
        return now;
    }
    @Override
    public void write(PricePackStrategyPO po) {
        HibernateUtil.getCurrentSession().save(po);
    }
    @Override
    public void update(PricePackStrategyPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }
    @Override
    public ArrayList<PricePackStrategyPO> listPricePackStrategy() {
        Query query=HibernateUtil.getCurrentSession().createQuery("from PricePackStrategyPO ");
        return new ArrayList<>(query.list());
    }
    @Override
    public void removePricePackStrategy(String id) {
        HibernateUtil.getCurrentSession().delete(this.readPricePackStrategy(id));
    }

    //代金券方案
    @Override
    public VoucherStrategyPO readVoucherStrategy(String id) {
        return (VoucherStrategyPO)HibernateUtil.getCurrentSession().get(VoucherStrategyPO.class,id);
    }
    @Override
    public VoucherStrategyPO readNowVoucherStrategy() {
        for(VoucherStrategyPO p:this.listVoucherStrategy()){
            if(p.getIsUsing()){
                return p;
            }
        }
        return null;
    }
    @Override
    public void write(VoucherStrategyPO po) {
        HibernateUtil.getCurrentSession().save(po);
    }
    @Override
    public void update(VoucherStrategyPO po) {
        HibernateUtil.getCurrentSession().update(po);
    }
    @Override
    public ArrayList<VoucherStrategyPO> listVoucherStrategy() {
        Query query=HibernateUtil.getCurrentSession().createQuery("from VoucherStrategyPO");
        return new ArrayList<>(query.list());
    }
    @Override
    public void removeVoucherStrategy(String id) {
        HibernateUtil.getCurrentSession().delete(this.readVoucherStrategy(id));
    }
}

