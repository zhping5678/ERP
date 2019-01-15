package MockObject;

import Start.RemoteHelper;
import vo.uservo.Position;
import vo.uservo.UserVO;
import vo.utilityvo.ResultMessage;

import java.rmi.RemoteException;

public class Userbl {
    public ResultMessage login(String id, String password){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getUserBusinessLogicService().login(id,password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

    public UserVO trace(String id) {
        UserVO vo = null;
        try {
            vo = RemoteHelper.getInstance().getServiceFactory().getUserBusinessLogicService().trace(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return vo;
    }

    public ResultMessage reset(String oldID, String newID, String oldPass, String newPass,String name, String confirm){
        ResultMessage message=null;
        try {
            message=RemoteHelper.getInstance().getServiceFactory().getUserBusinessLogicService().reset(oldID,newID,oldPass,newPass,name,confirm);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return message;
    }

}
