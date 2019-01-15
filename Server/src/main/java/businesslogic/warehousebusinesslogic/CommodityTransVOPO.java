package businesslogic.warehousebusinesslogic;

import po.warehousepo.CommodityPO;
import vo.warehousevo.CommodityVO;

import java.util.ArrayList;

/**
 *@Name: CommodityTransVOPO
 *@Description: 商品信息的VOPO之间的转换
 *@Author: Jane
 *@Date: 2017/12/2 15:46
 */
public class CommodityTransVOPO {
    public CommodityVO transPoToVo(CommodityPO po){
        CommodityVO vo=new CommodityVO(po.getID(),po.getName(),po.getSize(),po.getAmountOfInventory(),po.getBuyingPrice(),po.getMarketPrice(),po.getTheRecentBuyingPrice(),po.getTheRecentMarketPrice(),po.getWarningNumber(),po.getIsBan());
        return vo;
    }

    public ArrayList<CommodityVO> transPOsToVOs(ArrayList<CommodityPO> pos){
        ArrayList<CommodityVO> vos=new ArrayList<CommodityVO>();
        for(CommodityPO p:pos){
            vos.add(transPoToVo(p));
        }
        return vos;
    }
}
