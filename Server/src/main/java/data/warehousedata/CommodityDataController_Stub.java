package data.warehousedata;

import dataservice.warehousedataservice.CommodityDataService;
import po.warehousepo.CommodityPO;

import java.util.ArrayList;

public class CommodityDataController_Stub implements CommodityDataService {
    @Override
    public void write(CommodityPO commodityPO) {

    }

    @Override
    public CommodityPO read(String ID) {
        if(ID=="A/a/a1/1"){
            CommodityPO po=new CommodityPO("A/a/a1/1","大灯","XXL",5000,20.0,30.0,20.0,30.0,500,"A/a/a1",false,true,false,false);
            return po;
//            return null;
        }else if(ID=="A/a/a1/2"){
            CommodityPO po=new CommodityPO("A/a/a1/2","小灯","XXL",5000,20.0,30.0,20.0,30.0,500,"A/a/a2",false,true,false,false);
            return po;
        }else if(ID=="A/b/1"){
            CommodityPO po=new CommodityPO("A/b/1","小灯","XXL",5000,20.0,30.0,20.0,30.0,500,"A/b",false,true,false,false);
            return po;
        }else if(ID=="A/b/2"){
            CommodityPO po=new CommodityPO("A/b/2","小灯","XXL",5000,20.0,30.0,20.0,30.0,500,"A/b",false,true,false,false);
            return po;
        }else{
            return null;
        }
    }

    @Override
    public void remove(String ID) {
    }

    @Override
    public void update(CommodityPO commodityPO) {

    }

    @Override
    public ArrayList<CommodityPO> showList() {
        return null;
    }

    @Override
    public ArrayList<CommodityPO> findByKeywords(String keywords) {
        return null;
    }

    @Override
    public ArrayList<String> findSonIDByFatherID(String fatherID) {
        if(fatherID=="A/a/a1"){
            ArrayList<String> a=new ArrayList<>();
            String a1= "A/a/a1/1";
            String a2="A/a/a1/2";
            a.add(a1);
            a.add(a2);
            return a;
        }if(fatherID=="A/b"){
            ArrayList<String> a=new ArrayList<>();
            String a1= "A/b/1";
            String a2="A/b/2";
            a.add(a1);
            a.add(a2);
            return a;
        }else{
            return null;
        }
    }
}
