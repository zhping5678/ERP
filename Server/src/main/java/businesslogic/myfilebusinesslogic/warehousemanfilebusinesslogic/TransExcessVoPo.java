package businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic;

import po.filepo.ExcessPO;
import vo.filevo.ExcessVO;

import java.util.ArrayList;

public class TransExcessVoPo {
    ExcessVO excessPoToVo(ExcessPO excessPO){
        ExcessVO vo=new ExcessVO(excessPO.getCreateTime(),excessPO.getExcessID(),excessPO.getOperator(),excessPO.getWarehouseID(),new ArrayList<>(excessPO.getCommodityItems()),excessPO.getSumNum(),excessPO.getSumMoney(),new ArrayList<>(excessPO.getInformation()));
        return vo;
    }

    ArrayList<ExcessVO> excessPosToVos(ArrayList<ExcessPO> excessPOS){
        ArrayList<ExcessVO> excessVOS=new ArrayList<>();
        for(ExcessPO excessPO:excessPOS){
            excessVOS.add(this.excessPoToVo(excessPO));
        }
        return excessVOS;
    }
}
