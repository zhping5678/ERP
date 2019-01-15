package pkg.ui.settingui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.TitledWithList;

public class LogTitledWithList extends TitledWithList {
    private static LogTitledWithList logTitledWithList;
    public Label icon=new Label("");

    private LogTitledWithList(){

        super("LOGS",false);
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/LogIconPurple.png);"
                +"-fx-background-size:17;"
                +"-fx-pref-width: 17;"
                +"-fx-pref-height: 17;"
                +"-fx-background-repeat: no-repeat;"
        );
        //  Rectangle rectangle=new Rectangle(10,10);
        //  this.setGraphic(rectangle);

        this.setGraphic(icon);
        this.getStylesheets().add("/pkg/stylesheet/TitledWithoutList.css");

        this.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue==true){
                    setExpandedIcon();
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToLogViewer();
                }else{
                    setCollapsedIcon();
                }
            }
        });
    }

    public void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/LogIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/LogIconBlack.png);"
        );
    }

    public static LogTitledWithList getInstance(){
        if (logTitledWithList ==null){
            logTitledWithList =new LogTitledWithList();
        }
        return logTitledWithList;
    }

}

