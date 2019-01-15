package vo.clientvo;

import java.io.Serializable;

/*
 * @Name ClientVO
 * @Description 与展示层交互的数据传递
 * @author zhangping
 * @date 2017/11/29 9:50
 */

public class ClientVO implements Serializable{
    private static final long serialVersionUID=2L;

    private String ID;//客户编号ID
    private String name;//客户的名字，可以是中文
    private ClientIdentity category;//客户种类
    private int Level;//客户级别
    private String address;//客户地址
    private String phoneNum;//客户电话号码
    private String Email;//客户邮箱
    private String postcode;//客户邮编
    private String staff;//该客户的默认业务员
    private String note;//备注
    private double quota;//应收额度，只有最高权限可以修改
    private double toPay;//客户应付，不能手动修改
    private double toCollect;//客户应收，不能手动修改
    private double totalAmount;//累计金额
    private boolean isBan;//客户状态，是否已经被禁用



    public ClientVO(String ID,String name, ClientIdentity category, int Level, String address, String phoneNum, String Email, String postcode, String staff,String note, double quota,double toPay,double toCollect,double total,boolean isBan) {
        this.ID=ID;
        this.name=name;
        this.category=category;
        this.Level=Level;
        this.address=address;
        this.phoneNum=phoneNum;
        this.Email=Email;
        this.staff=staff;
        this.note=note;
        this.quota=quota;
        this.postcode=postcode;
        this.toPay=toPay;
        this.toCollect=toCollect;
        this.totalAmount=total;
        this.isBan=isBan;
    }

    public String getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public ClientIdentity getCategory() {
        return this.category;
    }

    public int getLevel() {
        return this.Level;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public String getStaff() {
        return this.staff;
    }

    public String getNote(){
        return this.note;
    }

    public double getQuota() {
        return this.quota;
    }

    public double getToCollect() {
        return this.toCollect;
    }

    public double getToPay(){
        return this.toPay;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public boolean getIsBan(){
        return this.isBan;
    }


    @Override
    public String toString(){
        return "ID:"+this.ID+"名字："+this.name+",类型:"+this.category.getIdentity()+",邮箱:"+this.Email+",电话号码:"+this.phoneNum;
    }
}
