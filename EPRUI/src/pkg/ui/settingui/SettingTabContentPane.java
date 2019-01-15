package pkg.ui.settingui;

import javafx.scene.layout.VBox;
import pkg.unclassified.*;


public class SettingTabContentPane extends TabContentPane {
    public MiddlePaneForSetting middlePaneForSetting= MiddlePaneForSetting.getInstance();
    public VBox rightPane;
    private static SettingTabContentPane settingTabContentPane;
    public Viewer viewer;
    private SettingTabContentPane(){
        this.getChildren().add(middlePaneForSetting);
        rightPane=new VBox();
        viewer= AccountBookViewer.getInstance();

        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static SettingTabContentPane getInstance(){
        if(settingTabContentPane ==null){
            settingTabContentPane =new SettingTabContentPane();
        }
        return settingTabContentPane;

    }


}
