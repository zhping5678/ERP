package MessageUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class partController {
    @FXML Label close;
    @FXML Label message1;
    @FXML Label message2;
    public void ClickedRemove(MouseEvent mouseEvent) {

    }

    public void Entered(MouseDragEvent mouseDragEvent) {
        close.setTextFill(Paint.valueOf("#660099"));
    }

    public void Exited(MouseDragEvent mouseDragEvent) {
        close.setFont(Font.font("system 18",FontWeight.NORMAL,18));
    }
}
