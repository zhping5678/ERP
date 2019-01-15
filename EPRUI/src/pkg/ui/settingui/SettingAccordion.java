package pkg.ui.settingui;

import pkg.unclassified.BetterAccordion;

public class SettingAccordion extends BetterAccordion{
    private static SettingAccordion fileAccordion;

    public ProfileTitledWithList profileTitledWithList =ProfileTitledWithList.getInstance();
    public LogTitledWithList logTitledWithList =LogTitledWithList.getInstance();
    public AccountBookTitledWithList accountBookTitledWithList =AccountBookTitledWithList.getInstance();




    private SettingAccordion(){
        this.getPanes().addAll(profileTitledWithList, logTitledWithList, accountBookTitledWithList);
        this.setExpandedPane(accountBookTitledWithList);





    }
    public static SettingAccordion getInstance(){
        if (fileAccordion ==null){
            fileAccordion =new SettingAccordion();
        }
        return fileAccordion;
    }
}
