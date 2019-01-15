package businesslogicservice.warehousebusinesslogicservice;

import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityTypeVO;
import vo.warehousevo.CommodityVO;
import vo.warehousevo.WarehouseVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface WarehouseBusinessLogicService extends Remote{
    public void connectionTest() throws RemoteException;

    public ArrayList<WarehouseVO> getOnlyWarehouse() throws RemoteException;

    public boolean hasCommodity(String path)throws RemoteException;

    public boolean isCommodity(String path) throws RemoteException;

    public boolean hasCommodityType(String path) throws RemoteException;

    public CommodityVO getCommodity(String path) throws RemoteException;

    public boolean hasChildren(String path) throws RemoteException;

    public ArrayList<String> getChildren(String path) throws RemoteException;

    public ResultMessage addNewCommodityID(String path) throws RemoteException;

    public ResultMessage newCommodity(String userID, CommodityVO vo) throws RemoteException;

    public ResultMessage addNewCommodityTypeID(String path) throws RemoteException;

    public ResultMessage newCommodityType(String userID, CommodityTypeVO vo) throws RemoteException;

    public ResultMessage addNewWarehouseID(String path) throws RemoteException;

    public ResultMessage newWarehouse(String userID, WarehouseVO vo) throws RemoteException;

    public ResultMessage updateCommodity(String userID, String oldID, CommodityVO vo) throws RemoteException;

    //不包括修改商品分类所属位置的情况
    public ResultMessage updateCommodityType(String userID, String oldID, CommodityTypeVO vo) throws RemoteException;

    public ResultMessage updateWarehouse(String userID, String oldID, WarehouseVO vo) throws RemoteException;

    public ResultMessage modifyCommodityLocation(String userID, String oldID, String path) throws RemoteException;

    //修改商品分类所属位置
    public ResultMessage modifyCommodityTypeLocation(String userID, String oldID, String path) throws RemoteException;

    public ResultMessage deleteCommodity(String userID, String path) throws RemoteException;

    public ResultMessage deleteCommodityType(String userID, String path) throws RemoteException;

    public ResultMessage deleteWarehouse(String userID, String path)throws RemoteException;

    public ResultMessage banCommodity(String userID, String path) throws RemoteException;

    public ResultMessage banCommodityType(String userID, String path) throws RemoteException;

    public ResultMessage banWarehouse(String userID, String path) throws RemoteException;

    public ResultMessage recoverCommodity(String userID, String path) throws RemoteException;

    public ResultMessage recoverCommodityType(String userID, String path) throws RemoteException;

    public ResultMessage recoverWarehouse(String userID, String path) throws RemoteException;

    public boolean isBanedCommodity(String path) throws RemoteException;

    public boolean isBanedCommodityType(String path) throws RemoteException;

    public boolean isBanedWarehouse(String path) throws RemoteException;

    //模糊查找 返回两个Arr,一个Arr为未被禁用的商品，一个Arr为已被禁用的商品
    public ArrayList<ArrayList<String>> findByKeyWords(String keyWords) throws RemoteException;
}
