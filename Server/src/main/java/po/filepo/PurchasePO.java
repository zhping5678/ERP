package po.filepo;

import infor.Infor;
import infor.ProductItem;
import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Date;
import java.util.Set;

/*
 * @Name PurchasePO
 * @Description 采购单
 * @author zhangping
 * @date 2017/12/26 0026 16:04
 */

public class PurchasePO{

    private String ID;//单据通过审批后根据单据类型，通过审批时间和编号生成
    private FileType fileType;//单据类型
    private String operator;//操作员
    private String clerk;//业务员
    private String note;//备注
    private String createTime;//单据或者策略的创建时间
    private String passTime;//如果是策略类文件，创建时间不是passTime，开始使用时间才是
    private FileState state;//单据和策略的状态，包括草稿状态，提交等待审批，和已经通过审批状态，通过审批则不能再修改
    private String client;//供应商
    private String warehouse;//进货仓库
    private Set<ProductItem> commodityList;//进货商品清单
    private double total;//进货商品总价
    private Set<Infor> information;//单据的经手信息
    private Date date;

    /*public PurchasePO(String ID,FileType type,String operator,String clerk, String note, String createTime, String passTime, FileState state, String client,String warehouse, Set<ProductItem> products, double total,Set<String> infor){

        this.ID=ID;
        this.fileType=type;
        this.operator=operator;
        this.clerk=clerk;
        this.note=note;
        this.createTime=createTime;
        this.passTime=passTime;
        this.state=state;
        this.client=client;
        this.warehouse=warehouse;
        this.commodityList=products;
        this.total=total;
        this.information=infor;
    }*/
    public PurchasePO(){}

    public String  getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public FileType getFileType() {
        return fileType;
    }
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
    public String getOperator(){
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getClerk() {
        return this.clerk;
    }
    public void setClerk(String upo){
        this.clerk=upo;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getPassTime() {
        return passTime;
    }
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }
    public FileState getState() {
        return state;
    }
    public void setState(FileState state) {
        this.state = state;
    }
    public String getClient() {
        return this.client;
    }
    public void setClient(String cpo){
        this.client=cpo;
    }
    public String getWarehouse() {
        return this.warehouse;
    }
    public void setWarehouse(String wpo){
        this.warehouse=wpo;
    }
    public Set<ProductItem> getCommodityList() {
        return this.commodityList;
    }
    public void setCommodityList(Set<ProductItem> commodityList) {
        this.commodityList = commodityList;
    }
    public double getTotal() {
        return this.total;
    }
    public void setTotal(double total){
        this.total=total;
    }
    public Set<Infor> getInformation(){
        return this.information;
    }
    public void setInformation(Set<Infor> information) {
        this.information = information;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
