package vo.warehousevo;

import java.io.Serializable;
import java.util.Date;

public class CheckInventoryCommodityVO implements Serializable{
    private static final long serialVersionUID=4000L;

    private String passTime;
    private String commodityID;
    private String name;
    private String inOrOut;
    private int num;
    private double money;
    private int warehouseLeftNum;

    public CheckInventoryCommodityVO(String passTime,String commodityID,String name,String inOrOut,int num,double money,int warehouseLeftNum){
        this.passTime=passTime;
        this.commodityID=commodityID;
        this.name=name;
        this.inOrOut=inOrOut;
        this.num=num;
        this.money=money;
        this.warehouseLeftNum=warehouseLeftNum;
    }

    public String getPassTime() {
        return passTime;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public String getName() {
        return name;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public int getNum() {
        return num;
    }

    public double getMoney() {
        return money;
    }

    public int getWarehouseLeftNum() {
        return warehouseLeftNum;
    }
}
