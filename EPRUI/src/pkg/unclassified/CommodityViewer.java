package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommodityViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static CommodityViewer commodityViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent basicSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public TextFieldComponent idTextFieldComponent;
    public TextFieldComponent modelTextFieldComponent;
    public SpaceComponent spaceComponent;
    public TextFieldComponent recentSalesPriceTextFieldComponent;
    public TextFieldComponent recentPurchasePriceTextFieldComponent;
    public TextFieldComponent averageCostTextFieldComponent;
    public TextFieldComponent quantityValueComponent;
    public ButtonsComponent selectButtonsCompoent;



    public CommodityViewer() {
        //  choiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("philips", "飛利浦");
        idTextFieldComponent = new TextFieldComponent("ID");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A commodity");
        separatorComponent = new SeparatorComponent();
        basicSeparatorWithTitleComponent = new SeparatorWithTitleComponent("BASIC");
        othersSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        modelTextFieldComponent = new TextFieldComponent("Model");
        spaceComponent = new SpaceComponent();
        remarkTextFieldComponent =new TextFieldComponent("Remark");

        recentSalesPriceTextFieldComponent =new TextFieldComponent("Recent Sales Price");
        recentPurchasePriceTextFieldComponent =new TextFieldComponent("Recent Purchase Price");
        averageCostTextFieldComponent =new TextFieldComponent("Average Cost");
        quantityValueComponent =new TextFieldComponent("Quantity");
        selectButtonsCompoent=new ButtonsComponent("Select");

        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , basicSeparatorWithTitleComponent
                , idTextFieldComponent
                , modelTextFieldComponent
                , remarkTextFieldComponent
                , othersSeparatorWithTitleComponent
                , quantityValueComponent
                , recentSalesPriceTextFieldComponent
                , recentPurchasePriceTextFieldComponent
                , averageCostTextFieldComponent
                ,selectButtonsCompoent
                , spaceComponent

        );
    }


    public static CommodityViewer getInstance() {
        if (commodityViewer == null) {
            commodityViewer = new CommodityViewer();
        }
        return commodityViewer;
    }
}

