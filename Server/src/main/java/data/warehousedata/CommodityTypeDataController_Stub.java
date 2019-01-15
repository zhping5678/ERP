package data.warehousedata;

import dataservice.warehousedataservice.CommodityTypeDataService;
import po.warehousepo.CommodityTypePO;

import java.util.ArrayList;

public class CommodityTypeDataController_Stub implements CommodityTypeDataService{
    @Override
    public void write(CommodityTypePO commodityPO) {

    }

    @Override
    public CommodityTypePO read(String ID) {
        if(ID=="A/a"){
            CommodityTypePO po=new CommodityTypePO("A/a","灯","A",false);
            return po;
        }else if(ID=="A/a/a1"){
            CommodityTypePO po=new CommodityTypePO("A/a/a1","日光灯","A/a",false);
            return po;
        }else if(ID=="A/a/a2"){
            CommodityTypePO po=new CommodityTypePO("A/a/a2","夜光灯","A/a",false);
            return po;
        }else if(ID=="A/b"){
            CommodityTypePO po=new CommodityTypePO("A/b","不知道什么灯","A",false);
            return po;
        }else{
            return null;
        }
    }

    @Override
    public void remove(String ID) {
    }

    @Override
    public void update(CommodityTypePO commodityTypePO) {

    }

    @Override
    public ArrayList<CommodityTypePO> showList() {
        return null;
    }

    @Override
    public ArrayList<String> findSonIDByFatherID(String fatherID) {
        if(fatherID=="A"){
            ArrayList<String> a=new ArrayList<>();
            String a1="A/a";
            String a2="A/b";
            a.add(a1);
            a.add(a2);
            return a;
        }
        if(fatherID=="A/a"){
            ArrayList<String> a=new ArrayList<>();
            String a1="A/a/a1";
            String a2="A/a/a2";
            a.add(a1);
            a.add(a2);
            return a;
        }else if(fatherID=="A/b"){
            ArrayList<String> a=new ArrayList<>();
            String a1="A/b/1";
            String a2="A/b/2";
            a.add(a1);
            a.add(a2);
            return a;
        } else{
            return null;
        }
    }
}
