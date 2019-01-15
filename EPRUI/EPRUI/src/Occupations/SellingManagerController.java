package Occupations;

import ClientUI.MakeClientList;
import Login.CurrentState;
import MessageUI.GetMessage;
import MyFileUI.MyFile;
import Others.AddPart.SolveAdd;
import Others.CreateTitledPane;
import SettingUI.GetSetting;
import javafx.geometry.Pos;
import vo.uservo.Position;
import Others.SolveSearch;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SellingManagerController {
    //1.这是最左侧AnchorPane中的所有Label
    public Label BackL;
    public Label MessageL;
    public Label MyFileL;
    public Label ClientL;
    public Label SettingL;

    //2.这是中间AnchorPane中的所有组件
    public Button NewB;
    public TextField SearchT;
    public Button SearchB;
    public AnchorPane PaneM;
    public VBox VBoxM;
    public VBox VBoxO;

    //3.这是右边AnchorPane中的所有组件
    public HBox HBoxR;

    //4.其他
    private String state="";


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
        else if(part.contains("ClientL")){
            ClientL.setFont(font);
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
        else if(part.contains("ClientL")){
            ClientL.setFont(font);
        }
        else if(part.contains("SettingL")){
            SettingL.setFont(font);
        }
    }

    private ListView<String> pay;
    private ListView<String> sell;
    private ListView<String> draft;
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
            GetMessage gm=new GetMessage(Position.Salesman,HBoxR);
            VBoxM.getChildren().add(gm.getMidVBox());
        }
        else if(part.contains("MyFileL")){
            reChoose();
            MyFileL.setStyle("-fx-background-color:rgb(242,242,242)");
            MyFileL.setTextFill(Color.web("#660099"));
            state="MyFile";

            MyFile myfile=new MyFile(HBoxR);
            CreateTitledPane create=new CreateTitledPane();
            draft=myfile.getDraft(Position.Salesman);
            TitledPane t1=create.getTitledPane("草稿",myfile.getDraft(Position.Salesman));;
            TitledPane t2=create.getTitledPane("待审核",myfile.getWaitingCheckedFile(Position.Salesman));
            TitledPane t3=create.getTitledPane("已审核",myfile.getCheckedFile(Position.Salesman));
            TitledPane t4=create.getTitledPane("回收站",myfile.getTrash(Position.Salesman));
            Accordion accordion=new Accordion();
            accordion.getPanes().add(t1);
            accordion.getPanes().add(t2);
            accordion.getPanes().add(t3);
            accordion.getPanes().add(t4);
            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("ClientL")){
            reChoose();
            ClientL.setStyle("-fx-background-color:rgb(242,242,242)");
            ClientL.setTextFill(Color.web("#660099"));
            state="Client";

            MakeClientList selllist=new MakeClientList(HBoxR, true);
            MakeClientList paylist=new MakeClientList(HBoxR,false);
            Accordion accordion=new Accordion();
            pay=paylist.getList();
            sell=paylist.getList();
            CreateTitledPane create=new CreateTitledPane();
            TitledPane t1=create.getTitledPane("进货商",pay);
            TitledPane t2=create.getTitledPane("销售商",sell);
            accordion.getPanes().add(t1);
            accordion.getPanes().add(t2);
            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("SettingL")){
            reChoose();
            SettingL.setStyle("-fx-background-color:rgb(242,242,242)");
            SettingL.setTextFill(Color.web("#660099"));
            state="Setting";

            GetSetting getSetting=new GetSetting(HBoxR,Position.Salesman);
            VBox v=getSetting.getMidVBox();
            VBoxM.getChildren().add(v);
        }
    }

    //该方法用于初始化清空M和R界面
    public void reChoose(){
        VBoxM.getChildren().clear();
        HBoxR.getChildren().clear();
        MessageL.setTextFill(Color.web("#f2f2f2"));
        MessageL.setStyle("-fx-background-color:rgb(102,0,153)");
        MyFileL.setTextFill(Color.web("#f2f2f2"));
        MyFileL.setStyle("-fx-background-color:rgb(102,0,153)");
        ClientL.setTextFill(Color.web("#f2f2f2"));
        ClientL.setStyle("-fx-background-color:rgb(102,0,153)");
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

    public void ClickedA(MouseEvent mouseEvent) {
        if(state.equals("Client")){
            SolveAdd sa=new SolveAdd(Position.Salesman,state);
            sa.getClientPane(pay,sell,HBoxR);
        }
        else if(state.equals("MyFile")){
            SolveAdd sa=new SolveAdd(Position.Salesman,state);
            sa.getMyFilePane(draft,HBoxR);
        }


    }



    public void ClickedT(MouseEvent mouseEvent) {
    }

    public void ClickedS(MouseEvent mouseEvent) {
    }
}
