package pkg.unclassified;

import javafx.beans.property.BooleanProperty;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ReimbursementOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static ReimbursementOrderViewer reimbursementOrderViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    //   public TextFieldComponent clerkTextFieldComponent;
    public TextFieldComponent staffTextFieldComponent;
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





    public ReimbursementOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Reimbursement Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));

        creationInformationComponent=new CreationInformationComponent("A reimbursement order","","");
        separatorComponent=new SeparatorComponent();
        //      clerkTextFieldComponent=new TextFieldComponent("Clerk");
        staffTextFieldComponent =new TextFieldComponent("Staff");
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

        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                //             ,clerkTextFieldComponent
                , staffTextFieldComponent
                ,accountTextFieldComponent
                ,itemsListSeparatorWithTitleComponent
                , itemsTableComponent
                //      ,addCommodityButtonsComponent

                ,summarySeparatorWithTitleComponent
                ,amountTextFieldComponent
                ,remarkTextFieldComponent
                ,returnReceiptsSeparatorWithTitleComponent
                ,returnReceiptsTableComponent
                ,spaceComponent


        );
    }

    public static ReimbursementOrderViewer getInstance(){
        if (reimbursementOrderViewer ==null){
            reimbursementOrderViewer =new ReimbursementOrderViewer();
        }


        return reimbursementOrderViewer;
    }
}