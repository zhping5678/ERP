package vo.filevo;


import infor.Infor;

import java.io.Serializable;
import java.util.ArrayList;

public class SaleReturnVO implements Serializable {
    private static final long serialVersionUID=1111L;

    private String ID;//单据根据单据类型，创建时间和编号生成
    private FileType fileType;//单据类型
    private String operator;//操作员
    private String clerk;//业务员
    private String note;//备注
    private String createTime;//单据或者策略的创建时间
    private String passTime;//通过审批的时间
    private FileState state;//单据和策略的状态，包括草稿状态，提交等待审批，和已经通过审批状态，通过审批则不能再修改
    private String client;//销售商
    private String warehouse;//退货仓库
    private ArrayList<String[]> commodityList;//退货商品清单
    private double total;//退货商品最后的总价
    private ArrayList<Infor> information;//单据的经手信息

    public SaleReturnVO(String id,FileType type,FileState state,String operator,String clerk,String note,String createTime,String passTime,
                        String warehouse,String client,ArrayList<String[]> commodityList,double total,ArrayList<Infor> information){
        this.ID=id;
        this.fileType=type;
        this.operator=operator;
        this.clerk=clerk;
        this.note=note;
        this.createTime=createTime;
        this.passTime=passTime;
        this.state=state;
        this.client=client;
        this.warehouse=warehouse;
        this.commodityList=commodityList;
        this.total=total;
        this.information=information;
    }

    public String getID() {
        return ID;
    }
    public FileType getFileType() {
        return fileType;
    }
    public FileState getState() {
        return state;
    }
    public String getCreateTime() {
        return createTime;
    }
    public String getPassTime() {
        return passTime;
    }
    public String getClerk() {
        return clerk;
    }
    public String getOperator() {
        return operator;
    }
    public String getNote() {
        return note;
    }
    public String getClient() {
        return client;
    }
    public String getWarehouse(){
        return warehouse;
    }
    public ArrayList<String[]> getCommodityList() {
        return commodityList;
    }
    public double getTotal() {
        return total;
    }
    public ArrayList<Infor> getInformation() {
        return information;
    }
}
