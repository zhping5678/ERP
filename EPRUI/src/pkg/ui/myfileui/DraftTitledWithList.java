package pkg.ui.myfileui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import pkg.ui.RemoteHelper;
import pkg.ui.settingui.SettingUIControllerAccess;
import pkg.unclassified.ItemText;
import pkg.unclassified.PrimaryStage;
import pkg.unclassified.TitledWithList;
import vo.uservo.Position;

import java.rmi.RemoteException;


public class DraftTitledWithList extends TitledWithList {
    private static DraftTitledWithList draftTitledWithList;
    public Label icon=new Label("");
    public ContextMenu managerDraftMenu=new ContextMenu();
    public ContextMenu financialDraftMenu=new ContextMenu();


    private DraftTitledWithList(){
        super("DRAFTS");
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/DraftIconPurple.png);"
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
                if (newValue==true){
                    setExpandedIcon();
                }else{
                    setCollapsedIcon();
                }
            }
        });

        MenuItem addReceiptMenuItem=new MenuItem("New Receipt Order");
        addReceiptMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newid="";
                try{
                    newid=RemoteHelper.getAccountManBusinessLogicService().addReceipt(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());

                }catch (RemoteException e){
                    e.printStackTrace();
                }
                loadData();
                System.out.println(newid);
                selectnew(newid);
            }
        });
        this.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                loadData();
            }
        });

        MenuItem addPaymentMenuItem=new MenuItem("New Payment Order");
        addPaymentMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newid="";
                try{
                    newid=RemoteHelper.getAccountManBusinessLogicService().addPayment(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),0);

                }catch (RemoteException e){
                    e.printStackTrace();
                }
                loadData();
                System.out.println(newid);
                selectnew(newid);

            }
        });

        MenuItem addReimbursementMenItem=new MenuItem("New Reimbursement Order");
        addReimbursementMenItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newid="";
                try{
                    newid=RemoteHelper.getAccountManBusinessLogicService().addPayment(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),1);

                }catch (RemoteException e){
                    e.printStackTrace();
                }
                loadData();
                System.out.println(newid);
                selectnew(newid);
            }
        });


        MenuItem saveOrderMenuItem=new MenuItem("Save");
        saveOrderMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyFileTabContentPane.getInstance().viewer.save();

            }
        });

        MenuItem submitOrderMenuItem=new MenuItem("Submit");
        submitOrderMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyFileTabContentPane.getInstance().viewer.submit();

               // loadData();
               // PendingTitledWithList.getInstance().loadData();
            }
        });






        financialDraftMenu.getItems().addAll(addReceiptMenuItem,addPaymentMenuItem,addReimbursementMenItem,saveOrderMenuItem,submitOrderMenuItem);

       // this.getStylesheets().add("/pkg.stylesheet/PrettyContextMenu.css");
        MenuItem addLevelStrategyMenuItem=new MenuItem("New Level Strategy");
        MenuItem addVoucherStrategyMenuItem=new MenuItem("New Voucher Strategy");
        MenuItem addPackageStrategyMenuItem=new MenuItem("New Package Strategy");
        MenuItem addDiscountStrategyMenuItem=new MenuItem("New Discount Strategy");
        MenuItem addGiftStrategyMenuItem=new MenuItem("New Gift Strategy");
        MenuItem removeStrategyMenuItem=new MenuItem("Remove");




        managerDraftMenu.getItems().addAll(addLevelStrategyMenuItem,addVoucherStrategyMenuItem,addDiscountStrategyMenuItem,addPackageStrategyMenuItem,addGiftStrategyMenuItem,removeStrategyMenuItem);
        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.Manager) {
            listed.setContextMenu(managerDraftMenu);
        }else{
            listed.setContextMenu(financialDraftMenu);

        }

        listed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemText>() {
            @Override
            public void changed(ObservableValue<? extends ItemText> observable, ItemText oldValue, ItemText newValue) {
                if (newValue!=null){
                    MyFileTabContentPane.getInstance().viewer.readAndEdit(newValue.id);
                }
            }
        });
    }





    private void setExpandedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/DraftIconPurple.png);"
        );
    }

    private void setCollapsedIcon(){
        icon.setStyle(icon.getStyle()
                +"-fx-background-image:url(/pkg/image/DraftIconBlack.png);"
        );
    }
    public static DraftTitledWithList getInstance(){
        if (draftTitledWithList==null){
            draftTitledWithList=new DraftTitledWithList();
        }
        return draftTitledWithList;
    }

    public void loadData(){
       // System.out.println(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList().size());
        listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());
        System.out.println("loadDATA");
    }
    public void selectnew(String newid){
        for (int i=0;i<listed.getItems().size();i++){
            System.out.println(listed.getItems().get(i).id);
            if (listed.getItems().get(i).id.equals(newid)){
                System.out.println("false");
                listed.getSelectionModel().select(i);
                return;
            }
        }

    }


}
