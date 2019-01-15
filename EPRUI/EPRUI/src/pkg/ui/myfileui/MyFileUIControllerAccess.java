package pkg.ui.myfileui;

import javafx.collections.ObservableList;
import pkg.unclassified.ItemText;
import vo.filevo.*;

public interface MyFileUIControllerAccess {
    public static MyFileUIControllerAccess myFileUIControllerAccess =MyFileUIController.getInstance();
    public ObservableList<ItemText> getDraftList();
    public ObservableList<ItemText> getPendingList();
    public ObservableList<ItemText> getArchiveList();
    public ObservableList<ItemText> getTrashList();
    public LevelStrategyVO getLevelVO();
    public VoucherStrategyVO getVoucherVO();
    public PricePackStrategyVO getPricePack();
    public DiscountStrategyVO getDiscount();
    public GiftStrategyVO getGift();
    public void saveLevelVO(LevelStrategyVO levelStrategyVO);
    public void saveVoucherVO(VoucherStrategyVO voucherStrategyVO);
    public void savePricePackVO(PricePackStrategyVO pricePackStrategyVO);
    public void saveDiscountVO(DiscountStrategyVO discountStrategyVO);
    public void saveGiftStrategyVO(GiftStrategyVO giftStrategyVO);
    public void startStrategy(String id);
    public void stopStrategy(String id);
    public SaleVO getSalesVO(String id);
    public PurchaseVO getPurchaseVO(String id);






}
