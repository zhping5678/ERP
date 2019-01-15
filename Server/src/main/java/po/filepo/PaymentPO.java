package po.filepo;

import infor.Infor;
import infor.SheetList;

import java.util.Set;

/**
 *@Name:
 *@Description: 付款单。单据中包含：
 *               单据编号（XJFYD-yyyyMMdd-xxxxx）,操作员（当前登录用户），银行账户，条目清单，总额。
 *               条目清单中包括：条目名，金额，备注。
 *@Author: Jane
 @Date: 2017/12/13 15:09
 */
 
public class PaymentPO {
    private String createTime;
    private String paymentID;
    private String operator;
    private String receicerID;
    private String accountID;
    private Set<SheetList> sheet;
    private double totalAmount;//总额
    private String remark;
    private String state;
    private String type;
    private Set<Infor> information;

    public PaymentPO(String createTime,String paymentID, String operator,String receiverID, String accountID, Set<SheetList> sheet, double totalAmount,String remark,String state,String type,Set<Infor> information){
        this.createTime=createTime;
        this.paymentID=paymentID;
        this.operator=operator;
        this.receicerID=receiverID;
        this.accountID=accountID;
        this.sheet=sheet;
        this.totalAmount=totalAmount;
        this.remark=remark;
        this.state=state;
        this.type=type;
        this.information=information;
    }

    public PaymentPO() {
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

    public Set<SheetList> getSheet() {
        return sheet;
    }
    public void setSheet(Set<SheetList> sheet) {
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Set<Infor> getInformation() {
        return information;
    }
    public void setInformation(Set<Infor> information) {
        this.information = information;
    }

    public String getReceicerID() {
        return receicerID;
    }
    public void setReceicerID(String receicerID) {
        this.receicerID = receicerID;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
