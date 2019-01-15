package po.warehousepo;

/*
 *@Name: WarehousePO
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/3 2:35
 */

public class WarehousePO {
    private String ID;
    private String name;
    private boolean isBan;

    public WarehousePO(String i,String n,boolean isBan){
        this.ID=i;
        this.name=n;
        this.isBan=isBan;
    }

    public WarehousePO() {
    }

    public void setIsBan(boolean isBan) {
        this.isBan = isBan;
    }

    public boolean getIsBan(){
        return this.isBan;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String i){
        this.ID=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
