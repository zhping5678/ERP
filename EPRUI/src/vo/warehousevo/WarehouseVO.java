package vo.warehousevo;

import java.io.Serializable;

public class WarehouseVO implements Serializable{
    private static final long serialVersionUID=7L;

    private String ID;
    private String name;
    private boolean isBan;

    public WarehouseVO(String id,String name,boolean isBan){
        this.ID=id;
        this.name=name;
        this.isBan=isBan;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean getIsBan(){
        return isBan;
    }

    public String showWarehouse(){
        return getID()+" "+getName();
    }
}
