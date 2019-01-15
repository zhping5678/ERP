package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static ClientViewer clientViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent basicSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public TextFieldComponent remarkTextFieldComponent;
    public TextFieldComponent emailTextFieldComponent;
    public TextFieldComponent telephoneTextFieldComponent;
    public TextFieldComponent addressTextFieldComponent;
    public SpaceComponent spaceComponent;
    public ChoiceBoxComponent choiceBoxComponent;
    public TextFieldComponent presetClerkTextFieldComponent;
    public TextFieldComponent maxReceivableAmountTextFieldComponent;
    public TextFieldComponent payableAmountTextFieldComponent;
    public TextFieldComponent receivableAmountTextFieldComponent;
    public TextFieldComponent cumulativePaymentTextFieldComponent;
    public TextFieldComponent cumulativeReceiptTextFieldComponent;
    public TextFieldComponent voucherValueComponent;



    public ClientViewer() {
        choiceBoxComponent=new ChoiceBoxComponent("Type","Buyer","Seller","Buyer and seller");
        choiceBoxComponent.choiceBox.getSelectionModel().select(0);
      //  choiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("nju", "Nanjing University");
        emailTextFieldComponent = new TextFieldComponent("Email");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A client");
        separatorComponent = new SeparatorComponent();
        basicSeparatorWithTitleComponent = new SeparatorWithTitleComponent("BASIC");
        othersSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        telephoneTextFieldComponent = new TextFieldComponent("Telephone");
        addressTextFieldComponent = new TextFieldComponent("Address");
        spaceComponent = new SpaceComponent();
        remarkTextFieldComponent =new TextFieldComponent("Remark");
        presetClerkTextFieldComponent =new TextFieldComponent("Preset Clerk");
        maxReceivableAmountTextFieldComponent =new TextFieldComponent("Max Receivable Amount");
        payableAmountTextFieldComponent=new TextFieldComponent("Payable Amount");
        receivableAmountTextFieldComponent =new TextFieldComponent("Receivable Amount");
        cumulativePaymentTextFieldComponent=new TextFieldComponent("Cumulative Payment");
        cumulativeReceiptTextFieldComponent=new TextFieldComponent("Cumulative Receipt");
        voucherValueComponent =new TextFieldComponent("Voucher Value");

        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , basicSeparatorWithTitleComponent
                , choiceBoxComponent
                , emailTextFieldComponent
                , telephoneTextFieldComponent
                , addressTextFieldComponent
                , presetClerkTextFieldComponent
                , maxReceivableAmountTextFieldComponent
                , remarkTextFieldComponent
                , othersSeparatorWithTitleComponent
                ,payableAmountTextFieldComponent
                ,receivableAmountTextFieldComponent
                ,cumulativePaymentTextFieldComponent
                ,cumulativeReceiptTextFieldComponent
                , voucherValueComponent
                , spaceComponent

        );
    }


    public static ClientViewer getInstance() {
        if (clientViewer == null) {
            clientViewer = new ClientViewer();
        }
        return clientViewer;
    }
}







