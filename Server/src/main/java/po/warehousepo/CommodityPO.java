package po.warehousepo;

/*
 *@Name: CommodityPO.有一个标签值，如果标签为1，代表商品分类；如果标签为0，代表商品。
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/1 14:49
 */

public class CommodityPO {
    private String ID;
    private String name;
    private String size;
    private int amountOfInventory;//库存数量
    private double buyingPrice;//进价
    private double marketPrice;//零售价
    private double theRecentBuyingPrice;//最近进价
    private double theRecentMarketPrice;//最近零售价
    private int warningNumber;
    private String father_id;
    private boolean isBan;
    private boolean isVisible;
    private boolean canDel;
    private boolean isOnService;

    public CommodityPO(){}

    public CommodityPO(String id,String name,String size,int amountOfInventory,double buyingPrice,double marketPrice,double theRecentBuyingPrice,double theRecentMarketPrice,int warningNumber,String father_id,boolean isBan,boolean isVisible,boolean canDel,boolean isOnService){
        this.ID=id;
        this.name=name;
        this.size=size;
        this.amountOfInventory=amountOfInventory;
        this.buyingPrice=buyingPrice;
        this.marketPrice=marketPrice;
        this.theRecentBuyingPrice=theRecentBuyingPrice;
        this.theRecentMarketPrice=theRecentMarketPrice;
        this.warningNumber=warningNumber;
        this.father_id=father_id;
        this.isBan=isBan;
        this.isVisible=isVisible;
        this.canDel=canDel;
        this.isOnService=isOnService;
    }


    public String getID(){
        return ID;
    }
    public void setID(String id){
        this.ID=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size=size;
    }

    public int getAmountOfInventory(){
        return amountOfInventory;
    }
    public void setAmountOfInventory(int amountOfInventory){
        this.amountOfInventory=amountOfInventory;
    }

    public double getBuyingPrice(){
        return buyingPrice;
    }
    public void setBuyingPrice(double buyingPrice){
        this.buyingPrice=buyingPrice;
    }

    public double getMarketPrice(){
        return marketPrice;
    }
    public void setMarketPrice(double marketPrice){
        this.marketPrice=marketPrice;
    }

    public double getTheRecentBuyingPrice(){
        return theRecentBuyingPrice;
    }
    public void setTheRecentBuyingPrice(double theRecentBuyingPrice){
        this.theRecentBuyingPrice=theRecentBuyingPrice;
    }

    public double getTheRecentMarketPrice(){
        return theRecentMarketPrice;
    }
    public void setTheRecentMarketPrice(double theRecentMarketPrice){
        this.theRecentMarketPrice=theRecentMarketPrice;
    }

    public int getWarningNumber(){
        return this.warningNumber;
    }
    public void setWarningNumber(int w){
        this.warningNumber=w;
    }

    public String getFather_id() {
        return father_id;
    }
    public void setFather_id(String father_id) {
        this.father_id = father_id;
    }

    public boolean getCanDel(){
        return canDel;
    }
    public void setCanDel(boolean canDel){
        this.canDel=canDel;
    }

    public boolean getIsVisible() {
        return isVisible;
    }
    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getIsBan(){
        return isBan;
    }
    public void setIsBan(boolean isBan){
        this.isBan=isBan;
    }

    public boolean getIsOnService(){
        return isOnService;
    }
    public void setIsOnService(boolean is){
        this.isOnService=is;
    }

    public String showCommodityPO(){
        return getID()+" "+getFather_id()+"..."+"isBan:"+getIsBan()+" "+"isVisible:"+getIsVisible()+" "+"canDel:"+getCanDel()+" isOnService:"+getIsOnService()+".";
    }
}
