package infor;

import java.io.Serializable;

public class BusinessConditions implements Serializable {
    private static final long serialVersionUID=2222L;
    private double salesIncome;
    private double commodityExcessIncome;
    private double costAdjustmentIncome;
    private double spreadOfPurchaseAndReturn;
    private double spreadOfVoucherAndReceivable;
    private double discount;
    private double incomeAfterDiscount;

    private double salesCost;
    private double commodityLoss;
    private double generalOutcome;

    private double profit;

    public BusinessConditions(double salesIncome,double commodityExcessIncome,double costAdjustmentIncome, double spreadOfPurchaseAndReturn,double spreadOfVoucherAndReceivable, double discount,double incomeAfterDiscount,double salesCost, double commodityLoss,double generalOutcome,double profit){
        this.salesIncome=salesIncome;
        this.commodityExcessIncome=commodityExcessIncome;
        this.costAdjustmentIncome=costAdjustmentIncome;
        this.spreadOfPurchaseAndReturn=spreadOfPurchaseAndReturn;
        this.spreadOfVoucherAndReceivable=spreadOfVoucherAndReceivable;
        this.discount=discount;
        this.incomeAfterDiscount=incomeAfterDiscount;
        this.salesCost=salesCost;
        this.commodityLoss=commodityLoss;
        this.generalOutcome=generalOutcome;
        this.profit=profit;
    }

    public double getSalesIncome() {
        return salesIncome;
    }

    public double getCommodityExcessIncome() {
        return commodityExcessIncome;
    }

    public double getCostAdjustmentIncome() {
        return costAdjustmentIncome;
    }

    public double getSpreadOfPurchaseAndReturn() {
        return spreadOfPurchaseAndReturn;
    }

    public double getSpreadOfVoucherAndReceivable() {
        return spreadOfVoucherAndReceivable;
    }

    public double getDiscount() {
        return discount;
    }

    public double getIncomeAfterDiscount() {
        return incomeAfterDiscount;
    }

    public double getSalesCost() {
        return salesCost;
    }

    public double getCommodityLoss() {
        return commodityLoss;
    }

    public double getGeneralOutcome() {
        return generalOutcome;
    }

    public double getProfit(){
        return this.profit;
    }
}
