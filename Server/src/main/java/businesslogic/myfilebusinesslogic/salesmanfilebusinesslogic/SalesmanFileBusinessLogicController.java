package businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic;

import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
import businesslogic.clientbusinesslogic.ClientBusinessLogicControllerAccess;
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicController;
import businesslogic.myfilebusinesslogic.Strategybusinesslogic.StrategyBusinessLogicControllerAccess;
import businesslogic.userbusinesslogic.UserBusinessLogicController;
import businesslogic.userbusinesslogic.UserBusinessLogicControllerAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.myfilebusinesslogicservice.SalesmanFileBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.myfiledataservice.SalesmanDataService;
import infor.Infor;
import infor.ProductItem;
import org.hibernate.HibernateException;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;
import vo.filevo.*;
import po.filepo.PurchasePO;
import po.filepo.PurchaseReturnPO;
import util.HibernateUtil;
import util.IDUtil;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @Name SalesmanFileBusinessLogicController
 * @Description 进货销售人员的进销单，退货单的逻辑操作
 * @author zhangping
 * @date 2017/12/27 0027 8:50
 */

public class SalesmanFileBusinessLogicController extends UnicastRemoteObject implements SalesmanFileBusinessLogicService,SalesmanFileBusinessLogicAccess {

    private SalesmanDataService salesmanDataService;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private UserBusinessLogicControllerAccess userBusinessLogicControllerAccess;
    private ClientBusinessLogicControllerAccess clientBusinessLogicControllerAccess;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private TransSaleAndPurchaseVoPo trans;
    private StrategyBusinessLogicControllerAccess strategyBusinessLogicControllerAccess;
    private WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    public SalesmanFileBusinessLogicController() throws RemoteException{
        salesmanDataService= DataFactoryImpl.getInstance().getSalesmanDataService();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
        userBusinessLogicControllerAccess=new UserBusinessLogicController();
        clientBusinessLogicControllerAccess=new ClientBusinessLogicController();
        strategyBusinessLogicControllerAccess=new StrategyBusinessLogicController();
        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
        trans=new TransSaleAndPurchaseVoPo();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("SalesmanFileBusinessLogicService is connected.\n");
    }

    private ArrayList<PurchasePO> listPurchase(){
        HibernateUtil.getCurrentSession().beginTransaction();
        return salesmanDataService.listPurchase();
    }

    private ArrayList<PurchaseReturnPO> listPurchaseReturn(){
        HibernateUtil.getCurrentSession().beginTransaction();
        return salesmanDataService.listPurchaseReturn();
    }

    private ArrayList<SalePO> listSale(){
        HibernateUtil.getCurrentSession().beginTransaction();
        return salesmanDataService.listSale();
    }

    private ArrayList<SaleReturnPO> listSaleReturn(){
        HibernateUtil.getCurrentSession().beginTransaction();
        return salesmanDataService.listSaleReturn();
    }

    @Override
    public ArrayList<String> listDraft(String userID) throws RemoteException {
        ArrayList<String> draftFiles=new ArrayList<>();
        if(this.listPurchase()!=null){
            for(PurchasePO ppo:this.listPurchase()){
                if((ppo.getState()== FileState.DRAFT)&&(ppo.getOperator().equals(userID))){
                    draftFiles.add(ppo.getID());
                }
            }
        }
        if(this.listPurchaseReturn()!=null){
            for(PurchaseReturnPO prpo:this.listPurchaseReturn()){
                if((prpo.getState()==FileState.DRAFT)&&(prpo.getOperator().equals(userID))){
                    draftFiles.add(prpo.getID());
                }
            }
        }
        if(this.listSale()!=null){
            for(SalePO spo:this.listSale()){
                if((spo.getState()==FileState.DRAFT)&&(spo.getOperator().equals(userID))){
                    draftFiles.add(spo.getID());
                }
            }
        }
        if(this.listSaleReturn()!=null){
            for(SaleReturnPO srpo:this.listSaleReturn()){
                if((srpo.getState()==FileState.DRAFT)&&(srpo.getOperator().equals(userID))){
                    draftFiles.add(srpo.getID());
                }
            }
        }
        return draftFiles;
    }

    @Override
    public ArrayList<String> listWaitToReview(String userID) throws RemoteException {
        ArrayList<String> waitReviewFiles=new ArrayList<>();
        if(listPurchase()!=null){
            for(PurchasePO ppo:listPurchase()){
                if((ppo.getOperator().equals(userID))&&(ppo.getState()== FileState.WAITREVIEW)){
                    waitReviewFiles.add(ppo.getID());
                }
            }
        }
        if(listPurchaseReturn()!=null){
            for(PurchaseReturnPO prpo:listPurchaseReturn()){
                if((prpo.getOperator().equals(userID))&&(prpo.getState()==FileState.WAITREVIEW)){
                    waitReviewFiles.add(prpo.getID());
                }
            }
        }
        if(this.listSale()!=null){
            for(SalePO spo:this.listSale()){
                if((spo.getState()==FileState.WAITREVIEW)&&(spo.getOperator().equals(userID))){
                    waitReviewFiles.add(spo.getID());
                }
            }
        }
        if(this.listSaleReturn()!=null){
            for(SaleReturnPO srpo:this.listSaleReturn()){
                if((srpo.getState()==FileState.WAITREVIEW)&&(srpo.getOperator().equals(userID))){
                    waitReviewFiles.add(srpo.getID());
                }
            }
        }
        return waitReviewFiles;
    }

    @Override
    public ArrayList<String> listReviewed(String userID) throws RemoteException {
        ArrayList<String> ReviewedFiles=new ArrayList<>();
        if(listPurchase()!=null){
            for(PurchasePO ppo:listPurchase()){
                if(ppo.getState()== FileState.REVIEWED){
                    ReviewedFiles.add(ppo.getID());
                }
            }
        }
        if(listPurchaseReturn()!=null){
            for(PurchaseReturnPO prpo:listPurchaseReturn()){
                if(prpo.getState()==FileState.REVIEWED){
                    ReviewedFiles.add(prpo.getID());
                }
            }
        }
        if(listSale()!=null){
            for(SalePO spo:listSale()){
                if(spo.getState()==FileState.REVIEWED){
                    ReviewedFiles.add(spo.getID());
                }
            }
        }
        if(listSaleReturn()!=null){
            for(SaleReturnPO srpo:listSaleReturn()){
                if(srpo.getState()==FileState.REVIEWED){
                    ReviewedFiles.add(srpo.getID());
                }
            }
        }
        return ReviewedFiles;
    }

    @Override
    public ArrayList<String> listTrash(String userID) throws RemoteException {
        ArrayList<String> trashFiles=new ArrayList<>();
        if(listPurchase()!=null){
            for(PurchasePO ppo:listPurchase()){
                if((ppo.getOperator().equals(userID))&&ppo.getState()== FileState.TRASH){
                    trashFiles.add(ppo.getID());
                }
            }
        }
        if(listPurchaseReturn()!=null){
            for(PurchaseReturnPO prpo:listPurchaseReturn()){
                if((prpo.getOperator().equals(userID))&&(prpo.getState()==FileState.TRASH)){
                    trashFiles.add(prpo.getID());
                }
            }
        }
        if(this.listSale()!=null){
            for(SalePO spo:this.listSale()){
                if((spo.getState()==FileState.TRASH)&&(spo.getOperator().equals(userID))){
                    trashFiles.add(spo.getID());
                }
            }
        }
        if(this.listSaleReturn()!=null){
            for(SaleReturnPO srpo:this.listSaleReturn()){
                if((srpo.getState()==FileState.TRASH)&&(srpo.getOperator().equals(userID))){
                    trashFiles.add(srpo.getID());
                }
            }
        }
        return trashFiles;
    }

    //新建进货单，进货退货单，销售单，销售退货单时，由此方法得到ID
    @Override
    public String createSaleAndPurchase(String userID, FileType type) throws RemoteException{
        try{
            String time=new IDUtil().getTime();
            int num=new IDUtil().getID(type);
            new IDUtil().increment(type);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = dateFormat.format(new Date());
            String newID="";
            if(type==FileType.PURCHASE){
                PurchasePO purchasePO=new PurchasePO();
                newID="pc"+time+""+num;
                purchasePO.setID(newID);
                purchasePO.setFileType(type);
                purchasePO.setState(FileState.DRAFT);
                purchasePO.setCreateTime(createTime);
                purchasePO.setOperator(userID);
                purchasePO.setClerk("");
                purchasePO.setNote("");
                purchasePO.setPassTime("");
                purchasePO.setClient("");
                purchasePO.setWarehouse("");
                purchasePO.setCommodityList(null);
                purchasePO.setInformation(null);
                purchasePO.setTotal(0);
                HibernateUtil.getCurrentSession().beginTransaction();
                salesmanDataService.writePurchase(purchasePO);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.SALE){
                SalePO salePO=new SalePO();
                newID= "sa"+time+""+num;
                salePO.setID(newID);
                salePO.setFileType(type);
                salePO.setState(FileState.DRAFT);
                salePO.setCreateTime(createTime);
                salePO.setOperator(userID);
                salePO.setTotal(0);
                HibernateUtil.getCurrentSession().beginTransaction();
                salesmanDataService.writeSale(salePO);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.PURCHASERETURN){
                PurchaseReturnPO prpo=new PurchaseReturnPO();
                newID="pr"+time+""+num;
                prpo.setID(newID);
                prpo.setFileType(type);
                prpo.setState(FileState.DRAFT);
                prpo.setCreateTime(createTime);
                prpo.setOperator(userID);
                prpo.setClerk("");
                prpo.setNote("");
                prpo.setPassTime("");
                prpo.setClient("");
                prpo.setWarehouse("");
                prpo.setCommodityList(null);
                prpo.setInformation(null);
                prpo.setTotal(0);
                HibernateUtil.getCurrentSession().beginTransaction();
                salesmanDataService.writePurchaseReturn(prpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            if(type==FileType.SALERETURN){
                SaleReturnPO saleReturnPO=new SaleReturnPO();
                newID= "sr"+time+""+num;
                saleReturnPO.setID(newID);
                saleReturnPO.setFileType(FileType.SALERETURN);
                saleReturnPO.setState(FileState.DRAFT);
                saleReturnPO.setOperator(userID);
                saleReturnPO.setCreateTime(createTime);
                HibernateUtil.getCurrentSession().beginTransaction();
                salesmanDataService.writeSaleReturn(saleReturnPO);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            }
            return newID;
        }catch (HibernateException he){
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return "Something Wrong.";
        }
    }

    @Override
    public ResultMessage delete(String userID, String fileID) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(fileID.startsWith("pc")){//如果是进货单
                PurchasePO ppo=salesmanDataService.readPurchase(fileID);
                if(ppo.getState()==FileState.DRAFT){
                    ppo.setState(FileState.TRASH);
                    salesmanDataService.updatePurchase(ppo);
                }else if(ppo.getState()==FileState.TRASH){
                    salesmanDataService.removePurchase(fileID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            }
            if(fileID.startsWith("pr")) {
                PurchaseReturnPO prpo = salesmanDataService.readPurchaseReturn(fileID);
                if (prpo.getState() == FileState.DRAFT) {
                    prpo.setState(FileState.TRASH);
                    salesmanDataService.updatePurchaseReturn(prpo);
                } else if (prpo.getState() == FileState.TRASH) {
                    salesmanDataService.removePurchaseReturn(fileID);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            }
            if(fileID.startsWith("sa")){
                SalePO spo=salesmanDataService.readSale(fileID);
                if(spo.getState()==FileState.TRASH){
                    salesmanDataService.removeSale(fileID);
                }else if(spo.getState()==FileState.DRAFT){
                    spo.setState(FileState.TRASH);
                    salesmanDataService.updateSale(spo);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            }
            if(fileID.startsWith("sr")){
                SaleReturnPO srpo=salesmanDataService.readSaleReturn(fileID);
                if(srpo.getState()==FileState.TRASH){
                    salesmanDataService.removeSaleReturn(fileID);
                }else if(srpo.getState()==FileState.DRAFT){
                    srpo.setState(FileState.TRASH);
                    salesmanDataService.updateSaleReturn(srpo);
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.delSuccess;
            }
        }catch (HibernateException he) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return ResultMessage.Fail;
        }
        return null;
    }

    @Override
    public ResultMessage recover(String userID, String fileID) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(fileID.startsWith("pc")){//如果是进货单
                PurchasePO ppo=salesmanDataService.readPurchase(fileID);
                ppo.setState(FileState.DRAFT);
                salesmanDataService.updatePurchase(ppo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.recoverSuccess;
            }
            if(fileID.startsWith("pr")) {
                PurchaseReturnPO prpo = salesmanDataService.readPurchaseReturn(fileID);
                prpo.setState(FileState.DRAFT);
                salesmanDataService.updatePurchaseReturn(prpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.recoverSuccess;
            }
            if(fileID.startsWith("sa")){
                SalePO spo=salesmanDataService.readSale(fileID);
                spo.setState(FileState.DRAFT);
                salesmanDataService.updateSale(spo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.recoverSuccess;
            }
            if(fileID.startsWith("sr")){
                SaleReturnPO srpo=salesmanDataService.readSaleReturn(fileID);
                srpo.setState(FileState.DRAFT);
                salesmanDataService.updateSaleReturn(srpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return ResultMessage.recoverSuccess;
            }
        }catch (HibernateException he) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return ResultMessage.Fail;
        }
        return null;
    }

    @Override
    public ResultMessage commit(String userID, String fileID) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(fileID.startsWith("pc")){//如果是进货单
                PurchasePO ppo=salesmanDataService.readPurchase(fileID);
                ppo.setState(FileState.WAITREVIEW);
                salesmanDataService.updatePurchase(ppo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"提交进货单"+fileID);
                messageBusinessLogicControllerAccess.addMessage(userID,"提交进货单"+fileID,userBusinessLogicControllerAccess.getManageID(),true);
                return ResultMessage.commitSuccess;
            }
            if(fileID.startsWith("pr")) {
                PurchaseReturnPO prpo = salesmanDataService.readPurchaseReturn(fileID);
                prpo.setState(FileState.WAITREVIEW);
                salesmanDataService.updatePurchaseReturn(prpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"提交进货退货单"+fileID);
                messageBusinessLogicControllerAccess.addMessage(userID,"提交进货退货单"+fileID,userBusinessLogicControllerAccess.getManageID(),true);
                return ResultMessage.commitSuccess;
            }
            if(fileID.startsWith("sa")){
                SalePO spo=salesmanDataService.readSale(fileID);
                spo.setState(FileState.WAITREVIEW);
                salesmanDataService.updateSale(spo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"提交销售单"+fileID);
                messageBusinessLogicControllerAccess.addMessage(userID,"提交销售单"+fileID,userBusinessLogicControllerAccess.getManageID(),true);

                return ResultMessage.commitSuccess;
            }
            if(fileID.startsWith("sr")){
                SaleReturnPO srpo=salesmanDataService.readSaleReturn(fileID);
                srpo.setState(FileState.WAITREVIEW);
                salesmanDataService.updateSaleReturn(srpo);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"提交销售退货单"+fileID);
                messageBusinessLogicControllerAccess.addMessage(userID,"提交销售退货单"+fileID,userBusinessLogicControllerAccess.getManageID(),true);
                return ResultMessage.commitSuccess;
            }
        }catch (HibernateException he) {
            HibernateUtil.getCurrentSession().getTransaction().rollback();
            return ResultMessage.Fail;
        }
        return null;
    }

    //修改草稿状态的进货单并且日志不需要记录
    @Override
    public ResultMessage modifyPurchase(String userID,String fileID, PurchaseVO pvo) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchasePO purchasePO=salesmanDataService.readPurchase(fileID);
        Set<ProductItem> products=new HashSet<>();
        double total=0;
        for(String[] s:pvo.getProductItems()){
            ProductItem p=new ProductItem(s[0],s[2],Integer.valueOf(s[3]),Double.valueOf(s[4]),s[6]);
            products.add(p);
            total=total+p.getPrice()*p.getNum();
        }
        if(pvo.getClerk()!=null){
            purchasePO.setClerk(pvo.getClerk());
        }
        if(pvo.getNote()!=null){
            purchasePO.setNote(pvo.getNote());
        }
        if(pvo.getClientID()!=null){
            purchasePO.setClient(pvo.getClientID());
        }
        //purchasePO.setWarehouse(pvo.getWarehouseID());
        purchasePO.setCommodityList(products);
        Set<Infor> infor=new HashSet<>(pvo.getInformation());
        purchasePO.setInformation(infor);
        purchasePO.setTotal(total);
        HibernateUtil.getCurrentSession().beginTransaction();
        salesmanDataService.updatePurchase(purchasePO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifyPurchaseReturn(String userID,String fileID, PurchaseReturnVO purchaseReturnVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchaseReturnPO PO=salesmanDataService.readPurchaseReturn(fileID);
        Set<ProductItem> products=new HashSet<>();
        double total=0;
        for(String[] s:purchaseReturnVO.getProductItems()){
            ProductItem p=new ProductItem(s[0],s[2],Integer.valueOf(s[3]),Double.valueOf(s[4]),s[6]);
            products.add(p);
            total=total+p.getPrice()*p.getNum();
        }
        if(purchaseReturnVO.getClerk()!=null){
            PO.setClerk(purchaseReturnVO.getClerk());
        }
        if(purchaseReturnVO.getNote()!=null){
            PO.setNote(purchaseReturnVO.getNote());
        }
        if(purchaseReturnVO.getNote()!=null){
            PO.setClient(purchaseReturnVO.getClientID());
        }
        //PO.setWarehouse(purchaseReturnVO.getWarehouseID());
        PO.setCommodityList(products);
        Set<Infor> infor=new HashSet<>(purchaseReturnVO.getInformation());
        PO.setInformation(infor);
        PO.setTotal(total);
        HibernateUtil.getCurrentSession().beginTransaction();
        salesmanDataService.writePurchaseReturn(PO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifySale(String userID, SaleVO saleVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        SalePO spo=salesmanDataService.readSale(saleVO.getID());
        Set<ProductItem> commodityList=new HashSet<>();
        for(String [] s:saleVO.getCommodityList()){
            ProductItem p=new ProductItem(s[0],s[2],Integer.valueOf(s[3]),Double.valueOf(s[4]),s[6]);
            commodityList.add(p);
        }
        spo.setClerk(saleVO.getClerk());
        spo.setNote(saleVO.getNote());
        spo.setCommodityList(commodityList);
        spo.setInformation(new HashSet<>(saleVO.getInformation()));
        spo.setClient(saleVO.getClient());
        spo.setWarehouse(saleVO.getWarehouse());
        spo.setGiftStrategy(saleVO.getGiftStrategy());
        spo.setVoucherStrategy(saleVO.getVoucherStrategy());
        spo.setPricePack(saleVO.getPricePack());
        spo.setAllowance(saleVO.getAllowance());
        spo.setVoucher(saleVO.getVoucher());
        spo.setTotal(saleVO.getTotal());
        salesmanDataService.writeSale(spo);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ResultMessage modifySaleReturn(String userID, SaleReturnVO saleReturnVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        SaleReturnPO PO=salesmanDataService.readSaleReturn(saleReturnVO.getID());
        Set<ProductItem> products=new HashSet<>();
        double total=0;
        for(String[] s:saleReturnVO.getCommodityList()){
            ProductItem p=new ProductItem(s[0],s[2],Integer.valueOf(s[3]),Double.valueOf(s[4]),s[6]);
            products.add(p);
            total=total+p.getPrice()*p.getNum();
        }
        if(saleReturnVO.getClerk()!=null){
            PO.setClerk(saleReturnVO.getClerk());
        }
        if(saleReturnVO.getNote()!=null){
            PO.setNote(saleReturnVO.getNote());
        }
        if(saleReturnVO.getClient()!=null){
            PO.setClient(saleReturnVO.getClient());
        }
        PO.setWarehouse(saleReturnVO.getWarehouse());
        PO.setCommodityList(products);
        Set<Infor> infor=new HashSet<>(saleReturnVO.getInformation());
        PO.setInformation(infor);
        PO.setTotal(total);
        HibernateUtil.getCurrentSession().beginTransaction();
        salesmanDataService.updateSaleReturn(PO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public PurchaseVO findPurchase(String id) throws RemoteException {
        PurchaseVO purchaseVO=this.showPurchaseDetail(id);
        return purchaseVO;
    }

    @Override
    public PurchaseReturnVO findPurchaseReturn(String id) throws RemoteException {
        PurchaseReturnVO purchaseReturnVO=this.showPurchaseReturnDetail(id);
        return purchaseReturnVO;
    }

    @Override
    public SaleVO findSale(String id) throws RemoteException {
        SaleVO saleVO=this.showSaleDetail(id);
        return saleVO;
    }

    @Override
    public SaleReturnVO findSaleReturn(String id) throws RemoteException {
        SaleReturnVO saleReturnVO=this.showSaleReturnDetail(id);
        return saleReturnVO;
    }

    @Override
    public double checkDiscountStrategy(double total_money) throws RemoteException {
        return strategyBusinessLogicControllerAccess.checkDiscountStrategy(total_money);
    }

    @Override
    public double checkVoucherStrategy(double total_money) throws RemoteException {
        return strategyBusinessLogicControllerAccess.checkVoucherStrategy(total_money);
    }

    @Override
    public GiftStrategyVO checkGiftStrategy(double total_money) throws RemoteException {
        return strategyBusinessLogicControllerAccess.checkGiftStrategy(total_money);
    }

    @Override
    public ArrayList<PricePackStrategyVO> listPricePackStrategy() throws RemoteException {
        return strategyBusinessLogicControllerAccess.listPricePackStrategy();
    }

    @Override
    public double getTotal(SaleVO saleVO) throws RemoteException {
        double comMoney=0;
        for(String [] s:saleVO.getCommodityList()){
            comMoney=comMoney+Double.valueOf(s[5]);
        }
        double pricePackAmount=0;
        if(saleVO.getPricePack()!=null){
            for (Map.Entry<String,Integer> entry : saleVO.getPricePack().entrySet()) {
                double temp=strategyBusinessLogicControllerAccess.getPricePackTotalMoney(entry.getKey());
                pricePackAmount=pricePackAmount+temp*entry.getValue();
            }
        }
        return comMoney+pricePackAmount-saleVO.getDiscount()-saleVO.getAllowance()-saleVO.getVoucher();
    }

    @Override
    public PricePackStrategyVO getPricePackStrategy(String id) throws RemoteException {
        return strategyBusinessLogicControllerAccess.getPricePackStrategy(id);
    }

    @Override
    public void approvalSale(String userID, String saleID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        SalePO salePO=salesmanDataService.readSale(saleID);
        Set<Infor> infors=salePO.getInformation();
        infors.add(infor);
        salePO.setInformation(infors);
        if(toApprove){//通过该单据
            salePO.setState(FileState.REVIEWED);
            salePO.setPassTime(this.getTime());
            salesmanDataService.updateSale(salePO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            for(ProductItem p:salePO.getCommodityList()){
                warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,p.getCommodityID(),p.getNum()*(-1));
            }
            clientBusinessLogicControllerAccess.modifyPayMoney(salePO.getClient(),salePO.getTotal());
            clientBusinessLogicControllerAccess.modifyClientLevel(salePO.getClient());
            logBusinessLogicControllerAccess.addLog(userID,"通过单据"+saleID);
            messageBusinessLogicControllerAccess.addMessage(userID,"通过单据"+saleID,salePO.getOperator(),true);

        }else{//驳回该单据
            salePO.setState(FileState.DRAFT);
            salesmanDataService.updateSale(salePO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回单据"+saleID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回单据"+saleID,salePO.getOperator(),true);
        }
    }

    @Override
    public void approvalSaleReturn(String userID, String saleReturnID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        SaleReturnPO saleReturnPO=salesmanDataService.readSaleReturn(saleReturnID);
        Set<Infor> infors=saleReturnPO.getInformation();
        infors.add(infor);
        saleReturnPO.setInformation(infors);
        if(toApprove){//通过该单据
            saleReturnPO.setState(FileState.REVIEWED);
            saleReturnPO.setPassTime(this.getTime());
            salesmanDataService.updateSaleReturn(saleReturnPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            for(ProductItem p:saleReturnPO.getCommodityList()){
                warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,p.getCommodityID(),p.getNum());
            }
            messageBusinessLogicControllerAccess.addMessage(userID,"通过单据"+saleReturnID,saleReturnPO.getOperator(),true);
            clientBusinessLogicControllerAccess.modifyReceiveMoney(saleReturnPO.getClient(),saleReturnPO.getTotal());
            clientBusinessLogicControllerAccess.modifyClientLevel(saleReturnPO.getClient());
            logBusinessLogicControllerAccess.addLog(userID,"通过单据"+saleReturnID);

        }else{//驳回该单据
            saleReturnPO.setState(FileState.DRAFT);
            salesmanDataService.updateSaleReturn(saleReturnPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回单据"+saleReturnID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回单据"+saleReturnID,saleReturnPO.getOperator(),true);
        }
    }

    @Override
    public void approvalPurchase(String userID, String purchaseID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchasePO purchasePO=salesmanDataService.readPurchase(purchaseID);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        Set<Infor> infors=purchasePO.getInformation();
        infors.add(infor);
        purchasePO.setInformation(infors);
        if(toApprove){//通过该单据
            HibernateUtil.getCurrentSession().beginTransaction();
            purchasePO.setState(FileState.REVIEWED);
            purchasePO.setPassTime(this.getTime());
            salesmanDataService.updatePurchase(purchasePO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            for(ProductItem p:purchasePO.getCommodityList()){
                warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,p.getCommodityID(),p.getNum());
            }
            logBusinessLogicControllerAccess.addLog(userID,"通过单据"+purchaseID);
            clientBusinessLogicControllerAccess.modifyReceiveMoney(purchasePO.getClient(),purchasePO.getTotal());
            clientBusinessLogicControllerAccess.modifyClientLevel(purchasePO.getClient());
            messageBusinessLogicControllerAccess.addMessage(userID,"通过单据"+purchaseID,purchasePO.getOperator(),true);

        }else{//驳回该单据
            HibernateUtil.getCurrentSession().beginTransaction();
            purchasePO.setState(FileState.DRAFT);
            salesmanDataService.updatePurchase(purchasePO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回单据"+purchaseID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回单据"+purchaseID,purchasePO.getOperator(),true);
        }
    }

    @Override
    public void approvalPurchaseReturn(String userID, String purchaseReturnID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchaseReturnPO purchaseReturnPO=salesmanDataService.readPurchaseReturn(purchaseReturnID);
        Set<Infor> infors=purchaseReturnPO.getInformation();
        System.err.println(infors.size());
        infors.add(infor);
        System.err.println(infors.size());
        purchaseReturnPO.setInformation(infors);
        if(toApprove){//通过该单据
            purchaseReturnPO.setState(FileState.REVIEWED);
            purchaseReturnPO.setPassTime(this.getTime());
            salesmanDataService.updatePurchaseReturn(purchaseReturnPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            for(ProductItem p:purchaseReturnPO.getCommodityList()){
                warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,p.getCommodityID(),p.getNum()*(-1));
            }
            logBusinessLogicControllerAccess.addLog(userID,"通过单据"+purchaseReturnID);
            messageBusinessLogicControllerAccess.addMessage(userID,"通过单据"+purchaseReturnID,purchaseReturnPO.getOperator(),true);
            clientBusinessLogicControllerAccess.modifyPayMoney(purchaseReturnPO.getClient(),purchaseReturnPO.getTotal());
            clientBusinessLogicControllerAccess.modifyClientLevel(purchaseReturnPO.getClient());
        }else{//驳回该单据
            HibernateUtil.getCurrentSession().beginTransaction();
            purchaseReturnPO.setState(FileState.DRAFT);
            salesmanDataService.updatePurchaseReturn(purchaseReturnPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回单据"+purchaseReturnID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回单据"+purchaseReturnID,purchaseReturnPO.getOperator(),true);
        }
    }

    @Override
    public ArrayList<String> waitReviewed(){
        ArrayList<String> ReviewedFiles=new ArrayList<>();
        if(listSale()!=null){
            for(SalePO spo:listSale()){
                if(spo.getState()==FileState.WAITREVIEW){
                    ReviewedFiles.add(spo.getID());
                }
            }
        }
        if(listPurchase()!=null){
            for(PurchasePO ppo:listPurchase()){
                if(ppo.getState()== FileState.WAITREVIEW){
                    ReviewedFiles.add(ppo.getID());
                }
            }
        }
        if(listPurchaseReturn()!=null){
            for(PurchaseReturnPO prpo:listPurchaseReturn()){
                if(prpo.getState()==FileState.WAITREVIEW){
                    ReviewedFiles.add(prpo.getID());
                }
            }
        }
        if(listSaleReturn()!=null){
            for(SaleReturnPO srpo:listSaleReturn()){
                if(srpo.getState()==FileState.WAITREVIEW){
                    ReviewedFiles.add(srpo.getID());
                }
            }
        }
        return ReviewedFiles;
    }

    @Override
    public SaleVO showSaleDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        SalePO salePO=salesmanDataService.readSale(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.SalePoToVo(salePO);
    }

    @Override
    public SaleReturnVO showSaleReturnDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        SaleReturnPO saleReturnPO=salesmanDataService.readSaleReturn(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.SaleReturnPoToVo(saleReturnPO);
    }

    @Override
    public PurchaseVO showPurchaseDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchasePO purchasePO=salesmanDataService.readPurchase(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.PurchasePoToVo(purchasePO);
    }

    @Override
    public PurchaseReturnVO showPurchaseReturnDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PurchaseReturnPO prpo=salesmanDataService.readPurchaseReturn(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.PurchaseReturnPoToVo(prpo);
    }

    private String getTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
