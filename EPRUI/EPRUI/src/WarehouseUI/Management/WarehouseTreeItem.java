package WarehouseUI.Management;

import MockObject.Warehousebl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

public class WarehouseTreeItem<String> extends TreeItem<String> {
    private boolean isLeaf;
    private boolean isFirstTimeChildren=true;
    private boolean isFirstTimeLeaf=true;
    private boolean isBanned;
    private String name;
    Warehousebl ware=new Warehousebl();
    java.lang.String path;

    public WarehouseTreeItem(String str){
        super(str);
        name=str;
    }

    @Override public boolean isLeaf(){
        if(isFirstTimeLeaf){
            isFirstTimeLeaf=false;
            String item=getValue();

            //建立path
            java.lang.String path=this.getValue().toString();
            TreeItem<String> temp=this;
            while(temp.getParent()!=null){
                path=temp.getParent().getValue().toString()+"/"+path;
                temp=temp.getParent();
            }
            this.path=path;
            isLeaf=!ware.hasChildren((java.lang.String) path);
            //下面这一行是之后加的，如果不对的话则删去
            isLeaf=ware.isCommodity(path);
        }
        return isLeaf;
    }

    @Override public ObservableList<TreeItem<String>> getChildren(){
        if(isFirstTimeChildren){
            isFirstTimeChildren=false;
            super.getChildren().setAll(buildChildren(this));
        }
        return super.getChildren();
    }

    public ObservableList<TreeItem<String>> buildChildren(TreeItem<String> TreeItem){
        String item=TreeItem.getValue();

        //建立path
        java.lang.String path=TreeItem.getValue().toString();
        TreeItem<String> temp=TreeItem;
        while(temp.getParent()!=null){
            path=temp.getParent().getValue().toString()+"/"+path;
            temp=temp.getParent();
        }
        boolean hasChildren=ware.hasChildren(path);
        if(item!=null&&hasChildren){
            ObservableList<TreeItem<String>> children= FXCollections.observableArrayList();

            java.lang.String[] C=  ware.getChildren((java.lang.String) path);
            if(C!=null){
                for(int i=0;i<C.length;i++){
                    java.lang.String[] s=  C[i].split("/");
                    WarehouseTreeItem<String> newitem=new WarehouseTreeItem<String>((String) s[s.length-1]);
                    children.add(newitem);
                }
                return children;
            }
        }
        return FXCollections.emptyObservableList();
    }

    public java.lang.String getPath(){
        return path;
    }

    public boolean isBanned(){
        String item=getValue();
        //建立path
        java.lang.String path=this.getValue().toString();
        TreeItem<String> temp=this;
        while(temp.getParent()!=null){
            path=temp.getParent().getValue().toString()+"/"+path;
            temp=temp.getParent();
        }
        Warehousebl bl=new Warehousebl();
        if(bl.isCommodity(path)){
            return bl.isBanedCommodity(path);
        }
        else if(path.contains("/")){
            return bl.isBanedCommodityType(path);
        }
        else{
            return bl.isBanedWarehouse(path);
        }
    }

    public String getName(){
        return name;
    }

}
