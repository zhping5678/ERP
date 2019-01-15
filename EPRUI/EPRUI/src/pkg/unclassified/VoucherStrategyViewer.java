package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VoucherStrategyViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static VoucherStrategyViewer voucherStrategyViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent voucherValueSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent conditionsSeparatorWithTitleComponent;
    public CheckBoxesComponent checkBoxesComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public TextFieldComponent voucherValueTextFieldComponent;

    public ButtonsComponent addCommodityButtonsComponent;
    public TextFieldComponent conditionalAmountTextFieldComponent;
    public TextFieldComponent startTimeTextFieldComponent;
    public TextFieldComponent stopTimeTextFieldComponent;
    public ButtonsComponent stopButtonComponent;
    public SpaceComponent spaceComponent;

    public VoucherStrategyViewer() {
        fileTitleComponent = new FileTitleComponent("", "Voucher Strategy");
        conditionalAmountTextFieldComponent = new TextFieldComponent("Conditional Amount");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A voucher strategy", "paulwong", df.format(day));
        separatorComponent = new SeparatorComponent();
        voucherValueSeparatorWithTitleComponent = new SeparatorWithTitleComponent("VOUCHER VALUE");
        conditionsSeparatorWithTitleComponent = new SeparatorWithTitleComponent("CONDITIONS");
        othersSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        addCommodityButtonsComponent = new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        startTimeTextFieldComponent = new TextFieldComponent("Start Time");
        stopTimeTextFieldComponent = new TextFieldComponent("Stop Time");
        stopButtonComponent = new ButtonsComponent("Stop");
        spaceComponent = new SpaceComponent();
        checkBoxesComponent = new CheckBoxesComponent();
        voucherValueTextFieldComponent=new TextFieldComponent("Voucher Value");
        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , conditionsSeparatorWithTitleComponent
                , conditionalAmountTextFieldComponent
                , checkBoxesComponent
                , voucherValueSeparatorWithTitleComponent
                ,voucherValueTextFieldComponent
                , othersSeparatorWithTitleComponent
                //,addCommodityButtonsComponent
                , startTimeTextFieldComponent
                , stopTimeTextFieldComponent
                , stopButtonComponent
                , spaceComponent
        );
    }


    public static VoucherStrategyViewer getInstance() {
        if (voucherStrategyViewer == null) {
            voucherStrategyViewer = new VoucherStrategyViewer();
        }
        return voucherStrategyViewer;
    }

}







