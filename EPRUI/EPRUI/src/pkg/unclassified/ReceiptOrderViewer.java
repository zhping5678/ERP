package pkg.unclassified;

import infor.Infor;
import infor.Transfer;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import pkg.ui.RemoteHelper;
import pkg.ui.myfileui.PendingTitledWithList;
import pkg.ui.settingui.SettingUIControllerAccess;
import vo.accountvo.AccountVO;
import vo.filevo.ReceiptVO;


import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class ReceiptOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static ReceiptOrderViewer receiptOrderViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    //   public TextFieldComponent clerkTextFieldComponent;
    public TextFieldComponent payerTextFieldComponent;
    public TextFieldComponent accountTextFieldComponent;
    public SeparatorWithTitleComponent itemsListSeparatorWithTitleComponent;
    public ItemsTableComponent itemsTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;

    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent amountTextFieldComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public SeparatorWithTitleComponent returnReceiptsSeparatorWithTitleComponent;
    public ReturnReceiptsTableComponent returnReceiptsTableComponent;
    public SpaceComponent spaceComponent;

    public ReceiptOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Receipt Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));

        creationInformationComponent=new CreationInformationComponent("A receipt order","","");
        separatorComponent=new SeparatorComponent();
        //      clerkTextFieldComponent=new TextFieldComponent("Clerk");
        payerTextFieldComponent =new TextFieldComponent("Payer");
        accountTextFieldComponent=new TextFieldComponent("Account");
        itemsListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("ITEMS LIST");
        itemsTableComponent =new ItemsTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);

        summarySeparatorWithTitleComponent=new SeparatorWithTitleComponent("SUMMARY");
        amountTextFieldComponent =new TextFieldComponent("Amount");
        remarkTextFieldComponent=new TextFieldComponent("Remark");
        returnReceiptsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("RETURN RECEIPTS");
        returnReceiptsTableComponent=new ReturnReceiptsTableComponent();
        spaceComponent=new SpaceComponent();
        ContextMenu contextMenu=new ContextMenu();
        MenuItem menuItem=new MenuItem("Add");
        MenuItem menuItem1=new MenuItem("Delete");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("New Remark");
                dialog.setTitle("New Remark");
                dialog.setHeaderText("");
                dialog.setContentText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    TextInputDialog dialog2 = new TextInputDialog("New Money");
                    dialog2.setTitle("New Money");
                    dialog2.setHeaderText("");
                    dialog2.setContentText("");
                    Optional<String> result2 = dialog.showAndWait();
                    if (result2.isPresent()){
                        try {
                            itemsTableComponent.dataList.add(new ItemsTableComponentItem(result.get(), Double.valueOf(result2.get())));
                        }catch (Exception e){
                        }
                    }
                }
            }
        });
        itemsListSeparatorWithTitleComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("New Remark");
                dialog.setTitle("New Remark");
                dialog.setHeaderText("");
                dialog.setContentText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    TextInputDialog dialog2 = new TextInputDialog("New Money");
                    dialog2.setTitle("New Money");
                    dialog2.setHeaderText("");
                    dialog2.setContentText("");
                    Optional<String> result2 = dialog2.showAndWait();
                    if (result2.isPresent()){
                        try {
                            itemsTableComponent.dataList.add(new ItemsTableComponentItem(result.get(), Double.valueOf(result2.get())));
                            double sum=0.0;
                            for (int i=0;i<itemsTableComponent.dataList.size();i++){
                                sum=sum+itemsTableComponent.dataList.get(i).value.getValue();
                            }
                            amountTextFieldComponent.textField.setText(String.valueOf(sum));
                        }catch (Exception e){
                        }
                    }
                }

            }
        });


        itemsTableComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextInputDialog dialog = new TextInputDialog("New Remark");
                dialog.setTitle("New Remark");
                dialog.setHeaderText("");
                dialog.setContentText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    TextInputDialog dialog2 = new TextInputDialog("New Money");
                    dialog2.setTitle("New Money");
                    dialog2.setHeaderText("");
                    dialog2.setContentText("");
                    Optional<String> result2 = dialog.showAndWait();
                    if (result2.isPresent()){
                        try {
                            itemsTableComponent.dataList.add(new ItemsTableComponentItem(result.get(), Double.valueOf(result2.get())));
                        }catch (Exception e){
                        }
                    }
                }

            }
        });



        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                //             ,clerkTextFieldComponent
                , payerTextFieldComponent
                ,accountTextFieldComponent
                ,itemsListSeparatorWithTitleComponent
                , itemsTableComponent
                //      ,addCommodityButtonsComponent

                ,summarySeparatorWithTitleComponent
                ,amountTextFieldComponent
                ,remarkTextFieldComponent
               // ,returnReceiptsSeparatorWithTitleComponent
               // ,returnReceiptsTableComponent
                ,spaceComponent


        );
    }

    public static ReceiptOrderViewer getInstance(){
        if (receiptOrderViewer ==null){
            receiptOrderViewer =new ReceiptOrderViewer();
        }
        return receiptOrderViewer;
    }
    public void readAndEdit(String id){
        ButtonsComponent savebutton =new ButtonsComponent("Save");
        itemsTableComponent.dataList.clear();
        ReceiptVO receiptVO;
        try {
            receiptVO= RemoteHelper.getAccountManBusinessLogicService().findReceipt(id);
            fileTitleComponent.purpleTitle.setText(receiptVO.getId());
            fileTitleComponent.blackTitle.setText("");
            payerTextFieldComponent.textField.setText(receiptVO.getClientID());
            accountTextFieldComponent.textField.setText(receiptVO.getAccountID());
            for (int i=0;i<receiptVO.getTransferList().size();i++){
                itemsTableComponent.dataList.add(new ItemsTableComponentItem(receiptVO.getTransferList().get(i).getNote(),receiptVO.getTransferList().get(i).getMoney()));
            }
            amountTextFieldComponent.textField.setText(String.valueOf(receiptVO.getSumMoney()));
            remarkTextFieldComponent.textField.setText(receiptVO.getRemark());
        }catch (RemoteException e){
            e.printStackTrace();
        }
        savebutton.buttonArrayList.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                save();
            }
        });

    }

    public void save(){
        ArrayList<Transfer> transfers=new ArrayList<>();

        for(int i=0;i<itemsTableComponent.dataList.size();i++){
            Transfer transfer=new Transfer();
            transfer.setMoney(itemsTableComponent.dataList.get(i).value.getValue());
            transfer.setNote(itemsTableComponent.dataList.get(i).remark.getValue());
            transfers.add(transfer);
        }
        ReceiptVO receiptVO=new ReceiptVO(fileTitleComponent.purpleTitle.getText()
                ,""
                ,payerTextFieldComponent.textField.getText()
                ,accountTextFieldComponent.textField.getText()
                ,""
                ,transfers
                ,Double.valueOf(amountTextFieldComponent.textField.getText())
                ,"DRAFT"
                ,remarkTextFieldComponent.textField.getText()
                ,new ArrayList<Infor>()
        );

        try{
            RemoteHelper.getAccountManBusinessLogicService().modReceipt(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),receiptVO);

        }catch(RemoteException e){

        }

    }
    public void submit(){
        save();

        try{
            System.out.println("commit user" +SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());
            System.out.println("commit id" +fileTitleComponent.purpleTitle.getText());

            RemoteHelper.getAccountManBusinessLogicService().commitReceipt(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),fileTitleComponent.purpleTitle.getText());
            System.out.println("submit 1 successfully");
        }catch (RemoteException e){
            e.printStackTrace();
        }
        System.out.println("submit 2 successfully");
    }

    public void review(String id){
        readAndEdit(id);
        ButtonsComponent reviewButtonsComponent=new ButtonsComponent("Reject","Approve");
        vBox.getChildren().add(vBox.getChildren().size()-1,reviewButtonsComponent);
        reviewButtonsComponent.buttonArrayList.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*
                /*
                try {
                    RemoteHelper.getManagerBusinessLogicService().connectionTest();
                }catch(RemoteException e){
                    e.printStackTrace();
                }



                try {
                    RemoteHelper.getManagerBusinessLogicService().approveSalesman(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(), purchaseVO.getID(), false,infor);
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                */
                Infor infor=new Infor();
                infor.setCheckerID(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                infor.setCheckerTime(df.format(day));
                infor.setRemark("no remarks");
                infor.setResult("rejected");

                try{
                    RemoteHelper.getManagerBusinessLogicService().approveAccountMan(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),fileTitleComponent.purpleTitle.getText(),false,infor);
                }catch(RemoteException e){


                }


                PendingTitledWithList.getInstance().loadData();

            }
        });
        reviewButtonsComponent.buttonArrayList.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*
                /*
                try {
                    RemoteHelper.getManagerBusinessLogicService().connectionTest();
                }catch(RemoteException e){
                    e.printStackTrace();
                }



                try {
                    RemoteHelper.getManagerBusinessLogicService().approveSalesman(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(), purchaseVO.getID(), false,infor);
                }catch(RemoteException e){
                    e.printStackTrace();
                }
                */
                Infor infor=new Infor();
                infor.setCheckerID(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                infor.setCheckerTime(df.format(day));
                infor.setRemark("no remarks");
                infor.setResult("approve");

                try{
                    RemoteHelper.getManagerBusinessLogicService().approveAccountMan(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(),fileTitleComponent.purpleTitle.getText(),true,infor);
                }catch(RemoteException e){


                }
                PendingTitledWithList.getInstance().loadData();

            }
        });

    }
}
