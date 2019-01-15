package pkg.unclassified;

import javafx.scene.layout.VBox;
import pkg.ui.myfileui.MyFileButton;

import java.util.ArrayList;

public abstract class LeftPane extends VBox {
    ReturnButton returnButton=new ReturnButton();
    MyFileButton myFileButton=new MyFileButton();
    ArrayList<TabButton> tabButtonArrayList=new ArrayList<>();
    TabButton currentTabButton;
    public static LeftPane leftPane;
    protected LeftPane(){
        this.setPrefWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setMinWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setMaxWidth(Parameters.LEFT_BUTTON_WIDTH);
        this.setStyle("-fx-background-color:"+Parameters.CSS_NJU_PURPLE+";");
        this.getChildren().addAll(returnButton);
        tabButtonArrayList.add(new MyFileButton());
        tabButtonArrayList.get(0).setSelected(true);
        setCurrentTabButton(tabButtonArrayList.get(0));
    }

    public void setCurrentTabButton(TabButton newCurrentTabButton){
        if(currentTabButton!=null) currentTabButton.setSelected(false);
        currentTabButton=newCurrentTabButton;
        currentTabButton.setSelected(true);

    }
    public TabButton getCurrentTabButton(){
        return currentTabButton;
    }
    public static LeftPane getInstance(){
        return leftPane;
    }

}
