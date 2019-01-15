package pkg.ui.fileui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import pkg.unclassified.*;

public class FileButton extends TabButton {
    public FileButton(){
        super("Files");
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (available&&!selected){
                    UtilityUIControllerAccess.utilityUIControllerAccess.switchToTabContentPane(FileTabContentPane.getInstance());
                    LeftPane.getInstance().setCurrentTabButton((TabButton)event.getSource());
                }
            }
        });
    }
}
