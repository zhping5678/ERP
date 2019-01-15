package pkg.unclassified;

import infor.Infor;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.RemoteHelper;
import pkg.ui.myfileui.MyFileUIControllerAccess;
import pkg.ui.settingui.SettingUIControllerAccess;
import vo.filevo.PurchaseVO;


import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static PurchaseOrderViewer purchaseOrderViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public TextFieldComponent clerkTextFieldComponent;
    public ChoiceBoxComponent clerkChoiceBoxComponent;
    public TextFieldComponent sellerTextFieldComponent;
    public SeparatorWithTitleComponent commoditiesListSeparatorWithTitleComponent;
    public CommoditiesTableComponent commoditiesTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;

    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent payableAmountTextFieldComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public SeparatorWithTitleComponent returnReceiptsSeparatorWithTitleComponent;
    public ReturnReceiptsTableComponent returnReceiptsTableComponent;
    public SpaceComponent spaceComponent;
    public ButtonsComponent reviewButtonsComponent;





    public PurchaseOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Purchase Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));
        clerkChoiceBoxComponent=new ChoiceBoxComponent("Clerk","sdfsadfsdf","sdfsfsfad");

        creationInformationComponent=new CreationInformationComponent("A purchase order","","");
        separatorComponent=new SeparatorComponent();
        clerkTextFieldComponent=new TextFieldComponent("Clerk");
        sellerTextFieldComponent=new TextFieldComponent("Seller");
        commoditiesListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("COMMODITIES LIST");
        commoditiesTableComponent =new CommoditiesTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);

        summarySeparatorWithTitleComponent=new SeparatorWithTitleComponent("SUMMARY");
        payableAmountTextFieldComponent =new TextFieldComponent("Payable Amount");
        remarkTextFieldComponent=new TextFieldComponent("Remark");
        returnReceiptsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("RETURN RECEIPTS");
        returnReceiptsTableComponent=new ReturnReceiptsTableComponent();
        spaceComponent=new SpaceComponent();
        reviewButtonsComponent=new ButtonsComponent("Reject","Approve");




        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent

                ,sellerTextFieldComponent
                ,clerkTextFieldComponent
          //      ,clerkChoiceBoxComponent
                ,commoditiesListSeparatorWithTitleComponent
                , commoditiesTableComponent
                //      ,addCommodityButtonsComponent

                ,summarySeparatorWithTitleComponent

                ,payableAmountTextFieldComponent
                ,remarkTextFieldComponent
              //  ,returnReceiptsSeparatorWithTitleComponent
              //  ,returnReceiptsTableComponent

                ,spaceComponent


        );
    }


    public static PurchaseOrderViewer getInstance(){
        if (purchaseOrderViewer==null){
            purchaseOrderViewer=new PurchaseOrderViewer();
        }
        return purchaseOrderViewer;
    }
    public void review(String id){
        PurchaseVO purchaseVO= MyFileUIControllerAccess.myFileUIControllerAccess.getPurchaseVO(id);
        System.err.println("purchasevo"+purchaseVO.getID());
        fileTitleComponent.blackTitle.setEditable(false);
        fileTitleComponent.purpleTitle.setEditable(false);
        fileTitleComponent.purpleTitle.setText(purchaseVO.getID());
        fileTitleComponent.blackTitle.setText("");
        clerkTextFieldComponent.textField.setEditable(false);
        clerkTextFieldComponent.textField.setText(purchaseVO.getClerk());
        sellerTextFieldComponent.textField.setText(purchaseVO.getClientID());
        commoditiesTableComponent.dataList.clear();
        for(int i=0;i<purchaseVO.getProductItems().size();i++){
            //进货商品清单,需要id(0)，name(1)，型号(2)，数量(3)，进货单价(4),单项总额(5)，备注(6)
            String[] strings=purchaseVO.getProductItems().get(i);
            commoditiesTableComponent.dataList.add(new CommoditiesTableComponentItem(strings[0],strings[1],Integer.parseInt(strings[3]),Double.parseDouble(strings[4]),strings[6]));
        }
        payableAmountTextFieldComponent.textField.setText(String.valueOf(purchaseVO.getTotal()));
        remarkTextFieldComponent.textField.setText(purchaseVO.getNote());
        vBox.getChildren().add(vBox.getChildren().size()-1,reviewButtonsComponent);
        reviewButtonsComponent.buttonArrayList.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*
                try {
                    RemoteHelper.getManagerBusinessLogicService().connectionTest();
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
                try {
                    RemoteHelper.getManagerBusinessLogicService().approveSalesman(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(), purchaseVO.getID(), false,infor);
                }catch(RemoteException e){
                    e.printStackTrace();
                }

            }
        });

        reviewButtonsComponent.buttonArrayList.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Infor infor=new Infor();
                infor.setCheckerID(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser());
                Date day=new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                infor.setCheckerTime(df.format(day));
                infor.setRemark("no remarks");
                infor.setResult("approved");
                try {
                    RemoteHelper.getManagerBusinessLogicService().approveSalesman(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(), purchaseVO.getID(), true, infor);
                }catch(RemoteException e){
                  //  e.printStackTrace();
                }

            }
        });


    }
}


