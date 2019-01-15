package pkg.ui.myfileui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import pkg.unclassified.TitledWithList;


public class ArchiveTitledWithList extends TitledWithList {
    private static ArchiveTitledWithList archiveTitledWithList;
    public Label icon=new Label("");

    private ArchiveTitledWithList(){

        super("ARCHIVE");
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
        loadData();

        this.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                loadData();
            }
        });



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

    public static ArchiveTitledWithList getInstance(){
        if (archiveTitledWithList==null){
            archiveTitledWithList=new ArchiveTitledWithList();
        }
        return archiveTitledWithList;
    }
    public void loadData(){
        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getArchiveList());
    }
}

