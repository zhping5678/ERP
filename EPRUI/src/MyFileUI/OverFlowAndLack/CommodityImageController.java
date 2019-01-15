package MyFileUI.OverFlowAndLack;

import MockObject.Warehousebl;
import javafx.scene.control.TextField;
import vo.warehousevo.CommodityVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class CommodityImageController {
    @FXML Label namelC;
    @FXML ImageView photo;
    public void Clicked(MouseEvent mouseEvent) {
        Scenes s=new Scenes();

        //1.找到该商品的具体信息
        Warehousebl warehouse=new Warehousebl();
        CommodityVO vo=warehouse.getCommodity(namelC.getText());

        //2.找到最初界面的所有对象
        s.pruductChosen();
        FXMLLoader loader=s.getLoader();
        Map<String, Object> fxmlNamespace = loader.getNamespace();
        Label title= (Label) fxmlNamespace.get("title");
        VBox V=null;
        System.out.println(loader);
        if(s.getState().equals("报溢单")){
            title.setText("报溢单");
            V= (VBox) fxmlNamespace.get("V");
        }
        else if(s.getState().equals("报损单")){
            title.setText("报损单");
            V= (VBox) fxmlNamespace.get("V");
        }
        else if(s.getState().equals("销售单")){
            //title.setText("销售单");
            V= (VBox) fxmlNamespace.get("V1");
        }
        else if(s.getState().equals("销售退货单")){
            //title.setText("销售退货单");
            V= (VBox) fxmlNamespace.get("V1");
        }
        else if(s.getState().equals("进货单")){
            V= (VBox) fxmlNamespace.get("V1");
        }
        else if(s.getState().equals("进货退货单")){
            V= (VBox) fxmlNamespace.get("V1");
        }

        //3.初始化条目对象
        if(s.getState().equals("报溢单")||s.getState().equals("报损单")){
            URL url=getClass().getResource("AddPaneOverflow.fxml");
            if(s.getNumber()%2==0){
                url=getClass().getResource("AddPart001.fxml");
            }
            else{
                url=getClass().getResource("AddPart002.fxml");
            }
            FXMLLoader subloader = new FXMLLoader(url);
            Parent root = null;
            try {
                root=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> subfxmlNamespace = subloader.getNamespace();
            Label label1= (Label) subfxmlNamespace.get("label1");
            Label label2= (Label) subfxmlNamespace.get("label2");
            Label label3= (Label) subfxmlNamespace.get("label3");
            Label label4= (Label) subfxmlNamespace.get("label4");
            Label label5= (Label) subfxmlNamespace.get("label5");
            Label label7= (Label) subfxmlNamespace.get("label7");

            label1.setText((s.getNumber()+1)+"");
            label2.setText(vo.getID());
            label3.setText(vo.getName());
            label4.setText(vo.getSize());
            label5.setText(vo.getAmountOfInventory()+"");
            label7.setText(vo.getAmountOfInventory()+"");
            V.getChildren().add(root);
        }

        else if(s.getState().equals("销售单")||s.getState().equals("销售退货单")||s.getState().equals("进货单")||s.getState().equals("进货退货单")){
            URL url=getClass().getResource("AddPaneOverflow.fxml");
            if(s.getNumber()%2==0){
                url=getClass().getResource("/MyFileUI/Sell/AddPart001.fxml");
            }
            else{
                url=getClass().getResource("/MyFileUI/Sell/AddPart002.fxml");
            }

            FXMLLoader subloader = new FXMLLoader(url);
            Parent root = null;
            try {
                root=subloader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Map<String, Object> subfxmlNamespace = subloader.getNamespace();
            Label label1= (Label) subfxmlNamespace.get("label1");
            Label label2= (Label) subfxmlNamespace.get("label2");
            Label label3= (Label) subfxmlNamespace.get("label3");
            Label label4= (Label) subfxmlNamespace.get("label4");
            Label label5= (Label) subfxmlNamespace.get("label5");
            TextField text1= (TextField) subfxmlNamespace.get("textfield1");
            TextField text2= (TextField) subfxmlNamespace.get("textfield2");
            TextField text3= (TextField) subfxmlNamespace.get("textfield3");

            label1.setText((s.getNumber()+1)+"");
            label2.setText(vo.getID());
            label3.setText(vo.getName());
            label4.setText(vo.getSize());
            label5.setText("0");

            V.getChildren().add(root);
        }

        s.setNumber();
        startChoose controller=new startChoose();
        controller.getHBox().getChildren().clear();
        controller.getHBox().getChildren().add(s.getParent());
    }

    public void Entered(MouseEvent mouseEvent) {
        namelC.setFont(Font.font(20));
    }

    public void Exited(MouseEvent mouseEvent) {
        namelC.setFont(Font.font(18));
    }
}
