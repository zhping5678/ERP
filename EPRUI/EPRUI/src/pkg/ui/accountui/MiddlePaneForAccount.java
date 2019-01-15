package pkg.ui.accountui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pkg.ui.RemoteHelper;
import pkg.ui.settingui.SettingUIControllerAccess;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.*;
import vo.accountvo.AccountVO;

import java.rmi.RemoteException;
import java.util.Optional;

public class MiddlePaneForAccount extends MiddlePane {
    public Listed listed=new Listed();
   // public ObservableList<ItemText> data= FXCollections.observableArrayList();
    private static MiddlePaneForAccount middlePaneForAccount;

    private MiddlePaneForAccount(){
        super(true);

        /*for test*/
        //listed.setItems(MyFileUIControllerAccess.myFileUIControllerAccess.getDraftList());
       // listed.setItems(ObservableList< ItemText>=FXCollections.observableArrayL);

       // data.add(new ItemText("wer","wer","wer"));


        //listed.setItems(data);


        ContextMenu accountMenu=new ContextMenu();
        MenuItem addMenuItem=new MenuItem("New Account");
        accountMenu.getItems().addAll(addMenuItem);
        addMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("New Account");
                dialog.setTitle("New Account");
                dialog.setHeaderText("");
                dialog.setContentText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    boolean same=false;


                    if (!same) {
                        System.out.println("Your name: " + result.get());
                        AccountVO accountVO = new AccountVO(result.get(), "", 0.0, false);

                        try {
                            RemoteHelper.getAccountBusinessLogicService().addAccount(SettingUIControllerAccess.settingUIControllerAccess.getCurrentUser(), accountVO);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        loadData();
                    }

                    selectnew(result.get());
                }
            }
        });


        loadData();
        VBox.setVgrow(listed, Priority.ALWAYS);

        listed.setFocusTraversable(false);
        setMiddleBelowPane(listed);
        listed.setStyle(listed.getStyle()
                +"-fx-background-color:"+Parameters.CSS_MIDDLE_PANE_GRAY+";"
                +"-fx-border-color: "+Parameters.CSS_MIDDLE_PANE_GRAY+";"
        );
        listed.setContextMenu(accountMenu);



        this.setStyle(this.getStyle()



        );
        listed.getSelectionModel().select(0);
        listed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemText>() {
            @Override
            public void changed(ObservableValue<? extends ItemText> observable, ItemText oldValue, ItemText newValue) {
                if( newValue!=null){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToAccountViewer(newValue.id);
                }

            }
        });






    }
    public static MiddlePaneForAccount getInstance(){
        if (middlePaneForAccount ==null){
            middlePaneForAccount =new MiddlePaneForAccount();
        }
        return middlePaneForAccount;

    }
    public void loadData(){
        listed.setItems(AccountUIControllerAccess.accountUIControllerAccess.getAccountList());



    }
    public void selectnew(String newid){
        for (int i=0;i<listed.getItems().size();i++){
            System.out.println(listed.getItems().get(i).id);
            if (listed.getItems().get(i).id.equals(newid)){
                System.out.println("false");
                listed.getSelectionModel().select(i);
                return;
            }
        }

    }
}
