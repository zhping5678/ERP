package pkg.ui.settingui;

import pkg.ui.RemoteHelper;
import vo.uservo.Position;
import vo.uservo.UserVO;

import java.rmi.RemoteException;

class SettingUIController implements SettingUIControllerAccess{
    private static SettingUIController settingUIController;
    public String currentUser;
    private Position currentPosition;
    private boolean currentAuthority;
    private UserVO userVO;




    private SettingUIController(){



    }

    static SettingUIController getInstance(){
        if (settingUIController==null){
            settingUIController=new SettingUIController();
        }
        return settingUIController;
    }
    @Override
    public void setCurrentUser(String currentUser){

        this.currentUser=currentUser;

    }
    @Override
    public String getCurrentUser(){
        System.out.println("get current user:"+currentUser);




        return currentUser;


    }
    @Override
    public void setCurrentPosition(Position currentPosition){
        this.currentPosition=currentPosition;




    }
    @Override
    public Position getCurrentPosition(){

        return currentPosition;

    }

    @Override
    public boolean getCurrentAuthority() {
        return currentAuthority;
    }
    @Override
    public void setCurrentAuthority(boolean currentAuthority){
        this.currentAuthority=currentAuthority;

    }

    @Override
    public void loadProfile(String currentUser) {
        try{
            userVO=RemoteHelper.getUserBusinessLogicService().trace(currentUser);
        }catch (RemoteException e){
        }
        //System.out.println(userVO.toString());
        //System.out.println("uservo.getid:"+userVO.getID());
        this.currentUser=userVO.getID();
        System.out.println("currentuser: "+this.currentUser);
        this.currentAuthority=userVO.getRight();
        this.currentPosition=userVO.getPosition();
        System.out.println("loaded profile");

    }
}
