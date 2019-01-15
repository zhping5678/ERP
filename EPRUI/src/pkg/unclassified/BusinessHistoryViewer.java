package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BusinessHistoryViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    private static BusinessHistoryViewer businessHistoryViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public BusinessHistoryFilterComponent businessHistoryFilterComponent;
    public SeparatorWithTitleComponent screeningResultsSeparatorWithTitleComponent;
    public BusinessHistoryTableComponent businessHistoryTableComponent;
    public SpaceComponent spaceComponent;

    private BusinessHistoryViewer() {

        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("", "Business History");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("");
        separatorComponent = new SeparatorComponent();
        businessHistoryFilterComponent=new BusinessHistoryFilterComponent();
        screeningResultsSeparatorWithTitleComponent=new SeparatorWithTitleComponent("SCREENING RESULTS");
        spaceComponent = new SpaceComponent();
        businessHistoryTableComponent=new BusinessHistoryTableComponent();
        vBox.getChildren().addAll(fileTitleComponent
                ,creationInformationComponent
                ,separatorComponent
                ,businessHistoryFilterComponent
                ,screeningResultsSeparatorWithTitleComponent
                ,businessHistoryTableComponent
                ,spaceComponent
        );
    }


    public static BusinessHistoryViewer getInstance() {
        if (businessHistoryViewer == null) {
            businessHistoryViewer = new BusinessHistoryViewer();
        }
        return businessHistoryViewer;
    }
}

