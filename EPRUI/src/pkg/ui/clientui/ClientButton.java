package pkg.ui.clientui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.LeftPane;
import pkg.unclassified.TabButton;

import pkg.unclassified.*;
public class ClientButton extends TabButton {
    public ClientButton(){
        super("Clients");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (available&&!selected){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToTabContentPane(ClientTabContentPane.getInstance());
                    LeftPane.getInstance().setCurrentTabButton((TabButton)event.getSource());
                    System.out.println("switch to client");
                }
            }
        });
    }

}
