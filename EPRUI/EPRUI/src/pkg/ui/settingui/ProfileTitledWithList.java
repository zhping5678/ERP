package pkg.ui.settingui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.TitledWithList;

public class ProfileTitledWithList extends TitledWithList {
    private static ProfileTitledWithList profileTitledWithList;
    public Label icon=new Label("");

    private ProfileTitledWithList(){

        super("PROFILE",false);
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/ProfileIconPurple.png);"
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
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToUserViewer();
                }else{
                    setCollapsedIcon();
                }
            }
        });
    }

    public void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/ProfileIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/ProfileIconBlack.png);"
        );
    }

    public static ProfileTitledWithList getInstance(){
        if (profileTitledWithList ==null){
            profileTitledWithList =new ProfileTitledWithList();
        }
        return profileTitledWithList;
    }

}

