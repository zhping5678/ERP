package pkg.unclassified;

import javafx.beans.property.BooleanProperty;


import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseReturnOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static PurchaseReturnOrderViewer purchaseReturnOrderViewer;

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





    public PurchaseReturnOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Purchase Return Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));
        clerkChoiceBoxComponent=new ChoiceBoxComponent("Clerk","sdfsadfsdf","sdfsfsfad");

        creationInformationComponent=new CreationInformationComponent("An purchase return order","","");
        separatorComponent=new SeparatorComponent();
        clerkTextFieldComponent=new TextFieldComponent("Clerk");
        sellerTextFieldComponent =new TextFieldComponent("Seller");
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

        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
               // ,clerkTextFieldComponent


                ,sellerTextFieldComponent
                ,clerkChoiceBoxComponent
                ,commoditiesListSeparatorWithTitleComponent
                , commoditiesTableComponent
                //      ,addCommodityButtonsComponent

                ,summarySeparatorWithTitleComponent

                ,payableAmountTextFieldComponent
                ,remarkTextFieldComponent
                ,returnReceiptsSeparatorWithTitleComponent
                ,returnReceiptsTableComponent
                ,spaceComponent


        );
    }


    public static PurchaseReturnOrderViewer getInstance(){
        if (purchaseReturnOrderViewer==null){
            purchaseReturnOrderViewer=new PurchaseReturnOrderViewer();
        }


        return purchaseReturnOrderViewer;
    }
}