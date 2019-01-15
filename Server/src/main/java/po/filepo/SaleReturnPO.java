package po.filepo;

import infor.Infor;
import infor.ProductItem;

import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Date;
import java.util.Set;

public class SaleReturnPO{

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
    private Set<ProductItem> commodityList;//退货商品清单
    private double total;//退货商品最后的总价
    private Set<Infor> information;//单据的经手信息
    private Date searchDate;

    public SaleReturnPO(){}

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
    public Set<ProductItem> getCommodityList() {
        return commodityList;
    }
    public double getTotal() {
        return total;
    }
    public Set<Infor> getInformation() {
        return information;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
    public void setState(FileState state) {
        this.state = state;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }
    public void setClerk(String clerk) {
        this.clerk = clerk;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public void setInformation(Set<Infor> information) {
        this.information = information;
    }
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }
    public void setCommodityList(Set<ProductItem> commodityList) {
        this.commodityList = commodityList;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
}
