package MyFileUI.OverFlowAndLack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;


public class startChoose {
    private static HBox hb;
    public static HBox getHBox() {
        return hb;
    }

    public void go(FXMLLoader loader,Parent root, String overflowOrLack){
        hb=new HBox();
        Scenes s=new Scenes();
        s.clear();
        s.addLoader(loader);
        s.addParent(root);
        s.setState(overflowOrLack);
        hb.getChildren().add(root);
    }

}
