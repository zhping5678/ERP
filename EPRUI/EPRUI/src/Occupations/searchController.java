package Occupations;

import Login.CurrentState;
import MockObject.Accountbl;
import MockObject.Clientbl;
import MockObject.Userbl;
import MockObject.Warehousebl;
import vo.uservo.Position;
import vo.uservo.UserVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class searchController {
    @FXML Button NewB;
    @FXML TextField SearchT;
    @FXML Button SearchB;

    public void typed(KeyEvent keyEvent) {
        String keyWords=SearchT.getText();
        Userbl bl=new Userbl();
        UserVO vo=bl.trace(CurrentState.getLoginID());
        ArrayList<String> array;
        if(vo.getPosition()== Position.Administrator){
            /*
            Warehousebl warehouse=new Warehousebl();
            array=warehouse.findByKeyWords(keyWords);
            */
        }
        else if(vo.getPosition()==Position.Salesman){
            Clientbl client=new Clientbl();
            array=client.findClient(keyWords);
        }
        else if(vo.getPosition()==Position.FinancialOfficer){
            Accountbl account=new Accountbl();
            array=account.findAccountsByKeywords(keyWords);
        }
        else{

        }



    }

    public void Clicked(MouseEvent mouseEvent) {
        SearchT.setStyle("-fx-background-color:#f2f2f2; -fx-background-radius:50;");
    }

    public void ClickedS(MouseEvent mouseEvent) {
    }

    public void Entered(MouseEvent mouseEvent) {
    }

    public void Exited(MouseEvent mouseEvent) {
    }

    public void ClickedA(MouseEvent mouseEvent) {
    }
}
