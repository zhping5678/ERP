package WarehouseUI.Management;

import Login.CurrentState;
import MockObject.Warehousebl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.CommodityVO;

public class ProductController {
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
    @FXML public VBox VBoxL;
    @FXML public Label attention;
    @FXML public Label saveOrModify;

    public void ClickedT(MouseEvent mouseEvent) {
        if(saveOrModify.getText().equals("保存")){
            redo();
            TextField t= (TextField) mouseEvent.getSource();
            t.setStyle("-fx-background-color:rgb(242,242,242); -fx-background-radius:50; -fx-text-fill:rgb(102,0,153); -fx-font-size:18;");
        }
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
    }

    public void sameID(KeyEvent keyEvent) {
        String origin=CurrentProduct.getProductID();
        String result=origin.substring(0,origin.lastIndexOf("/")+1);
        text1.setText(result+ID.getText());
    }

    public void ClickedSave(MouseEvent mouseEvent) {
        if(saveOrModify.getText().equals("修改")){
            saveOrModify.setText("保存");
            Editable();
        }
        else{
            if(ID.getText().equals("")){
                attention.setText("商品ID不能为空");
                attention.setTextFill(Paint.valueOf("red"));
            }
            else{
                Warehousebl bl=new Warehousebl();
                CommodityVO vo=new CommodityVO(text1.getText(),NAME.getText(),text2.getText(),Integer.parseInt(text3.getText()),Double.parseDouble(text4.getText()),Double.parseDouble(text5.getText()),Double.parseDouble(text6.getText()),Double.parseDouble(text7.getText()),Integer.parseInt(text8.getText()),CurrentProduct.getIsBan());
                ResultMessage message=bl.updateCommodity(CurrentState.getLoginID(),CurrentProduct.getProductID(),vo);
                System.out.println(CurrentProduct.getProductID()+"AAAAAAAAAAAAAAAAAAAAAA"+vo.getID());
                if(message==ResultMessage.modiSuccess){
                    saveOrModify.setText("修改");
                    UnEditable();
                }
                else if(message==ResultMessage.exist){
                    attention.setText("该路径下已存在该商品ID");
                    attention.setTextFill(Paint.valueOf("red"));
                }
                else if(message==ResultMessage.Fail){
                    attention.setText("修改失败，原因未知");
                    attention.setTextFill(Paint.valueOf("red"));
                }
            }
        }




    }

    public void EnteredSave(MouseEvent mouseEvent) {
        saveOrModify.setFont(Font.font("system18", FontWeight.BOLD,18));
    }

    public void ExitedSave(MouseEvent mouseEvent) {
        saveOrModify.setFont(Font.font("system18",FontWeight.NORMAL,18));
    }

    public void Editable(){
        ID.setEditable(true);
        NAME.setEditable(true);
        text2.setEditable(true);
        text3.setEditable(true);
        text4.setEditable(true);
        text5.setEditable(true);
        text6.setEditable(true);
        text7.setEditable(true);
        text8.setEditable(true);
    }

    public void UnEditable(){
        ID.setEditable(false);
        NAME.setEditable(false);
        text2.setEditable(false);
        text3.setEditable(false);
        text4.setEditable(false);
        text5.setEditable(false);
        text6.setEditable(false);
        text7.setEditable(false);
        text8.setEditable(false);
    }
}
