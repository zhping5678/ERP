package businesslogic.warehousebusinesslogic;

import po.warehousepo.CommodityTypePO;
import vo.warehousevo.CommodityTypeVO;

import java.util.ArrayList;

public class CommodityTypeTransVOPO {
    public CommodityTypeVO transPoToVo(CommodityTypePO po){
        CommodityTypeVO vo=new CommodityTypeVO(po.getID(),po.getName(),po.getIsBan());
        return vo;
    }

    public ArrayList<CommodityTypeVO> transPOsToVOs(ArrayList<CommodityTypePO> pos){
        ArrayList<CommodityTypeVO> vos=new ArrayList<CommodityTypeVO>();
        for(CommodityTypePO p:pos){
            vos.add(transPoToVo(p));
        }
        return vos;
    }
}
