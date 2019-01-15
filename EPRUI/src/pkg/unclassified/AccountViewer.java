package pkg.unclassified;
import javafx.beans.property.BooleanProperty;
import pkg.ui.accountui.AccountUIControllerAccess;
import vo.accountvo.AccountVO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static AccountViewer accountViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public TextFieldComponent balanceTextFieldComponent;

    public SpaceComponent spaceComponent;




    public AccountViewer() {

        //  choiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("boc", "Bank of China");

        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("An account");
        separatorComponent = new SeparatorComponent();

        spaceComponent = new SpaceComponent();
        balanceTextFieldComponent =new TextFieldComponent("Balance");


        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , balanceTextFieldComponent
                , spaceComponent

        );


    }


    public static AccountViewer getInstance() {
        if (accountViewer == null) {
            accountViewer = new AccountViewer();
        }
        return accountViewer;
    }

    public void readOnly(String id){
        AccountVO accountVO= AccountUIControllerAccess.accountUIControllerAccess.getAccountVO(id);
        fileTitleComponent.purpleTitle.setEditable(false);
        fileTitleComponent.blackTitle.setEditable(false);
        balanceTextFieldComponent.textField.setEditable(false);
        fileTitleComponent.purpleTitle.setText(accountVO.getID());
        fileTitleComponent.blackTitle.setText(accountVO.getName());
        balanceTextFieldComponent.textField.setText(String.valueOf(accountVO.getMoney()));

    }
}


