package pkg.ui.utilityui;


import pkg.unclassified.TabContentPane;
import pkg.unclassified.Viewer;
import vo.filevo.FileType;

public interface UtilityUIControllerAccess {
    public static UtilityUIControllerAccess utilityUIControllerAccess = UtilityUIController.getInstance();
    public void switchToTabContentPane(TabContentPane tabContentPane);
    public void login(String username);
    public void switchToAccountBookViewer();
    public void switchToUserViewer();
    public void switchToLogViewer();
    public void switchToBusinessHistoryViewer();
    public void switchToBusinessConditionsViewer();
    public void switchToSalesDetailsViewer();
    public FileType fromIdToType(String fileid);
    public Viewer switchToFileViewer(String fileid);
    public void switchToAccountViewer(String id);

}
