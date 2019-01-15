package infor;

import java.io.Serializable;

public class SheetList implements Serializable{
    private static final long serialVersionUID=1010L;

    double money;
    String note;

    public SheetList( double money, String note){
        this.money=money;
        this.note=note;
    }

    public SheetList() {
    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
