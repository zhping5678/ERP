package po.filepo;

import infor.Infor;
import infor.ProductItem;
import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class SalePO{

    private String ID;//单据根据单据类型，创建时间和编号生成
    private FileType fileType;//单据类型
    private String operator;//操作员
    private String clerk;//业务员
    private String note;//备注
    private String createTime;//单据或者策略的创建时间
    private String passTime;//如果是策略类文件，创建时间不是passTime，开始使用时间才是
    private FileState state;//单据和策略的状态，包括草稿状态，提交等待审批，和已经通过审批状态，通过审批则不能再修改
    private String client;//销售商
    private String warehouse;//出货仓库
    private Set<ProductItem> commodityList;//出货商品清单
    private Map<String,Integer> pricePack;//特价包方案，方案代码和特价包数量
    private double voucherStrategy;//满足的代金券赠送策略，本次赠送代金券，下次交易可用
    private String giftStrategy;//满足的赠送策略的ID
    private double discount;//折扣策略
    private double allowance;//进货销售人员实行的折让金额
    private double voucher;//使用代金券金额
    private double total;//出货商品最后的总价
    private Set<Infor> information;//单据的经手信息
    private Date searchTime;

    public SalePO(){}

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
    public Map<String,Integer> getPricePack(){
        return pricePack;
    }
    public double getVoucherStrategy(){
        return voucherStrategy;
    }
    public String getGiftStrategy(){
        return giftStrategy;
    }
    public double getDiscount() {
        return discount;
    }
    public double getAllowance() {
        return allowance;
    }
    public double getVoucher() {
        return voucher;
    }
    public double getTotal() {
        return total;
    }
    public Set<Infor> getInformation() {
        return information;
    }
    public Date getSearchTime() {
        return searchTime;
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
    public void setPricePack(Map<String, Integer> pricePack) {
        this.pricePack = pricePack;
    }
    public void setVoucherStrategy(double voucherStrategy) {
        this.voucherStrategy = voucherStrategy;
    }
    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public void setGiftStrategy(String giftStrategy) {
        this.giftStrategy = giftStrategy;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public void setVoucher(double voucher) {
        this.voucher = voucher;
    }
    public void setSearchTime(Date searchTime) {
        this.searchTime = searchTime;
    }
}
