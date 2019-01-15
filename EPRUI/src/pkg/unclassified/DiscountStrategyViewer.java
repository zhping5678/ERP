package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountStrategyViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static DiscountStrategyViewer discountStrategyViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent discountAndAllowanceSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent conditionsSeparatorWithTitleComponent;
    public CheckBoxesComponent checkBoxesComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public TextFieldComponent discountTextFieldComponent;
    public TextFieldComponent allowanceTextFieldComponent;

    public ButtonsComponent addCommodityButtonsComponent;
    public TextFieldComponent conditionalAmountTextFieldComponent;
    public TextFieldComponent startTimeTextFieldComponent;
    public TextFieldComponent stopTimeTextFieldComponent;
    public ButtonsComponent stopButtonComponent;
    public SpaceComponent spaceComponent;

    public DiscountStrategyViewer() {
        fileTitleComponent = new FileTitleComponent("", "Discount Strategy");
        conditionalAmountTextFieldComponent = new TextFieldComponent("Conditional Amount");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A discount strategy", "paulwong", df.format(day));
        separatorComponent = new SeparatorComponent();
        discountAndAllowanceSeparatorWithTitleComponent = new SeparatorWithTitleComponent("DISCOUNT AND ALLOWANCE");
        conditionsSeparatorWithTitleComponent = new SeparatorWithTitleComponent("CONDITIONS");
        othersSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        addCommodityButtonsComponent = new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        startTimeTextFieldComponent = new TextFieldComponent("Start Time");
        stopTimeTextFieldComponent = new TextFieldComponent("Stop Time");
        stopButtonComponent = new ButtonsComponent("Stop");
        spaceComponent = new SpaceComponent();
        checkBoxesComponent = new CheckBoxesComponent();
        discountTextFieldComponent=new TextFieldComponent("Discount");
        allowanceTextFieldComponent=new TextFieldComponent("Allowance");
        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , conditionsSeparatorWithTitleComponent
                , conditionalAmountTextFieldComponent
                , checkBoxesComponent
                , discountAndAllowanceSeparatorWithTitleComponent
                ,discountTextFieldComponent
                ,allowanceTextFieldComponent
                , othersSeparatorWithTitleComponent
                //,addCommodityButtonsComponent
                , startTimeTextFieldComponent
                , stopTimeTextFieldComponent
                , stopButtonComponent
                , spaceComponent
        );
    }

    public static DiscountStrategyViewer getInstance() {
        if (discountStrategyViewer == null) {
            discountStrategyViewer = new DiscountStrategyViewer();
        }
        return discountStrategyViewer;
    }
}
