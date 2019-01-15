package pkg.ui.settingui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.LeftPane;
import pkg.unclassified.TabButton;

public class SettingButton extends TabButton{
    public SettingButton(){
        super("Setting");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (available&&!selected){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToTabContentPane(SettingTabContentPane.getInstance());
                    LeftPane.getInstance().setCurrentTabButton((TabButton)event.getSource());
                }
            }
        });
    }
}
