package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import pkg.ui.myfileui.MyFileUIControllerAccess;


public class TrashTitledWithList extends TitledWithList{
    public static TrashTitledWithList trashTitledWithList;
    public Label icon=new Label("");

    public TrashTitledWithList(){
        super("TRASH");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/TrashIconPurple.png);"
                +"-fx-background-size:17;"
                +"-fx-pref-width: 17;"
                +"-fx-pref-height: 17;"
                +"-fx-background-repeat: no-repeat;"
        );

        //  Rectangle rectangle=new Rectangle(10,10);
        //  this.setGraphic(rectangle);
        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());

        this.setGraphic(icon);
        this.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue==true){
                    setExpandedIcon();
                }else{
                    setCollapsedIcon();
                }
            }
        });
    }



    public void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/TrashIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/TrashIconBlack.png);"
        );
    }
    public void loadData(){
        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());
    }
    public static TrashTitledWithList getInstance(){
        if (trashTitledWithList ==null){
            trashTitledWithList=new TrashTitledWithList();
        }
        return trashTitledWithList;
    }
}
