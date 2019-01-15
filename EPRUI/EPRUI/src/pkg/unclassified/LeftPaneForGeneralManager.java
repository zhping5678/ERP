package pkg.unclassified;

import pkg.ui.accountui.AccountButton;
import pkg.ui.clientui.ClientButton;
import pkg.ui.commodityui.CommodityButton;
import pkg.ui.fileui.FileButton;
import pkg.ui.settingui.SettingButton;

public class LeftPaneForGeneralManager extends LeftPane {

    private LeftPaneForGeneralManager(){
        super();
        tabButtonArrayList.add(new ClientButton());
        tabButtonArrayList.add(new CommodityButton());
        tabButtonArrayList.add(new AccountButton());
        tabButtonArrayList.add(new FileButton());
        tabButtonArrayList.add(new SettingButton());
        this.getChildren().addAll(tabButtonArrayList);
    }

    public static LeftPane getInstance(){
        if (leftPane==null){
            leftPane=new LeftPaneForGeneralManager();
        }
        return leftPane;
    }



}
