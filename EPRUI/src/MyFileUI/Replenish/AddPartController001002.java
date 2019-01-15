package MyFileUI.Replenish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.math.BigDecimal;

public class AddPartController001002 {
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML Label label6;
    @FXML TextField textfield1;
    @FXML TextField textfield2;


    public void NumberChanged(KeyEvent keyEvent) {
        String str1=textfield1.getText();
        String str2=label5.getText();
        if(str2.equals("")){
            str2=textfield2.getPromptText();
        }
        double number=Double.parseDouble(str1);
        double price=Double.parseDouble(str2);
        double result=number*price;
        BigDecimal b=new BigDecimal(result);
        double finalresult=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        String text=finalresult+"";
        label6.setText(text);
    }

}
