package pkg.ui.clientui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import pkg.ui.myfileui.MyFileUIControllerAccess;
import pkg.unclassified.TitledWithList;

public class BuyerTitledWithList extends TitledWithList{
    private static BuyerTitledWithList buyerTitledWithList;
    public Label icon=new Label("");

    private BuyerTitledWithList(){

        super("BUYERS");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BuyerIconPurple.png);"
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
                +"-fx-background-image:url(/pkg/image/BuyerIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/BuyerIconBlack.png);"
        );
    }

    public static BuyerTitledWithList getInstance(){
        if (buyerTitledWithList ==null){
            buyerTitledWithList =new BuyerTitledWithList();
        }
        return buyerTitledWithList;
    }
}
