package businesslogic.warehousebusinesslogic;

import po.warehousepo.WarehousePO;
import vo.warehousevo.WarehouseVO;

import java.util.ArrayList;

public class WarehouseTransVOPO {
    public WarehouseVO transPoToVo(WarehousePO po){
        WarehouseVO vo=new WarehouseVO(po.getID(),po.getName(),po.getIsBan());
        return vo;
    }

    public ArrayList<WarehouseVO> transPosToVos(ArrayList<WarehousePO> pos){
        ArrayList<WarehouseVO> vos=new ArrayList<>();
        for(WarehousePO po:pos){
            vos.add(transPoToVo(po));
        }
        return vos;
    }
}
