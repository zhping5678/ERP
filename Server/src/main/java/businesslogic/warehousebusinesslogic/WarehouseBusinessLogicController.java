package businesslogic.warehousebusinesslogic;
/*
 *@Name: 
 *@Description: 
 *@Author: Janeas
 @Date: 2017/12/26 20:46
 */
 
import businesslogic.logbusinesslogic.LogBusinessLogicController;
import businesslogic.logbusinesslogic.LogBusinessLogicControllerAccess;
import businesslogic.messagebusinesslogic.MessageBusinessLogicController;
import businesslogic.messagebusinesslogic.MessageBusinessLogicControllerAccess;
import businesslogic.userbusinesslogic.UserBusinessLogicController;
import businesslogic.userbusinesslogic.UserBusinessLogicControllerAccess;
import businesslogic.utilitybusinesslogic.UtilityBusinessLogicControllerAccess;
import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;
import data.DataFactoryImpl;
//import data.warehousedata.CommodityDataController_Stub;
//import data.warehousedata.CommodityTypeDataController_Stub;
//import data.warehousedata.WarehouseDataController_Stub;
import dataservice.warehousedataservice.CommodityDataService;
import dataservice.warehousedataservice.CommodityTypeDataService;
import dataservice.warehousedataservice.WarehouseDataService;
import po.warehousepo.CommodityPO;
import po.warehousepo.CommodityTypePO;
import po.warehousepo.WarehousePO;
import util.HibernateUtil;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityTypeVO;
import vo.warehousevo.CommodityVO;
import vo.warehousevo.WarehouseVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class WarehouseBusinessLogicController extends UnicastRemoteObject implements WarehouseBusinessLogicService,WarehouseBusinessLogicAccess {

//    private UtilityBusinessLogicControllerAccess utilityBusinessLogicControllerAccess;
    private WarehouseDataService warehouseDataService;
    private CommodityDataService commodityDataService;
    private CommodityTypeDataService commodityTypeDataService;
    private WarehouseTransVOPO warehouseTransVOPO;
    private CommodityTransVOPO commodityTransVOPO;
//    private CommodityTypeTransVOPO commodityTypeTransVOPO;
    private LogBusinessLogicControllerAccess logBusinessLogicControllerAccess;
    private MessageBusinessLogicControllerAccess messageBusinessLogicControllerAccess;
    private UserBusinessLogicControllerAccess userBusinessLogicControllerAccess;

    public WarehouseBusinessLogicController() throws RemoteException{
//        warehouseDataService=new WarehouseDataController_Stub();
//        commodityDataService=new CommodityDataController_Stub();
//        commodityTypeDataService=new CommodityTypeDataController_Stub();
        commodityDataService= DataFactoryImpl.getInstance().getCommodityDataService();
        commodityTypeDataService=DataFactoryImpl.getInstance().getCommodityTypeDataService();
        warehouseDataService=DataFactoryImpl.getInstance().getWarehouseDataService();
        commodityTransVOPO=new CommodityTransVOPO();
//        commodityTypeTransVOPO=new CommodityTypeTransVOPO();
        warehouseTransVOPO=new WarehouseTransVOPO();
        logBusinessLogicControllerAccess=new LogBusinessLogicController();
        messageBusinessLogicControllerAccess=new MessageBusinessLogicController();
        userBusinessLogicControllerAccess=new UserBusinessLogicController();
    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.println();
    }

    @Override
    public ArrayList<WarehouseVO> getOnlyWarehouse() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        warehouseTransVOPO=new WarehouseTransVOPO();
        ArrayList<WarehousePO> warehousePOS=warehouseDataService.showList();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return warehouseTransVOPO.transPosToVos(warehousePOS);
    }

    @Override
    public boolean isCommodity(String path) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        if(commodityDataService.read(path)==null){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return false;
        }else{
            return true;
        }
    }

    //判断该结点下有没有商品
    @Override
    public boolean hasCommodity(String path) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> a=commodityDataService.findSonIDByFatherID(path);
        if(a.size()==0){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            System.out.println(path+" No Commodity!!!!!!!!!!!!!!!!!!!!!!");
            return false;
        }else{
            HibernateUtil.getCurrentSession().getTransaction().commit();
            System.out.println(path+" Has Commodity!!!!!!!!!!!!!!!!!!!!!!!!");
            return true;
        }
    }

    //判断该节点下有没有商品分类
    @Override
    public boolean hasCommodityType(String path) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> a=commodityTypeDataService.findSonIDByFatherID(path);
        if(a.size()==0){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return false;
        }else{
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return true;
        }
    }

    @Override
    public CommodityVO getCommodity(String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        CommodityPO po=commodityDataService.read(path);
        CommodityVO vo=commodityTransVOPO.transPoToVo(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return vo;
    }

    @Override
    public boolean hasChildren(String path) throws RemoteException{
        if((!hasCommodity(path))&&(!hasCommodityType(path))){
            System.out.println(path+" No Children!!!!!!!!!!!!!!!!!!!!");
            return false;
        }else{
            System.out.println(path+" Has Children!!!!!!!!!!!!!!!!!!!!");
            return true;
        }
    }

    @Override
    public ArrayList<String> getChildren(String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> str;
        if(commodityTypeDataService.findSonIDByFatherID(path).size()==0){
            str=commodityDataService.findSonIDByFatherID(path);
            HibernateUtil.getCurrentSession().getTransaction().commit();
        }else{
            str=commodityTypeDataService.findSonIDByFatherID(path);
            HibernateUtil.getCurrentSession().getTransaction().commit();
        }
        return str;
    }

    @Override
    public ResultMessage addNewCommodityID(String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        if(commodityDataService.read(path)==null){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.addSuccess;
        }else{
            return ResultMessage.exist;
        }
    }

    @Override
    public ResultMessage newCommodity(String userID,CommodityVO vo) throws RemoteException{
        String path=vo.getID();
        String[] a=path.split("/");
        String father_id=a[0];
        for(int i=1;i<a.length-1;i++){
            father_id=father_id+"/"+a[i];
        }
        if(addNewCommodityID(vo.getID()).getIndex()==1){//id不存在
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityPO po=new CommodityPO(vo.getID(),vo.getName(),vo.getSize(),vo.getAmountOfInventory(),vo.getBuyingPrice(),vo.getMarketPrice(),vo.getTheRecentBuyingPrice(),vo.getTheRecentMarketPrice(),vo.getWarningNumber(),father_id,vo.getIsBan(),true,true,false);
            commodityDataService.write(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            System.out.println(ResultMessage.addSuccess.getExpression());
            logBusinessLogicControllerAccess.addLog(userID,"Add Commodity "+vo.getID());
            return ResultMessage.addSuccess;
        }else{
            return ResultMessage.exist;
        }
    }

    @Override
    public ResultMessage addNewCommodityTypeID(String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        if(commodityTypeDataService.read(path)==null){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.addSuccess;
        }else{
            return ResultMessage.exist;
        }
    }

    @Override
    public ResultMessage newCommodityType(String userID,CommodityTypeVO vo) throws RemoteException{
        try{
            String path=vo.getID();
            String[] a=path.split("/");
            String father_id=a[0];
            for(int i=1;i<a.length-1;i++){
                father_id=father_id+"/"+a[i];
            }
            if(addNewCommodityTypeID(path).getIndex()==1){
//                System.out.println("22222222222222222222222222222");
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po=new CommodityTypePO(vo.getID(),vo.getName(),father_id,vo.getIsBan());
                commodityTypeDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                System.out.println(ResultMessage.addSuccess.getExpression());
                logBusinessLogicControllerAccess.addLog(userID,"Add CommodityType "+vo.getID());
//                System.out.println("Add Success!!!!!!!!!!!!!!!!!!!!!!!");
                return ResultMessage.addSuccess;
            }else{
                return ResultMessage.exist;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage addNewWarehouseID(String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        if(warehouseDataService.read(path)==null){
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.addSuccess;
        }else{
            return ResultMessage.exist;
        }
    }

        @Override
    public ResultMessage newWarehouse(String userID,WarehouseVO vo) throws RemoteException{
        try{
            if(addNewWarehouseID(vo.getID()).getIndex()==1){
                HibernateUtil.getCurrentSession().beginTransaction();
                WarehousePO po=new WarehousePO(vo.getID(),vo.getName(),vo.getIsBan());
                warehouseDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Add Warehouse "+vo.getID());
                return ResultMessage.addSuccess;
            }else{
                return ResultMessage.exist;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage updateCommodity(String userID,String oldID,CommodityVO vo) throws RemoteException{
  //      try{
            if(oldID.equals(vo.getID())){//没有改商品id
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityPO oldPO=commodityDataService.read(oldID);
                oldPO.setName(vo.getName());
                oldPO.setSize(vo.getSize());
                oldPO.setAmountOfInventory(vo.getAmountOfInventory());
                oldPO.setBuyingPrice(vo.getBuyingPrice());
                oldPO.setMarketPrice(vo.getMarketPrice());
                oldPO.setWarningNumber(vo.getWarningNumber());
                commodityDataService.update(oldPO);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Update Commodity "+oldID+" to "+vo.showCommodity());
                return ResultMessage.modiSuccess;
            }else{//修改了商品id
                if(addNewCommodityID(vo.getID()).getIndex()==1){
                    HibernateUtil.getCurrentSession().beginTransaction();
                    String[] a=oldID.split("/");
                    String father_id=a[0];
                    for(int i=1;i<a.length-1;i++){
                        father_id=father_id+"/"+a[i];
                    }
                    CommodityPO oldPO=commodityDataService.read(oldID);
                    CommodityPO po=new CommodityPO(vo.getID(),vo.getName(),vo.getSize(),oldPO.getAmountOfInventory(),vo.getBuyingPrice(),vo.getMarketPrice(),oldPO.getTheRecentBuyingPrice(),oldPO.getTheRecentMarketPrice(),vo.getWarningNumber(),father_id,vo.getIsBan(),oldPO.getIsVisible(),oldPO.getCanDel(),oldPO.getIsOnService());
                    commodityDataService.remove(oldID);
                    commodityDataService.write(po);
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    logBusinessLogicControllerAccess.addLog(userID,"Update Commodity "+oldID+" to "+vo.showCommodity());
                    return ResultMessage.modiSuccess;
                }else{
                    System.out.println(ResultMessage.exist.getExpression());
                    return ResultMessage.exist;
                }
            }
//        }catch(Exception e){
//            System.out.println(ResultMessage.Fail.getExpression());
//            return ResultMessage.Fail;
//        }
    }

    @Override
    public ResultMessage updateCommodityType(String userID,String oldID,CommodityTypeVO vo) throws RemoteException{
//        try{
            if(oldID.equals(vo.getID())){//没有修改商品分类ID，即只修改了商品分类名称
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po=commodityTypeDataService.read(oldID);
                po.setName(vo.getName());
                commodityTypeDataService.remove(vo.getID());
                commodityTypeDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Update CommodityType "+oldID+" to "+vo.showCommodityType());
                return ResultMessage.modiSuccess;
            }else {//修改了商品分类id
                String father_id;
                if (addNewCommodityTypeID(vo.getID()).getIndex() == 1) {
                    if (hasChildren(oldID)) {//有子分类
                        HibernateUtil.getCurrentSession().beginTransaction();
                        ArrayList<String> sonStr = getChildren(oldID);
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityTypePO po = commodityTypeDataService.read(oldID);
                        po.setID(vo.getID());
                        po.setName(vo.getName());
                        String[] a = vo.getID().split("/");
                        father_id = a[0];
                        for (int i = 1; i < a.length - 1; i++) {
                            father_id = father_id + "/" + a[i];
                        }
                        po.setFather_id(father_id);
                        commodityTypeDataService.remove(oldID);
                        commodityTypeDataService.write(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID, "Update CommodityType " + oldID + " to " + vo.getID());
                        if (isCommodity(sonStr.get(0))) {//该商品分类下是商品
                            for (int k = 0; k < sonStr.size(); k++) {
                                HibernateUtil.getCurrentSession().beginTransaction();
                                CommodityPO po1 = commodityDataService.read(sonStr.get(k));
                                father_id = vo.getID();
                                String newID[] = po1.getID().split("/");
                                po1.setID(father_id + "/" + newID[newID.length - 1]);
                                po1.setFather_id(father_id);
                                commodityDataService.remove(sonStr.get(k));
                                commodityDataService.write(po1);
//                            updateCommodity(userID,sonStr.get(k),commodityTransVOPO.transPoToVo(po1));
                                HibernateUtil.getCurrentSession().getTransaction().commit();
                                logBusinessLogicControllerAccess.addLog(userID, "Update Commodity " + sonStr.get(k) + " to " + po1.getID());
                            }
                        } else {//该商品分类下是商品分类
                            for (int k = 0; k < sonStr.size(); k++) {
                                HibernateUtil.getCurrentSession().beginTransaction();
                                CommodityTypePO p = commodityTypeDataService.read(sonStr.get(k));
                                father_id = vo.getID();
                                String newID[] = p.getID().split("/");
                                CommodityTypeVO v = new CommodityTypeVO(father_id + "/" + newID[newID.length - 1], p.getName(), p.getIsBan());
                                updateCommodityType(userID, sonStr.get(k), v);
                            }
                        }
                        return ResultMessage.modiSuccess;
                    } else {
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityTypePO po = commodityTypeDataService.read(oldID);
                        String[] a = vo.getID().split("/");
                        father_id = a[0];
                        for (int i = 1; i < a.length - 1; i++) {
                            father_id = father_id + "/" + a[i];
                        }
                        po.setID(vo.getID());
                        po.setName(vo.getName());
                        po.setFather_id(father_id);
                        commodityTypeDataService.remove(oldID);
                        commodityTypeDataService.write(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID, "Update CommodityType " + oldID + " to " + vo.showCommodityType());
                        return ResultMessage.modiSuccess;
                    }
                }else{
                    return ResultMessage.exist;
                }
            }
        }//catch(Exception e){
//            return ResultMessage.Fail;
//        }
//    }

    @Override
    public ResultMessage updateWarehouse(String userID,String oldID,WarehouseVO vo) throws RemoteException{
        try{
            if(oldID.equals(vo.getID())){//没有修改仓库ID，即只修改了仓库名称
                HibernateUtil.getCurrentSession().beginTransaction();
                WarehousePO po=warehouseDataService.read(oldID);
                po.setName(vo.getName());
                warehouseDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Update Warehouse "+oldID+" to "+vo.showWarehouse());
                return ResultMessage.modiSuccess;
            }else {//修改了仓库id
                if (addNewCommodityTypeID(vo.getID()).getIndex() == 1) {
                    if(hasChildren(oldID)){//有子分类
                        HibernateUtil.getCurrentSession().beginTransaction();
                        ArrayList<String> sonStr=getChildren(oldID);
                        HibernateUtil.getCurrentSession().beginTransaction();
//                    for(int i=0;i<sonStr.size();i++){
//                        System.out.println(sonStr.get(i)+" AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                    }
                        WarehousePO po=warehouseDataService.read(oldID);
                        po.setID(vo.getID());
                        po.setName(vo.getName());
                        warehouseDataService.remove(oldID);
                        warehouseDataService.write(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Update Warehouse "+oldID+" to "+vo.getID());
                        if(isCommodity(sonStr.get(0))){//该商品分类下是商品
                            for(int k=0;k<sonStr.size();k++){
                                HibernateUtil.getCurrentSession().beginTransaction();
                                CommodityPO po1=commodityDataService.read(sonStr.get(k));
                                String father_id=vo.getID();
                                String newID[]=po1.getID().split("/");
                                po1.setID(father_id+"/"+newID[newID.length-1]);
                                po1.setFather_id(father_id);
                                commodityDataService.remove(sonStr.get(k));
                                commodityDataService.write(po1);
//                            updateCommodity(userID,sonStr.get(k),commodityTransVOPO.transPoToVo(po1));
                                HibernateUtil.getCurrentSession().getTransaction().commit();
                                logBusinessLogicControllerAccess.addLog(userID,"Update Commodity "+sonStr.get(k)+" to "+po1.getID());
                            }
                        }else{//该仓库下是商品分类
                            for(int k=0;k<sonStr.size();k++){
                                HibernateUtil.getCurrentSession().beginTransaction();
                                CommodityTypePO p=commodityTypeDataService.read(sonStr.get(k));
                                String father_id=vo.getID();
                                String newID[]=p.getID().split("/");
                                CommodityTypeVO v=new CommodityTypeVO(father_id+"/"+newID[newID.length-1],p.getName(),p.getIsBan());
                                updateCommodityType(userID,sonStr.get(k),v);
                            }
                        }
                        return ResultMessage.modiSuccess;
                    }else{
                        HibernateUtil.getCurrentSession().beginTransaction();
                        WarehousePO po=warehouseDataService.read(oldID);
                        String[] a=vo.getID().split("/");
                        po.setID(vo.getID());
                        po.setName(vo.getName());
                        warehouseDataService.remove(oldID);
                        warehouseDataService.write(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Update Warehouse "+oldID+" to "+vo.showWarehouse());
                        return ResultMessage.modiSuccess;
                    }
                } else {
                    return ResultMessage.exist;
                }
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage modifyCommodityLocation(String userID,String oldID,String path) throws RemoteException{
        HibernateUtil.getCurrentSession().beginTransaction();
        CommodityPO isExist=commodityDataService.read(path);
        if(isExist==null){
            CommodityPO p=commodityDataService.read(oldID);
            String[] a=path.split("/");
            String father_id=a[0];
            for(int i=1;i<a.length-1;i++){
                father_id=father_id+"/"+a[i];
            }
            p.setID(path);
            p.setFather_id(father_id);
            commodityDataService.remove(oldID);
            commodityDataService.write(p);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"Update Commodity location from "+oldID+" to "+path);
            return ResultMessage.modiSuccess;
        }else{
            return ResultMessage.exist;
        }
    }

    @Override
    public ResultMessage modifyCommodityTypeLocation(String userID,String oldID,String path) throws RemoteException{
        String father_id;
        if (addNewCommodityTypeID(path).getIndex() == 1) {
            if (hasChildren(oldID)) {//有子分类
                ArrayList<String> sonStr = getChildren(oldID);
                for(int i=0;i<sonStr.size();i++){
                    System.out.println(sonStr.get(i)+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                }
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po = commodityTypeDataService.read(oldID);
                po.setID(path);
                String[] a = path.split("/");
                father_id = a[0];
                for (int i = 1; i < a.length - 1; i++) {
                    father_id = father_id + "/" + a[i];
                }
                po.setFather_id(father_id);
                commodityTypeDataService.remove(oldID);
                commodityTypeDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID, "Update CommodityType " + oldID + " to " + path);
                if (isCommodity(sonStr.get(0))) {//该商品分类下是商品
                    for (int k = 0; k < sonStr.size(); k++) {
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityPO po1 = commodityDataService.read(sonStr.get(k));
                        father_id = path;
                        String newID[] = po1.getID().split("/");
                        po1.setID(father_id + "/" + newID[newID.length - 1]);
                        po1.setFather_id(father_id);
                        commodityDataService.remove(sonStr.get(k));
                        commodityDataService.write(po1);
//                            updateCommodity(userID,sonStr.get(k),commodityTransVOPO.transPoToVo(po1));
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID, "Update Commodity " + sonStr.get(k) + " to " + po1.getID());
                    }
                } else {//该商品分类下是商品分类
                    for (int k = 0; k < sonStr.size(); k++) {
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityTypePO p = commodityTypeDataService.read(sonStr.get(k));
                        father_id = path;
                        String newID[] = p.getID().split("/");
                        CommodityTypeVO v = new CommodityTypeVO(father_id + "/" + newID[newID.length - 1], p.getName(), p.getIsBan());
                        updateCommodityType(userID, sonStr.get(k), v);
                    }
                }
                return ResultMessage.modiSuccess;
            } else {
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po = commodityTypeDataService.read(oldID);
                String[] a = path.split("/");
                father_id = a[0];
                for (int i = 1; i < a.length - 1; i++) {
                    father_id = father_id + "/" + a[i];
                }
                po.setID(path);
                po.setFather_id(father_id);
                commodityTypeDataService.remove(oldID);
                commodityTypeDataService.write(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID, "Update CommodityType " + oldID + " to " + path);
                return ResultMessage.modiSuccess;
            }
        }else{
            return ResultMessage.exist;
        }
    }

    @Override
    public ResultMessage deleteCommodity(String userID,String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityPO po=commodityDataService.read(path);
            if(po.getIsOnService()){
                System.out.println(ResultMessage.onDeal.getExpression());
                return ResultMessage.onDeal;
            }else{
                if(po.getCanDel()){
                    commodityDataService.remove(path);
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    logBusinessLogicControllerAccess.addLog(userID,"Delete Commodity "+path);
//                    System.out.println("彻底删除成功");
                    return ResultMessage.delSuccess;
                }else{
                    po.setIsVisible(false);
                    commodityDataService.update(po);
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    logBusinessLogicControllerAccess.addLog(userID,"Delete Commodity"+path);
//                    System.out.println(po.showCommodityPO());
//                    System.out.println("删除成功");
                    return ResultMessage.delSuccess;
                }
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage deleteCommodityType(String userID,String path) throws RemoteException{
        try{
            boolean isOnService = false;
            if(hasChildren(path)){//该商品分类下有子树
                HibernateUtil.getCurrentSession().beginTransaction();
                ArrayList<String> sonStr=getChildren(path);
                for(int i=0;i<sonStr.size();i++){
                    if(!canDeleteCommodityType(userID,sonStr.get(i))){
                        isOnService=true;
                        break;
                    }
                    if(isOnService){
                        System.out.println(ResultMessage.onDeal.getExpression());
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        return ResultMessage.onDeal;
                    }else{
                        HibernateUtil.getCurrentSession().beginTransaction();
                        commodityTypeDataService.remove(path);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Delete CommodityType "+path);
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityTypePO p=commodityTypeDataService.read(sonStr.get(i));
                        if(p==null){//该商品分类下是商品
                            for(int k=0;k<sonStr.size();k++){
                                deleteCommodity(userID,sonStr.get(k));
                            }
                        }else{//该商品分类下是商品分类
                            for(int k=0;k<sonStr.size();k++){
                                deleteCommodityType(userID,sonStr.get(k));
                            }
                        }
                        return ResultMessage.delSuccess;
                    }
                }
            }else{//该商品分类下没有子树
                HibernateUtil.getCurrentSession().beginTransaction();
                commodityTypeDataService.remove(path);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Delete CommodityType "+path);
                return ResultMessage.delSuccess;
            }
        }catch(RemoteException e){
            return ResultMessage.Fail;
        }
        return null;
    }

    @Override
    public ResultMessage deleteWarehouse(String userID,String path)throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            boolean isOnService=false;
            if(hasChildren(path)){
                HibernateUtil.getCurrentSession().beginTransaction();
                ArrayList<String> sonStr=getChildren(path);
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO p=commodityTypeDataService.read(sonStr.get(0));
                if(p==null){//该仓库下是商品
                    for(int i=0;i<sonStr.size();i++){
                        CommodityPO temp=commodityDataService.read(sonStr.get(i));
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        if(temp.getIsOnService()){//该商品处于业务逻辑中
                            isOnService=true;
                            break;
                        }
                    }
                    if(isOnService){
                        System.out.println(ResultMessage.onDeal.getExpression());
                        return ResultMessage.onDeal;
                    }else{
                        HibernateUtil.getCurrentSession().beginTransaction();
                        warehouseDataService.remove(path);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Delete Warehouse "+path);
                        for(int i=0;i<sonStr.size();i++){
                            deleteCommodity(userID,sonStr.get(i));
                        }
                        System.out.println(ResultMessage.delSuccess.getExpression());
                        return ResultMessage.delSuccess;
                    }
                }else{//该仓库下是商品分类
                    boolean res=true;
                    for(int k=0;k<sonStr.size();k++){
                        if(!canDeleteCommodityType(userID,sonStr.get(k))){
                            res=false;
                            break;
                        }
                    }
                    if(res){
                        HibernateUtil.getCurrentSession().beginTransaction();
                        warehouseDataService.remove(path);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Delete Warehouse"+path);
                        for(int k=0;k<sonStr.size();k++){
                            deleteCommodityType(userID,sonStr.get(k));
                        }
                        System.out.println(ResultMessage.delSuccess.getExpression());
                        return ResultMessage.delSuccess;
                    }else{
                        System.out.println(ResultMessage.onDeal.getExpression());
                        return ResultMessage.onDeal;
                    }
                }
            }else{//商品下没有子分类
                HibernateUtil.getCurrentSession().beginTransaction();
                warehouseDataService.remove(path);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Delete Warehouse "+path);
                return ResultMessage.delSuccess;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage banCommodity(String userID,String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityPO po=commodityDataService.read(path);
            if(po.getIsOnService()){
                System.out.println(ResultMessage.onDeal.getExpression());
                return ResultMessage.onDeal;
            }else{
                po.setIsBan(true);
                commodityDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Ban Commodity "+path);
                return ResultMessage.banSuccess;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage banCommodityType(String userID,String path) throws RemoteException{
        try {
            boolean isOnService = false;
            if (hasChildren(path)) {//该商品分类下有子树
                ArrayList<String> sonStr = getChildren(path);//得到子树
                HibernateUtil.getCurrentSession().beginTransaction();
                for (int i = 0; i < sonStr.size(); i++) {
                    if (!canDeleteCommodityType(userID, sonStr.get(i))) {
                        isOnService = true;
                        break;
                    }
                    if (isOnService) {
                        System.out.println(ResultMessage.onDeal.getExpression());
                        return ResultMessage.onDeal;
                    } else {
                        HibernateUtil.getCurrentSession().beginTransaction();
                        CommodityTypePO po=commodityTypeDataService.read(path);
                        po.setIsBan(true);
                        commodityTypeDataService.update(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID, "Ban CommodityType " + path);
                        HibernateUtil.getCurrentSession().beginTransaction();
                        if (commodityTypeDataService.read(sonStr.get(i)) == null) {//该商品分类下是商品
                            for (int k = 0; k < sonStr.size(); k++) {
                                banCommodity(userID, sonStr.get(k));
                            }
                        } else {//该商品分类下是商品分类
                            for (int k = 0; k < sonStr.size(); k++) {
                                banCommodityType(userID, sonStr.get(k));
                            }
                        }
                        return ResultMessage.banSuccess;
                    }
                }
            } else {//该商品分类下没有子树
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po=commodityTypeDataService.read(path);
                po.setIsBan(true);
                commodityTypeDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID, "Ban CommodityType " + path);
                return ResultMessage.banSuccess;
            }
        } catch (RemoteException e) {
            return ResultMessage.Fail;
        }
        return null;
    }

    @Override
    public ResultMessage banWarehouse(String userID,String path) throws RemoteException{
        try{
            boolean isOnService=false;
            if(hasChildren(path)){
                HibernateUtil.getCurrentSession().beginTransaction();
                ArrayList<String> sonStr=getChildren(path);
                HibernateUtil.getCurrentSession().beginTransaction();
                if(commodityTypeDataService.read(sonStr.get(0))==null){//该仓库下是商品
                    for(int i=0;i<sonStr.size();i++){
                        if(commodityDataService.read(sonStr.get(i)).getIsOnService()){//该商品处于业务逻辑中
                            isOnService=true;
                            break;
                        }
                    }
                    if(isOnService){
                        System.out.println(ResultMessage.onDeal.getExpression());
                        return ResultMessage.onDeal;
                    }else{
                        WarehousePO po=warehouseDataService.read(path);
                        po.setIsBan(true);
                        warehouseDataService.update(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Ban Warehouse "+path);
                        for(int i=0;i<sonStr.size();i++){
                            banCommodity(userID,sonStr.get(i));
                        }
                        System.out.println(ResultMessage.banSuccess.getExpression());
                        return ResultMessage.banSuccess;
                    }
                }else{//该仓库下是商品分类
                    boolean res=true;
                    for(int k=0;k<sonStr.size();k++){
                        if(!canDeleteCommodityType(userID,sonStr.get(k))){
                            res=false;
                            break;
                        }
                    }
                    HibernateUtil.getCurrentSession().beginTransaction();
                    if(res){
                        WarehousePO po=warehouseDataService.read(path);
                        po.setIsBan(true);
                        warehouseDataService.update(po);
                        HibernateUtil.getCurrentSession().getTransaction().commit();
                        logBusinessLogicControllerAccess.addLog(userID,"Ban Warehouse"+path);
                        for(int k=0;k<sonStr.size();k++){
                            banCommodityType(userID,sonStr.get(k));
                        }
                        System.out.println(ResultMessage.banSuccess.getExpression());
                        return ResultMessage.banSuccess;
                    }else{
                        System.out.println(ResultMessage.onDeal.getExpression());
                        return ResultMessage.onDeal;
                    }
                }
            }else{//商品下没有子分类
                HibernateUtil.getCurrentSession().beginTransaction();
                WarehousePO po=warehouseDataService.read(path);
                po.setIsBan(true);
                warehouseDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Ban Warehouse "+path);
                return ResultMessage.banSuccess;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage recoverCommodity(String userID,String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityPO po=commodityDataService.read(path);
            po.setIsBan(false);
            commodityDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            logBusinessLogicControllerAccess.addLog(userID,"Recover Commodity "+path);
            return ResultMessage.recoverSuccess;
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage recoverCommodityType(String userID,String path) throws RemoteException{
        try{
            if (hasChildren(path)) {//该商品分类下有子树
                ArrayList<String> sonStr = getChildren(path);//得到子树
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po=commodityTypeDataService.read(path);
                po.setIsBan(false);
                commodityTypeDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID, "Recover CommodityType " + path);
                HibernateUtil.getCurrentSession().beginTransaction();
                if (commodityTypeDataService.read(sonStr.get(0)) == null) {//该商品分类下是商品
                    for (int i = 0; i < sonStr.size(); i++) {
                        recoverCommodity(userID, sonStr.get(i));
                    }
                } else {//该商品分类下是商品分类
                    for (int i = 0; i < sonStr.size(); i++) {
                        recoverCommodityType(userID, sonStr.get(i));
                    }
                }
                return ResultMessage.recoverSuccess;
            } else {//该商品分类下没有子树
                HibernateUtil.getCurrentSession().beginTransaction();
                CommodityTypePO po=commodityTypeDataService.read(path);
                po.setIsBan(false);
                commodityTypeDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID, "Recover CommodityType " + path);
                return ResultMessage.recoverSuccess;
            }
        }catch(RemoteException e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public ResultMessage recoverWarehouse(String userID,String path) throws RemoteException{
        try{
            if(hasChildren(path)){
                ArrayList<String> sonStr=getChildren(path);
                HibernateUtil.getCurrentSession().beginTransaction();
                if(commodityTypeDataService.read(sonStr.get(0))==null){//该仓库下是商品
                    WarehousePO po=warehouseDataService.read(path);
                    po.setIsBan(false);
                    warehouseDataService.update(po);
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    logBusinessLogicControllerAccess.addLog(userID,"Recover Warehouse "+path);
                    for(int i=0;i<sonStr.size();i++){
                        recoverCommodity(userID,sonStr.get(i));
                    }
                    System.out.println(ResultMessage.recoverSuccess.getExpression());
                    return ResultMessage.recoverSuccess;
                }else{//该仓库下是商品分类
                    WarehousePO po=warehouseDataService.read(path);
                    po.setIsBan(false);
                    warehouseDataService.update(po);
                    HibernateUtil.getCurrentSession().getTransaction().commit();
                    logBusinessLogicControllerAccess.addLog(userID,"Recover Warehouse "+path);
                    for(int k=0;k<sonStr.size();k++){
                        recoverCommodityType(userID,sonStr.get(k));
                    }
                    System.out.println(ResultMessage.recoverSuccess.getExpression());
                    return ResultMessage.recoverSuccess;
                }
            }else{//仓库下没有子分类
                HibernateUtil.getCurrentSession().beginTransaction();
                WarehousePO po=warehouseDataService.read(path);
                po.setIsBan(false);
                warehouseDataService.update(po);
                HibernateUtil.getCurrentSession().getTransaction().commit();
                logBusinessLogicControllerAccess.addLog(userID,"Recover Warehouse "+path);
                return ResultMessage.recoverSuccess;
            }
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

    @Override
    public boolean isBanedCommodity(String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(commodityDataService.read(path).getIsBan()){
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return true;
            }else{
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isBanedCommodityType(String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(commodityTypeDataService.read(path).getIsBan()){
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return true;
            }else{
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isBanedWarehouse(String path) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            if(warehouseDataService.read(path).getIsBan()){
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return true;
            }else{
                HibernateUtil.getCurrentSession().getTransaction().commit();
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ArrayList<String>> findByKeyWords(String keyWords) throws RemoteException{
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            ArrayList<ArrayList<String>> a=new ArrayList<>();
            ArrayList<CommodityPO> pos=commodityDataService.findByKeywords(keyWords);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            ArrayList<String> banedCommoditys=new ArrayList<>();
            ArrayList<String> validCommoditys=new ArrayList<>();
            for(CommodityPO po:pos){
                if(po.getIsVisible()){
                    if(po.getIsBan()){//该商品被禁用
                        banedCommoditys.add(po.getID()+" "+po.getName());
                    }else{//该商品未被禁用
                        validCommoditys.add(po.getID()+" "+po.getName());
                    }
                }
            }
            a.add(validCommoditys);
            a.add(banedCommoditys);
//            for(int i=0;i<validCommoditys.size();i++){
//                System.out.println(validCommoditys.get(i));
//            }
            return a;
        }catch(Exception e){
            return null;
        }
    }


    public CommodityPO getCommodityHere(String path) {
        HibernateUtil.getCurrentSession().beginTransaction();
        CommodityPO commodityPO=commodityDataService.read(path);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return commodityPO;
    }

    private boolean canDeleteCommodityType(String userID,String path) throws RemoteException{
        boolean result=true;
        boolean isOnService=false;
        if(hasChildren(path)){
            HibernateUtil.getCurrentSession().beginTransaction();
            ArrayList<String> sonStr=getChildren(path);
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityTypePO po=commodityTypeDataService.read(sonStr.get(0));
            if(po==null){//该商品分类下是商品
                for(int i=0;i<sonStr.size();i++){
                    CommodityPO p=commodityDataService.read((sonStr.get(i)));
                    if(p.getIsOnService()){//该商品处于业务逻辑中
                        isOnService=true;
                        break;
                    }
                }
                HibernateUtil.getCurrentSession().getTransaction().commit();
                if(isOnService){
                    result=false;
                }else{
                    result=true;
                }
            }else{//该商品分类下是商品分类
                for(int i=0;i<sonStr.size();i++){
                    canDeleteCommodityType(userID,sonStr.get(i));
                }
            }
        }else{//该商品分类下没有子分类
            return true;
        }
        return result;
    }

    @Override
    public ArrayList<CommodityPO> list(){
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<CommodityPO> pos=commodityDataService.showList();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return pos;
    }

    @Override
    public ArrayList<CommodityVO> listCommodityVO() {
        ArrayList<CommodityVO> re=new ArrayList<>();
        for(CommodityPO cpo:this.list()){
            if((!cpo.getIsBan())&&(cpo.getIsVisible())){
                re.add(commodityTransVOPO.transPoToVo(cpo));
            }
        }
        return re;
    }

    @Override
    public ResultMessage modifyAmountOfInventory(String userID,String commodityID,int amount){
        try{
            HibernateUtil.getCurrentSession().beginTransaction();
            CommodityPO po=commodityDataService.read(commodityID);
            po.setAmountOfInventory(po.getAmountOfInventory()+amount);
            if(po.getAmountOfInventory()<po.getWarningNumber()){
                messageBusinessLogicControllerAccess.addMessage(userID,commodityID+"'s amount of inventory is less than warning Number!",userBusinessLogicControllerAccess.getWarehousemanList(),true);
            }
            HibernateUtil.getCurrentSession().beginTransaction();
            commodityDataService.update(po);
            HibernateUtil.getCurrentSession().getTransaction().commit();
            return ResultMessage.modiSuccess;
        }catch(Exception e){
            return ResultMessage.Fail;
        }
    }

}
