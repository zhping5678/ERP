package vo.filevo;

import infor.Infor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class SaleVO implements Serializable {
    private static final long serialVersionUID=5000L;

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
    private ArrayList<String[]> commodityList;//出货商品清单,需要id(0)，name(1)，型号(2)，数量(3)，销售单价(4),单项总额(5)，备注(6)
    private String giftStrategy;//满足的赠送策略的ID
    private ArrayList<String[]> gifts;//赠品清单
    private Map<String,Integer> pricePack;//特价包方案，方案代码和特价包数量
    private double voucherStrategy;//满足的代金券赠送策略，本次赠送代金券，下次交易可用
    private double discount;//折扣策略
    private double allowance;//进货销售人员实行的折让金额
    private double voucher;//代金券金额
    private double total;//出货商品最后的总价
    private ArrayList<Infor> information;//单据的经手信息

    public SaleVO(String id,FileType type,FileState state,String operator,String clerk,String note,String createTime,String passTime,
                  String client,String warehouse,ArrayList<String[]> commodityList,String giftStrategy,ArrayList<String[]> gifts,double discount,
                  double voucherStrategy,Map<String,Integer> pricePack,double allowance,double voucher,double total,ArrayList<Infor> infor){
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
        this.giftStrategy=giftStrategy;
        this.gifts=gifts;
        this.voucherStrategy=voucherStrategy;
        this.pricePack=pricePack;
        this.discount=discount;
        this.allowance=allowance;
        this.voucher=voucher;
        this.total=total;
        this.information=infor;
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
    public String getGiftStrategy(){
        return giftStrategy;
    }
    public double getVoucherStrategy(){
        return voucherStrategy;
    }
    public Map<String,Integer> getPricePack(){
        return pricePack;
    }
    public ArrayList<String[]> getGifts(){
        return gifts;
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
    public ArrayList<Infor> getInformation() {
        return information;
    }
}
