package vo.warehousevo;

import java.io.Serializable;

/*
 *@Name: CommodityPO.有一个标签值，如果标签为1，代表商品分类；如果标签为0，代表商品。
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/1 14:49
 */

public class CommodityVO implements Serializable{
    private static final long serialVersionUID=6L;
    private String ID;
    private String name;
    private String size;
    private int amountOfInventory;//库存数量
    private double buyingPrice;//进价
    private double marketPrice;//零售价
    private double theRecentBuyingPrice;//最近进价
    private double theRecentMarketPrice;//最近零售价
    private int warningNumber;//警戒值
    private boolean isBan;

    public CommodityVO(String id,String name,String size,int amountOfInventory,double buyingPrice,double marketPrice,double theRecentBuyingPrice,double theRecentMarketPrice,int warningNumber,boolean isBan){
        this.ID=id;
        this.name=name;
        this.size=size;
        this.amountOfInventory=amountOfInventory;
        this.buyingPrice=buyingPrice;
        this.marketPrice=marketPrice;
        this.theRecentBuyingPrice=theRecentBuyingPrice;
        this.theRecentMarketPrice=theRecentMarketPrice;
        this.warningNumber=warningNumber;
        this.isBan=isBan;
    }

    public String getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public String getSize(){
        return size;
    }
    public int getAmountOfInventory(){
        return amountOfInventory;
    }
    public double getBuyingPrice(){
        return buyingPrice;
    }
    public double getMarketPrice(){
        return marketPrice;
    }
    public double getTheRecentBuyingPrice(){
        return theRecentBuyingPrice;
    }
    public double getTheRecentMarketPrice(){
        return theRecentMarketPrice;
    }
    public int getWarningNumber(){return warningNumber;}
    public boolean getIsBan(){
        return isBan;
    }

    public String showCommodity(){
        return this.ID+" "+this.name+" "+this.size+" "+"..."+" ";
    }
}
