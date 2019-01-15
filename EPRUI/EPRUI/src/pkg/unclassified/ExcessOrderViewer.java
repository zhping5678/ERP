package pkg.unclassified;

import javafx.beans.property.BooleanProperty;


import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcessOrderViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static ExcessOrderViewer excessOrderViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public SeparatorWithTitleComponent commoditiesListSeparatorWithTitleComponent;
    public CommoditiesTableComponent commoditiesTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;

    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent amountTextField;
    public TextFieldComponent remarkTextFieldComponent;
    public SeparatorWithTitleComponent returnReceiptsSeparatorWithTitleComponent;
    public ReturnReceiptsTableComponent returnReceiptsTableComponent;
    public SpaceComponent spaceComponent;





    public ExcessOrderViewer(){
        fileTitleComponent=new FileTitleComponent("","Excess Order");

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // vBox.getChildren().add(new CreationInformationComponent("paulwong",df.format(day)));

        creationInformationComponent=new CreationInformationComponent("An excess order","","");
        separatorComponent=new SeparatorComponent();

        commoditiesListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("COMMODITIES LIST");
        commoditiesTableComponent =new CommoditiesTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);

        summarySeparatorWithTitleComponent=new SeparatorWithTitleComponent("SUMMARY");
        amountTextField =new TextFieldComponent("Amount");
        remarkTextFieldComponent=new TextFieldComponent("Remark");
        returnReceiptsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("RETURN RECEIPTS");
        returnReceiptsTableComponent=new ReturnReceiptsTableComponent();
        spaceComponent=new SpaceComponent();

        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                ,commoditiesListSeparatorWithTitleComponent
                , commoditiesTableComponent
                //      ,addCommodityButtonsComponent

                ,summarySeparatorWithTitleComponent

                ,amountTextField
                ,remarkTextFieldComponent
                ,returnReceiptsSeparatorWithTitleComponent
                ,returnReceiptsTableComponent
                ,spaceComponent


        );
    }


    public static ExcessOrderViewer getInstance(){
        if (excessOrderViewer ==null){
            excessOrderViewer =new ExcessOrderViewer();
        }


        return excessOrderViewer;
    }
}