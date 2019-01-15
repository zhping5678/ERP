package pkg.ui.myfileui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkg.ui.RemoteHelper;
import pkg.ui.settingui.SettingUIControllerAccess;
import pkg.unclassified.ItemText;
import vo.filevo.*;
import vo.uservo.Position;

import java.rmi.RemoteException;
import java.util.ArrayList;

class MyFileUIController implements MyFileUIControllerAccess{
    private static MyFileUIController myFileUIController;

    private MyFileUIController(){

    }
     static MyFileUIController getInstance(){
        if (myFileUIController==null){
            myFileUIController=new MyFileUIController();
        }
        return myFileUIController;
    }
    @Override
    public ObservableList<ItemText> getDraftList(){
        ArrayList<ItemText> draftArrayList=new ArrayList<>();

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.Manager){
            //   System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList =RemoteHelper.getStrategyBusinessLogicService().listDraftStrategy();
                for (int i=0;i<stringArrayList.size();i++){
                    draftArrayList.add(new ItemText(stringArrayList.get(i),stringArrayList.get(i),""));
                }
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.FinancialOfficer){
            //   System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList =RemoteHelper.getAccountManBusinessLogicService().showDraft();
                for (int i=0;i<stringArrayList.size();i++){
                    draftArrayList.add(new ItemText(stringArrayList.get(i),stringArrayList.get(i),""));
                }
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }


        ObservableList<ItemText> result= FXCollections.<ItemText>observableArrayList(draftArrayList);
        return result;





    }


    @Override
    public ObservableList<ItemText> getPendingList()
    {

        ArrayList<ItemText> draftArrayList=new ArrayList<>();

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.Manager){
            //   System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList =RemoteHelper.getManagerBusinessLogicService().showWaitReview();
                for (int i=0;i<stringArrayList.size();i++){
                    draftArrayList.add(new ItemText(stringArrayList.get(i),stringArrayList.get(i),""));
                }
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }



        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition()== Position.FinancialOfficer){
            //   System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList =RemoteHelper.getAccountManBusinessLogicService().showWaitReview();
                for (int i=0;i<stringArrayList.size();i++){
                    draftArrayList.add(new ItemText(stringArrayList.get(i),stringArrayList.get(i),""));
                }
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }


        ObservableList<ItemText> result= FXCollections.<ItemText>observableArrayList(draftArrayList);
        return result;
    }

    @Override
    public ObservableList<ItemText> getArchiveList() {
        ArrayList<ItemText> draftArrayList = new ArrayList<>();

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition() == Position.Manager) {
            // System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {

                RemoteHelper.getStrategyBusinessLogicService().connectionTest();

                stringArrayList = RemoteHelper.getStrategyBusinessLogicService().listOnUseStrategy();

                for (int i = 0; i < stringArrayList.size(); i++) {
                    draftArrayList.add(new ItemText(stringArrayList.get(i), stringArrayList.get(i), ""));
                }

                stringArrayList = RemoteHelper.getStrategyBusinessLogicService().listUsedStrategy();
                for (int i = 0; i < stringArrayList.size(); i++) {
                    draftArrayList.add(new ItemText(stringArrayList.get(i), stringArrayList.get(i), ""));
                }

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition() == Position.FinancialOfficer) {
            // System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList = RemoteHelper.getAccountManBusinessLogicService().showReviewed();
                for (int i = 0; i < stringArrayList.size(); i++) {
                    draftArrayList.add(new ItemText(stringArrayList.get(i), stringArrayList.get(i), ""));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }



        }
        ObservableList<ItemText> result = FXCollections.<ItemText>observableArrayList(draftArrayList);
        return result;

    }

    @Override
    public ObservableList<ItemText> getTrashList () {
        ArrayList<ItemText> draftArrayList = new ArrayList<>();

        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition() == Position.Manager) {
            // System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList = RemoteHelper.getStrategyBusinessLogicService().listTrashStrategy();
                for (int i = 0; i < stringArrayList.size(); i++) {
                    draftArrayList.add(new ItemText(stringArrayList.get(i), stringArrayList.get(i), ""));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }


        if (SettingUIControllerAccess.settingUIControllerAccess.getCurrentPosition() == Position.FinancialOfficer) {
            // System.out.println("its manager");
            ArrayList<String> stringArrayList;
            try {
                stringArrayList = RemoteHelper.getAccountManBusinessLogicService().showTrash();
                for (int i = 0; i < stringArrayList.size(); i++) {
                    draftArrayList.add(new ItemText(stringArrayList.get(i), stringArrayList.get(i), ""));
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        ObservableList<ItemText> result = FXCollections.<ItemText>observableArrayList(draftArrayList);
        return result;
    }

    @Override
    public DiscountStrategyVO getDiscount() {
        return null;
    }

    @Override
    public GiftStrategyVO getGift() {
        return null;
    }

    @Override
    public LevelStrategyVO getLevelVO() {
        return null;
    }

    @Override
    public PricePackStrategyVO getPricePack() {
        return null;
    }

    @Override
    public void saveDiscountVO(DiscountStrategyVO discountStrategyVO) {

    }

    @Override
    public void saveGiftStrategyVO(GiftStrategyVO giftStrategyVO) {

    }
    @Override
    public void saveLevelVO(LevelStrategyVO levelStrategyVO) {
    }
    @Override
    public void savePricePackVO(PricePackStrategyVO pricePackStrategyVO) {
    }

    @Override
    public void saveVoucherVO(VoucherStrategyVO voucherStrategyVO) {

    }

    @Override
    public void startStrategy(String id) {

    }

    @Override
    public void stopStrategy(String id) {

    }

    @Override
    public VoucherStrategyVO getVoucherVO() {
        return null;
    }
    public SaleVO getSalesVO(String id){
        SaleVO saleVO=null;
        try{
            saleVO=RemoteHelper.getManagerBusinessLogicService().showSale(id);
        }catch(RemoteException e){
            e.printStackTrace();
        }
        return saleVO;
    }

    @Override
    public PurchaseVO getPurchaseVO(String id) {
        PurchaseVO purchaseVO=null;
        try{
            System.out.println(id);
            purchaseVO=RemoteHelper.getManagerBusinessLogicService().showPurchase(id);


        }catch(RemoteException e){
            e.printStackTrace();
        }
        return purchaseVO;
    }
}
