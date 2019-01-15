package WarehouseUI.Management;

import Login.CurrentState;
import MockObject.Warehousebl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import vo.utilityvo.ResultMessage;
import vo.warehousevo.WarehouseVO;

import java.rmi.Naming;

public class WarehouseController {
    @FXML TextField ID;
    @FXML TextField NAME;
    @FXML TextField id;
    @FXML TextField name;
    @FXML Label banOrRecover;
    @FXML Label saveOrModify;
    @FXML Label attention;
    public void IDChanged(KeyEvent keyEvent) {
        id.setText(ID.getText());
    }

    public void NAMEChanged(KeyEvent keyEvent) {
        name.setText(NAME.getText());
    }

    public void ClickedBan(MouseEvent mouseEvent) {
        if(saveOrModify.getText().equals("保 存")){
            if(banOrRecover.getText().equals("禁用该仓库")){
                banOrRecover.setText("重启该仓库");
            }
            else if(banOrRecover.getText().equals("重启该仓库")){
                banOrRecover.setText("禁用该仓库");
            }
            else{
                ;
            }
        }

    }

    public void Entered(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("该仓库")){
            banOrRecover.setFont(Font.font("system 18", FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("保 存")){
            saveOrModify.setFont(Font.font("system18",FontWeight.BOLD,18));
        }
        else if(mouseEvent.getPickResult().toString().contains("修 改")){
            saveOrModify.setFont(Font.font("system18",FontWeight.BOLD,18));
        }
        else{
            ;
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        banOrRecover.setFont(Font.font("system18",FontWeight.NORMAL,18));
        saveOrModify.setFont(Font.font("system18",FontWeight.NORMAL,18));
    }

    public void ClickedSave(MouseEvent mouseEvent) {
        if(saveOrModify.getText().equals("修 改")){
            Editable();
            saveOrModify.setText("保 存");
        }
        else if(saveOrModify.getText().equals("保 存")){
            Warehousebl bl=new Warehousebl();
            boolean isban=false;
            if(banOrRecover.getText().contains("禁用")){
                isban=false;
            }
            else{
                isban=true;
            }
            WarehouseVO vo=new WarehouseVO(id.getText(),name.getText(),isban);
            ResultMessage message=bl.newWarehouse(CurrentState.getLoginID(),vo);

            if(message==ResultMessage.exist){
                attention.setTextFill(Paint.valueOf("red"));
            }
            else if(message==ResultMessage.addSuccess){

            }
        }
    }

    public void Editable(){
        ID.setEditable(true);
        NAME.setEditable(true);
        id.setEditable(true);
        name.setEditable(true);
    }

    public void UnEditable(){
        ID.setEditable(false);
        NAME.setEditable(false);
        id.setEditable(false);
        name.setEditable(false);
    }
}
