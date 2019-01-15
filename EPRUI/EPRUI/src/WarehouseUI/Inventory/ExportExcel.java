package WarehouseUI.Inventory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportExcel {
    private String title;
    private String[] subtitles;
    private ArrayList<String[]> content;
    private String path;
    public ExportExcel(String title, String[] subtitles, ArrayList<String[]> content, String path){
        this.title=title;
        this.subtitles=subtitles;
        this.content=content;
        this.path=path;
    }

    public void ExportExcel(){
        try {
            WritableWorkbook wwb= Workbook.createWorkbook(new File(path,title+".xls"));
            WritableSheet sheet=wwb.createSheet("第一页",0);
            for(int i=0;i<subtitles.length;i++){
                Label label=new Label(i,0,subtitles[i]);
                try {
                    sheet.addCell(label);
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<content.size();i++){
                for(int j=1;j<content.get(i).length;j++){
                    Label label=new Label(j-1,i+1,content.get(i)[j]);
                    try {
                        sheet.addCell(label);
                    } catch (WriteException e) {
                        e.printStackTrace();
                    }
                }
            }
            wwb.write();

            try {
                wwb.close();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
