package pkg.ui.myfileui;

import javafx.scene.layout.VBox;
import pkg.unclassified.*;

public class MyFileTabContentPane extends TabContentPane {
    public MiddlePaneForMyFile middlePaneForMyFile;
    public VBox rightPane;
    private static MyFileTabContentPane myFileTabContentPane;
    public Viewer viewer;
    private MyFileTabContentPane(){
        this.getChildren().add(MiddlePaneForMyFile.getInstance());
        rightPane=new VBox();
       // rightPane.prefHeightProperty().bind(PrimaryStage.getInstance().heightProperty().subtract(Parameters.LEFT_BUTTON_HEIGHT));
        this.setStyle(this.getStyle()
     //           +"-fx-border-color:#5272ff;"

        );
        rightPane.setStyle(rightPane.getStyle()
      //          +"-fx-border-color:pink;"
      //          +"-fx-background-color: red;"
        );



        viewer=new PackageStrategyViewer(); /*FOR TEST*/
        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static MyFileTabContentPane getInstance(){
        System.out.println("getinstanceof myfiletab");
        if(myFileTabContentPane ==null){
            myFileTabContentPane =new MyFileTabContentPane();
        }
        return myFileTabContentPane;
    }
}
