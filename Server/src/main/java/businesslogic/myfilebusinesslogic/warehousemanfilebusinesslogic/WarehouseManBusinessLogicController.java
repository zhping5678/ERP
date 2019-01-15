package businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic;

import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import businesslogicservice.myfilebusinesslogicservice.WarehouseManBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.myfiledataservice.WarehouseManDataService;
import infor.CommodityItem;
import infor.Infor;
import infor.ProductItem;
import po.filepo.ExcessPO;
import po.filepo.PurchasePO;
import po.filepo.SalePO;
import po.filepo.SaleReturnPO;
import po.warehousepo.CommodityPO;
import util.HibernateUtil;
import util.IDUtil;
import vo.filevo.ExcessVO;
import vo.filevo.FileState;
import vo.filevo.FileType;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CheckInventoryCommodityVO;
import vo.warehousevo.CommodityVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class WarehouseManBusinessLogicController extends UnicastRemoteObject implements WarehouseManBusinessLogicService,WarehouseManBusinessLogicControllerAccess {

    private WarehouseManDataService warehouseManDataService;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private TransExcessVoPo transExcessVoPo;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    public WarehouseManBusinessLogicController() throws RemoteException{
        warehouseManDataService= DataFactoryImpl.getInstance().getWarehouseManDataService();
        transExcessVoPo=new TransExcessVoPo();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.println("WarehouseManBusinessLogicController is connected.");
    }

    /*
     *@Name:add
     *@Description: tag为0，生成报溢单。tag为1，生成报损单
     *@Author: Jane
     @Date: 2018/1/5 0:47
     */

    @Override
    public String addExcessOrLoss(String userID, int tag) throws RemoteException{
        ExcessPO excessPO=new ExcessPO();
        String time=new IDUtil().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = dateFormat.format(new Date());
        String newID="";
        if(tag==0){
            int num=new IDUtil().getID(FileType.EXCESS);
            new IDUtil().increment(FileType.EXCESS);
            newID=FileType.EXCESS.getExpression()+""+time+""+num;
            excessPO.setExcessID(newID);
            excessPO.setType(FileType.EXCESS.getExpression());
        }else if(tag==1){
            int num=new IDUtil().getID(FileType.LOSS);
            new IDUtil().increment(FileType.LOSS);
            newID=FileType.LOSS.getExpression()+""+time+""+num;
            excessPO.setExcessID(newID);
            excessPO.setType(FileType.LOSS.getExpression());
        }
        excessPO.setState(FileState.DRAFT.getExpression());
        excessPO.setCreateTime(createTime);
        excessPO.setOperator(userID);
        excessPO.setWarehouseID("");
        excessPO.setSumNum(0);
        excessPO.setSumMoney(0.0);
        excessPO.setCommodityItems(null);
        excessPO.setInformation(null);
        excessPO.setPassTime(null);
        HibernateUtil.getCurrentSession().beginTransaction();
        warehouseManDataService.writeExcess(excessPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        System.out.println("Success");
        return newID;
    }

    @Override
    public ResultMessage delExcessOrLoss(String userID,String fileID) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        ExcessPO excessPO=warehouseManDataService.readExcess(fileID);
        if(excessPO.getState().equals(FileState.DRAFT.getExpression())){
            excessPO.setState(FileState.TRASH.getExpression());
            warehouseManDataService.updateExcess(excessPO);
        }else if(excessPO.getState().equals(FileState.TRASH.getExpression())){
            warehouseManDataService.removeExcess(fileID);
        }
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.delSuccess;
    }

    @Override
    public ResultMessage modExcessOrLoss(String userID, ExcessVO excessVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ExcessPO excessPO=warehouseManDataService.readExcess(excessVO.getExcessID());
        excessPO.setWarehouseID(excessVO.getWarehouseID());
        excessPO.setSumNum(excessVO.getSumNum());
        excessPO.setSumMoney(excessVO.getSumMoney());
        Set<Infor> information=new HashSet<>();
        ArrayList<Infor> infor=excessVO.getInformation();
        information.addAll(infor);
        excessPO.setInformation(information);
        Set<CommodityItem> commodityItems=new HashSet<>();
        ArrayList<CommodityItem> commodityItems1=excessVO.getCommodityItems();
        commodityItems.addAll(commodityItems1);
        excessPO.setCommodityItems(commodityItems);
        warehouseManDataService.updateExcess(excessPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.modiSuccess;
    }

    @Override
    public ExcessVO findExcessOrLoss(String userID, String fileID) throws RemoteException {
        ExcessVO vo=this.showExcessDetail(fileID);
        return vo;
    }

    @Override
    public ArrayList<String> showTrash() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ExcessPO> excessPOS=this.showList();
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.TRASH.getExpression())){
                array.add(po.getExcessID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showDraft() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ExcessPO> excessPOS=this.showList();
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.DRAFT.getExpression())){
                array.add(po.getExcessID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showWaitReview() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ExcessPO> excessPOS=this.showList();
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getExcessID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showReviewed() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ExcessPO> excessPOS=this.showList();
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.REVIEWED.getExpression())){
                array.add(po.getExcessID());
                System.out.println(po.getExcessID());
            }
        }
        return array;
    }

    @Override
    public ResultMessage commit(String userID, String fileID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ExcessPO excessPO=warehouseManDataService.readExcess(fileID);
        excessPO.setState(FileState.WAITREVIEW.getExpression());
        warehouseManDataService.updateExcess(excessPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(userID,"Commit file "+fileID);
        return ResultMessage.modiSuccess;
    }

//    @Override
//    public ArrayList<String> test(String startTime, String endTime) throws RemoteException {
//        HibernateUtil.getCurrentSession().beginTransaction();
//        ArrayList<String> a=warehouseManDataService.searchByDate(startTime,endTime);
//        HibernateUtil.getCurrentSession().getTransaction().commit();
//        return a;
//    }

    private ArrayList<ExcessPO> showList(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<ExcessPO> excessPOS=warehouseManDataService.showList();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return excessPOS;
    }

    @Override
    public void approveExcessOrLoss(String userID, String fileID, boolean result, Infor infor){
        HibernateUtil.getCurrentSession().beginTransaction();
        ExcessPO excessPO=warehouseManDataService.readExcess(fileID);
        Set<Infor> infors=excessPO.getInformation();
        infors.add(infor);
        excessPO.setInformation(infors);
        int tag=0;
        if(excessPO.getType().equals(FileType.EXCESS.getExpression())){
            tag=1;//如果是报溢单，tag=1
        }
        if(result){//通过审批
            excessPO.setState(FileState.REVIEWED.getExpression());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //String passTime = dateFormat.format(new Date());
            //excessPO.setPassTime(passTime);
            warehouseManDataService.updateExcess(excessPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            if(tag==0){
                for(CommodityItem ci:excessPO.getCommodityItems()){
                    warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,ci.getCommodityID(),(-1)*ci.getModiNum());
                }
            }else{
                for(CommodityItem ci:excessPO.getCommodityItems()){
                    warehouseBusinessLogicAccess.modifyAmountOfInventory(userID,ci.getCommodityID(),ci.getModiNum());
                }
            }
            logBusinessLogicControllerAccess.addLog(userID,"通过单据"+fileID);
            messageBusinessLogicControllerAccess.addMessage(userID,"通过单据"+fileID,excessPO.getOperator(),true);
        }else{//未通过审批
            excessPO.setState(FileState.DRAFT.getExpression());
            warehouseManDataService.updateExcess(excessPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回单据"+fileID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回单据"+fileID,excessPO.getOperator(),true);
        }
    }

    @Override
    public ArrayList<ArrayList<CheckInventoryCommodityVO>> checkInventory(String startTime, String endTime) throws RemoteException{
        ArrayList<ArrayList<CheckInventoryCommodityVO>> res=new ArrayList<>();
        ArrayList<CheckInventoryCommodityVO> checkInventoryCommodityVOS=new ArrayList<>();
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<PurchasePO> purchasePOS=warehouseManDataService.searchPurchaseByDate(startTime,endTime);
        ArrayList<SalePO> salePOS=warehouseManDataService.searchSaleByDate(startTime,endTime);
        ArrayList<SaleReturnPO> saleReturnPOS=warehouseManDataService.searchSaleReturnByDate(startTime,endTime);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        for(PurchasePO po:purchasePOS){
            System.out.println(po.getID()+"PURCHASE PURCHASE PURCHASE");
        }
        for(SalePO po:salePOS){
            System.out.println(po.getID()+"SALE SALE SALE SALE SALE");
        }
        for(SaleReturnPO po:saleReturnPOS){
            System.out.println(po.getID()+"SALERETURN SALERETURN SALERETURN ");
        }

        for(PurchasePO po:purchasePOS){
            if(po.getFileType().equals(FileType.PURCHASE)){
                ArrayList<ProductItem> productItems=new ArrayList<>(po.getCommodityList());
                for(int k=0;k<productItems.size();k++){
                    ProductItem productItem=productItems.get(k);
                    CommodityPO commodity=warehouseBusinessLogicAccess.getCommodityHere(productItem.getCommodityID());
                    CheckInventoryCommodityVO checkInventoryCommodityVO=new CheckInventoryCommodityVO(po.getPassTime(),commodity.getID(),commodity.getName(),"In",productItem.getNum(),commodity.getBuyingPrice()*productItem.getNum(),commodity.getAmountOfInventory());
                    checkInventoryCommodityVOS.add(checkInventoryCommodityVO);
                }
            }else if(po.getFileType().equals(FileType.PURCHASERETURN)){
                ArrayList<ProductItem> productItems=new ArrayList<>(po.getCommodityList());
                for(int k=0;k<productItems.size();k++){
                    ProductItem productItem=productItems.get(k);
                    CommodityPO commodity=warehouseBusinessLogicAccess.getCommodityHere(productItem.getCommodityID());
                    CheckInventoryCommodityVO checkInventoryCommodityVO=new CheckInventoryCommodityVO(po.getPassTime(),commodity.getID(),commodity.getName(),"Out",productItem.getNum(),commodity.getBuyingPrice()*productItem.getNum(),commodity.getAmountOfInventory());
                    checkInventoryCommodityVOS.add(checkInventoryCommodityVO);
                }
            }
        }
        for(SalePO po:salePOS){
            ArrayList<ProductItem> productItems=new ArrayList<>(po.getCommodityList());
            for(ProductItem productItem:productItems){
                CommodityPO commodityPO=warehouseBusinessLogicAccess.getCommodityHere(productItem.getCommodityID());
                System.out.println(productItem.getCommodityID()+"SSSSSSSSSSSSSS");
                System.out.println(commodityPO.getID()+"AAAAAAAAAAAAAAAAA");
                CheckInventoryCommodityVO checkInventoryCommodityVO=new CheckInventoryCommodityVO(po.getPassTime(),commodityPO.getID(),commodityPO.getName(),"Out",productItem.getNum(),commodityPO.getMarketPrice()*productItem.getNum(),commodityPO.getAmountOfInventory());
                checkInventoryCommodityVOS.add(checkInventoryCommodityVO);
            }
        }
        for(SaleReturnPO po:saleReturnPOS){
            ArrayList<ProductItem> productItems=new ArrayList<>(po.getCommodityList());
            for(ProductItem productItem:productItems){
                CommodityPO commodityPO=warehouseBusinessLogicAccess.getCommodityHere(productItem.getCommodityID());
                CheckInventoryCommodityVO checkInventoryCommodityVO=new CheckInventoryCommodityVO(po.getPassTime(),commodityPO.getID(),commodityPO.getName(),"In",productItem.getNum(),commodityPO.getMarketPrice()*productItem.getNum(),commodityPO.getAmountOfInventory());
            }
        }

        /*
         *@Name:
         *@Description: 合计
         *@Author: Jane
         @Date: 2018/1/12 22:13
         */
        ArrayList<CheckInventoryCommodityVO> calTotal=new ArrayList<>();
        for(CheckInventoryCommodityVO c:checkInventoryCommodityVOS){

        }
        res.add(checkInventoryCommodityVOS);
        return res;
    }

    @Override
    public ArrayList<String[]> stocking() throws RemoteException {
        ArrayList<String[]> stockingCommodities=new ArrayList<>();
        Random random=null;
        ArrayList<CommodityVO> commodityVOS=warehouseBusinessLogicAccess.listCommodityVO();
        for(CommodityVO vo:commodityVOS){
            String[] a=new String[7];
            a[0]=vo.getID();
            a[1]=vo.getSize();
            a[2]=vo.getAmountOfInventory()+"";
            a[3]=vo.getBuyingPrice()+"";
            a[4]="1";
            a[5]=Math.random()*10+"";
            a[6]="2018-01-13";
            stockingCommodities.add(a);
        }
        return stockingCommodities;
    }

    @Override
    public ArrayList<String> waitReview(){
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ExcessPO> excessPOS=this.showList();
        for(ExcessPO po:excessPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getExcessID());
            }
        }
        return array;
    }

    @Override
    public ExcessVO showExcessDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ExcessPO excessPO=warehouseManDataService.readExcess(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transExcessVoPo.excessPoToVo(excessPO);
    }



}
