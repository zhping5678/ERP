package dataservice.warehousedataservice;

/*
 *@Name: CommodityDataService
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/3 1:52
 */
import po.warehousepo.CommodityPO;

import java.util.ArrayList;

public interface CommodityDataService{
    void write(CommodityPO commodityPO);
    CommodityPO read(String ID);
    void remove(String ID);
    void update(CommodityPO commodityPO);
    ArrayList<CommodityPO> showList();
    ArrayList<CommodityPO> findByKeywords(String keywords);
    ArrayList<String> findSonIDByFatherID(String fatherID);
}

