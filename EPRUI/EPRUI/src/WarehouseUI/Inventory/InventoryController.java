package WarehouseUI.Inventory;

import MockObject.Warehousebl;
import Others.UISetting;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.FileSystem;
import java.util.ArrayList;


public class InventoryController {
    @FXML Label exportl;
    @FXML Label title;
    @FXML Label timel;


    public void Clicked(MouseEvent mouseEvent) {
        //1.弹出路径保存选择框
        FileChooser fileChooser=new FileChooser();
        FileSystemView fsv=FileSystemView.getFileSystemView();
        fileChooser.setInitialDirectory(fsv.getHomeDirectory());
        fileChooser.setTitle("选择EXCEL导出路径");
        String temp=timel.getText();
        temp=temp.substring(3,temp.indexOf(" "));
        String[] timetemp=temp.split("-");
        temp="";
        for(int i=0;i<timetemp.length;i++){
            temp+=timetemp[i];
        }
        String Title=temp+" "+title.getText()+"盘点结果";
        fileChooser.setInitialFileName(Title+".xls");
        File file=fileChooser.showSaveDialog(new Stage());
        String path=file.getAbsolutePath();
        int i=path.lastIndexOf("\\");
        path=path.substring(0,i);
        Warehousebl bl=new Warehousebl();
        String[] subtitlles={"商品名称","型号","库存数量","库存均价","批次","批号","出厂日期"};
        ArrayList<String[]> content=bl.makeInventory();
        ExportExcel export=new ExportExcel(Title,subtitlles,content,path);
        export.ExportExcel();
    }

    public void Entered(MouseEvent mouseEvent) {
        Font font=new Font("System 18px", UISetting.getPre_font_change_size());
        exportl.setFont(font);
    }

    public void Exitedd(MouseEvent mouseEvent) {
        Font font=new Font("System 18px", UISetting.getPre_font_size());
        exportl.setFont(font);
    }
}
