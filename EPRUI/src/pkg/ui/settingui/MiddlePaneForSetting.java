package pkg.ui.settingui;

import pkg.unclassified.MiddlePane;

public class MiddlePaneForSetting extends MiddlePane {
    private SettingAccordion settingAccordion;
    public static MiddlePaneForSetting middlePaneForSetting;

    private MiddlePaneForSetting(){
        super(true);
        settingAccordion =SettingAccordion.getInstance();
        setMiddleBelowPane(settingAccordion);
    }
    public static MiddlePaneForSetting getInstance(){
        if (middlePaneForSetting ==null){
            middlePaneForSetting =new MiddlePaneForSetting();

        }
        return middlePaneForSetting;

    }

}
