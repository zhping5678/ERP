package dataservice.warehousedataservice;

import po.warehousepo.WarehousePO;

import java.util.ArrayList;

public interface WarehouseDataService {
    void write(WarehousePO warehousePO);
    WarehousePO read(String ID);
    void remove(String ID);
    void update(WarehousePO warehousePO);
    ArrayList<WarehousePO> showList();
    //ArrayList<String> findSonIDByFatherID(String fatherID);
}
