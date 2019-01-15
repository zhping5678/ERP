package pkg.unclassified;

import pkg.ui.commodityui.CommodityButton;
import pkg.ui.settingui.SettingButton;

public class LeftPaneForWarehouseWorker extends LeftPane {
    public LeftPaneForWarehouseWorker(){
        super();
        tabButtonArrayList.add(new CommodityButton());
        tabButtonArrayList.add(new SettingButton());
        this.getChildren().addAll(tabButtonArrayList);
    }
}
