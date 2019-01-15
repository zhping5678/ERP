package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static LogViewer logViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public SeparatorWithTitleComponent logsListSeparatorWithTitleComponent;
    public LogsTableComponent logsTableComponent;


    public SpaceComponent spaceComponent;







    public LogViewer() {

        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("", "Logs  ");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("");
        separatorComponent = new SeparatorComponent();
        logsListSeparatorWithTitleComponent = new SeparatorWithTitleComponent("LOGS LIST");
        logsTableComponent=new LogsTableComponent();

        spaceComponent = new SpaceComponent();

        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , logsListSeparatorWithTitleComponent
                ,logsTableComponent
                , spaceComponent

        );
    }


    public static LogViewer getInstance() {
        if (logViewer == null) {
            logViewer = new LogViewer();
        }
        return logViewer;
    }
}

