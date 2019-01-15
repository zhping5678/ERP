package infor;

import java.io.Serializable;

public class AccountInitInfor implements Serializable{
    private static final long serialVersionUID=18L;

    private long aid;
    private String ID;
    private String name;
    private double money;
    private InitState isBan;

    public AccountInitInfor(String id,String name,double money,InitState isBan){

        this.ID=id;
        this.name=name;
        this.money=money;
        this.isBan=isBan;
    }

    public AccountInitInfor() {}

    public long getAid() {
        return this.aid;
    }

    public void setAid(long a){
        this.aid=a;
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

    public InitState getIsBan() {
        return isBan;
    }

    public void setIsBan(InitState isBan) {
        this.isBan = isBan;
    }

    public String toString(){
        return this.ID+" "+this.name;
    }
}
