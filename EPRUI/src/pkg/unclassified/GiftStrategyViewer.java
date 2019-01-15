package pkg.unclassified;

import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GiftStrategyViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static GiftStrategyViewer giftStrategyViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent giftsListSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent conditionsSeparatorWithTitleComponent;
    public CheckBoxesComponent checkBoxesComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public GiftsTableComponent giftsTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;
    public TextFieldComponent conditionalAmountTextFieldComponent;
    public TextFieldComponent startTimeTextFieldComponent;
    public TextFieldComponent stopTimeTextFieldComponent;
    public ButtonsComponent stopButtonComponent;
    public SpaceComponent spaceComponent;
    public GiftStrategyViewer(){
        fileTitleComponent=new FileTitleComponent("","Gift Strategy");
        conditionalAmountTextFieldComponent=new TextFieldComponent("Conditional Amount");
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent=new CreationInformationComponent("A gift strategy","paulwong",df.format(day));
        separatorComponent=new SeparatorComponent();
        giftsListSeparatorWithTitleComponent=new SeparatorWithTitleComponent("GIFTS LIST");
        conditionsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("CONDITIONS");
        othersSeparatorWithTitleComponent=new SeparatorWithTitleComponent("OTHERS");
        giftsTableComponent =new GiftsTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        startTimeTextFieldComponent=new TextFieldComponent("Start Time");
        stopTimeTextFieldComponent=new TextFieldComponent("Stop Time");
        stopButtonComponent=new ButtonsComponent("Stop");
        spaceComponent=new SpaceComponent();
        checkBoxesComponent=new CheckBoxesComponent();
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                ,conditionsSeparatorWithTitleComponent
                ,conditionalAmountTextFieldComponent
                ,checkBoxesComponent
                ,giftsListSeparatorWithTitleComponent
                ,giftsTableComponent
                ,othersSeparatorWithTitleComponent
                //,addCommodityButtonsComponent
                ,startTimeTextFieldComponent
                ,stopTimeTextFieldComponent
                ,stopButtonComponent
                ,spaceComponent
        );
    }


    public static GiftStrategyViewer getInstance(){
        if (giftStrategyViewer ==null){
            giftStrategyViewer =new GiftStrategyViewer();
        }
        return giftStrategyViewer;
    }

}
