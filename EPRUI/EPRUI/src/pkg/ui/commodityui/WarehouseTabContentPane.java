package pkg.ui.commodityui;

import javafx.scene.layout.VBox;
import pkg.unclassified.*;

public class WarehouseTabContentPane extends TabContentPane {
    public MiddlePaneForWarehouse middlePaneForWarehouse =MiddlePaneForWarehouse.getInstance();
    public VBox rightPane;
    private static WarehouseTabContentPane warehouseTabContentPane;
    public Viewer viewer;
    private WarehouseTabContentPane(){
        this.getChildren().add(middlePaneForWarehouse);
        rightPane=new VBox();
        viewer= CommodityViewer.getInstance();

        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static WarehouseTabContentPane getInstance(){
        if(warehouseTabContentPane ==null){
            warehouseTabContentPane =new WarehouseTabContentPane();

        }
        return warehouseTabContentPane;

    }
}
