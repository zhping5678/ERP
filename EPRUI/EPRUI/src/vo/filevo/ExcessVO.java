package vo.filevo;

import infor.CommodityItem;
import infor.Infor;

import java.io.Serializable;
import java.util.ArrayList;

public class ExcessVO implements Serializable{
    private static final long serialVersionUID=1000L;

    private String createTime;
    private String excessID;
    private String operator;
    private String warehouseID;
    private ArrayList<CommodityItem> commodityItems;
    private int sumNum;
    private double sumMoney;
    private ArrayList<Infor> information;

    public ExcessVO(String createTime,String excessID,String operator,String warehouseID,ArrayList<CommodityItem> commodityItems,int sumNum,double sumMoney,ArrayList<Infor> information){
//        this.state=state;
//        this.type=type;
        this.createTime=createTime;
        this.excessID=excessID;
        this.operator=operator;
        this.warehouseID=warehouseID;
        this.commodityItems=commodityItems;
        this.sumNum=sumNum;
        this.sumMoney=sumMoney;
        this.information=information;
    }

//    public ExcessVO(String operator,String warehouseID,ArrayList<CommodityItem> commodityItemSet,int sumNum,double sumMoney,ArrayList<String> information){
//        this.operator=operator;
//        this.warehouseID=warehouseID;
//        this.commodityItems=commodityItemSet;
//        this.sumNum=sumNum;
//        this.sumMoney=sumMoney;
//        this.information=information;
//    }



//    public String getState() {
//        return state;
//    }
//
//    public String getType() {
//        return type;
//    }

    public String getCreateTime() {
        return createTime;
    }

    public String getExcessID() {
        return excessID;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public ArrayList<CommodityItem> getCommodityItems() {
        return commodityItems;
    }

    public int getSumNum() {
        return sumNum;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public ArrayList<Infor> getInformation() {
        return information;
    }

}
