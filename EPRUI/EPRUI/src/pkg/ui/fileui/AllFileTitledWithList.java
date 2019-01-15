package pkg.ui.fileui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import pkg.ui.myfileui.MyFileUIControllerAccess;
import pkg.unclassified.TitledWithList;


public class AllFileTitledWithList extends TitledWithList {
    private static AllFileTitledWithList allFileTitledWithList;
    public Label icon=new Label("");

    private AllFileTitledWithList(){

        super("ALL FILES");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/ArchiveIconPurple.png);"
                +"-fx-background-size:17;"
                +"-fx-pref-width: 17;"
                +"-fx-pref-height: 17;"
                +"-fx-background-repeat: no-repeat;"
        );

        //  Rectangle rectangle=new Rectangle(10,10);
        //  this.setGraphic(rectangle);

        this.setGraphic(icon);

        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());

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
                +"-fx-background-image:url(/pkg/image/ArchiveIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/ArchiveIconBlack.png);"
        );
    }

    public static AllFileTitledWithList getInstance(){
        if (allFileTitledWithList ==null){
            allFileTitledWithList =new AllFileTitledWithList();
        }
        return allFileTitledWithList;
    }
}
