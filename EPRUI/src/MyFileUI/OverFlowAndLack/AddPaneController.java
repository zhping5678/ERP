package MyFileUI.OverFlowAndLack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;

public class AddPaneController {
    @FXML public TextField textfield1;
    @FXML public Label label;
    @FXML public Label back1;
    @FXML public FlowPane flowpane1;
    @FXML public AnchorPane FinalPane;

    public void BackClicked(MouseEvent mouseEvent) {
        Scenes s=new Scenes();
        s.removeLocation();
        startChoose controller=new startChoose();
        controller.getHBox().getChildren().clear();
        controller.getHBox().getChildren().add(s.getParent());
    }

    public void BackEntered(MouseEvent mouseEvent) {
        back1.setTextFill(Paint.valueOf("#660099"));
    }

    public void BackExited(MouseEvent mouseEvent) {
        back1.setTextFill(Paint.valueOf("f2f2f2"));
    }
}
