package pkg.ui.fileui;

import javafx.scene.layout.VBox;
import pkg.unclassified.*;

public class FileTabContentPane extends TabContentPane {
    public MiddlePaneForFile middlePaneForFile=MiddlePaneForFile.getInstance();
    public VBox rightPane;
    private static FileTabContentPane fileTabContentPane;
    public Viewer viewer;
    private FileTabContentPane(){
        this.getChildren().add(middlePaneForFile);
        rightPane=new VBox();
        viewer= BusinessConditionsViewer.getInstance(); /*FOR TEST*/


        viewer= BusinessConditionsViewer.getInstance();

        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static FileTabContentPane getInstance(){
        if(fileTabContentPane ==null){
            fileTabContentPane =new FileTabContentPane();
        }
        return fileTabContentPane;

    }
}
