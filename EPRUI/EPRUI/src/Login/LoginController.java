package Login;

import MockObject.Userbl;
import Occupations.AccountManagerMain;
import Occupations.ManagerMain;
import Occupations.SellingManagerMain;
import Occupations.WarehouseManagerMain;
import pkg.ui.utilityui.UtilityUIControllerAccess;
import vo.uservo.Position;
import vo.uservo.UserVO;
import vo.utilityvo.ResultMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginController {
    //0.设置窗体关闭和最小化
    @FXML Button closeB;
    @FXML Button smallB;
    @FXML Button attention;
    @FXML Button forget;
    @FXML Button login;
    public void CloseLogin(MouseEvent mouseEvent) {
        main.getStage().close();
    }

    public void SmallLogin(MouseEvent mouseEvent) {
        main.getStage().setIconified(true);
    }


    public void EnteredB(MouseEvent mouseEvent) {
        if(mouseEvent.getPickResult().toString().contains("—")){
            smallB.setTextFill(Paint.valueOf("#660099"));
        }
        else if(mouseEvent.getPickResult().toString().contains("忘记密码")){
            forget.setTextFill(Paint.valueOf("#660099"));
        }
        else if(mouseEvent.getPickResult().toString().contains("X")){
            closeB.setTextFill(Paint.valueOf("#660099"));
        }
    }

    public void ExitedB(MouseEvent mouseEvent) {
        smallB.setTextFill(Paint.valueOf("#f2f2f2"));
        closeB.setTextFill(Paint.valueOf("#f2f2f2"));
        forget.setTextFill(Paint.valueOf("#000000"));
    }

    //1.设置账号和密码框选中效果
    @FXML HBox nameV;
    @FXML HBox passwordV;


    public void ClickedT1(MouseEvent mouseEvent) {
        passwordV.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        nameV.setStyle("-fx-border-color:#660099; -fx-border-width:2;");
    }

    public void ClickedT2(MouseEvent mouseEvent) {
        nameV.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        passwordV.setStyle("-fx-border-color:#660099; -fx-border-width:2;");
    }

    //还原所有效果
    public void Back(MouseEvent mouseEvent) {
        nameV.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        passwordV.setStyle("-fx-border-color:#f2f2f2; -fx-border-width:2;");
        nameV.requestFocus();
    }


    public void forget(MouseEvent mouseEvent) {
    }


    public void ClickedLogin(MouseEvent mouseEvent) {
        ArrayList<String> array=getInfo();
        Userbl user=new Userbl();
        if(user.login(array.get(0),array.get(1))== ResultMessage.loginSuccess){
            UserVO vo=user.trace(array.get(0));
            if(vo.getPosition()== Position.Warehouseman){
                WarehouseManagerMain Main=new WarehouseManagerMain();
                Main.start(new Stage());
                CurrentState.setLoginID(array.get(0));
                CurrentState.setPosition(vo.getPosition());
                CurrentState.setName(vo.getName());
                CurrentState.setRight(vo.getRight());
                main.getStage().close();
            }
            else if(vo.getPosition()==Position.Salesman){
                SellingManagerMain Main=new SellingManagerMain();
                Main.start(new Stage());
                CurrentState.setLoginID(array.get(0));
                CurrentState.setPosition(vo.getPosition());
                CurrentState.setName(vo.getName());
                CurrentState.setRight(vo.getRight());
                main.getStage().close();
            }
            else if(vo.getPosition()==Position.FinancialOfficer){
                /*
                AccountManagerMain Main=new AccountManagerMain();
                Main.start(new Stage());
                CurrentState.setLoginID(array.get(0));
                CurrentState.setPosition(vo.getPosition());
                CurrentState.setName(vo.getName());
                CurrentState.setRight(vo.getRight());
                */
                UtilityUIControllerAccess.utilityUIControllerAccess.login(nameT.getText());
                main.getStage().close();

            }
            else{
                /*
                ManagerMain Main=new ManagerMain();
                Main.start(new Stage());
                CurrentState.setLoginID(array.get(0));
                CurrentState.setPosition(vo.getPosition());
                CurrentState.setName(vo.getName());
                CurrentState.setRight(vo.getRight());
                */
                UtilityUIControllerAccess.utilityUIControllerAccess.login(nameT.getText());
                main.getStage().close();
            }
        }
        else{
            attention.setText("账户与密码不匹配");
            attention.setTextFill(Paint.valueOf("red"));
        }
    }

    public void ClickedRegister(MouseEvent mouseEvent) {
    }


    //得到面板所有信息
    @FXML TextField nameT;
    @FXML TextField passwordT;
    public ArrayList<String> getInfo(){
        ArrayList<String> array=new ArrayList<>();
        String name=nameT.getText();
        String password=passwordT.getText();
        array.add(name);
        array.add(password);
        return array;
    }
}
