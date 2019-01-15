package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LevelStrategyViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static LevelStrategyViewer levelStrategyViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent conditionalAmountsSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;

    public ButtonsComponent addCommodityButtonsComponent;
    public TextFieldComponent startTimeTextFieldComponent;
    public TextFieldComponent stopTimeTextFieldComponent;
    public ButtonsComponent stopButtonComponent;
    public SpaceComponent spaceComponent;
    public ArrayList<TextFieldComponent> textFieldComponentArrayList=new ArrayList<>();

    public LevelStrategyViewer() {
        fileTitleComponent = new FileTitleComponent("", "Level Strategy");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("A level strategy", "paulwong", df.format(day));
        separatorComponent = new SeparatorComponent();
        conditionalAmountsSeparatorWithTitleComponent = new SeparatorWithTitleComponent("CONDITIONAL AMOUNTS" );
        othersSeparatorWithTitleComponent = new SeparatorWithTitleComponent("OTHERS");
        addCommodityButtonsComponent = new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        startTimeTextFieldComponent = new TextFieldComponent("Start Time");
        stopTimeTextFieldComponent = new TextFieldComponent("Stop Time");
        stopButtonComponent = new ButtonsComponent("Stop");
        spaceComponent = new SpaceComponent();
        for (int i=0;i<5;i++){
            textFieldComponentArrayList.add(new TextFieldComponent("Level "+String.valueOf(i+1)));
        }
        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , conditionalAmountsSeparatorWithTitleComponent
        );
        vBox.getChildren().addAll(textFieldComponentArrayList);
        vBox.getChildren().addAll(
                 othersSeparatorWithTitleComponent
                //,addCommodityButtonsComponent
                , startTimeTextFieldComponent
                , stopTimeTextFieldComponent
                , stopButtonComponent
                , spaceComponent

        );
    }


    public static LevelStrategyViewer getInstance() {
        if (levelStrategyViewer == null) {
            levelStrategyViewer = new LevelStrategyViewer();
        }
        return levelStrategyViewer;
    }

    public void readOnly(String id){


    }
    public void save(){

    }
    public void readAndEdit(String id){

    }

}







