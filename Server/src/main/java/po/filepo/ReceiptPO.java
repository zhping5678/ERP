package po.filepo;


import infor.Infor;
import infor.Transfer;

import java.util.Set;

public class ReceiptPO{
    private String id;
    private String createTime;
    private String clientID;
    private String accountID;
    private String operatorID;
    private Set<Transfer> transferList;
    private double sumMoney;
    private String state;
    private String remark;
    private Set<Infor> information;

//    public ReceiptPO(String id,String clientID,String operatorID,Set<Transfer> transferList,double sumMoney,FileState state,Set<String> information){
//        this.id=id;
//        this.clientID=clientID;
//        this.operatorID=operatorID;
//        this.transferList=transferList;
//        this.sumMoney=sumMoney;
//        this.state=state;
//        this.information=information;
//    }

    public ReceiptPO() {
    }


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClientID() {
        return clientID;
    }
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getOperatorID() {
        return operatorID;
    }
    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public Set<Transfer> getTransferList() {
        return transferList;
    }
    public void setTransferList(Set<Transfer> transferList) {
        this.transferList = transferList;
    }

    public double getSumMoney() {
        return sumMoney;
    }
    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public Set<Infor> getInformation() {
        return information;
    }
    public void setInformation(Set<Infor> information) {
        this.information = information;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
