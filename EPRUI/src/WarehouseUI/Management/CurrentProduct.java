package WarehouseUI.Management;

public class CurrentProduct {
    private static String productID;
    private static boolean isBan;
    public static void setProductID(String id){
        productID=id;
    }
    public static String getProductID(){
        return productID;
    }

    public static void setIsBan(boolean is){
        isBan=is;
    }

    public static boolean getIsBan(){
        return isBan;
    }
}
