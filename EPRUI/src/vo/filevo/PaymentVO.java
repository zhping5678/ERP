package vo.filevo;

import infor.Infor;
import infor.SheetList;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentVO implements Serializable{
    private static final long serialVersionUID=1001L;

    private String createTime;
    private String paymentID;
    private String operator;
    private String receiverID;
    private String accountID;
    private ArrayList<SheetList> sheet;
    private double totalAmount;//总额
    private String state;
    private String remark;
    private ArrayList<Infor> information;

    public PaymentVO(String createTime,String paymentID, String operator,String receiverID, String accountID, ArrayList<SheetList> sheet, double totalAmount,String state,String note,ArrayList<Infor> information){
        this.createTime=createTime;
        this.paymentID=paymentID;
        this.operator=operator;
        this.receiverID=receiverID;
        this.accountID=accountID;
        this.sheet=sheet;
        this.totalAmount=totalAmount;
        this.state=state;
        this.remark=note;
        this.information=information;
    }

    public PaymentVO() {
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public ArrayList<SheetList> getSheet() {
        return sheet;
    }
    public void setSheet(ArrayList<SheetList> sheet) {
        this.sheet = sheet;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public ArrayList<Infor> getInformation() {
        return information;
    }
    public void setInformation(ArrayList<Infor> information) {
        this.information = information;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiverID() {
        return receiverID;
    }
    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }
}