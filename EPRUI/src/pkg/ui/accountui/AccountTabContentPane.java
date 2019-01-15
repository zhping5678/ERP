package pkg.ui.accountui;

import javafx.scene.layout.VBox;
import pkg.unclassified.AccountViewer;
import pkg.unclassified.EmptyTitleBar;
import pkg.unclassified.TabContentPane;
import pkg.unclassified.Viewer;
import pkg.unclassified.*;
import pkg.ui.*;


public class AccountTabContentPane extends TabContentPane {
    public MiddlePaneForAccount middlePaneForAccount =MiddlePaneForAccount.getInstance();
    public VBox rightPane;
    private static AccountTabContentPane accountTabContentPane;
    public Viewer viewer;
    private AccountTabContentPane(){
        this.getChildren().add(middlePaneForAccount);
        rightPane=new VBox();

        viewer= new AccountViewer();
        if(middlePaneForAccount.listed.getItems().size()>0) {
            viewer.readOnly(middlePaneForAccount.listed.getItems().get(0).id);
        }

        System.out.println("new accountviewer;");

        rightPane.getChildren().add(new EmptyTitleBar());
        rightPane.getChildren().add(viewer);
        this.getChildren().add(rightPane);
    }
    public static AccountTabContentPane getInstance(){
        if(accountTabContentPane ==null){
            accountTabContentPane =new AccountTabContentPane();
        }
        return accountTabContentPane;

    }

}
