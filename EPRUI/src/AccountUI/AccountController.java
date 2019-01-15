package AccountUI;

import MockObject.Accountbl;
import Others.UserId;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class AccountController {
    @FXML TextField id;
    @FXML Label balance;
    @FXML Label ban;
    public void Finished(MouseEvent mouseEvent) {
        id.setStyle("-fx-background-color:#ffffff");
        balance.requestFocus();
    }


    public void ClickedI(MouseEvent mouseEvent) {
        id.setStyle("-fx-background-color:#f2f2f2; -fx-background-radius:50;");
    }

    public void Update(KeyEvent keyEvent) {
        Accountbl bl=new Accountbl();
        String id=this.id.getText();
        UserId user=new UserId();
        bl.modifyAccountID(user.getUserId(),MakeAccountList.getAccount(),id);
    }

    public void Clicked(MouseEvent mouseEvent) {
        Accountbl bl=new Accountbl();
        UserId user=new UserId();
        if(mouseEvent.getPickResult().toString().contains("禁用该账户")){
            bl.banAccount(user.getUserId(),id.getText());
            ban.setText("重启该账户");
        }
        else{
            bl.recoverAccount(user.getUserId(),id.getText());
            ban.setText("禁用该账户");
        }

    }

    public void Entered(MouseEvent mouseEvent) {
        Font font=new Font("System 18px",20);
        ban.setFont(font);
    }

    public void Exited(MouseEvent mouseEvent) {
        Font font=new Font("System 18px",18);
        ban.setFont(font);
    }
}
