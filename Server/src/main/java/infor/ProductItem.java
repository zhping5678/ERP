package infor;

import java.io.Serializable;

public class ProductItem implements Serializable{
    private static final long serialVersionUID=1007L;

    private long pid;
    private String commodityID;
    private String size;
    private int num;
    private double price;//单价
    private String note;

    public ProductItem(){}

    public ProductItem(String id,String size,int num,double price,String note){
        this.commodityID=id;
        this.size=size;
        this.num=num;
        this.price=price;
        this.note=note;
    }

    public long getPid() {
        return pid;
    }
    public String getCommodityID() {
        return commodityID;
    }
    public String getSize(){
        return size;
    }
    public int getNum() {
        return num;
    }
    public double getPrice() {
        return price;
    }
    public String getNote() {
        return note;
    }
    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setSize(String size){
        this.size=size;
    }
    public void setPid(long pid) {
        this.pid = pid;
    }
}
