package infor;


import java.io.Serializable;

//转账单
public class Transfer implements Serializable{
    private static final long serialVersionUID=1011L;

    private double money;
    private String note;
//    private ReceiptPO receiptPO;

//    public ReceiptPO getReceiptPO() {
//        return receiptPO;
//    }
//
//    public void setReceiptPO(ReceiptPO receiptPO) {
//        this.receiptPO = receiptPO;
//    }

//    public Set<Transfer> getReceiptPO() {
//        return receiptPO;
//    }
//    public void setReceiptPO(Set<Transfer> receiptPO) {
//        this.receiptPO = receiptPO;
//    }

    public Transfer(){}

//    public Transfer(String acountID,double money,String note){
//        this.accountID=acountID;
//        this.money=money;
//        this.note=note;
//    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

}
