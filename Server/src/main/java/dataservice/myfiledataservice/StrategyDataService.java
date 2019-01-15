package dataservice.myfiledataservice;

import po.filepo.StrategyPO.*;

import java.util.ArrayList;

public interface StrategyDataService {

    //分级策略
    LevelStrategyPO readLevelStrategy(String id);
    LevelStrategyPO readNowLevelStrategy();
    void write(LevelStrategyPO po);
    void update(LevelStrategyPO po);
    ArrayList<LevelStrategyPO> listLevelStrategy();
    void removeLevelStrategy(String id);

    //赠品策略
    GiftStrategyPO readGiftStrategy(String id);
    ArrayList<GiftStrategyPO> readNowGiftStrategy();
    void write(GiftStrategyPO po);
    void update(GiftStrategyPO po);
    ArrayList<GiftStrategyPO> listGiftStrategy();
    void removeGiftStrategy(String id);

    //折扣策略
    DiscountStrategyPO readDiscountStrategy(String id);
    DiscountStrategyPO readNowDiscountStrategy();
    void write(DiscountStrategyPO po);
    void update(DiscountStrategyPO po);
    ArrayList<DiscountStrategyPO> listDiscountStrategy();
    void removeDiscountStrategy(String id);

    //特价包策略
    PricePackStrategyPO readPricePackStrategy(String id);
    ArrayList<PricePackStrategyPO> readNowPricePackStrategy();
    void write(PricePackStrategyPO po);
    void update(PricePackStrategyPO po);
    ArrayList<PricePackStrategyPO> listPricePackStrategy();
    void removePricePackStrategy(String id);

    //代金券策略
    VoucherStrategyPO readVoucherStrategy(String id);
    VoucherStrategyPO readNowVoucherStrategy();
    void write(VoucherStrategyPO po);
    void update(VoucherStrategyPO po);
    ArrayList<VoucherStrategyPO> listVoucherStrategy();
    void removeVoucherStrategy(String id);

}
