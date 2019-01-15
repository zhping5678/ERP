package businesslogic.myfilebusinesslogic.Strategybusinesslogic;

import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicAccess;
import businesslogic.warehousebusinesslogic.WarehouseBusinessLogicController;
import infor.ProductItem;
import po.filepo.StrategyPO.*;
import po.warehousepo.CommodityPO;
import vo.filevo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

class TransStrategyPoVo {

    private WarehouseBusinessLogicAccess warehouseBusinessLogicAccess;
    TransStrategyPoVo() throws RemoteException{
        warehouseBusinessLogicAccess=new WarehouseBusinessLogicController();
    }

    LevelStrategyVO LevelStrategyPoToVo(LevelStrategyPO lpo){
        double[] bounds={lpo.getLevel1(),lpo.getLevel2(),lpo.getLevel3(),lpo.getLevel4()};
        return new LevelStrategyVO(lpo.getID(),lpo.getType(),lpo.getFileState(),lpo.getCreator(),lpo.getLeastLevelToDel(),
                bounds,lpo.getIsUsing(),lpo.getCreateTime(),lpo.getStartTime(),lpo.getEndTime(),lpo.getNote());
    }

    GiftStrategyVO GiftStrategyPoToVo(GiftStrategyPO gpo){
        ArrayList<String[]> gifts=new ArrayList<>();
        for(ProductItem p:gpo.getGifts()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            String [] g={p.getCommodityID(),cpo.getName(),p.getSize(),String.valueOf(p.getNum()),p.getNote()};
            gifts.add(g);
        }
        return new GiftStrategyVO(gpo.getId(),gpo.getAmount(),gpo.getState(),gpo.getType(),gpo.getCreator(),
                gifts,gpo.getIsUsing(),gpo.getCreateTime(),gpo.getStartTime(), gpo.getEndTime(),gpo.getNote());
    }

    DiscountStrategyVO DiscountStrategyPoToVo(DiscountStrategyPO dpo){
        return new DiscountStrategyVO(dpo.getID(),dpo.getState(),dpo.getType(),dpo.getCreator(),dpo.getContent(),dpo.getIsUsing(),
                dpo.getCreateTime(),dpo.getStartTime(),dpo.getEndTime(),dpo.getNote());
    }

    PricePackStrategyVO PricePackStrategyPoToVo(PricePackStrategyPO ppo){
        ArrayList<String[]> comGroup=new ArrayList<>();
        for(ProductItem p:ppo.getCommodityGroup()){
            CommodityPO cpo=warehouseBusinessLogicAccess.getCommodityHere(p.getCommodityID());
            comGroup.add(new String[]{p.getCommodityID(),cpo.getName(),p.getSize(),p.getNum()+"",p.getPrice()+"",p.getNote()});
        }
        return new PricePackStrategyVO(ppo.getId(),ppo.getType(), ppo.getState(),ppo.getCreator(),comGroup,ppo.getIsUsing(),
                ppo.getCreateTime(),ppo.getStartTime(),ppo.getEndTime(),ppo.getNote());
    }

    VoucherStrategyVO VoucherStrategyPoToVo(VoucherStrategyPO vpo){
        return new VoucherStrategyVO(vpo.getID(),vpo.getState(),vpo.getType(),vpo.getCreator(),vpo.getContent(),vpo.getIsUsing(),
                vpo.getCreateTime(),vpo.getStartTime(),vpo.getEndTime(),vpo.getNote());
    }
}
