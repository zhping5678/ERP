package vo.filevo;

import infor.Infor;
import infor.Transfer;

import java.io.Serializable;
import java.util.ArrayList;

public class ReceiptVO implements Serializable{
    private static final long serialVersionUID=1003L;

    private String id;
    private String createTime;
    private String clientID;
    private String accountID;
    private String operatorID;
    private ArrayList<Transfer> transferList;
    private double sumMoney;
    private String state;
    private String remark;
    private ArrayList<Infor> information;

    public ReceiptVO(String id,String createTime, String clientID,String accountID, String operatorID, ArrayList<Transfer> transferList, double sumMoney, String state,String remark,ArrayList<Infor> information){
        this.id=id;
        this.createTime=createTime;
        this.clientID=clientID;
        this.accountID=accountID;
        this.transferList=transferList;
        this.operatorID=operatorID;
        this.sumMoney=sumMoney;
        this.state=state;
        this.remark=remark;
        this.information=information;
    }

    public String getId() {
        return id;
    }

    public String getClientID() {
        return clientID;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public ArrayList<Transfer> getTransferList() {
        return transferList;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public String getState() {
        return state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public ArrayList<Infor> getInformation() {
        return information;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getRemark() {
        return remark;
    }
}
