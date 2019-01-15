package pkg.ui.myfileui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;

import pkg.ui.settingui.SettingUIControllerAccess;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.*;
import vo.filevo.FileType;
import vo.uservo.Position;


public class PendingTitledWithList extends TitledWithList {
    public static PendingTitledWithList pendingTitledWithList;
    public Label icon=new Label("");

    public PendingTitledWithList(){

        super("PENDING");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/PendingIconPurple.png);"
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

        listed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemText>() {
            @Override
            public void changed(ObservableValue<? extends ItemText> observable, ItemText oldValue, ItemText newValue) {
                if (newValue!=null) {
                    if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition() == Position.Manager) {
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.SALE) {
                            ((SalesOrderViewer) (MyFileTabContentPane.getInstance().viewer)).review(newValue.id);
                        }
                        ;
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.PURCHASE) {
                            ((PurchaseOrderViewer) (MyFileTabContentPane.getInstance().viewer)).review(newValue.id);
                        }
                        ;
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.RECEIPT) {
                            ((ReceiptOrderViewer) (MyFileTabContentPane.getInstance().viewer)).review(newValue.id);
                        }
                        ;
                    } else {
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.SALE) {
                            ((SalesOrderViewer) (MyFileTabContentPane.getInstance().viewer)).readAndEdit(newValue.id);
                        }
                        ;
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.PURCHASE) {
                            ((PurchaseOrderViewer) (MyFileTabContentPane.getInstance().viewer)).readAndEdit(newValue.id);
                        }
                        ;
                        if (UtilityUIControllerAccess.utilityUIControllerAccess.fromIdToType(newValue.id) == FileType.RECEIPT) {
                            ((ReceiptOrderViewer) (MyFileTabContentPane.getInstance().viewer)).readAndEdit(newValue.id);
                        }
                        ;

                    }
                }

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
                +"-fx-background-image:url(/pkg/image/PendingIconPurple.png);"
        );
    }

    public void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/PendingIconBlack.png);"
        );
    }
    public static PendingTitledWithList getInstance(){
        if (pendingTitledWithList==null){
            pendingTitledWithList=new PendingTitledWithList();
        }
        return pendingTitledWithList;
    }
    public void loadData(){
        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getPendingList());
    }


}
