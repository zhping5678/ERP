package pkg.ui.commodityui;

import javafx.scene.control.TreeItem;
import pkg.unclassified.ItemText;
import pkg.unclassified.MiddlePane;
import pkg.unclassified.Treed;

public class MiddlePaneForWarehouse extends MiddlePane {
    public Treed treed;
    public TreeItem<ItemText> rootTreeItem;
    private static MiddlePaneForWarehouse middlePaneForWarehouse;

    private MiddlePaneForWarehouse(){
        super(true);
        /*for test*/
        rootTreeItem =new TreeItem<ItemText>(new ItemText("id","sdfsadf","sdfsf"));
        TreeItem<ItemText> njItem=new TreeItem<>(new ItemText("sdfs","sdfsf","sfd"));
        TreeItem<ItemText> szItem=new TreeItem<>(new ItemText("sdfs","sdfsf","sfd"));
        TreeItem<ItemText> sadfItem=new TreeItem<>(new ItemText("sdafsddf","sdfsdfs","sdfsdf"));
        njItem.getChildren().add(sadfItem);


        rootTreeItem.getChildren().addAll(njItem,szItem);
        treed =new Treed(rootTreeItem);
        treed.setShowRoot(false);
        setMiddleBelowPane(treed);
    }
    public static MiddlePaneForWarehouse getInstance(){
        if (middlePaneForWarehouse ==null){
            middlePaneForWarehouse =new MiddlePaneForWarehouse();
        }
        return middlePaneForWarehouse;

    }

}
