package pkg.ui.clientui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.myfileui.MyFileUIControllerAccess;
import pkg.unclassified.TitledWithList;

public class SellerTitledWithList extends TitledWithList{
    private static SellerTitledWithList sellerTitledWithList;
    public Label icon=new Label("");

    private SellerTitledWithList(){

        super("SELLERS");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/SellerIconPurple.png);"
                +"-fx-background-size:17;"
                +"-fx-pref-width: 17;"
                +"-fx-pref-height: 17;"
                +"-fx-background-repeat: no-repeat;"
        );

        //  Rectangle rectangle=new Rectangle(10,10);
        //  this.setGraphic(rectangle);

        this.setGraphic(icon);

        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());//////////////////////////

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
                +"-fx-background-image:url(/pkg/image/SellerIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/SellerIconBlack.png);"
        );
    }

    public static SellerTitledWithList getInstance(){
        if (sellerTitledWithList ==null){
            sellerTitledWithList =new SellerTitledWithList();
        }
        return sellerTitledWithList;
    }
}
