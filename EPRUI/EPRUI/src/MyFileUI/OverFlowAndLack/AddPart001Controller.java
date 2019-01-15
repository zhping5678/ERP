package MyFileUI.OverFlowAndLack;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

public class AddPart001Controller {
    @FXML Label label5;
    @FXML TextField textfield1;
    @FXML Label label7;


    public void Typed(KeyEvent keyEvent) {
        String number=textfield1.getText();
        Scenes s=new Scenes();
        if(!number.equals("")){
            s.updateAmountSum(Integer.parseInt(number));
            if(s.getState().equals("报溢单")){
                label7.setText(Integer.parseInt(label5.getText())+Integer.parseInt(number)+"");
            }
            else{
                label7.setText(Integer.parseInt(label5.getText())-Integer.parseInt(number)+"");
            }
        }
        else{
            label7.setText(label5.getText());
        }

        FXMLLoader loader=s.getLoader();
        Map<String, Object> fxmlNamespace = loader.getNamespace();
        VBox V= (VBox) fxmlNamespace.get("V");
        Label AmountSum= (Label) fxmlNamespace.get("AmountSum");
        Label MoneySum= (Label) fxmlNamespace.get("MoneySum");
        //ObservableList<Node> list=V.getChildren();


    }
}
