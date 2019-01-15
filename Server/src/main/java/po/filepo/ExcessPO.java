package po.filepo;
/*
 *@Name:
 *@Description: 库存报溢单
 *@Author: Jane
 @Date: 2018/1/5 0:03
 */

import infor.CommodityItem;
import infor.Infor;

import java.util.Date;
import java.util.Set;

public class ExcessPO{
    private String state;
    private String type;
    private String createTime;
    private String excessID;
    private String operator;
    private String warehouseID;
    private Set<CommodityItem> commodityItems;
    private int sumNum;
    private double sumMoney;
    private Set<Infor> information;
    private Date passTime;

    public ExcessPO(){}

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

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExcessID() {
        return excessID;
    }
    public void setExcessID(String excessID) {
        this.excessID = excessID;
    }

    public String getOperator() {
        return this.operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getWarehouseID() {
        return warehouseID;
    }
    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public Set<CommodityItem> getCommodityItems() {
        return commodityItems;
    }
    public void setCommodityItems(Set<CommodityItem> commodityItems) {
        this.commodityItems = commodityItems;
    }

    public int getSumNum() {
        return sumNum;
    }
    public void setSumNum(int sumNum) {
        this.sumNum = sumNum;
    }

    public double getSumMoney() {
        return sumMoney;
    }
    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Set<Infor> getInformation() {
        return information;
    }
    public void setInformation(Set<Infor> information) {
        this.information = information;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }
}
