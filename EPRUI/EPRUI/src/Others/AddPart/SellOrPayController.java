package Others.AddPart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SellOrPayController {
    @FXML Label close;
    @FXML Button newPay;
    @FXML Button newSell;
    @FXML Label attention;
    @FXML Label title;
    public void ClickedClose(MouseEvent mouseEvent) {
    }

    public void EnteredClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#660099"));
    }

    public void ExitedClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#f2f2f2"));
    }

    public void Entered(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("进")){
            newPay.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("销")){
            newSell.setFont(Font.font("system18",FontWeight.BOLD,18));
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        newPay.setFont(Font.font("system18",FontWeight.NORMAL,18));
        newSell.setFont(Font.font("system18",FontWeight.NORMAL,18));
    }

    public void ClickedSell(MouseEvent mouseEvent) {
    }

    public void ClickedPay(MouseEvent mouseEvent) {
    }
}
