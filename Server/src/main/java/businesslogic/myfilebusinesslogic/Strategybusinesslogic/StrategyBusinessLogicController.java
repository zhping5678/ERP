package businesslogic.myfilebusinesslogic.Strategybusinesslogic;

import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.userbusinesslogic.UserBusinessLogicController;
import businesslogic.userbusinesslogic.UserBusinessLogicControllerAccess;
import businesslogicservice.myfilebusinesslogicservice.StrategyBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.myfiledataservice.StrategyDataService;
import infor.ProductItem;
import org.hibernate.HibernateException;
import po.filepo.StrategyPO.*;
import util.HibernateUtil;
import util.IDUtil;
import vo.filevo.*;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class StrategyBusinessLogicController extends UnicastRemoteObject implements StrategyBusinessLogicService,StrategyBusinessLogicControllerAccess {

    private StrategyDataService strategyDataService;
    private TransStrategyPoVo transStrategyPoVo;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private UserBusinessLogicControllerAccess userBusinessLogicControllerAccess;

    public StrategyBusinessLogicController() throws RemoteException{
        strategyDataService= DataFactoryImpl.getInstance().getStrategyDataService();
        transStrategyPoVo=new TransStrategyPoVo();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        userBusinessLogicControllerAccess=new UserBusinessLogicController();
    }
    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("StrategyBusinessLogicService is connected.\n");
    }

    @Override
    public String createNewStrategy(String userID, FileType type) throws RemoteException {
        try{
            String time=new IDUtil().getTime();
            int num=new IDUtil().getID(type);
            new IDUtil().increment(type);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = dateFormat.format(new Date());
            String newID="";
            if(type==FileType.LEVEL){
                LevelStrategyPO po=new LevelStrategyPO();
                newID="lv"+time+""+num;
                po.setID(newID);
                po.setType(FileType.LEVEL);
                po.setFileState(FileState.DRAFT);
                po.setCreator(userID);
                po.setCreateTime(createTime);
                po.setIsUsing(false);
                HibernateUtil.getCurrentSession().beginTransaction();
                strategyDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.GIFT){
                GiftStrategyPO po=new GiftStrategyPO();
                newID="gf"+time+""+num;
                po.setId(newID);
                po.setType(FileType.GIFT);
                po.setState(FileState.DRAFT);
                po.setIsUsing(false);
                po.setCreator(userID);
                po.setCreateTime(createTime);
                HibernateUtil.getCurrentSession().beginTransaction();
                strategyDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.DISCOUNT){
                DiscountStrategyPO po=new DiscountStrategyPO();
                newID="da"+time+""+num;
                po.setID(newID);
                po.setType(FileType.DISCOUNT);
                po.setState(FileState.DRAFT);
                po.setIsUsing(false);
                po.setCreateTime(createTime);
                po.setCreator(userID);
                HibernateUtil.getCurrentSession().beginTransaction();
                strategyDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.PRICEPACK){
                PricePackStrategyPO po=new PricePackStrategyPO();
                newID="pp"+time+""+num;
                po.setId(newID);
                po.setType(FileType.PRICEPACK);
                po.setCreateTime(createTime);
                po.setIsUsing(false);
                po.setState(FileState.DRAFT);
                po.setCreator(userID);
                HibernateUtil.getCurrentSession().beginTransaction();
                strategyDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.VOUCHER){
                VoucherStrategyPO po=new VoucherStrategyPO();
                newID="vc"+time+""+num;
                po.setID(newID);
                po.setType(FileType.VOUCHER);
                po.setState(FileState.DRAFT);
                po.setIsUsing(false);
                po.setCreateTime(createTime);
                po.setCreator(userID);
                HibernateUtil.getCurrentSession().beginTransaction();
                strategyDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            return newID;
        }catch (HibernateException he){
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return "Something Wrong!";
        }

    }

    @Override
    public ResultMessage modifyLevelStrategy(String userID, LevelStrategyVO vo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        LevelStrategyPO po=strategyDataService.readLevelStrategy(vo.getID());
        po.setLeastLevelToDel(vo.getLeastLevelToDel());
        po.setNote(vo.getNote());
        po.setLevel1(vo.getLevel()[0]);
        po.setLevel2(vo.getLevel()[1]);
        po.setLevel3(vo.getLevel()[2]);
        po.setLevel4(vo.getLevel()[3]);
        strategyDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifyGiftStrategy(String userID, GiftStrategyVO vo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        GiftStrategyPO po=strategyDataService.readGiftStrategy(vo.getId());
        Set<ProductItem> gifts=new HashSet<>();
        for(String[] s:vo.getGifts()){
            ProductItem p=new ProductItem();
            p.setCommodityID(s[0]);
            p.setSize(s[2]);
            p.setNum(Integer.valueOf(s[3]));
            p.setNote(s[4]);
            gifts.add(p);
        }
        po.setGifts(gifts);
        po.setAmount(vo.getAmount());
        po.setNote(vo.getNote());
        strategyDataService.update(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifyDiscountStrategy(String strategyID, String note, Map<Double, Double> contents) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        DiscountStrategyPO dpo=strategyDataService.readDiscountStrategy(strategyID);
        dpo.setNote(note);
        dpo.setContent(contents);
        strategyDataService.update(dpo);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifyPricePackStrategy(String strategyID, String note, ArrayList<String[]> commodityGroup) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PricePackStrategyPO ppo=strategyDataService.readPricePackStrategy(strategyID);
        Set<ProductItem> comGroup=new HashSet<>();
        for(String[] s:commodityGroup){
            ProductItem p=new ProductItem(s[0],s[2],Integer.valueOf(s[3]),Double.valueOf(s[4]),s[5]);
            comGroup.add(p);
        }
        ppo.setNote(note);
        ppo.setCommodityGroup(comGroup);
        strategyDataService.update(ppo);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifyVoucherStrategy(String strategyID, String note, Map<Double, Double> con) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        VoucherStrategyPO vpo=strategyDataService.readVoucherStrategy(strategyID);
        vpo.setNote(note);
        vpo.setContent(con);
        strategyDataService.update(vpo);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage stopStrategy(String userID, String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        String type=strategyID.substring(0,2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = dateFormat.format(new Date());
        switch (type){
            case "lv":
                LevelStrategyPO lpo=strategyDataService.readLevelStrategy(strategyID);
                lpo.setIsUsing(false);
                lpo.setEndTime(endTime);
                strategyDataService.update(lpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"停用客户分级策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"停用客户分级策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.stopSuccess;
            case "gf":
                GiftStrategyPO gpo=strategyDataService.readGiftStrategy(strategyID);
                gpo.setIsUsing(false);
                gpo.setEndTime(endTime);
                strategyDataService.update(gpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"停用赠品策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"停用赠品策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.stopSuccess;
            case "da":
                DiscountStrategyPO dpo=strategyDataService.readDiscountStrategy(strategyID);
                dpo.setIsUsing(false);
                dpo.setEndTime(endTime);
                strategyDataService.update(dpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"停用折扣策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"停用折扣策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.stopSuccess;
            case "pp":
                PricePackStrategyPO ppo=strategyDataService.readPricePackStrategy(strategyID);
                ppo.setIsUsing(false);
                ppo.setEndTime(endTime);
                strategyDataService.update(ppo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"停用特价包策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"停用特价包策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.stopSuccess;
            case "vc":
                VoucherStrategyPO vpo=strategyDataService.readVoucherStrategy(strategyID);
                vpo.setEndTime(endTime);
                vpo.setIsUsing(false);
                strategyDataService.update(vpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"停用代金券策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"停用代金券策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.stopSuccess;
        }
        return null;
    }

    @Override
    public ResultMessage startStrategy(String userID, String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        String type=strategyID.substring(0,2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = dateFormat.format(new Date());
        switch (type){
            case "lv":
                LevelStrategyPO isOn=strategyDataService.readNowLevelStrategy();
                if(isOn!=null){
                    isOn.setIsUsing(false);
                    isOn.setEndTime(startTime);
                    strategyDataService.update(isOn);
                }
                LevelStrategyPO lpo=strategyDataService.readLevelStrategy(strategyID);
                lpo.setIsUsing(true);
                lpo.setFileState(FileState.REVIEWED);
                lpo.setStartTime(startTime);
                strategyDataService.update(lpo);
                HibernateUtil.getCurrentSession().beginTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"启用客户分级策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"启用客户分级策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.startSuccess;
            case "gf":
                GiftStrategyPO gpo=strategyDataService.readGiftStrategy(strategyID);
                gpo.setIsUsing(true);
                gpo.setState(FileState.REVIEWED);
                gpo.setStartTime(startTime);
                strategyDataService.update(gpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"启用赠品策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"启用赠品策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);

                return ResultMessage.startSuccess;
            case "da":
                DiscountStrategyPO now=strategyDataService.readNowDiscountStrategy();
                if(now!=null){
                    now.setIsUsing(false);
                    now.setEndTime(startTime);
                    strategyDataService.update(now);
                }
                DiscountStrategyPO dpo=strategyDataService.readDiscountStrategy(strategyID);
                dpo.setIsUsing(true);
                dpo.setState(FileState.REVIEWED);
                dpo.setStartTime(startTime);
                strategyDataService.update(dpo);
                HibernateUtil.getCurrentSession().beginTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"启用折扣策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"启用折扣策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.startSuccess;
            case "pp":
                PricePackStrategyPO ppo=strategyDataService.readPricePackStrategy(strategyID);
                ppo.setIsUsing(true);
                ppo.setStartTime(startTime);
                ppo.setState(FileState.REVIEWED);
                strategyDataService.update(ppo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"启用特价包策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"启用特价包策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.startSuccess;
            case "vc":
                VoucherStrategyPO nowVoucher=strategyDataService.readNowVoucherStrategy();
                if(nowVoucher!=null){
                    nowVoucher.setIsUsing(false);
                    nowVoucher.setEndTime(startTime);
                    strategyDataService.update(nowVoucher);
                }
                VoucherStrategyPO vpo=strategyDataService.readVoucherStrategy(strategyID);
                vpo.setIsUsing(true);
                vpo.setState(FileState.REVIEWED);
                vpo.setStartTime(startTime);
                strategyDataService.update(vpo);
                HibernateUtil.getCurrentSession().beginTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"启用代金券策略"+strategyID);
                messageBusinessLogicControllerAccess.addMessage(userID,"启用代金券策略"+strategyID,userBusinessLogicControllerAccess.getSalesmanList(),true);
                return ResultMessage.startSuccess;
        }
        return null;
    }

    @Override
    public ResultMessage deleteStrategy(String userID, String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        String type=strategyID.substring(0,2);
        switch (type){
            case "lv":
                LevelStrategyPO lpo=strategyDataService.readLevelStrategy(strategyID);
                if(lpo.getFileState()==FileState.DRAFT){
                    lpo.setFileState(FileState.TRASH);
                    strategyDataService.update(lpo);
                }else if(lpo.getFileState()==FileState.TRASH){
                    strategyDataService.removeLevelStrategy(strategyID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            case "gf":
                GiftStrategyPO gpo=strategyDataService.readGiftStrategy(strategyID);
                if(gpo.getState()==FileState.DRAFT){
                    gpo.setState(FileState.TRASH);
                    strategyDataService.update(gpo);
                }else if(gpo.getState()==FileState.TRASH){
                    strategyDataService.removeGiftStrategy(strategyID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            case "da":
                DiscountStrategyPO dpo=strategyDataService.readDiscountStrategy(strategyID);
                if(dpo.getState()==FileState.DRAFT){
                    dpo.setState(FileState.TRASH);
                    strategyDataService.update(dpo);
                }else if(dpo.getState()==FileState.TRASH){
                    strategyDataService.removeDiscountStrategy(strategyID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            case "pp":
                PricePackStrategyPO ppo=strategyDataService.readPricePackStrategy(strategyID);
                if(ppo.getState()==FileState.DRAFT){
                    ppo.setState(FileState.TRASH);
                    strategyDataService.update(ppo);
                }else if(ppo.getState()==FileState.TRASH){
                    strategyDataService.removePricePackStrategy(strategyID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            case "vc":
                VoucherStrategyPO vpo=strategyDataService.readVoucherStrategy(strategyID);
                if(vpo.getState()==FileState.DRAFT){
                    vpo.setState(FileState.TRASH);
                    strategyDataService.update(vpo);
                }else if(vpo.getState()==FileState.TRASH){
                    strategyDataService.removeVoucherStrategy(strategyID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
        }
        return null;
    }

    @Override
    public LevelStrategyVO readLevelStrategy(String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        LevelStrategyPO lpo=strategyDataService.readLevelStrategy(strategyID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transStrategyPoVo.LevelStrategyPoToVo(lpo);
    }

    @Override
    public GiftStrategyVO readGiftStrategy(String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        GiftStrategyPO gpo=strategyDataService.readGiftStrategy(strategyID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transStrategyPoVo.GiftStrategyPoToVo(gpo);
    }

    @Override
    public DiscountStrategyVO readDiscountStrategy(String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        DiscountStrategyPO dpo=strategyDataService.readDiscountStrategy(strategyID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transStrategyPoVo.DiscountStrategyPoToVo(dpo);
    }

    @Override
    public PricePackStrategyVO readPricePackStrategy(String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PricePackStrategyPO ppo=strategyDataService.readPricePackStrategy(strategyID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transStrategyPoVo.PricePackStrategyPoToVo(ppo);
    }

    @Override
    public VoucherStrategyVO readVoucherStrategy(String strategyID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        VoucherStrategyPO vpo=strategyDataService.readVoucherStrategy(strategyID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transStrategyPoVo.VoucherStrategyPoToVo(vpo);
    }

    @Override
    public ArrayList<String> listDraftStrategy() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> vos=new ArrayList<>();
        for(LevelStrategyPO lpo:strategyDataService.listLevelStrategy()){
            if(lpo.getFileState()==FileState.DRAFT){
                vos.add(lpo.getID());
            }
        }
        for(GiftStrategyPO gpo:strategyDataService.listGiftStrategy()){
            if(gpo.getState()==FileState.DRAFT){
                vos.add(gpo.getId());
            }
        }
        for(DiscountStrategyPO dpo:strategyDataService.listDiscountStrategy()){
            if(dpo.getState()==FileState.DRAFT){
                vos.add(dpo.getID());
            }
        }
        for(PricePackStrategyPO ppo:strategyDataService.listPricePackStrategy()){
            if(ppo.getState()==FileState.DRAFT){
                vos.add(ppo.getId());
            }
        }
        for(VoucherStrategyPO vpo:strategyDataService.listVoucherStrategy()){
            if(vpo.getState()==FileState.DRAFT){
                vos.add(vpo.getID());
            }
        }
        return vos;
    }

    @Override
    public ArrayList<String> listTrashStrategy() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> vos=new ArrayList<>();
        for(LevelStrategyPO lpo:strategyDataService.listLevelStrategy()){
            if(lpo.getFileState()==FileState.TRASH){
                vos.add(lpo.getID());
            }
        }
        for(GiftStrategyPO gpo:strategyDataService.listGiftStrategy()){
            if(gpo.getState()==FileState.TRASH){
                vos.add(gpo.getId());
            }
        }
        for(DiscountStrategyPO dpo:strategyDataService.listDiscountStrategy()){
            if(dpo.getState()==FileState.TRASH){
                vos.add(dpo.getID());
            }
        }
        for(PricePackStrategyPO ppo:strategyDataService.listPricePackStrategy()){
            if(ppo.getState()==FileState.TRASH){
                vos.add(ppo.getId());
            }
        }
        for(VoucherStrategyPO vpo:strategyDataService.listVoucherStrategy()){
            if(vpo.getState()==FileState.TRASH){
                vos.add(vpo.getID());
            }
        }
        return vos;
    }

    @Override
    public ArrayList<String> listOnUseStrategy() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> onUse=new ArrayList<>();
        if(strategyDataService.readNowLevelStrategy()!=null){
            onUse.add(strategyDataService.readNowLevelStrategy().getID());
        }
        for(GiftStrategyPO gpo:strategyDataService.readNowGiftStrategy()){
            onUse.add(gpo.getId());
        }
        if(strategyDataService.readNowDiscountStrategy()!=null){
            onUse.add(strategyDataService.readNowDiscountStrategy().getID());
        }
        for(PricePackStrategyPO ppo:strategyDataService.readNowPricePackStrategy()){
            onUse.add(ppo.getId());
        }
        if(strategyDataService.readNowVoucherStrategy()!=null){
            onUse.add(strategyDataService.readNowVoucherStrategy().getID());
        }
        return onUse;
    }

    @Override
    public ArrayList<String> listUsedStrategy() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> used=new ArrayList<>();
        for(LevelStrategyPO lpo:strategyDataService.listLevelStrategy()){
            if((lpo.getFileState()==FileState.REVIEWED)&&(!lpo.getIsUsing())){
                used.add(lpo.getID());
            }
        }
        for(GiftStrategyPO gpo:strategyDataService.listGiftStrategy()){
            if((gpo.getState()==FileState.REVIEWED)&&(!gpo.getIsUsing())){
                used.add(gpo.getId());
            }
        }
        for(DiscountStrategyPO dpo:strategyDataService.listDiscountStrategy()){
            if((dpo.getState()==FileState.REVIEWED)&&(!dpo.getIsUsing())){
                used.add(dpo.getID());
            }
        }
        for(PricePackStrategyPO ppo:strategyDataService.listPricePackStrategy()){
            if((ppo.getState()==FileState.REVIEWED)&&(!ppo.getIsUsing())){
                used.add(ppo.getId());
            }
        }
        for(VoucherStrategyPO vpo:strategyDataService.listVoucherStrategy()){
            if((vpo.getState()==FileState.REVIEWED)&&(!vpo.getIsUsing())){
                used.add(vpo.getID());
            }
        }
        return used;
    }

    @Override
    public int getLeastLevelToDel() {
        HibernateUtil.getCurrentSession().beginTransaction();
        LevelStrategyPO lpo=strategyDataService.readNowLevelStrategy();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return lpo.getLeastLevelToDel();
    }

    @Override
    public int checkLevel(double totalAmount) {
        HibernateUtil.getCurrentSession().beginTransaction();
        LevelStrategyPO lpo=strategyDataService.readNowLevelStrategy();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(totalAmount>=lpo.getLevel4()){
            return 5;
        }
        if(totalAmount>=lpo.getLevel3()){
            return 4;
        }
        if(totalAmount>=lpo.getLevel2()){

            return 3;
        }
        if(totalAmount>=lpo.getLevel1()){
            return 2;
        }
        return 1;
    }

    @Override
    public GiftStrategyVO checkGiftStrategy(double money) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<GiftStrategyPO> list=strategyDataService.readNowGiftStrategy();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        GiftStrategyPO temp=null;
        for(GiftStrategyPO gpo:list){
            if((money>=gpo.getAmount()&&(gpo.getAmount()>temp.getAmount()))){
                temp=gpo;
            }
        }
        if(temp!=null){
            return transStrategyPoVo.GiftStrategyPoToVo(temp);
        }else{
            return null;
        }
    }

    @Override
    public double checkVoucherStrategy(double money) {
        HibernateUtil.getCurrentSession().beginTransaction();
        VoucherStrategyPO voucherStrategyPO=strategyDataService.readNowVoucherStrategy();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(voucherStrategyPO==null){
            return 0;
        }
        double temp=0;
        for(Double key:voucherStrategyPO.getContent().keySet()){
            if((money>=key)&&(key>temp)){
                temp=key;
            }
        }
        return voucherStrategyPO.getContent().get(temp);
    }

    @Override
    public double checkDiscountStrategy(double money) {
        HibernateUtil.getCurrentSession().beginTransaction();
        DiscountStrategyPO discountStrategyPO=strategyDataService.readNowDiscountStrategy();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if(discountStrategyPO==null){
            return 0;
        }
        double temp=0;
        for(Double key:discountStrategyPO.getContent().keySet()){
            if((money>=key)&&(key>temp)){
                temp=key;
            }
        }
        return money*(1-discountStrategyPO.getContent().get(temp));
    }

    @Override
    public ArrayList<PricePackStrategyVO> listPricePackStrategy() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<PricePackStrategyVO> re=new ArrayList<>();
        for(PricePackStrategyPO ppo:strategyDataService.readNowPricePackStrategy()){
            re.add(transStrategyPoVo.PricePackStrategyPoToVo(ppo));
        }
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return re;
    }

    @Override
    public double getPricePackTotalMoney(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PricePackStrategyPO pppo=strategyDataService.readPricePackStrategy(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        double total=0;
        for(ProductItem p:pppo.getCommodityGroup()){
            total=total+p.getPrice()*p.getNum();
        }
        return total;
    }

    @Override
    public PricePackStrategyVO getPricePackStrategy(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        return transStrategyPoVo.PricePackStrategyPoToVo(strategyDataService.readPricePackStrategy(id));
    }

    @Override
    public ArrayList<ProductItem> getGiftStrategy(String id) {
        return new ArrayList<>(strategyDataService.readGiftStrategy(id).getGifts());
    }


}
