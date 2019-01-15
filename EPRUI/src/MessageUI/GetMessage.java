package MessageUI;

import Login.CurrentState;
import MockObject.Messagebl;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import vo.messagevo.MessageVO;
import vo.uservo.Position;
import vo.utilityvo.ResultMessage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class GetMessage {
    private Position position;
    private HBox hb;
    private Label send;
    private Label recieve;

    public GetMessage(Position position,HBox hb){
        this.position=position;
        this.hb=hb;
    }

    public VBox getMidVBox(){
        VBox v=new VBox();
        v.getChildren().add(GetSendMessage());
        v.getChildren().add(GetRecieveMessage());
        return v;
    }

    public Label GetSendMessage(){
        send=new Label("    发送的信息");
        send.setStyle("-fx-background-color:#f2f2f2; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50;-fx-font-size:18;");
        send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                send.setStyle("-fx-background-color:#ffffff; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50;-fx-font-size:18;");
                recieve.setStyle("-fx-background-color:#f2f2f2; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50;-fx-font-size:18;");

                Messagebl bl=new Messagebl();
                ArrayList<MessageVO> array=bl.showMySendMessage(CurrentState.getLoginID());
                URL url=getClass().getResource("Send.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root=null;
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String,Object> fxmlnamespace=loader.getNamespace();
                VBox V= (VBox) fxmlnamespace.get("V");

                for(int i=0;i<array.size();i++){
                    URL suburl=getClass().getResource("part001.fxml");
                    if(i%2==0){
                        suburl=getClass().getResource("part001.fxml");
                    }
                    else{
                        suburl=getClass().getResource("part002.fxml");
                    }

                    FXMLLoader subloader=new FXMLLoader(suburl);
                    Parent subroot=null;
                    try {
                        subroot=subloader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Map<String,Object> subfxml=subloader.getNamespace();
                    Label message1= (Label) subfxml.get("message1");
                    Label message2= (Label) subfxml.get("message2");
                    Label close= (Label) subfxml.get("close");
                    Circle circle= (Circle) subfxml.get("circle");

                    MessageVO vo=array.get(i);
                    message1.setText(vo.getTime());
                    message2.setText(vo.getEvent());
                    Parent finalSubroot = subroot;
                    int finalI = i;
                    close.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(finalI %2==0){
                                circle.setStrokeWidth(1);
                            }
                            else{
                                circle.setStrokeWidth(1);
                            }
                        }
                    });
                    close.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(finalI %2==0){
                                circle.setStrokeWidth(0);
                            }
                            else{
                                circle.setStrokeWidth(0);
                            }
                        }
                    });
                    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            ResultMessage message;
                            message=bl.removeMessage(vo.getId(),CurrentState.getLoginID());
                            if(message==ResultMessage.delSuccess){
                                V.getChildren().remove(finalSubroot);
                                for(int i=0;i<V.getChildren().size();i++){
                                    if(i%2==0){
                                        V.getChildren().get(i).lookup("HBox").setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:860; -fx-pref-height:50; -fx-background-radius:50;");
                                        HBox h= (HBox) V.getChildren().get(i).lookup("HBox");
                                        Circle c= (Circle) h.lookup("Circle");
                                        c.setFill(Paint.valueOf("#f2f2f2"));

                                    }
                                    else{
                                        V.getChildren().get(i).lookup("HBox").setStyle("-fx-background-color:#ffffff; -fx-pref-width:860; -fx-pref-height:50; -fx-background-radius:50;");

                                        HBox h= (HBox) V.getChildren().get(i).lookup("HBox");
                                        Circle c= (Circle) h.lookup("Circle");
                                        c.setFill(Paint.valueOf("#ffffff"));
                                    }
                                    }
                            }
                        }
                    });
                    V.getChildren().add(subroot);
                }
                hb.getChildren().add(root);
        }



        });
        return send;
    }

    public Label GetRecieveMessage(){
        recieve=new Label("    接收的信息");
        recieve.setStyle("-fx-background-color:#f2f2f2; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18;");
        recieve.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hb.getChildren().clear();
                recieve.setStyle("-fx-background-color:#ffffff; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50;-fx-font-size:18;");
                send.setStyle("-fx-background-color:#f2f2f2; -fx-text-fill:black;-fx-pref-width:350; -fx-pref-height:50;-fx-font-size:18;");

                Messagebl bl=new Messagebl();
                ArrayList<MessageVO> array=bl.showMyReceiveMessage(CurrentState.getLoginID());
                URL url=getClass().getResource("Recieve.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root=null;
                try {
                    root=loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Map<String,Object> fxmlnamespace=loader.getNamespace();
                VBox V= (VBox) fxmlnamespace.get("V");

                for(int i=0;i<array.size();i++){
                    URL suburl=getClass().getResource("part001.fxml");
                    if(i%2==0){
                        suburl=getClass().getResource("part001.fxml");
                    }
                    else{
                        suburl=getClass().getResource("part002.fxml");
                    }

                    FXMLLoader subloader=new FXMLLoader(suburl);
                    Parent subroot=null;
                    try {
                        subroot=subloader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Map<String,Object> subfxml=subloader.getNamespace();
                    Label message1= (Label) subfxml.get("message1");
                    Label message2= (Label) subfxml.get("message2");
                    Label close= (Label) subfxml.get("close");
                    Circle circle= (Circle) subfxml.get("circle");

                    MessageVO vo=array.get(i);
                    message1.setText(vo.getTime());
                    message2.setText(vo.getEvent());
                    Parent finalSubroot = subroot;
                    int finalI = i;
                    close.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(finalI %2==0){
                                circle.setStrokeWidth(1);
                            }
                            else{
                                circle.setStrokeWidth(1);
                            }
                        }
                    });
                    close.setOnMouseExited(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if(finalI %2==0){
                                circle.setStrokeWidth(0);
                            }
                            else{
                                circle.setStrokeWidth(0);
                            }
                        }
                    });
                    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            ResultMessage message;
                            message=bl.removeMessage(vo.getId(),CurrentState.getLoginID());
                            if(message==ResultMessage.delSuccess){
                                V.getChildren().remove(finalSubroot);
                                for(int i=0;i<V.getChildren().size();i++){
                                    if(i%2==0){
                                        V.getChildren().get(i).lookup("HBox").setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:860; -fx-pref-height:50; -fx-background-radius:50;");
                                        HBox h= (HBox) V.getChildren().get(i).lookup("HBox");
                                        Circle c= (Circle) h.lookup("Circle");
                                        c.setFill(Paint.valueOf("#f2f2f2"));

                                    }
                                    else{
                                        V.getChildren().get(i).lookup("HBox").setStyle("-fx-background-color:#ffffff; -fx-pref-width:860; -fx-pref-height:50; -fx-background-radius:50;");

                                        HBox h= (HBox) V.getChildren().get(i).lookup("HBox");
                                        Circle c= (Circle) h.lookup("Circle");
                                        c.setFill(Paint.valueOf("#ffffff"));
                                    }
                                }
                            }
                        }
                    });
                    V.getChildren().add(subroot);
                }
                hb.getChildren().add(root);
            }



        });
        return recieve;
    }

}
