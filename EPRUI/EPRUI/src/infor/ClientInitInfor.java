package infor;

import vo.clientvo.ClientIdentity;

import java.io.Serializable;

public class ClientInitInfor implements Serializable{
    private static final long serialVersionUID=15L;

    private long cid;
    private String ID;//客户编号ID,只能是英文和数字
    private String name;//客户名字，可以是中文
    private ClientIdentity category;//客户种类
    private int Level;//客户级别
    private String address;//客户地址
    private String phoneNum;//客户电话号码
    private String Email;//客户邮箱
    private String postcode;//客户邮编
    private String staff;//该客户的默认业务员
    private double quota;//应收额度，只有最高权限可以修改
    private double toPay;//客户应付，不能手动修改
    private double toCollect;//客户应收，不能手动修改
    private double TotalAmount;//客户的累计交易金额，由此来判断客户的等级
    private InitState isBan;//客户状态，是否已经被禁用

    public ClientInitInfor(){}
    public ClientInitInfor(String id,String name, ClientIdentity category, int level, String address,String phoneNum,String postcode, String email,double quota,double toPay,double toCollect, double TotalAmount,String staff,InitState isBan) {

        this.ID=id;
        this.name=name;
        this.category=category;
        this.Level=level;
        this.address=address;
        this.phoneNum=phoneNum;
        this.Email=email;
        this.staff=staff;
        this.quota=quota;
        this.postcode=postcode;
        this.toPay=toPay;
        this.toCollect=toCollect;
        this.TotalAmount=TotalAmount;
        this.isBan=isBan;
    }

    public long getCid() {
        return this.cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public ClientIdentity getCategory(){
        return this.category;
    }

    public void setCategory(ClientIdentity clientCategory){
        this.category=clientCategory;
    }

    public int getLevel(){
        return this.Level;
    }

    public void setLevel(int level){
        this.Level=level;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum(){
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail(){
        return this.Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getStaff(){
        return this.staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public double getQuota(){
        return this.quota;
    }

    public void setQuota(double quota){
        this.quota=quota;
    }

    public String getPostcode(){
        return this.postcode;
    }

    public void setPostcode(String postcode){
        this.postcode=postcode;
    }

    public double getToPay(){
        return this.toPay;
    }

    public void setToPay(double toPay){
        this.toPay=toPay;
    }

    public double getToCollect(){
        return this.toCollect;
    }

    public void setToCollect(double toCollect){
        this.toCollect=toCollect;
    }

    public double getTotalAmount() {
        return this.TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.TotalAmount = totalAmount;
    }

    public InitState getIsBan(){
        return this.isBan;
    }

    public void setIsBan(InitState isBan){
        this.isBan=isBan;
    }

    public String toString(){
        return this.ID+" "+this.name;
    }

}
