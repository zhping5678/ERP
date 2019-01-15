package pkg.ui.accountui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.LeftPane;
import pkg.unclassified.TabButton;

public class AccountButton extends TabButton {
    public AccountButton(){
        super("Accounts");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (available&&!selected){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToTabContentPane(AccountTabContentPane.getInstance());
                    LeftPane.getInstance().setCurrentTabButton((TabButton)event.getSource());
                    System.out.println("switch to account");
                }
            }
        });
    }
}
