package MyFileUI.Sell;

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
    @FXML TextField textfield1;
    @FXML TextField textfield2;
    @FXML TextField textfield3;


    public void NumberChanged(KeyEvent keyEvent) {
        String str1=textfield1.getText();
        String str2=textfield2.getText();
        if(str2.equals("")){
            str2=textfield2.getPromptText();
        }
        double number=Double.parseDouble(str1);
        double price=Double.parseDouble(str2);
        double result=number*price;
        BigDecimal b=new BigDecimal(result);
        double finalresult=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        String text=finalresult+"";
        label5.setText(text);
    }

    public void PriceChanged(KeyEvent keyEvent) {
        String str1=textfield1.getText();
        if(str1.equals("")){
            str1=textfield1.getPromptText();
        }
        String str2=textfield2.getText();
        double number=Double.parseDouble(str1);
        double price=Double.parseDouble(str2);
        double result=number*price;
        BigDecimal b=new BigDecimal(result);
        double finalresult=b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        String text=finalresult+"";
        label5.setText(text);
    }
}
