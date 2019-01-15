package SettingUI.MyInfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;


public class MyInfoController {
    @FXML Label id;
    @FXML Label name;
    @FXML Label state;
    @FXML Label position;
    @FXML Label ChangePassword;
    @FXML VBox VBoxL;
    public void Clicked(MouseEvent mouseEvent) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("Password.fxml"));
            HBox h=new HBox();
            h.getChildren().add(root);
            Temp.setHBox(h);
            VBoxL.getChildren().add(Temp.getHBox());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Entered(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("修改密码")){
            Font font=new Font("System 18px",20);
            ChangePassword.setFont(font);
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        Font font=new Font("System 18px",18);
        ChangePassword.setFont(font);
    }
}
