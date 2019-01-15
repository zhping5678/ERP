package MyFileUI.Sell;

import MockObject.Warehousebl;
import MyFileUI.OverFlowAndLack.Scenes;
import MyFileUI.OverFlowAndLack.startChoose;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class SellPaneController {
    @FXML public Line line1;
    @FXML public Line line2;
    @FXML public Line line3;
    @FXML public Circle circle1;
    @FXML public Circle circle2;
    @FXML public Circle circle3;
    @FXML public Label SubmitL;


    public void Clicked1(MouseEvent mouseEvent) {
        Scenes s=new Scenes();
        //1.初始化AddPane
        URL url=getClass().getResource("AddPaneOverflow.fxml");
        if(s.getState().equals("销售单")){
            url=getClass().getResource("AddPaneOverflow.fxml");
        }
        else{
            url=getClass().getResource("AddPaneLack.fxml");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> fxmlNamespace = loader.getNamespace();
        FlowPane flowpane1= (FlowPane) fxmlNamespace.get("flowpane1");
        Label title= (Label) fxmlNamespace.get("title");
        Label label1= (Label) fxmlNamespace.get("lable1");

        Warehousebl warehouse=new Warehousebl();
        ArrayList<String> array=warehouse.getOnlyWarehouse();
        for(int i=0;i<array.size();i++){
            URL suburl=getClass().getResource("/MyFileUI/OverFlowAndLack/WarehouseImage.fxml");
            FXMLLoader subloader = new FXMLLoader(suburl);
            Parent subroot = null;
            try {
                subroot = subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> subfxmlNamespace = subloader.getNamespace();
            Label namel= (Label) subfxmlNamespace.get("namelW");
            namel.setText(array.get(i));
            flowpane1.getChildren().add(subroot);
        }

        //2.将现有的Pane加入到Scenes中去

        s.addParent(root);
        s.addLoader(loader);
        s.addLocation(title.getText());
        label1.setText("当前位置 "+s.getLocation());

        //3.设置界面的显示结果
        startChoose controller=new startChoose();
        controller.getHBox().getChildren().clear();
        controller.getHBox().getChildren().add(root);
    }

    public void Entered1(MouseEvent mouseEvent) {
        line1.setStroke(Paint.valueOf("#f2f2f2"));
        circle1.setFill(Paint.valueOf("#f2f2f2"));
    }

    public void Exited1(MouseEvent mouseEvent) {
        line1.setStroke(Paint.valueOf("#ffffff"));
        circle1.setFill(Paint.valueOf("#ffffff"));
    }

    public void Clicked2(MouseEvent mouseEvent) {
    }

    public void Entered2(MouseEvent mouseEvent) {
        line2.setStroke(Paint.valueOf("#f2f2f2"));
        circle2.setFill(Paint.valueOf("#f2f2f2"));
    }

    public void Exited2(MouseEvent mouseEvent) {
        line2.setStroke(Paint.valueOf("#ffffff"));
        circle2.setFill(Paint.valueOf("#ffffff"));
    }

    public void Clicked3(MouseEvent mouseEvent) {
    }

    public void Entered3(MouseEvent mouseEvent) {
        line3.setStroke(Paint.valueOf("#f2f2f2"));
        circle3.setFill(Paint.valueOf("#f2f2f2"));
    }

    public void Exited3(MouseEvent mouseEvent) {
        line3.setStroke(Paint.valueOf("#ffffff"));
        circle3.setFill(Paint.valueOf("#ffffff"));
    }

    public void Entered(MouseEvent mouseEvent) {
        SubmitL.setFont(Font.font(20));
    }

    public void Exited(MouseEvent mouseEvent) {
        SubmitL.setFont(Font.font(18));
    }
}
