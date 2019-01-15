package MockObject;

import Start.RemoteHelper;
import vo.logvo.LogVO;
import businesslogicservice.ServiceFactory;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Logbl {
    public ArrayList<LogVO> LogView() {
        ArrayList<LogVO> array = null;
        try {
            array = RemoteHelper.getInstance().getServiceFactory().getLogBusinessLogicService().LogView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }
    public ArrayList<LogVO> LogSearch(String keywords){
        ArrayList<LogVO> array=null;
        try {
            array=RemoteHelper.getInstance().getServiceFactory().getLogBusinessLogicService().LogSearch(keywords);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return array;
    }
}
