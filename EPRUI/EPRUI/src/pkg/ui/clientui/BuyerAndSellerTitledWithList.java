package pkg.ui.clientui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.myfileui.MyFileUIControllerAccess;
import pkg.unclassified.TitledWithList;

public class BuyerAndSellerTitledWithList extends TitledWithList{
    private static BuyerAndSellerTitledWithList buyerAndSellerTitledWithList;
    public Label icon=new Label("");

    private BuyerAndSellerTitledWithList(){

        super("BUYERS & SELLERS");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BuyerAndSellerIconPurple.png);"
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
                +"-fx-background-image:url(/pkg/image/BuyerAndSellerIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BuyerAndSellerIconBlack.png);"
        );
    }

    public static BuyerAndSellerTitledWithList getInstance(){
        if (buyerAndSellerTitledWithList ==null){
            buyerAndSellerTitledWithList =new BuyerAndSellerTitledWithList();
        }
        return buyerAndSellerTitledWithList;
    }
}
