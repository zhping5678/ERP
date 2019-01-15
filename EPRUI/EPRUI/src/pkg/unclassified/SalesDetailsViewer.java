package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesDetailsViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    private static SalesDetailsViewer salesDetailsViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public SalesDetailsFilterComponent salesDetailsFilterComponent;
    public SeparatorWithTitleComponent screeningResultsSeparatorWithTitleComponent;
    public SalesDetailsTableComponent salesDetailsTableComponent;
    public SpaceComponent spaceComponent;

    private SalesDetailsViewer() {

        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("", "Sales Details");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("");
        separatorComponent = new SeparatorComponent();
        salesDetailsFilterComponent =new SalesDetailsFilterComponent();
        screeningResultsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("SCREENING RESULTS");
        spaceComponent = new SpaceComponent();
        salesDetailsTableComponent =new SalesDetailsTableComponent();
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                , salesDetailsFilterComponent
                ,screeningResultsSeparatorWithTitleComponent
                , salesDetailsTableComponent
                ,spaceComponent

        );
    }



    public static SalesDetailsViewer getInstance() {
        if (salesDetailsViewer == null) {
            salesDetailsViewer = new SalesDetailsViewer();
        }
        return salesDetailsViewer;
    }
}

