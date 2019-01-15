package pkg.ui.settingui;


import vo.uservo.Position;

public interface SettingUIControllerAccess {
    public static SettingUIControllerAccess settingUIControllerAccess = SettingUIController.getInstance();
    public void setCurrentUser(String currentUser);
    public String getCurrentUser();
    public void setCurrentPosition(Position currentPosition);
    public Position getCurrentPosition();
    public boolean getCurrentAuthority();
    public void setCurrentAuthority(boolean currentAuthority);
    public void loadProfile(String currentUser);
}
