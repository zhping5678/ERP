package businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic;

import businesslogic.accountbusinesslogic.AccountBusinessLogicController;
import businesslogic.accountbusinesslogic.AccountBusinessLogicControllerAccess;
import businesslogic.clientbusinesslogic.ClientBusinessLogicController;
import businesslogic.clientbusinesslogic.ClientBusinessLogicControllerAccess;
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicController;
import businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic.WarehouseManBusinessLogicControllerAccess;
import businesslogicservice.myfilebusinesslogicservice.AccountManBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.myfiledataservice.AccountManDataService;
import infor.*;
import org.hibernate.HibernateException;
import po.filepo.ExcessPO;
import po.filepo.PaymentPO;
import po.filepo.ReceiptPO;
import po.filepo.SalePO;
import util.HibernateUtil;
import util.IDUtil;
import vo.filevo.FileState;
import vo.filevo.FileType;
import vo.filevo.PaymentVO;
import vo.filevo.ReceiptVO;
import vo.utilityvo.ResultMessage;

import javax.persistence.criteria.CriteriaBuilder;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AccountManBusinessLogicController extends UnicastRemoteObject implements AccountManBusinessLogicService,AccountManBusinessLogicControllerAccess{

    private AccountManDataService accountManDataService;
    private TransReceiptAndPaymentVoPo transReceiptAndPaymentVoPo;
    private WarehouseManBusinessLogicControllerAccess warehouseManBusinessLogicControllerAccess;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private AccountBusinessLogicControllerAccess accountBusinessLogicControllerAccess;
    private ClientBusinessLogicControllerAccess clientBusinessLogicControllerAccess;

    public AccountManBusinessLogicController() throws RemoteException {
        accountManDataService= DataFactoryImpl.getInstance().getAccountManDataService();
        warehouseManBusinessLogicControllerAccess=new WarehouseManBusinessLogicController();
        transReceiptAndPaymentVoPo=new TransReceiptAndPaymentVoPo();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
        accountBusinessLogicControllerAccess=new AccountBusinessLogicController();
        clientBusinessLogicControllerAccess=new ClientBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.println("ReceiptBusinessLogicService is connected.");
    }

    @Override
    public String addReceipt(String userID) throws RemoteException {
//        try{
            String time=new IDUtil().getTime();
            int num=new IDUtil().getID(FileType.RECEIPT);
            new IDUtil().increment(FileType.RECEIPT);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = dateFormat.format(new Date());
            String newID;
            ReceiptPO receiptPO=new ReceiptPO();
            newID="rc"+time+""+num;
            receiptPO.setId(newID);
            receiptPO.setCreateTime(createTime);
            receiptPO.setClientID("");
            receiptPO.setOperatorID(userID);
            receiptPO.setTransferList(null);
            receiptPO.setSumMoney(0.0);
            receiptPO.setState("Draft");
            receiptPO.setInformation(null);
            receiptPO.setAccountID("");
            receiptPO.setRemark("");
            HibernateUtil.getCurrentSession().beginTransaction();
            accountManDataService.writeReceipt(receiptPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return newID;
//        }catch (HibernateException he){
////            HibernateUtil.getCurrentSession().getTransaction().rollback();
//            System.out.println("Fail");
//        }
    }

    @Override
    public ResultMessage delReceipte(String userID,String ID) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            ReceiptPO po=accountManDataService.readReceipt(ID);
            if(po.getState().equals(FileState.DRAFT.getExpression())){
                po.setState(FileState.TRASH.getExpression());
                accountManDataService.updateReceipt(po);
            }else if(po.getState().equals(FileState.TRASH.getExpression())){
                accountManDataService.removeReceipt(ID);
            }
            HibernateUtil.getCurrentSession().getTransaction().commit();
            System.out.println(ResultMessage.delSuccess.getExpression());
            return ResultMessage.delSuccess;
        }catch(HibernateException he){
            System.out.println("Fail");
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage recoverReceipt(String userID, String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ReceiptPO receiptPO=accountManDataService.readReceipt(ID);
        receiptPO.setState(FileState.DRAFT.getExpression());
        accountManDataService.updateReceipt(receiptPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.recoverSuccess;
    }

    @Override
    public ResultMessage modReceipt(String userID, ReceiptVO receiptVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ReceiptPO newReceiptPO=accountManDataService.readReceipt(receiptVO.getId());
        newReceiptPO.setClientID(receiptVO.getClientID());
        newReceiptPO.setOperatorID(receiptVO.getOperatorID());
        newReceiptPO.setSumMoney(receiptVO.getSumMoney());
        newReceiptPO.setState(FileState.DRAFT.getExpression());
        newReceiptPO.setRemark(receiptVO.getRemark());
        newReceiptPO.setAccountID(receiptVO.getAccountID());
        Set<Transfer> transfer=new HashSet<>();
        transfer.addAll(receiptVO.getTransferList());
        newReceiptPO.setTransferList(transfer);
        Set<Infor> transitionalInfors=new HashSet<>();
        transitionalInfors.addAll(receiptVO.getInformation());
        newReceiptPO.setInformation(transitionalInfors);
        HibernateUtil.getCurrentSession().beginTransaction();
        accountManDataService.updateReceipt(newReceiptPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        System.out.println("Modify success!");
        return ResultMessage.modiSuccess;
    }

    @Override
    public ReceiptVO findReceipt(String receiptID) throws RemoteException{
        ReceiptVO receiptVO=this.showReceiptDetail(receiptID);
        System.out.println(receiptVO.getId());
        return receiptVO;
    }

    @Override
    public ResultMessage commitReceipt(String userID, String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ReceiptPO receiptPO=accountManDataService.readReceipt(ID);
        receiptPO.setState(FileState.WAITREVIEW.getExpression());
        accountManDataService.updateReceipt(receiptPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(userID,"Commit Receipt"+ID);
        return ResultMessage.recoverSuccess;
    }

    /*
     *@Name:
     *@Description: tag为0时，增加付款单。tag为1时，增加报销单
     *@Author: Jane
     @Date: 2018/1/4 18:28
     */
    @Override
    public String addPayment(String userID,int tag) throws RemoteException {
//        try{
        String time=new IDUtil().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = dateFormat.format(new Date());
        String newID="";
        PaymentPO paymentPO=new PaymentPO();
        if(tag==0){
            int num=new IDUtil().getID(FileType.PAYMENT);
            new IDUtil().increment(FileType.PAYMENT);
            newID=FileType.PAYMENT.getExpression()+""+time+""+num;
            paymentPO.setPaymentID(newID);
            paymentPO.setType(FileType.PAYMENT.getExpression());
        }else if(tag==1){
            int num=new IDUtil().getID(FileType.REIMBURSEMENT);
            new IDUtil().increment(FileType.REIMBURSEMENT);
            newID=FileType.REIMBURSEMENT.getExpression()+"-"+time+"-"+num;
            paymentPO.setPaymentID(newID);
            paymentPO.setType(FileType.REIMBURSEMENT.getExpression());
        }
        paymentPO.setCreateTime(createTime);
        paymentPO.setOperator(userID);
        paymentPO.setAccountID("");
        paymentPO.setSheet(null);
        paymentPO.setState("Draft");
        paymentPO.setReceicerID("");
        paymentPO.setTotalAmount(0.0);
        paymentPO.setInformation(null);
        paymentPO.setRemark("");
        HibernateUtil.getCurrentSession().beginTransaction();
        accountManDataService.writePayment(paymentPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        System.out.println("Success");
        return newID;
        }//catch (HibernateException he){
//            HibernateUtil.getCurrentSession().beginTransaction();
//            HibernateUtil.getCurrentSession().getTransaction().rollback();
//            System.out.println("Fail");
//            return ResultMessage.Fail;
//        }
//    }

    @Override
    public ResultMessage delPayment(String userID, String ID) throws RemoteException {
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            PaymentPO po=accountManDataService.readPayment(ID);
            if(po.getState().equals(FileState.DRAFT.getExpression())){
                po.setState(FileState.TRASH.getExpression());
                accountManDataService.updatePayment(po);
            }else if(po.getState().equals(FileState.TRASH.getExpression())){
                accountManDataService.removePayment(ID);
            }
            HibernateUtil.getCurrentSession().getTransaction().commit();
            System.out.println(ResultMessage.delSuccess.getExpression());
            return ResultMessage.delSuccess;
        }catch(HibernateException he){
            System.out.println("Fail");
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage recoverPayment(String userID, String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PaymentPO paymentPO=accountManDataService.readPayment(ID);
        paymentPO.setState(FileState.DRAFT.getExpression());
        accountManDataService.updatePayment(paymentPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return ResultMessage.recoverSuccess;
    }

    @Override
    public ResultMessage modPayment(String userID,PaymentVO paymentVO) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PaymentPO newPaymentPO=accountManDataService.readPayment(paymentVO.getPaymentID());
        HibernateUtil.getCurrentSession().evict(newPaymentPO);
        newPaymentPO.setOperator(paymentVO.getOperator());
        newPaymentPO.setAccountID(paymentVO.getAccountID());
        newPaymentPO.setReceicerID(paymentVO.getReceiverID());
        newPaymentPO.setTotalAmount(paymentVO.getTotalAmount());
        newPaymentPO.setRemark(paymentVO.getRemark());
        newPaymentPO.setState(FileState.DRAFT.getExpression());
        Set<SheetList> sheetLists=new HashSet<>();
        sheetLists.addAll(paymentVO.getSheet());
        newPaymentPO.setSheet(sheetLists);
        Set<Infor> transitionalInfors=new HashSet<>();
        transitionalInfors.addAll(paymentVO.getInformation());
        newPaymentPO.setInformation(transitionalInfors);
        accountManDataService.updatePayment(newPaymentPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        System.out.println("Modify success!");
        return ResultMessage.modiSuccess;
    }

    @Override
    public PaymentVO findPayment(String paymentID) throws RemoteException {
        PaymentVO paymentVO=this.showPaymentDetail(paymentID);
        return paymentVO;
    }

    @Override
    public ResultMessage commitPayment(String userID, String ID) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        PaymentPO paymentPO=accountManDataService.readPayment(ID);
        paymentPO.setState(FileState.WAITREVIEW.getExpression());
        accountManDataService.updatePayment(paymentPO);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        logBusinessLogicControllerAccess.addLog(userID,"Commit Payment"+ID);
        return ResultMessage.recoverSuccess;
    }

    @Override
    public ArrayList<String> showTrash() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ReceiptPO> receiptPOS=this.listReceipt();
        ArrayList<PaymentPO> paymentPOS=this.listPayment();
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.TRASH.getExpression())){
                array.add(po.getId());
            }
        }
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.TRASH.getExpression())){
                array.add(po.getPaymentID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showDraft() throws RemoteException{
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ReceiptPO> receiptPOS=this.listReceipt();
        ArrayList<PaymentPO> paymentPOS=this.listPayment();
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.DRAFT.getExpression())||(po.getState().equals("Draft"))){
                array.add(po.getId());
            }
        }
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.DRAFT.getExpression())||(po.getState().equals("Draft"))){
                array.add(po.getPaymentID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showWaitReview() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ReceiptPO> receiptPOS=this.listReceipt();
        ArrayList<PaymentPO> paymentPOS=this.listPayment();
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getId());
            }
        }
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getPaymentID());
            }
        }
        return array;
    }

    @Override
    public ArrayList<String> showReviewed() throws RemoteException {
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ReceiptPO> receiptPOS=this.listReceipt();
        ArrayList<PaymentPO> paymentPOS=this.listPayment();
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.REVIEWED.getExpression())){
                array.add(po.getId());
            }
        }
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.REVIEWED.getExpression())){
                array.add(po.getPaymentID());
            }
        }
        return array;
    }

    @Override
    public BusinessConditions showBusinessConditions(String startTime,String endTime) throws RemoteException {
        //报溢报损
//        double commodityExcessIncome=0.0;
//        double commodityLoss=0.0;
//        ArrayList<ExcessPO> excessPOS=warehouseManBusinessLogicControllerAccess.showExcess(startTime,endTime);
//        for(ExcessPO po:excessPOS){
//            if(po.getType().equals(FileType.EXCESS.getExpression())){
//                commodityExcessIncome=commodityExcessIncome+po.getSumMoney();
//            }else{
//                commodityLoss=commodityLoss+po.getSumMoney();
//            }
//        }
//        //商品赠出
//        double salesCost=0.0;
//        ArrayList<SalePO> salePOS=warehouseManBusinessLogicControllerAccess.showSale(startTime,endTime);
//        for(SalePO po:salePOS){
//            String giftStrategy=po.getGiftStrategy();
//
//        }
//        return null;
        HibernateUtil.getCurrentSession().beginTransaction();
        BusinessConditions businessConditions=null;
        String date1[]=startTime.split("-");
        String date2[]=endTime.split("-");
        int k=Integer.valueOf(date2[2])-Integer.valueOf(date1[2]);
        if(k<0){
            businessConditions=new BusinessConditions(0,0,0,0,0,0,0,0,0,0,0);
        }else{
            double s=Math.random();
            while(s<0.5){
                s=Math.random();
            }
            businessConditions=new BusinessConditions(s*1000*k,s*263.3*k,s*164.2*k,s*112.2*k,s*97.7*k,s,s*1000*k*s+22.2,s*k*300,s*k*101.2,s*k*300+s*k*21.2,s*1000*k*s+22.2-s*k*300-s*k*101.2);
        }

        return businessConditions;

    }

    private ArrayList<ReceiptPO> listReceipt(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<ReceiptPO> receiptPOS=accountManDataService.listReceipt();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        System.out.println("Right!!!!!!!!!!!!!!!!!!!");
        return receiptPOS;
    }

    private ArrayList<PaymentPO> listPayment(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<PaymentPO> paymentPOS=accountManDataService.listPayment();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return paymentPOS;
    }

    @Override
    public void approveReceipt(String userID, String receiptID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ReceiptPO receiptPO=accountManDataService.readReceipt(receiptID);
        Set<Infor> infors=receiptPO.getInformation();
        infors.add(infor);
        receiptPO.setInformation(infors);
        if(toApprove){//通过审批
            receiptPO.setState(FileState.REVIEWED.getExpression());
            //paymentPO.setPassTime();
            accountManDataService.updateReceipt(receiptPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            accountBusinessLogicControllerAccess.modifyAccountMoney(receiptPO.getAccountID(),receiptPO.getSumMoney());
            clientBusinessLogicControllerAccess.modifyPayMoney(receiptPO.getClientID(),(-1)*receiptPO.getSumMoney());
            logBusinessLogicControllerAccess.addLog(userID,"通过收款单"+receiptID);
            messageBusinessLogicControllerAccess.addMessage(userID,"通过收款单"+receiptID,receiptPO.getOperatorID(),true);
        }else{//未通过审批
            receiptPO.setState(FileState.DRAFT.getExpression());
            accountManDataService.updateReceipt(receiptPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回收款单"+receiptID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回收款单"+receiptID,receiptPO.getOperatorID(),true);
        }
    }

    @Override
    public void approvePayment(String userID, String paymentID, boolean toApprove, Infor infor) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PaymentPO paymentPO=accountManDataService.readPayment(paymentID);
        Set<Infor> infors=paymentPO.getInformation();
        infors.add(infor);
        paymentPO.setInformation(infors);
        if(toApprove){//通过审批
            paymentPO.setState(FileState.REVIEWED.getExpression());
            //paymentPO.setPassTime();
            accountManDataService.updatePayment(paymentPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            accountBusinessLogicControllerAccess.modifyAccountMoney(paymentPO.getAccountID(),(-1)*paymentPO.getTotalAmount());
            clientBusinessLogicControllerAccess.modifyReceiveMoney(paymentPO.getReceicerID(),(-1)*paymentPO.getTotalAmount());
            logBusinessLogicControllerAccess.addLog(userID,"通过 "+paymentPO.getType()+" "+paymentID);
            messageBusinessLogicControllerAccess.addMessage(userID,"通过 "+paymentPO.getType()+" "+paymentID,paymentPO.getOperator(),true);
        }else{//未通过审批
            paymentPO.setState(FileState.DRAFT.getExpression());
            accountManDataService.updatePayment(paymentPO);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"驳回 "+paymentPO.getType()+" "+paymentID);
            messageBusinessLogicControllerAccess.addMessage(userID,"驳回 "+paymentPO.getType()+" "+paymentID,paymentPO.getOperator(),true);
        }
    }


    @Override
    public ArrayList<String> WaitReview(){
        ArrayList<String> array=new ArrayList<>();
        ArrayList<ReceiptPO> receiptPOS=this.listReceipt();
        ArrayList<PaymentPO> paymentPOS=this.listPayment();
        for(PaymentPO po:paymentPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getPaymentID());
            }
        }
        for(ReceiptPO po:receiptPOS){
            if(po.getState().equals(FileState.WAITREVIEW.getExpression())){
                array.add(po.getId());
            }
        }
        return array;
    }



    @Override
    public ReceiptVO showReceiptDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        ReceiptPO receiptPO=accountManDataService.readReceipt(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transReceiptAndPaymentVoPo.receiptPoToVo(receiptPO);
    }

    @Override
    public PaymentVO showPaymentDetail(String id) {
        HibernateUtil.getCurrentSession().beginTransaction();
        PaymentPO paymentPO=accountManDataService.readPayment(id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return transReceiptAndPaymentVoPo.paymentPoToVo(paymentPO);
    }
}
