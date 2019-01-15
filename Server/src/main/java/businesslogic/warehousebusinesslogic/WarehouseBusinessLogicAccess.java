package businesslogic.warehousebusinesslogic;

import po.warehousepo.CommodityPO;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityVO;

import java.util.ArrayList;

public interface WarehouseBusinessLogicAccess {
    public CommodityPO getCommodityHere(String path);

    public ArrayList<CommodityPO> list();//获得所有商品的列表，也可以只是那些isVisible是true的
    public ArrayList<CommodityVO> listCommodityVO();

    public ResultMessage modifyAmountOfInventory(String userID,String commodityID,int amount);

}
