package pkg.unclassified;

import javafx.beans.property.BooleanProperty;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesReturnOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static SalesReturnOrderViewer salesReturnOrderViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public TextFieldComponent clerkTextFieldComponent;
    public ChoiceBoxComponent clerkChoiceBoxComponent;
    public TextFieldComponent buyerTextFieldComponent;
    public SeparatorWithTitleComponent commoditiesListSeparatorWithTitleComponent;
    public CommoditiesTableComponent commoditiesTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;

    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent payableAmountTextFieldComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public SeparatorWithTitleComponent returnReceiptsSeparatorWithTitleComponent;
    public ReturnReceiptsTableComponent returnReceiptsTableComponent;
    public SpaceComponent spaceComponent;

    public SalesReturnOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Sales Return Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));


        creationInformationComponent=new CreationInformationComponent("A sales return order","","");
        clerkChoiceBoxComponent=new ChoiceBoxComponent("Clerk","sdfsadfsdf","sdfsfsfad");
        separatorComponent=new SeparatorComponent();
        clerkTextFieldComponent=new TextFieldComponent("Clerk");
        buyerTextFieldComponent =new TextFieldComponent("Buyer");
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
              //  ,clerkTextFieldComponent
                , buyerTextFieldComponent
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

    public static SalesReturnOrderViewer getInstance(){
        if (salesReturnOrderViewer ==null){
            salesReturnOrderViewer =new SalesReturnOrderViewer();
        }


        return salesReturnOrderViewer;
    }
}