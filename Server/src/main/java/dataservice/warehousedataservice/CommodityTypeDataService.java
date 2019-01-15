package dataservice.warehousedataservice;

import po.warehousepo.CommodityTypePO;

import java.util.ArrayList;

public interface CommodityTypeDataService {
    void write(CommodityTypePO commodityTypePO);
    CommodityTypePO read(String ID);
    void remove(String ID);
    void update(CommodityTypePO commodityTypePO);
    ArrayList<CommodityTypePO> showList();
    ArrayList<String> findSonIDByFatherID(String fatherID);
}
