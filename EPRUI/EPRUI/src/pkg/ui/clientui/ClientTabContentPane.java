package pkg.ui.clientui;

import javafx.scene.layout.VBox;
import pkg.unclassified.*;

public class ClientTabContentPane extends TabContentPane {
    public MiddlePaneForClient middlePaneForClient;
    public VBox rightPane;
    private static ClientTabContentPane clientTabContentPane;
    public Viewer viewer;
    private ClientTabContentPane(){
        this.getChildren().add(MiddlePaneForClient.getInstance());
        rightPane=new VBox();
        viewer=new PackageStrategyViewer(); /*FOR TEST*/
        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static  ClientTabContentPane getInstance(){
        if(clientTabContentPane ==null){
            clientTabContentPane =new ClientTabContentPane();
        }
        return clientTabContentPane;
    }
}
