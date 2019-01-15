package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.utilityui.UtilityUIControllerAccess;


public class SalesDetailsTitledWithList extends TitledWithList{
    private static SalesDetailsTitledWithList salesDetailsTitledWithList;
    public Label icon=new Label("");

    private SalesDetailsTitledWithList(){

        super("SALES DETAILS",false);
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/SalesDetailsIconPurple.png);"
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
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToSalesDetailsViewer();
                }else{
                    setCollapsedIcon();
                }
            }
        });
    }

    public void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/SalesDetailsIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/SalesDetailsIconBlack.png);"
        );
    }

    public static SalesDetailsTitledWithList getInstance(){
        if (salesDetailsTitledWithList ==null){
            salesDetailsTitledWithList =new SalesDetailsTitledWithList();
        }
        return salesDetailsTitledWithList;
    }
}
