package Others.AddPart;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FileController {
    @FXML Label close;
    @FXML Label newReplenish;
    @FXML Label newReplenishBack;
    @FXML Label newSell;
    @FXML Label newSellBack;
    @FXML Label newPay;
    @FXML Label newPayBack;

    public void ClickedClose(MouseEvent mouseEvent) {
    }

    public void EnteredClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#660099"));
    }

    public void ExitedClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#f2f2f2"));
    }

    private int temp=0;
    public void Entered(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("新 建 报 溢 单")){
            temp=1;
            newReplenish.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("新 建 报 损 单")){
            temp=1;
            newReplenishBack.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("新 建 销 售 单")){
            temp=2;
            newSell.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("新 建 进 货 单")){
            temp=2;
            newPay.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("销 售 退 货 单")){
            temp=2;
            newSellBack.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("进 货 退 货 单")){
            temp=2;
            newPayBack.setFont(Font.font("system18", FontWeight.BOLD,18));
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        if(temp==1){
            newReplenish.setFont(Font.font("system18", FontWeight.NORMAL,18));
            newReplenishBack.setFont(Font.font("system18", FontWeight.NORMAL,18));
        }
        else if(temp==2){
            newSell.setFont(Font.font("system18", FontWeight.NORMAL,18));
            newSellBack.setFont(Font.font("system18", FontWeight.NORMAL,18));
            newPay.setFont(Font.font("system18", FontWeight.NORMAL,18));
            newPayBack.setFont(Font.font("system18", FontWeight.NORMAL,18));
        }
    }
}
