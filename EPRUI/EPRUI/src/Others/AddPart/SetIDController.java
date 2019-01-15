package Others.AddPart;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SetIDController {
    @FXML Label close;
    @FXML TextField id;
    @FXML Button confirm;
    @FXML Button cancel;
    @FXML Label attention;
    @FXML Label title;
    public void ClickedClose(MouseEvent mouseEvent) {
        /*
        if(title.getText().contains("分")){
            NewCommodityType.getStage().close();
            NewCommodityType.setIsSuccess(false);
            NewCommodityType.setIsOver(true);
        }
        else{
            NewCommodity.getStage().close();
            NewCommodity.setIsSuccess(false);
            NewCommodity.setIsOver(true);
        }
        */
    }

    public void EnteredClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#660099"));
    }

    public void ExitedClose(MouseEvent mouseEvent) {
        close.setTextFill(Paint.valueOf("#f2f2f2"));
    }

    public void ClickedConfirm(MouseEvent mouseEvent) {
        /*
        if(id.getText().equals("")){
            attention.setTextFill(Paint.valueOf("red"));
        }
        else{
            Warehousebl warehousevo=new Warehousebl();
            String path="";
            if(title.getText().contains("分")){
                path=NewCommodityType.getPath();
                if(warehousevo.newCommodityType(path+"/"+id.getText())== ResultMessage.addSuccess){
                    NewCommodityType.setIsSuccess(true);
                    NewCommodityType.setIsOver(true);
                    NewCommodityType.setName(id.getText());
                    NewCommodityType.getStage().close();
                }
                else{
                    attention.setText("该目录下已存在相同ID的商品分类");
                    attention.setTextFill(Paint.valueOf("red"));
                }
            }
            else{
                path=NewCommodity.getPath();
                if(warehousevo.newCommodity(path+"/"+id.getText())==ResultMessage.addSuccess){
                    NewCommodity.setIsSuccess(true);
                    NewCommodity.setIsOver(true);
                    NewCommodity.setName(id.getText());
                    NewCommodity.getStage().close();
                }
                else{
                    attention.setText("该目录下已存在相同ID的商品分类");
                    attention.setTextFill(Paint.valueOf("red"));
                }
            }



        }
        */
    }

    public void Entered(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("确 定")){
            confirm.setFont(Font.font("system18",FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("取 消")){
            cancel.setFont(Font.font("system18",FontWeight.BOLD,18));
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        confirm.setFont(Font.font("system18",FontWeight.NORMAL,18));
        cancel.setFont(Font.font("system18",FontWeight.NORMAL,18));
    }

    public void ClickedCancel(MouseEvent mouseEvent) {
        /*
        if(title.getText().contains("分")){
            NewCommodityType.getStage().close();
            NewCommodityType.setIsSuccess(false);
            NewCommodityType.setIsOver(true);
        }
        else{
            NewCommodity.getStage().close();
            NewCommodity.setIsSuccess(false);
            NewCommodity.setIsOver(true);
        }
        */
    }
}
