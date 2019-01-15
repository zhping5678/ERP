package infor;

import java.io.Serializable;

public class CommodityItem implements Serializable{
    private static final long serialVersionUID=1005L;

    private String commodityID;
    private String commodityname;
    private String commoditySize;
    private int inventory;
    private int modiNum;
    private int updateNum;

    public CommodityItem(){}

    public CommodityItem(String commodityID,String commodityname,String commoditySize,int inventory,int modiNum,int updateNum){
        this.commodityID=commodityID;
        this.commodityname=commodityname;
        this.commoditySize=commoditySize;
        this.inventory=inventory;
        this.modiNum=modiNum;
        this.updateNum=updateNum;
    }

    public String getCommodityID() {
        return commodityID;
    }
    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public String getCommodityname() {
        return commodityname;
    }
    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommoditySize() {
        return commoditySize;
    }
    public void setCommoditySize(String commoditySize) {
        this.commoditySize = commoditySize;
    }

    public int getInventory() {
        return inventory;
    }
    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getModiNum() {
        return modiNum;
    }
    public void setModiNum(int modiNum) {
        this.modiNum = modiNum;
    }

    public int getUpdateNum() {
        return updateNum;
    }
    public void setUpdateNum(int updateNum) {
        this.updateNum = updateNum;
    }
}
