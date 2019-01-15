package businesslogicservice.myfilebusinesslogicservice;

import vo.filevo.*;
import vo.utilityvo.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

/*
 * @Name StraregyBusinessLogicService
 * @Description 为展示层策略类的实现提供接口，主要有创建策略，停止策略，列表显示等接口
 * @author zhangping
 * @date 2017/12/5 22:03
 */
public interface StrategyBusinessLogicService extends Remote {

    public void connectionTest() throws RemoteException;

    //新创建一个客户的分级方案,先返回一个id
    String createNewStrategy(String userID, FileType type) throws RemoteException;

    //修改草稿状态的分级方案，或是新建单据得到id后，填写具体信息
    ResultMessage modifyLevelStrategy(String userID,LevelStrategyVO vo) throws RemoteException;
    ResultMessage modifyGiftStrategy(String userID, GiftStrategyVO vo) throws RemoteException;
    //折扣策略只有备注和对应折扣内容可以修改，可以直接传过来
    ResultMessage modifyDiscountStrategy(String strategyID, String note, Map<Double,Double> contents) throws RemoteException;
    ResultMessage modifyPricePackStrategy(String strategyID,String note,ArrayList<String[]> commodityGroup) throws RemoteException;
    ResultMessage modifyVoucherStrategy(String strategyID,String note,Map<Double,Double> con) throws RemoteException;

    //停用一个方案
    ResultMessage stopStrategy(String userID,String strategyID) throws RemoteException;

    //开始启用一个方案
    ResultMessage startStrategy(String userID,String strategyID) throws RemoteException;

    //删除一个草稿状态，或是回收站中的方案
    ResultMessage deleteStrategy(String userID,String strategyID) throws RemoteException;

    //根据ID读取各种策略的ID
    LevelStrategyVO readLevelStrategy(String strategyID) throws RemoteException;
    GiftStrategyVO readGiftStrategy(String strategyID) throws RemoteException;
    DiscountStrategyVO readDiscountStrategy(String strategyID) throws RemoteException;
    PricePackStrategyVO readPricePackStrategy(String strategyID) throws RemoteException;
    VoucherStrategyVO readVoucherStrategy(String strategyID) throws RemoteException;

    //展示方案的列表
    ArrayList<String> listDraftStrategy() throws RemoteException;
    ArrayList<String> listTrashStrategy() throws RemoteException;
    ArrayList<String> listOnUseStrategy() throws RemoteException;
    ArrayList<String> listUsedStrategy() throws RemoteException;

}
