package po.clientpo;

import vo.clientvo.ClientIdentity;

public class ClientPO {

    private String ID;//客户编号ID,只能是英文和数字
    private String name;//客户名字，可以是中文
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
    private double TotalAmount;//客户的累计交易金额，由此来判断客户的等级
    private boolean isVisible;//客户是否已经被用户从界面删除
    private boolean isBan;//客户状态，是否已经被禁用
    private boolean CanDel;//客户是否已经产生了交易记录，如果有过交易，则不能彻底删除
    private boolean isOn;//客户现在是否有尚未结束的业务

    public ClientPO(){}

    public ClientPO(String id,String name, ClientIdentity category, int level, String phoneNum, String address,String postcode, String email,String note, double quota,double toPay,double toCollect, double TotalAmount,String staff,boolean isVisible,boolean isBan, boolean CanDel,boolean isOn) {

        this.ID=id;
        this.name=name;
        this.category=category;
        this.Level=level;
        this.address=address;
        this.phoneNum=phoneNum;
        this.Email=email;
        this.staff=staff;
        this.note=note;
        this.quota=quota;
        this.postcode=postcode;
        this.toPay=toPay;
        this.toCollect=toCollect;
        this.TotalAmount=TotalAmount;
        this.isVisible=isVisible;
        this.isBan=isBan;
        this.CanDel=CanDel;
        this.isOn=isOn;
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

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note=note;
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

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public void setIsVisible(boolean visible) {
        this.isVisible = visible;
    }

    public boolean getIsBan(){
        return this.isBan;
    }

    public void setIsBan(boolean isBan){
        this.isBan=isBan;
    }

    public boolean getCanDel(){
        return this.CanDel;
    }

    public void setCanDel(boolean canDel){
        this.CanDel=canDel;//一旦客户发生过交易事件，就不能彻底删除
    }

    public boolean getIsOn() {
        return this.isOn;
    }

    public void setIsOn(boolean on) {
        this.isOn = on;
    }

//    public String showClientPO(){
//        return this.getID()+" "+this.category+" "+this.Level+" "+this.address+" "+this.phoneNum+"..."+this.quota+" "+" "+this.toPay+" "+this.toCollect+" "+this.isBan+" "+this.CanDel+" "+this.isOn+".";
//    }
}
