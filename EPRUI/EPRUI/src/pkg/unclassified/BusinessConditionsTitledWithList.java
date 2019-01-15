package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.utilityui.UtilityUIControllerAccess;


public class BusinessConditionsTitledWithList extends TitledWithList{
    private static BusinessConditionsTitledWithList businessConditionsTitledWithList;
    public Label icon=new Label("");

    private BusinessConditionsTitledWithList(){

        super("BUSINESS CONDITIONS",false);
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BusinessConditionsIconPurple.png);"
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
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToBusinessConditionsViewer();
                }else{
                    setCollapsedIcon();
                }
            }
        });
    }

    public void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BusinessConditionsIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BusinessConditionsIconBlack.png);"
        );
    }

    public static BusinessConditionsTitledWithList getInstance(){
        if (businessConditionsTitledWithList ==null){
            businessConditionsTitledWithList =new BusinessConditionsTitledWithList();
        }
        return businessConditionsTitledWithList;
    }
}
