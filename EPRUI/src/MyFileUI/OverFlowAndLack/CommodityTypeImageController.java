package MyFileUI.OverFlowAndLack;

import MockObject.Warehousebl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CommodityTypeImageController {
    @FXML Label namelCT;
    public void Clicked(MouseEvent mouseEvent) {
        Scenes s=new Scenes();

        //1.初始化Pane
        URL url=getClass().getResource("AddPaneOverflow.fxml");
        if(s.getState().equals("报溢单")){
            url=getClass().getResource("AddPaneOverflow.fxml");
        }
        else if(s.getState().equals("报损单")){
            url=getClass().getResource("AddPaneLack.fxml");
        }
        else if(s.getState().equals("销售单")){
            url=getClass().getResource("AddPaneSale.fxml");
        }
        else if(s.getState().equals("销售退货单")){
            url=getClass().getResource("AddPaneSaleBack.fxml");
        }
        else if(s.getState().equals("进货单")){
            url=getClass().getResource("AddPanePurchase.fxml");
        }
        else if(s.getState().equals("进货退货单")){
            url=getClass().getResource("AddPanePurchaseReturn.fxml");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Parent root = null;
        try {
            root=loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> fxmlNamespace = loader.getNamespace();
        Label label1= (Label) fxmlNamespace.get("lable1");
        FlowPane flowpane1= (FlowPane) fxmlNamespace.get("flowpane1");

        Warehousebl warehouse=new Warehousebl();
        if(warehouse.hasChildren(namelCT.getText())){
            String[] children=warehouse.getChildren(namelCT.getText());
            for(int i=0;i<children.length;i++){
                URL suburl=getClass().getResource("AddPaneOverflow.fxml");
                boolean isCommodity=warehouse.isCommodity(children[i]);
                if(isCommodity){
                    suburl=getClass().getResource("CommodityImage.fxml");
                }
                else{
                    suburl=getClass().getResource("CommodityTypeImage.fxml");
                }
                FXMLLoader subloader = new FXMLLoader(suburl);
                Parent subroot = null;
                try {
                    subroot=subloader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String, Object> subfxmlNamespace = subloader.getNamespace();
                if(isCommodity){
                    Label namelC= (Label) subfxmlNamespace.get("namelC");
                    namelC.setText(children[i]);
                }
                else{
                    Label namelCT= (Label) subfxmlNamespace.get("namelCT");
                    namelCT.setText(children[i]);
                }

                flowpane1.getChildren().add(subroot);
            }
        }

        //3.对界面进行保存

        s.addParent(root);
        s.addLoader(loader);
        s.addLocation(namelCT.getText());
        label1.setText("当前位置 "+s.getLocation());

        startChoose controller=new startChoose();
        controller.getHBox().getChildren().clear();
        controller.getHBox().getChildren().add(root);
        System.out.println(s.getLocation());
    }

    public void Entered(MouseEvent mouseEvent) {
        namelCT.setFont(Font.font(20));
    }

    public void Exited(MouseEvent mouseEvent) {
        namelCT.setFont(Font.font(18));
    }
}
