package Occupations;

import AccountUI.MakeAccountList;
import Login.CurrentState;
import MyFileUI.MyFile;
import Others.CreateTitledPane;
import vo.uservo.Position;
import Others.AddPart.SolveAdd;
import Others.SolveSearch;
import SettingUI.GetSetting;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AccountManagerController {
    //1.这是最左侧AnchorPane中的所有Label
    public Label BackL;
    public Label MessageL;
    public Label MyFileL;
    public Label FileL;
    public Label AccountL;
    public Label SettingL;

    //2.这是中间AnchorPane中的所有组件
    public Button NewB;
    public TextField SearchT;
    public Button SearchB;
    public Button ChooseB;
    public AnchorPane PaneM;
    public VBox VBoxM;
    public VBox VBoxO;

    //3.这是右边AnchorPane中的所有组件
    public HBox HBoxR;

    //4.其他
    public String state="";
    private ListView<String> list;

    //1.以下对最左边的AnchorPane显示进行设置
    public void Entered(MouseEvent mouseEvent) {
        Font font=new Font("System 18px",20);
        String part=mouseEvent.getSource().toString();
        if(part.contains("BackL")){
            BackL.setFont(font);
        }
        else if(part.contains("MessageL")) {
            MessageL.setFont(font);
        }
        else if(part.contains("MyFileL")){
            MyFileL.setFont(font);
        }
        else if(part.contains("FileL")){
            FileL.setFont(font);
        }
        else if(part.contains("ClientL")){
            AccountL.setFont(font);
        }
        else if(part.contains("SettingL")){
            SettingL.setFont(font);
        }
    }

    public void Exited(MouseEvent mouseEvent) {
        Font font=new Font("System 18px", 18);
        String part=mouseEvent.getSource().toString();
        if(part.contains("BackL")){
            BackL.setFont(font);
        }
        else if(part.contains("MessageL")) {
            MessageL.setFont(font);
        }
        else if(part.contains("MyFileL")){
            MyFileL.setFont(font);
        }
        else if(part.contains("FileL")){
            FileL.setFont(font);
        }
        else if(part.contains("ClientL")){
            AccountL.setFont(font);
        }
        else if(part.contains("SettingL")){
            SettingL.setFont(font);
        }
    }

    public void Clicked(MouseEvent mouseEvent) {
        String part=mouseEvent.getSource().toString();
        if(part.contains("BackL")){
            reChoose();
        }
        else if(part.contains("MessageL")) {
            reChoose();
            MessageL.setStyle("-fx-background-color:rgb(242,242,242)");
            MessageL.setTextFill(Color.web("#660099"));
            state="Message";
        }
        else if(part.contains("MyFileL")){
            reChoose();
            MyFileL.setStyle("-fx-background-color:rgb(242,242,242)");
            MyFileL.setTextFill(Color.web("#660099"));
            state="MyFile";

            MyFile myfile=new MyFile(HBoxR);
            Accordion accordion=new Accordion();
            //TitledPane t1=new TitledPane("草稿",myfile.getDraft("AccountManager"));
            //TitledPane t2=new TitledPane("待审核",myfile.getWaitingCheckedFile("AccountManager"));
            TitledPane t3=new TitledPane("已审核",myfile.getCheckedFile(Position.FinancialOfficer));
            TitledPane t4=new TitledPane("回收站",myfile.getTrash(Position.FinancialOfficer));
            ///////////////////////
            CreateTitledPane c=new CreateTitledPane();
            TitledPane t1=c.getTitledPane("草稿",myfile.getDraft(Position.FinancialOfficer));
            TitledPane t2=c.getTitledPane("待审核",myfile.getWaitingCheckedFile(Position.FinancialOfficer));

            accordion.getPanes().add(t1);
            accordion.getPanes().add(t2);
            accordion.getPanes().add(t3);
            accordion.getPanes().add(t4);
            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("FileL")){
            reChoose();
            FileL.setStyle("-fx-background-color:rgb(242,242,242)");
            FileL.setTextFill(Color.web("#660099"));
            state="File";
        }
        else if(part.contains("AccountL")){
            reChoose();
            AccountL.setStyle("-fx-background-color:rgb(242,242,242)");
            AccountL.setTextFill(Color.web("#660099"));
            state="accountvo";

            MakeAccountList makelist=new MakeAccountList(HBoxR);
            Accordion accordion=new Accordion();
            list=makelist.getList();
            TitledPane t1=new TitledPane("账户管理",list);
            accordion.getPanes().add(t1);
            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("SettingL")){
            reChoose();
            SettingL.setStyle("-fx-background-color:rgb(242,242,242)");
            SettingL.setTextFill(Color.web("#660099"));
            state="Setting";

            GetSetting getSetting=new GetSetting(HBoxR,Position.FinancialOfficer);
            VBox v=getSetting.getMidVBox();
            VBoxM.getChildren().add(v);
        }
    }

    //该方法用于初始化清空M和R界面
    public void reChoose(){
        SearchT.setText("");
        VBoxO.requestFocus();
        VBoxO.getChildren().clear();
        VBoxO.getChildren().add(VBoxM);

        VBoxM.getChildren().clear();
        HBoxR.getChildren().clear();
        MessageL.setTextFill(Color.web("#f2f2f2"));
        MessageL.setStyle("-fx-background-color:rgb(102,0,153)");
        MyFileL.setTextFill(Color.web("#f2f2f2"));
        MyFileL.setStyle("-fx-background-color:rgb(102,0,153)");
        FileL.setTextFill(Color.web("#f2f2f2"));
        FileL.setStyle("-fx-background-color:rgb(102,0,153)");
        AccountL.setTextFill(Color.web("#f2f2f2"));
        AccountL.setStyle("-fx-background-color:rgb(102,0,153)");
        SettingL.setTextFill(Color.web("#f2f2f2"));
        SettingL.setStyle("-fx-background-color:rgb(102,0,153)");
    }

    public void typed(KeyEvent keyEvent) {
        VBoxO.getChildren().clear();
        String keywords=SearchT.getText();
        //若搜索框为空则返回原来状态
        if(keywords.equals("")){
            VBoxO.getChildren().add(VBoxM);
        }
        //否则显示搜索状态
        else{
            SolveSearch ss=new SolveSearch(HBoxR, CurrentState.getPosition(),state,keywords);
            ListView<String> list=ss.getList();
            if(list==null){
                VBoxO.getChildren().clear();
            }
            else{
                VBoxO.getChildren().clear();
                VBoxO.getChildren().add(list);
            }
        }
    }


    public void ClickedT(MouseEvent mouseEvent) {
    }

    public void ClickedS(MouseEvent mouseEvent) {
    }

    public void ClickedA(MouseEvent mouseEvent) {
        SolveAdd sa=new SolveAdd(Position.FinancialOfficer,state);
        sa.getAccountPane(list);
    }


}
