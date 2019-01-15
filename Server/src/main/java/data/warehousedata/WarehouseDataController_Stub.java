package data.warehousedata;

import dataservice.warehousedataservice.WarehouseDataService;
import po.warehousepo.WarehousePO;

import java.util.ArrayList;

public class WarehouseDataController_Stub implements WarehouseDataService{
    @Override
    public void write(WarehousePO commodityPO) {

    }

    @Override
    public WarehousePO read(String ID) {
        if(ID=="A"){
            WarehousePO po=new WarehousePO("A","南京仓库",false);
            return po;
        }else{
            return null;
        }
    }

    @Override
    public void remove(String ID) {

    }

    @Override
    public void update(WarehousePO warehousePO) {

    }

    @Override
    public ArrayList<WarehousePO> showList() {
        return null;
    }

//    @Override
//    public ArrayList<String> findSonIDByFatherID(String fatherID) {
//        return null;
//    }
}
