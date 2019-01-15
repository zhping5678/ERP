package po.accountpo;

/*
 *@Name: AccountPO
 *@Description:
 *@author: Jane
 *@Date: Created in 2017/11/29 18:44
 */

public class AccountPO {

    private String ID;//账户的ID，只能是英文和数字
    private String name;//账户的名字，可以是中文
    private double money;//账户金额
    private boolean isBan;//账户状态，是否被禁用
    private boolean canDel;//账户是否产生过交易，一旦产生，就不能彻底从数据库中删除
    private boolean isOnService;//该账户是否有尚未完成的交易



    public AccountPO(String id,String name,double money,boolean isBan,boolean canDel,boolean isOn){

        this.ID=id;
        this.name=name;
        this.money=money;
        this.isBan=isBan;
        this.canDel=canDel;
        this.isOnService=isOn;
    }

    public AccountPO() {
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String id){
        this.ID=id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney(){
        return this.money;
    }

    public void setMoney(double m){
        money=m;
    }

    public boolean getIsBan(){
        return this.isBan;
    }

    public void setIsBan(boolean b){
        this.isBan=b;
    }

    public boolean getCanDel(){
        return this.canDel;
    }

    public void setCanDel(boolean canDel){
        this.canDel=canDel;
    }

    public boolean getIsOnService(){
        return this.isOnService;
    }

    public void setIsOnService(boolean isOn){
        this.isOnService=isOn;
    }

//    public String showAccountPO(){
//        return this.getID()+" "+this.getMoney()+" "+this.getIsBan()+" "+this.getCanDel()+" "+this.getIsOnService()+".";
//    }

}
