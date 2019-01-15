package pkg.unclassified;

import javafx.beans.property.BooleanProperty;
import pkg.ui.RemoteHelper;
import pkg.ui.myfileui.MyFileUIControllerAccess;
import vo.filevo.SaleVO;


import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static SalesOrderViewer salesOrderViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public TextFieldComponent clerkTextFieldComponent;
    public ChoiceBoxComponent clerkChoiceBoxComponent;
    public TextFieldComponent buyerTextFieldComponent;
    public SeparatorWithTitleComponent commoditiesListSeparatorWithTitleComponent;
    public CommoditiesTableComponent commoditiesTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;
    public SeparatorWithTitleComponent packagesListSeparatorWithTitleComponent;
    public CommoditiesTableComponent packagesCommoditiesTableComponent;
    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent totalAmountTextFieldComponent;
    public TextFieldComponent discountsTextFieldComponent;
    public TextFieldComponent salesDiscountsTextFieldComponent;
    public TextFieldComponent voucherTextFieldComponent;
    public TextFieldComponent receivableAmountTextFieldComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public SeparatorWithTitleComponent returnReceiptsSeparatorWithTitleComponent;
    public ReturnReceiptsTableComponent returnReceiptsTableComponent;
    public SpaceComponent spaceComponent;
    public GiftsTableComponent giftsTableComponent;
    public SeparatorWithTitleComponent giftsListSeparatorWithTitleComponent;

    public SalesOrderViewer(){





        fileTitleComponent=new FileTitleComponent("","Sales Order");
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));
        creationInformationComponent=new CreationInformationComponent("A sales order","","");
        separatorComponent=new SeparatorComponent();


        clerkTextFieldComponent=new TextFieldComponent("Clerk");
        clerkChoiceBoxComponent=new ChoiceBoxComponent("Clerk","sdfsadfsdf","sdfsfsfad");



        buyerTextFieldComponent=new TextFieldComponent("Buyer");
        commoditiesListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("COMMODITIES LIST");
        commoditiesTableComponent =new CommoditiesTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
     //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        packagesListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("PACKAGES LIST");
        packagesCommoditiesTableComponent=new CommoditiesTableComponent();
        summarySeparatorWithTitleComponent=new SeparatorWithTitleComponent("SUMMARY");
        totalAmountTextFieldComponent=new TextFieldComponent("Total Amount");
        discountsTextFieldComponent=new TextFieldComponent("Discounts");
        salesDiscountsTextFieldComponent=new TextFieldComponent("Sales Discounts");
        voucherTextFieldComponent=new TextFieldComponent("Voucher");
        receivableAmountTextFieldComponent =new TextFieldComponent("Receivable Amount");
        remarkTextFieldComponent=new TextFieldComponent("Remark");
        returnReceiptsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("RETURN RECEIPTS");
        returnReceiptsTableComponent=new ReturnReceiptsTableComponent();
        packagesListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("PACKAGES LIST");
        giftsListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("GIFTS LIST");
        giftsTableComponent=new GiftsTableComponent();
        spaceComponent=new SpaceComponent();
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
               // ,clerkTextFieldComponent

                ,buyerTextFieldComponent
                ,clerkChoiceBoxComponent
                ,commoditiesListSeparatorWithTitleComponent
                , commoditiesTableComponent
          //    ,addCommodityButtonsComponent
                ,packagesListSeparatorWithTitleComponent
                ,packagesCommoditiesTableComponent
                ,giftsListSeparatorWithTitleComponent
                ,giftsTableComponent
                ,summarySeparatorWithTitleComponent
                ,totalAmountTextFieldComponent
                ,discountsTextFieldComponent
                ,salesDiscountsTextFieldComponent
                ,voucherTextFieldComponent
                ,receivableAmountTextFieldComponent
                ,remarkTextFieldComponent
                ,returnReceiptsSeparatorWithTitleComponent
                ,returnReceiptsTableComponent
                ,spaceComponent
        );
    }

    public void openSalesOrderAsEditable(){

    }
    public void openSalesOrderAsUneditable(){

    }
    public static SalesOrderViewer getInstance(){
        if (salesOrderViewer==null){
            salesOrderViewer=new SalesOrderViewer();
        }


        return salesOrderViewer;
    }

    public void review(String id){
        SaleVO saleVO=MyFileUIControllerAccess.myFileUIControllerAccess.getSalesVO(id);
        System.out.println(saleVO.toString());

    }
}
