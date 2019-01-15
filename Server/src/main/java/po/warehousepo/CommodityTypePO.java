package po.warehousepo;

/*
 *@Name: CommodityTypePO
 *@Description: 定义商品分类的po。有一个标签值，如果标签为1，代表商品分类；如果标签为0，代表商品。
 *@Author: Jane
 *@Date: 2017/12/3 0:26
 */

public class CommodityTypePO {
    private String ID;
    private String name;
    private String father_id;
    private boolean isBan;

    public CommodityTypePO(String i,String n,String father_id,boolean isBan){
        this.ID=i;
        this.name=n;
        this.father_id=father_id;
        this.isBan=isBan;
    }

    public CommodityTypePO() {
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String i){
        this.ID=i;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String n){
        this.name=n;
    }

    public String getFather_id() {
        return father_id;
    }
    public void setFather_id(String father_id) {
        this.father_id = father_id;
    }

//    public boolean getCanDel(){
//        return this.canDel;
//    }
//    public void setCanDel(boolean c){
//        this.canDel=c;
//    }
//
//    public boolean getIsVisible() {
//        return isVisible;
//    }
//    public void setIsVisible(boolean isVisible) {
//        this.isVisible = isVisible;
//    }

    public boolean getIsBan(){
        return this.isBan;
    }
    public void setIsBan(boolean is){
        this.isBan=is;
    }

//    public boolean getIsOnService(){
//        return this.isOnService;
//    }
//    public void setIsOnService(boolean is){
//        this.isOnService=is;
//    }

}



