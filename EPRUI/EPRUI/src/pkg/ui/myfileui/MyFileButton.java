package pkg.ui.myfileui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.*;

public class MyFileButton extends TabButton {
    public MyFileButton(){
        super("My Files");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (available&&!selected){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToTabContentPane(MyFileTabContentPane.getInstance());
                    LeftPane.getInstance().setCurrentTabButton((TabButton)event.getSource());
                    System.out.println("switch to my file");
                }
            }
        });
    }

}
