package pkg.unclassified;

import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PackageStrategyViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static PackageStrategyViewer packageStrategyViewer;

    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;

    public SeparatorWithTitleComponent commoditiesListSeparatorWithTitleComponent;
    public SeparatorWithTitleComponent conditionsSeparatorWithTitleComponent;
    public CheckBoxesComponent checkBoxesComponent;
    public SeparatorWithTitleComponent othersSeparatorWithTitleComponent;
    public CommoditiesTableComponent commoditiesTableComponent;
    public ButtonsComponent addCommodityButtonsComponent;
    public TextFieldComponent startTimeTextFieldComponent;
    public TextFieldComponent stopTimeTextFieldComponent;
    public ButtonsComponent stopButtonComponent;
    public SpaceComponent spaceComponent;
    public SeparatorWithTitleComponent summarySeparatorWithTitleComponent;
    public TextFieldComponent amountTextField;


    public PackageStrategyViewer(){
        fileTitleComponent=new FileTitleComponent("","Package Strategy");
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent=new CreationInformationComponent("A package strategy","paulwong",df.format(day));
        separatorComponent=new SeparatorComponent();
        commoditiesListSeparatorWithTitleComponent =new SeparatorWithTitleComponent("COMMODITIES LIST");

        conditionsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("CONDITIONS");
        othersSeparatorWithTitleComponent=new SeparatorWithTitleComponent("OTHERS");
        commoditiesTableComponent =new CommoditiesTableComponent();
        addCommodityButtonsComponent=new ButtonsComponent("Add");
        //   addCommodityButtonsComponent.buttonArrayList.get(0).setDisable(true);
        startTimeTextFieldComponent=new TextFieldComponent("Start Time");
        stopTimeTextFieldComponent=new TextFieldComponent("Stop Time");
        stopButtonComponent=new ButtonsComponent("Stop");
        spaceComponent=new SpaceComponent();
        checkBoxesComponent=new CheckBoxesComponent();
        summarySeparatorWithTitleComponent=new SeparatorWithTitleComponent("SUMMARY");
        amountTextField =new TextFieldComponent("Amount");
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                ,conditionsSeparatorWithTitleComponent
                ,checkBoxesComponent
                , commoditiesListSeparatorWithTitleComponent
                , commoditiesTableComponent
                //addCommodityButtonsComponent
                ,summarySeparatorWithTitleComponent
                ,amountTextField
                ,othersSeparatorWithTitleComponent
                ,startTimeTextFieldComponent
                ,stopTimeTextFieldComponent
                ,stopButtonComponent
                ,spaceComponent
        );
    }


    public static PackageStrategyViewer getInstance(){
        if (packageStrategyViewer ==null){
            packageStrategyViewer =new PackageStrategyViewer();
        }
        return packageStrategyViewer;
    }

}