package vo.filevo;

import infor.Infor;

import java.io.Serializable;
import java.util.ArrayList;

public class PurchaseReturnVO implements Serializable{
    private static final long serialVersionUID=10L;

    private String ID;//当单据通过审批，自动生成ID，结构为JHD-yyyyMMdd-xxxxx
    private FileType type;//单据类型，这里是进货单
    private String operator;//操作员ID，为当前系统的登录人员
    private String clerk;//业务员，一般客户有一个默认业务员
    private String note;//单据备注
    private String createTime;//创建时间
    private String passTime;//单据通过审批时间
    private FileState state;//单据状态，有三种情况
    private String clientID;//客户（供应商）的ID
    private String warehouseID;//进货仓库的ID
    private ArrayList<String []> productItems;//进货商品清单,需要id(0)，name(1)，型号(2)，数量(3)，进货单价(4),单项总额(5)，备注(6)
    private double total;//进货商品总额
    private ArrayList<Infor> information;//经手信息

    public PurchaseReturnVO(String id,FileType type,String operator,String clerk,String note,String createTime,
                      String passTime,FileState state,String clientID,String warehouseID,
                      ArrayList<String []> productItems,double total,ArrayList<Infor> infor){

        this.ID=id;
        this.type=type;
        this.operator=operator;
        this.clerk=clerk;
        this.note=note;
        this.createTime=createTime;
        this.passTime=passTime;
        this.state=state;
        this.clientID=clientID;
        this.warehouseID=warehouseID;
        this.productItems=productItems;
        this.total=total;
        this.information=infor;
    }

    public String getID() {
        return ID;
    }

    public FileType getType() {
        return type;
    }

    public String getOperator() {
        return operator;
    }

    public String getClerk() {
        return clerk;
    }

    public String getNote() {
        return note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getPassTime() {
        return passTime;
    }

    public FileState getState() {
        return state;
    }

    public String getClientID() {
        return clientID;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public ArrayList<String[]> getProductItems() {
        return productItems;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Infor> getInformation() {
        return information;
    }

    public String toString(){
        return ID;
    }
}

