package pkg.unclassified;
import infor.BusinessConditions;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pkg.ui.RemoteHelper;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static pkg.ui.RemoteHelper.getAccountManBusinessLogicService;

public class BusinessConditionsViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    private static BusinessConditionsViewer businessConditionsViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public DatePickerComponent datePickerComponent;
    public SeparatorWithTitleComponent incomeSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent outcomeSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent profitSeparatorWithTitleComponent;

    public TextFieldComponent salesIncomeTextFieldComponent;
    public TextFieldComponent commodityExcessIncomeTextFieldComponent;
    public TextFieldComponent costAdjustmentIncomeTextFiledComponent;
    public TextFieldComponent profitTextFieldComponent;
    public TextFieldComponent spreadOfPurchaseAndReturnTextFieldComponent;
    public TextFieldComponent spreadOfVoucherAndReceivableTextFieldComponent;
    public TextFieldComponent discountTextFieldComponent;
    public TextFieldComponent incomeAfterDiscountTextFieldComponent;
    public TextFieldComponent salesCostTextFieldComponent;
    public TextFieldComponent commodityLossTextFieldComponent;
    public TextFieldComponent giftTextFieldComponent;
    public TextFieldComponent generalOutcomeTextFieldComponent;
    public SpaceComponent spaceComponent;




    private BusinessConditionsViewer() {

        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("", "Business Conditions");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("");
        separatorComponent = new SeparatorComponent();
        datePickerComponent =new DatePickerComponent();

        incomeSeparatorWithTitleComponent =new SeparatorWithTitleComponent("INCOME");

        salesIncomeTextFieldComponent=new TextFieldComponent("Sales Income",false);
        commodityExcessIncomeTextFieldComponent=new TextFieldComponent("Commodity Excess Income",false);
        costAdjustmentIncomeTextFiledComponent=new TextFieldComponent("Cost Adjustment Income",false);
        spreadOfPurchaseAndReturnTextFieldComponent =new TextFieldComponent("Spread of Purchase And Return ",false);
        spreadOfVoucherAndReceivableTextFieldComponent =new TextFieldComponent("Spread of Voucher And Receivable",false);
        discountTextFieldComponent=new TextFieldComponent("Discount",false);
        incomeAfterDiscountTextFieldComponent=new TextFieldComponent("Income After Discount",false);
        salesCostTextFieldComponent=new TextFieldComponent("Sales Cost",false);
        commodityLossTextFieldComponent=new TextFieldComponent("Commodity Loss",false);
        giftTextFieldComponent=new TextFieldComponent("Gift",false);
        generalOutcomeTextFieldComponent =new TextFieldComponent("General Outcome",false);

        outcomeSeparatorWithTitleComponent=new SeparatorWithTitleComponent("OUTCOME");
        profitSeparatorWithTitleComponent=new SeparatorWithTitleComponent("PROFIT");
        profitTextFieldComponent=new TextFieldComponent("Profit",false);
        datePickerComponent.startDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                judge();
            }
        });
        datePickerComponent.endDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                judge();
            }
        });


        spaceComponent = new SpaceComponent();
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                , datePickerComponent
                , incomeSeparatorWithTitleComponent
                ,salesIncomeTextFieldComponent
                ,commodityExcessIncomeTextFieldComponent
                ,costAdjustmentIncomeTextFiledComponent

                ,spreadOfPurchaseAndReturnTextFieldComponent
                ,spreadOfVoucherAndReceivableTextFieldComponent
                ,discountTextFieldComponent
                ,incomeAfterDiscountTextFieldComponent
                ,outcomeSeparatorWithTitleComponent
                ,salesCostTextFieldComponent
                ,commodityLossTextFieldComponent
                ,generalOutcomeTextFieldComponent


                ,profitSeparatorWithTitleComponent

                ,profitTextFieldComponent

                ,spaceComponent

        );
    }


    public static BusinessConditionsViewer getInstance() {
        if (businessConditionsViewer == null) {
            businessConditionsViewer = new BusinessConditionsViewer();
        }
        return businessConditionsViewer;
    }

    public void judge(){
        if((datePickerComponent.startDatePicker.getValue()!=null)&&(datePickerComponent.endDatePicker.getValue()!=null)){
            System.out.println("datePickervaluestart"+datePickerComponent.startDatePicker.getValue());
            System.out.println("datePickervalueend"+datePickerComponent.endDatePicker.getValue());

            loadData(String.valueOf(datePickerComponent.startDatePicker.getValue()),String.valueOf(datePickerComponent.endDatePicker.getValue()));
        }




    }

    public void loadData(String starttime,String endtime){

        try{
            BusinessConditions businessConditions;//=new BusinessConditions(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0);
            //businessConditions=RemoteHelper.getAccountManBusinessLogicService().connectionTest();
            businessConditions= RemoteHelper.getAccountManBusinessLogicService().showBusinessConditions(starttime,endtime);
            salesIncomeTextFieldComponent.textField.setText(String.valueOf(businessConditions.getSalesIncome()));
            commodityExcessIncomeTextFieldComponent.textField.setText(String.valueOf(businessConditions.getCommodityExcessIncome()));
            costAdjustmentIncomeTextFiledComponent.textField.setText(String.valueOf(businessConditions.getCostAdjustmentIncome()));
            spreadOfPurchaseAndReturnTextFieldComponent.textField.setText(String.valueOf(businessConditions.getSpreadOfPurchaseAndReturn()));
            spreadOfVoucherAndReceivableTextFieldComponent.textField.setText(String.valueOf(businessConditions.getSpreadOfVoucherAndReceivable()));
            discountTextFieldComponent.textField.setText(String.valueOf(businessConditions.getDiscount()));
            incomeAfterDiscountTextFieldComponent.textField.setText(String.valueOf(businessConditions.getIncomeAfterDiscount()));
            salesCostTextFieldComponent.textField.setText(String.valueOf(businessConditions.getSalesCost()));
            commodityLossTextFieldComponent.textField.setText(String.valueOf(businessConditions.getCommodityLoss()));
            generalOutcomeTextFieldComponent.textField.setText(String.valueOf(businessConditions.getGeneralOutcome()));
            profitTextFieldComponent.textField.setText(String.valueOf(businessConditions.getProfit()));
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
    public void clearData(){

    }
}

