package businesslogicservice.myfilebusinesslogicservice;

import vo.filevo.*;
import vo.utilityvo.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * @Name SalesmanFileBusinessLogicService
 * @Description 进货退货单的方法接口
 * @author zhangping
 * @date 2017/12/26 0026 19:39
 */
public interface SalesmanFileBusinessLogicService extends Remote {

    void connectionTest() throws RemoteException;

    //列表显示草稿状态的单据
    ArrayList<String> listDraft(String userID) throws RemoteException;

    //列表显示待审核的单据
    ArrayList<String> listWaitToReview(String userID) throws RemoteException;

    //列表显示已经通过审核的单据
    ArrayList<String> listReviewed(String userID) throws RemoteException;

    //列表显示回收站里的单据
    ArrayList<String> listTrash(String userID) throws RemoteException;

    //新建一个进货单，销售单，以及退货单,点击新建，先返回一个id给界面
    String createSaleAndPurchase(String userID, FileType type) throws RemoteException;

    //删除一个处于草稿状态或是回收站的单据
    ResultMessage delete(String userID, String fileID) throws RemoteException;

    //还原回收站里面的进货单
    ResultMessage recover(String userID, String fileID) throws RemoteException;

    //提交进货单
    ResultMessage commit(String userID, String fileID) throws RemoteException;

    //修改草稿状态单据信息，开始填写单据信息时，相当于调用modify方法修改单据信息
    ResultMessage modifyPurchase(String userID, String fileID, PurchaseVO pvo) throws RemoteException;
    ResultMessage modifyPurchaseReturn(String userID, String fileID, PurchaseReturnVO purchaseReturnVO) throws RemoteException;
    ResultMessage modifySale(String userID, SaleVO saleVO) throws RemoteException;
    ResultMessage modifySaleReturn(String userID, SaleReturnVO saleReturnVO) throws RemoteException;

    //根据单据ID 查找单据信息
    PurchaseVO findPurchase(String id) throws RemoteException;
    PurchaseReturnVO findPurchaseReturn(String id) throws RemoteException;
    SaleVO findSale(String id) throws RemoteException;
    SaleReturnVO findSaleReturn(String id) throws RemoteException;

    //提供给进货销售人员判断满足的策略
    double checkDiscountStrategy(double total_money) throws RemoteException;
    double checkVoucherStrategy(double total_money) throws RemoteException;
    GiftStrategyVO checkGiftStrategy(double total_money) throws RemoteException;
    ArrayList<PricePackStrategyVO> listPricePackStrategy() throws RemoteException;

    //提供给进货销售人员的计算总额的方法
    double getTotal(SaleVO saleVO) throws RemoteException;
    //销售人员需要根据特价包的ID返回特价包的VO
    PricePackStrategyVO getPricePackStrategy(String id) throws RemoteException;
}
