package businesslogic.myfilebusinesslogic.Strategybusinesslogic;

import infor.ProductItem;
import vo.filevo.GiftStrategyVO;
import vo.filevo.PricePackStrategyVO;

import java.util.ArrayList;

public interface StrategyBusinessLogicControllerAccess {

    int getLeastLevelToDel();
    int checkLevel(double totalAmount);//根据客户的总交易金额判断客户应该处在的等级

    GiftStrategyVO checkGiftStrategy(double money);//制定销售单时根据总额筛选是否有符合条件的赠品策略
    double checkVoucherStrategy(double money);//制定销售单时根据总额筛选是否有符合条件的代金券赠送策略
    double checkDiscountStrategy(double money);//制定销售单时根据总额筛选是否有符合条件的折扣赠送策略
    ArrayList<PricePackStrategyVO> listPricePackStrategy();//制定销售单时根据总额筛选是否有符合条件的代金券赠送策略
    double getPricePackTotalMoney(String id);//根据特价包策略的ID得到该特价包的总额
    PricePackStrategyVO getPricePackStrategy(String id);

    ArrayList<ProductItem> getGiftStrategy(String id);
}
