package pkg.ui.utilityui;

import pkg.ui.accountui.AccountTabContentPane;
import pkg.ui.fileui.FileTabContentPane;
import pkg.ui.myfileui.MyFileTabContentPane;
import pkg.ui.settingui.SettingTabContentPane;
import pkg.ui.settingui.SettingUIControllerAccess;
import pkg.unclassified.*;
import vo.filevo.FileType;
import vo.uservo.Position;

class UtilityUIController implements UtilityUIControllerAccess {
    private static UtilityUIController utilityUIController;
    private UtilityUIController(){

    }
    @Override

    public void switchToTabContentPane(TabContentPane tabContentPane){
        PrimaryStage.getInstance().primaryHBox.getChildren().remove(PrimaryStage.getInstance().tabContentPane);
        PrimaryStage.getInstance().tabContentPane= tabContentPane;
        PrimaryStage.getInstance().primaryHBox.getChildren().add(PrimaryStage.getInstance().tabContentPane);
    }
    static UtilityUIController getInstance(){
        if (utilityUIController==null){
            utilityUIController=new UtilityUIController();
        }
        return utilityUIController;
    }

    @Override
    public void login(String username) {
        SettingUIControllerAccess.settingUIControllerAccess.loadProfile(username);
        if(SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.Manager){
            PrimaryStage.getInstance().launchForGeneralManager();
            System.out.println("it's a general manager");
        }else if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()==Position.FinancialOfficer){
            PrimaryStage.getInstance().launchForFinancialStaff();
        }else {
            System.out.println("neither general manager nor financial staff");
        }
    }
    public void switchToAccountBookViewer(){
        SettingTabContentPane.getInstance().rightPane.getChildren().remove(SettingTabContentPane.getInstance().viewer);
        SettingTabContentPane.getInstance().viewer= AccountBookViewer.getInstance();
        SettingTabContentPane.getInstance().rightPane.getChildren().add(SettingTabContentPane.getInstance().viewer);
    }
    public void switchToUserViewer(){
        SettingTabContentPane.getInstance().rightPane.getChildren().remove(SettingTabContentPane.getInstance().viewer);
        SettingTabContentPane.getInstance().viewer= UserViewer.getInstance();
        SettingTabContentPane.getInstance().rightPane.getChildren().add(SettingTabContentPane.getInstance().viewer);
    }
    public void switchToLogViewer(){
        SettingTabContentPane.getInstance().rightPane.getChildren().remove(SettingTabContentPane.getInstance().viewer);
        SettingTabContentPane.getInstance().viewer= LogViewer.getInstance();
        SettingTabContentPane.getInstance().rightPane.getChildren().add(SettingTabContentPane.getInstance().viewer);

    }
    public void switchToBusinessHistoryViewer(){
        FileTabContentPane.getInstance().rightPane.getChildren().remove(FileTabContentPane.getInstance().viewer);
        FileTabContentPane.getInstance().viewer=BusinessHistoryViewer.getInstance();
        FileTabContentPane.getInstance().rightPane.getChildren().add(FileTabContentPane.getInstance().viewer);
    }
    public void switchToBusinessConditionsViewer(){
        FileTabContentPane.getInstance().rightPane.getChildren().remove(FileTabContentPane.getInstance().viewer);
        FileTabContentPane.getInstance().viewer=BusinessConditionsViewer.getInstance();
        FileTabContentPane.getInstance().rightPane.getChildren().add(FileTabContentPane.getInstance().viewer);
    }
    public void switchToSalesDetailsViewer(){
        FileTabContentPane.getInstance().rightPane.getChildren().remove(FileTabContentPane.getInstance().viewer);
        FileTabContentPane.getInstance().viewer=SalesDetailsViewer.getInstance();
        FileTabContentPane.getInstance().rightPane.getChildren().add(FileTabContentPane.getInstance().viewer);
    }

    @Override
    public FileType fromIdToType(String fileid) {
        String fronttwo=fileid.substring(0,2);
        System.out.println("fronttwo"+fronttwo);
        switch (fronttwo){
            case "sa":return FileType.SALE;
            case "sr":return FileType.SALERETURN;
            case "pc":return FileType.PURCHASE;
            case "rc":return FileType.RECEIPT;
            case "pm":return FileType.PAYMENT;
            case "pr":return FileType.PURCHASERETURN;
            case "rb":return FileType.REIMBURSEMENT;
            case "lv":return FileType.LEVEL;
            case "gf":return FileType.GIFT;
            case "vc":return FileType.VOUCHER;
            case "pp":return FileType.PRICEPACK;
            case "da":return FileType.DISCOUNT;
            case "ec":return FileType.EXCESS;
            case "ls":return FileType.LOSS;
        }
        return null;
    }

    public Viewer switchToFileViewer(String fileid){
        FileType fileType=fromIdToType(fileid);
        Viewer newviewer=null;
        System.out.println("filetype"+fileType);
        switch (fileType){
            case SALE:
                newviewer=new SalesOrderViewer();
                break;
            case SALERETURN:
                newviewer=new SalesReturnOrderViewer();
                break;

            case PURCHASE:
                newviewer=new PurchaseOrderViewer();
                break;
            case RECEIPT:
                newviewer=new ReceiptOrderViewer();
                break;
            case PAYMENT:
                newviewer=new PaymentOrderViewer();
                break;
            case PURCHASERETURN:
                newviewer=new PurchaseReturnOrderViewer();
                break;
            case REIMBURSEMENT:
                newviewer=new ReimbursementOrderViewer();
                break;
            case LEVEL:
                newviewer=new LevelStrategyViewer();
                break;
            case GIFT:
                newviewer=new GiftStrategyViewer();
                break;
            case VOUCHER:
                newviewer=new VoucherStrategyViewer();
                break;
            case PRICEPACK:
                newviewer=new PackageStrategyViewer();
                break;
            case DISCOUNT:
                newviewer=new DiscountStrategyViewer();
                break;
            case EXCESS:
                newviewer=new ExcessOrderViewer();
                break;
            case LOSS:
                newviewer=new LossOrderViewer();
                break;
        }
        if(PrimaryStage.getInstance().tabContentPane==FileTabContentPane.getInstance()){
            FileTabContentPane.getInstance().rightPane.getChildren().remove(FileTabContentPane.getInstance().viewer);
            FileTabContentPane.getInstance().viewer=newviewer;
            FileTabContentPane.getInstance().rightPane.getChildren().add(FileTabContentPane.getInstance().viewer);

        }
        if(PrimaryStage.getInstance().tabContentPane== MyFileTabContentPane.getInstance()){
            MyFileTabContentPane.getInstance().rightPane.getChildren().remove(MyFileTabContentPane.getInstance().viewer);
            MyFileTabContentPane.getInstance().viewer=newviewer;
            MyFileTabContentPane.getInstance().rightPane.getChildren().add(MyFileTabContentPane.getInstance().viewer);

        }


        return newviewer;

    }

    @Override
    public void switchToAccountViewer(String id) {
        AccountTabContentPane.getInstance().rightPane.getChildren().remove(AccountTabContentPane.getInstance().viewer);
        AccountTabContentPane.getInstance().viewer=new AccountViewer();
        AccountTabContentPane.getInstance().viewer.readOnly(id);
        AccountTabContentPane.getInstance().rightPane.getChildren().add(AccountTabContentPane.getInstance().viewer);
    }
}
