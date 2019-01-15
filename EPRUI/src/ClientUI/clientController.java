package ClientUI;

import Login.CurrentState;
import MockObject.Clientbl;
import Start.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vo.clientvo.ClientIdentity;
import vo.clientvo.ClientVO;
import vo.utilityvo.ResultMessage;

import java.util.Currency;

public class clientController {
    @FXML public TextField ID;
    @FXML public TextField NAME;
    @FXML public TextField text1;
    @FXML public TextField text2;
    @FXML public TextField text3;
    @FXML public TextField text4;
    @FXML public TextField text5;
    @FXML public TextField text6;
    @FXML public TextField text7;
    @FXML public TextField text8;
    @FXML public TextField text9;
    @FXML public TextField text10;
    @FXML public TextField text11;
    @FXML public TextField text12;
    @FXML public TextField text13;
    @FXML public TextField text14;
    @FXML public VBox VBoxL;
    @FXML public Label saveOrModify;
    @FXML public Label banOrRecover;
    @FXML public Label attention1;
    @FXML public Label attention2;

    public void ClickedT(MouseEvent mouseEvent) {
        redo();
        TextField t= (TextField) mouseEvent.getSource();
        t.setStyle("-fx-background-color:rgb(242,242,242); -fx-background-radius:50; -fx-text-fill:rgb(102,0,153); -fx-font-size:18;");
    }

    public void ClickedP(MouseEvent mouseEvent) {
        VBoxL.requestFocus();
        redo();
    }

    public void redo(){
        text1.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text2.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text3.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text4.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text5.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text6.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text7.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text8.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text9.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text10.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text11.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text12.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text13.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        text14.setStyle("-fx-background-color:rgb(255,255,255); -fx-background-radius:50; -fx-text-fill:rgb(0,0,0); -fx-font-size:18;");
        attention1.setTextFill(Paint.valueOf("ffffff"));
        attention2.setTextFill(Paint.valueOf("ffffff"));
    }

    String oldID;
    public void ClickedSave(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("修改")){
            //点击修改
            saveOrModify.setText("保存");
            oldID=ID.getText();
            Editable();
        }
        else{
            //点击保存
            Clientbl bl=new Clientbl();
            if(ID.getText().equals("")){
                attention1.setText("客户ID/客户编号 为必填项");
                attention1.setTextFill(Paint.valueOf("red"));
            }
            else{
                //判断进货/销售
                ClientIdentity client=null;
                if(text2.getText().equals("进货商")){
                    client=ClientIdentity.Supplier;
                }
                else if(text2.getText().equals("销售商")){
                    client=ClientIdentity.Seller;
                }

                //判断是否被禁用
                Boolean isban=false;
                if(banOrRecover.getText().equals("禁用该客户")){
                    isban=false;
                }
                else if(banOrRecover.getText().equals("启用该客户")){
                    isban=true;
                }

                double quota=0;
                if(text11.getText().equals("")){
                    quota=0;
                }
                else {
                    quota=Double.parseDouble(text11.getText());
                }

                //进行修改更新
                ClientVO vo=new ClientVO(text1.getText(),text3.getText(),client,Integer.parseInt(text7.getText()),text5.getText(),text4.getText(),text12.getText(),text6.getText(),text14.getText(),text10.getText(),quota,Double.parseDouble(text9.getText()),Double.parseDouble(text8.getText()),Double.parseDouble(text13.getText()),isban);
                ResultMessage message=bl.modifyClient(CurrentState.getLoginID(),oldID,vo);
                if(message== ResultMessage.exist){
                    redo();
                    attention1.setText("该客户ID已存在");
                    attention1.setTextFill(Paint.valueOf("red"));
                }
                else if(message==ResultMessage.Fail){
                    redo();
                    attention1.setText("修改客户失败");
                    attention1.setTextFill(Paint.valueOf("red"));
                }
                else if(message==ResultMessage.modiSuccess){
                    redo();
                    attention1.setText("您已成功修改客户");
                    attention1.setTextFill(Paint.valueOf("#660099"));
                    UnEditable();
                    saveOrModify.setText("修改");
                }

            }
        }


    }

    public void UnEditable(){
        ID.setEditable(false);
        NAME.setEditable(false);
        text1.setEditable(false);
        text2.setEditable(false);
        text3.setEditable(false);
        text4.setEditable(false);
        text5.setEditable(false);
        text6.setEditable(false);
        text7.setEditable(false);
        text8.setEditable(false);
        text9.setEditable(false);
        text10.setEditable(false);
        text11.setEditable(false);
        text12.setEditable(false);
        text13.setEditable(false);
        text14.setEditable(false);
    }

    public void Editable(){
        ID.setEditable(true);
        NAME.setEditable(true);
        text1.setEditable(true);
        //text2.setEditable(false);
        text3.setEditable(true);
        text4.setEditable(true);
        text5.setEditable(true);
        text6.setEditable(true);
        text7.setEditable(true);
        //text8.setEditable(false);
        //text9.setEditable(false);
        if(CurrentState.getRight()){
            text10.setEditable(true);
        }
        else{
            text10.setEditable(false);
        }
        //text10.setEditable(false);
        text11.setEditable(true);
        text12.setEditable(true);
        text13.setEditable(true);
        text14.setEditable(true);
    }

    public void EnteredSave(MouseEvent mouseEvent) {
        saveOrModify.setFont(Font.font("system 18", FontWeight.BOLD,18));
    }

    public void ExitedSave(MouseEvent mouseEvent) {
        saveOrModify.setFont(Font.font("system 18",FontWeight.NORMAL,18));
    }

    public void keepSameWithTitle(KeyEvent keyEvent) {
        ID.setText(text1.getText());
    }

    public void keepSameWithItem(KeyEvent keyEvent) {
        text1.setText(ID.getText());
    }

    public void ClickedBan(MouseEvent mouseEvent) {
        if(saveOrModify.getText().equals("修改")){
            ;
        }
        else{
            if(banOrRecover.getText().equals("禁用该客户")){
                banOrRecover.setText("启用该客户");
            }
            else{
                banOrRecover.setText("禁用该客户");
            }
        }
    }

    public void EnteredBan(MouseEvent mouseEvent) {
        banOrRecover.setFont(Font.font("system 18",FontWeight.BOLD,18));
    }

    public void ExitedBan(MouseEvent mouseEvent) {
        banOrRecover.setFont(Font.font("system 18",FontWeight.NORMAL,18));
    }
}
