package businesslogic.logbusinesslogic;

import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import data.DataFactoryImpl;
import dataservice.logdataservice.LogDataService;
import po.logpo.LogPO;
import util.HibernateUtil;
import vo.logvo.LogVO;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LogBusinessLogicController extends UnicastRemoteObject implements LogBusinessLogicControllerAccess,LogBusinessLogicService {


    private LogDataService logDataService;
    private LogTransVoPo trans;

    public LogBusinessLogicController()throws RemoteException{
        logDataService= DataFactoryImpl.getInstance().getLogDataService();
        trans=new LogTransVoPo();

    }

    @Override
    public void connectionTest() throws RemoteException {
        System.out.print("LogBusinessLogicService is connected.\n");
    }


    @Override
    public ArrayList<LogVO> LogSearch(String keywords) throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<LogPO> pos=logDataService.Search(keywords);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.transPOsToVOs(pos);

    }

    @Override
    public ArrayList<LogVO> LogView() throws RemoteException {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<LogPO> pos=logDataService.list();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return trans.transPOsToVOs(pos);
    }

    @Override
    public void addLog(String userID, String event) {
        HibernateUtil.getCurrentSession().beginTransaction();
        //根据系统时间自动生成
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        LogPO po=new LogPO(time,userID,event);
        logDataService.write(po);
        HibernateUtil.getCurrentSession().getTransaction().commit();
    }

}

