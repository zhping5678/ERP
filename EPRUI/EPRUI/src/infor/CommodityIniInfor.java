package infor;

import java.io.Serializable;

public class CommodityIniInfor implements Serializable {
    private static final long serialVersionUID=22L;

    private long comID;
    private String ID;
    private String name;
    private String size;
    private int amountOfInventory;//库存数量
    private double buyingPrice;//进价
    private double marketPrice;//零售价
    private double theRecentBuyingPrice;//最近进价
    private double theRecentMarketPrice;//最近零售价
    private InitState isBan;

    public CommodityIniInfor(){}

    public CommodityIniInfor(String id,String name,String size,int amountOfInventory,double buyingPrice,double marketPrice,double theRecentBuyingPrice,double theRecentMarketPrice,InitState isBan){
        this.ID=id;
        this.name=name;
        this.size=size;
        this.amountOfInventory=amountOfInventory;
        this.buyingPrice=buyingPrice;
        this.marketPrice=marketPrice;
        this.theRecentBuyingPrice=theRecentBuyingPrice;
        this.theRecentMarketPrice=theRecentMarketPrice;
        this.isBan=isBan;
    }

    public long getComID() {
        return this.comID;
    }

    public void setComID(long comID) {
        this.comID = comID;
    }

    public String getID(){
        return ID;
    }

    public void setID(String id){
        this.ID=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size=size;
    }

    public int getAmountOfInventory(){
        return amountOfInventory;
    }

    public void setAmountOfInventory(int amountOfInventory){
        this.amountOfInventory=amountOfInventory;
    }

    public double getBuyingPrice(){
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice){
        this.buyingPrice=buyingPrice;
    }

    public double getMarketPrice(){
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice){
        this.marketPrice=marketPrice;
    }

    public double getTheRecentBuyingPrice(){
        return theRecentBuyingPrice;
    }

    public void setTheRecentBuyingPrice(double theRecentBuyingPrice){
        this.theRecentBuyingPrice=theRecentBuyingPrice;
    }

    public double getTheRecentMarketPrice(){
        return theRecentMarketPrice;
    }

    public void setTheRecentMarketPrice(double theRecentMarketPrice){
        this.theRecentMarketPrice=theRecentMarketPrice;
    }

    public InitState getIsBan(){
        return isBan;
    }

    public void setIsBan(InitState isBan) {
        this.isBan = isBan;
    }

    public String toString(){
        return this.ID+" "+this.name;
    }
}
