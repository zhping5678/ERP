package Occupations;

import Login.CurrentState;
import MessageUI.GetMessage;
import MockObject.Warehousebl;
import MyFileUI.MyFile;
import Others.AddPart.SolveAdd;
import Others.CreateTitledPane;
import SettingUI.GetSetting;
import vo.uservo.Position;
import Others.SolveSearch;
import WarehouseUI.Inventory.MakeInventoryList;
import WarehouseUI.View.MakeViewList;
import WarehouseUI.Management.MakeWarehouseTree;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import vo.warehousevo.WarehouseVO;

import java.util.ArrayList;

public class WarehouseManagerController {
    //1.这是最左侧AnchorPane中的所有Label
    public Label BackL;
    public Label MessageL;
    public Label MyFileL;
    public Label WarehouseL;
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
        else if(part.contains("WarehouseL")){
            WarehouseL.setFont(font);
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
        else if(part.contains("WarehouseL")){
            WarehouseL.setFont(font);
        }
        else if(part.contains("SettingL")){
            SettingL.setFont(font);
        }
    }

    Accordion accordion;
    ListView<String> draft;
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

            GetMessage gm=new GetMessage(Position.Warehouseman,HBoxR);
            VBox v=gm.getMidVBox();
            VBoxM.getChildren().add(v);
        }
        else if(part.contains("MyFileL")){
            reChoose();
            MyFileL.setStyle("-fx-background-color:rgb(242,242,242)");
            MyFileL.setTextFill(Color.web("#660099"));
            state="MyFile";

            MyFile myfile=new MyFile(HBoxR);
            Accordion accordion=new Accordion();

            draft=myfile.getDraft(Position.Warehouseman);
            CreateTitledPane create=new CreateTitledPane();
            TitledPane t1=create.getTitledPane("草稿",draft);
            TitledPane t2=create.getTitledPane("待审核",myfile.getWaitingCheckedFile(Position.Warehouseman));
            TitledPane t3=create.getTitledPane("已审核",myfile.getCheckedFile(Position.Warehouseman));
            TitledPane t4=create.getTitledPane("回收站",myfile.getTrash(Position.Warehouseman));

            accordion.getPanes().add(t1);
            accordion.getPanes().add(t2);
            accordion.getPanes().add(t3);
            accordion.getPanes().add(t4);
            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("WarehouseL")){
            reChoose();
            WarehouseL.setStyle("-fx-background-color:rgb(242,242,242)");
            WarehouseL.setTextFill(Color.web("#660099"));
            state="Warehouse";

            CreateTitledPane create=new CreateTitledPane();
            //1.库存查看
            accordion=new Accordion();
            MakeViewList viewlist=new MakeViewList(HBoxR);
            //TitledPane viewt=new TitledPane("库存查看",viewlist.getList());
            TitledPane viewt=create.getTitledPane("库存查看",viewlist.getList());
            accordion.getPanes().add(viewt);

            //2.库存盘点
            MakeInventoryList inventorylist=new MakeInventoryList(HBoxR);
            //TitledPane inventoryt=new TitledPane("库存盘点",inventorylist.getList());
            TitledPane inventoryt=create.getTitledPane("库存盘点",inventorylist.getList());
            accordion.getPanes().add(inventoryt);

            //3.加入库存
            Warehousebl warehouse=new Warehousebl();
            ArrayList<String> ware=warehouse.getOnlyWarehouse();
            if(ware!=null){
                for(int i=0;i<ware.size();i++){
                    MakeWarehouseTree makeTree=new MakeWarehouseTree(ware.get(i),HBoxR);
                    //TitledPane t=new TitledPane(ware.get(i),makeTree.getTree());
                    TitledPane t=create.getTitledPane(ware.get(i),makeTree.getTree());
                    accordion.getPanes().add(t);
                }
            }

            VBoxM.getChildren().add(accordion);
        }
        else if(part.contains("SettingL")) {
            reChoose();
            SettingL.setStyle("-fx-background-color:rgb(242,242,242)");
            SettingL.setTextFill(Color.web("#660099"));
            state="Setting";

            GetSetting gs=new GetSetting(HBoxR,Position.Warehouseman);
            VBoxM.getChildren().add(gs.getMidVBox());
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
        WarehouseL.setTextFill(Color.web("#f2f2f2"));
        WarehouseL.setStyle("-fx-background-color:rgb(102,0,153)");
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
        SolveAdd sa=new SolveAdd(Position.Warehouseman,state);
        if(state.equals("MyFile")){
            sa.getMyFilePane(draft,HBoxR);
        }
        else if(state=="Warehouse"){
            sa.getWarehosuePane(accordion,HBoxR);
        }
    }

    public void ClickedS(MouseEvent mouseEvent) {
    }
}
