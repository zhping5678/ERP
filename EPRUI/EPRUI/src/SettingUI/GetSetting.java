package SettingUI;


import Login.CurrentState;
import MockObject.Userbl;
import vo.uservo.Position;
import SettingUI.Log.CreateLog;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import vo.uservo.UserVO;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class GetSetting {
    private HBox hb;
    private Position p;
    private Label info=new Label("    我的信息");
    private Label initial=new Label("    期初建账");
    private Label log=new Label("    日志管理");
    public GetSetting(HBox hb,Position p){
        this.hb=hb;
        this.p=p;
    }

    public VBox getMidVBox(){
        VBox v=new VBox();
        v.setStyle("-fx-background-color:#f2f2f2");
        v.setPrefHeight(500);
        getLog();
        getInfo();
        getInitial();

        v.getChildren().add(info);
        if(p==Position.Manager){
            v.getChildren().add(log);
        }
        if(p==Position.FinancialOfficer){
            v.getChildren().add(initial);
            v.getChildren().add(log);
        }

        return v;
    }

    public Label getInfo(){
        info.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
        info.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                info.setStyle("-fx-background-color:#ffffff; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                initial.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                log.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");

                hb.getChildren().clear();

                URL url=getClass().getResource("MyInfo/MyInfo.fxml");
                FXMLLoader loader=new FXMLLoader(url);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Map<String, Object> fxmlnamespace=loader.getNamespace();
                Label name= (Label) fxmlnamespace.get("name");
                Label position= (Label) fxmlnamespace.get("position");
                Label id= (Label) fxmlnamespace.get("id");
                Userbl bl=new Userbl();
                UserVO vo=bl.trace(CurrentState.getLoginID());
                name.setText(vo.getName());
                position.setText(vo.getPosition()+"");
                id.setText(vo.getID());

                hb.getChildren().add(root);
            }
        });
        return info;
    }

    public Label getInitial() {
        initial.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
        initial.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                initial.setStyle("-fx-background-color:#ffffff; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                info.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                log.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");

                hb.getChildren().clear();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("MyInfo/MyInfo.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                hb.getChildren().add(root);
            }
        });
        return initial;
    }

    public Label getLog() {
        log.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
        log.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                log.setStyle("-fx-background-color:#ffffff; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                initial.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");
                info.setStyle("-fx-background-color:#f2f2f2; -fx-pref-width:350; -fx-pref-height:50; -fx-font-size:18; -fx-text-fill:black;");

                hb.getChildren().clear();
                CreateLog create=new CreateLog();
                Parent root = create.getPane();
                hb.getChildren().add(root);
            }
        });
        return log;
    }

    public void redo(){
        //info.setStyle();
    }
}
