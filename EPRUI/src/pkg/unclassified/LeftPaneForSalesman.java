package pkg.unclassified;

import pkg.ui.clientui.ClientButton;
import pkg.ui.commodityui.CommodityButton;
import pkg.ui.settingui.SettingButton;

public class LeftPaneForSalesman extends LeftPane {
    public LeftPaneForSalesman(){
        super();
        tabButtonArrayList.add(new ClientButton());
        tabButtonArrayList.add(new CommodityButton());
        tabButtonArrayList.add(new SettingButton());
        this.getChildren().addAll(tabButtonArrayList);
    }

}

