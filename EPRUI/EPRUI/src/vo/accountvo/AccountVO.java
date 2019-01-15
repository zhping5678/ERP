package vo.accountvo;

import java.io.Serializable;

/*
 *@Name:AccountVO
 *@Description:
 *@author: Jane
 *@Date: Created in 2017/11/29 19:07
 */

public class AccountVO implements Serializable{

    private static final long serialVersionUID=1L;
    private String ID;//账户的ID，只能是英文和数字
    private String name;//账户的名字，可以是中文
    private double money;//账户金额
    private boolean isBan;//账户状态，是否被禁用

    public AccountVO(String id,String name,double money,boolean isBan){
        this.ID=id;
        this.name=name;
        this.money=money;
        this.isBan=isBan;
    }

    public String getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }

    public double getMoney(){
        return this.money;
    }

    public boolean getIsBan(){
        return this.isBan;
    }

    /*public String showAccount(){
        return this.ID+" "+this.name +" "+this.money+" "+this.isBan+".";
    }*/


}
