package MockObject;

import Start.RemoteHelper;
import vo.clientvo.ClientIdentity;
import vo.clientvo.ClientVO;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Clientbl {
    public ArrayList<String> showSellerList(){
        ArrayList<String> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().showSellerList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    public ArrayList<String> showPayList(){
        ArrayList<String> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().showSupplierList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    //参数为当前登陆用户的ID和要增加的客户信息
    public ResultMessage addClient(String userID, ClientVO vo){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().addClient(userID,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    //从界面删除该客户，但是后端根据客户的具体情况决定彻底从
    public ResultMessage deleteClient(String userID,String ID){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().deleteClient(userID,ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    //还原已经被禁用的客户，参数为当前登录用户的ID和要还原的客户ID
    public ResultMessage recover(String userID,String ID){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().recover(userID,ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    //禁用客户，在界面显示为灰色，参数为当前登录用户的ID和要禁用的客户的ID
    public ResultMessage ban(String userID,String ID){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().ban(userID,ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    //根据客户ID返回特定的单一客户对象
    public ClientVO traceClient(String ID){
        ClientVO vo=null;
        try {
            vo=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().traceClient(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    //根据关键字返回满足关键字的多个客户对象
    public ArrayList<String> findClient(String keywords){
        ArrayList<String> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().findClient(keywords);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }

    //修改客户信息，参数为当前登录用户的ID和要修改的客户的原本ID和要修改的信息
    public ResultMessage modifyClient(String ID,String oldId,ClientVO vo){
        ResultMessage message=null;
        try {
            System.out.println(ID);
            System.out.println(oldId);
            System.out.println(vo.getName());
            message=RemoteHelper.getInstance().getServiceFactory().getClientBusinessLogicService().modifyClient(ID,oldId,vo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }
}
