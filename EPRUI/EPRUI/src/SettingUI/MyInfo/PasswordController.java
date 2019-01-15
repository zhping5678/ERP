package SettingUI.MyInfo;

import Login.CurrentState;
import MockObject.Userbl;
import vo.utilityvo.ResultMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;


public class PasswordController {
    @FXML TextField origin;
    @FXML TextField newpass;
    @FXML TextField assure;
    @FXML Button change;
    @FXML Label attention;
    @FXML HBox originV;
    @FXML HBox passV1;
    @FXML HBox passV2;

    public void Back(MouseEvent mouseEvent) {
        redo();
        attention.requestFocus();
    }

    public void Clicked1(MouseEvent mouseEvent) {
        redo();
        originV.setStyle("-fx-border-color:#660099; -fx-border-width:2;");
    }

    public void Clicked2(MouseEvent mouseEvent) {
        redo();
        passV1.setStyle("-fx-border-color:#660099; -fx-border-width:2;");
    }

    public void Clicked3(MouseEvent mouseEvent) {
        redo();
        passV2.setStyle("-fx-border-color:#660099; -fx-border-width:2;");
    }

    public void ClickedChange(MouseEvent mouseEvent) {
        Userbl bl=new Userbl();
        if(bl.login(CurrentState.getLoginID(),origin.getText())== ResultMessage.loginSuccess){
            if(newpass.getText().equals(assure.getText())){
                if(bl.reset(CurrentState.getLoginID(),CurrentState.getLoginID(),origin.getText(),newpass.getText(),CurrentState.getName(),assure.getText())==ResultMessage.modiSuccess){
                    attention.setTextFill(Paint.valueOf("#660099"));
                    attention.setText("修改成功");
                    Temp.getHBox().getChildren().clear();
                }
            }
            else{
                attention.setText("前后两次输入的新密码不一致");
                attention.setTextFill(Paint.valueOf("red"));
            }
        }
        else{
            attention.setText("原始密码输入错误");
            attention.setTextFill(Paint.valueOf("red"));
        }
    }

    public void redo(){
        originV.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        passV1.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        passV2.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
    }
}
