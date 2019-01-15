package SettingUI.MyInfo;

import javafx.scene.layout.HBox;

public class Temp {
    private static HBox hb;
    public static HBox getHBox(){
        return  hb;
    }
    public static void setHBox(HBox h){
        hb=h;
    }
}
