package pkg.unclassified;
import javafx.beans.property.BooleanProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountBookViewer extends Viewer {
    public BooleanProperty editable;
    public FileTitleComponent fileTitleComponent;
    public static AccountBookViewer accountBookViewer;
    public CreationInformationComponent creationInformationComponent;
    public SeparatorComponent separatorComponent;
    public SeparatorWithTitleComponent accountBooksListSeparatorWithTitleComponent;
    public LogsTableComponent logsTableComponent;
    public AccountBooksListComponent accountBooksList;


    public SpaceComponent spaceComponent;







    public AccountBookViewer() {

        //  positionChoiceBoxComponent.setDisable(true);
        fileTitleComponent = new FileTitleComponent("", "Account Books     ");
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        creationInformationComponent = new CreationInformationComponent("");
        separatorComponent = new SeparatorComponent();
        accountBooksListSeparatorWithTitleComponent = new SeparatorWithTitleComponent("ACCOUNT BOOKS LIST");
        logsTableComponent=new LogsTableComponent();
        accountBooksList=new AccountBooksListComponent();

        spaceComponent = new SpaceComponent();

        vBox.getChildren().addAll(fileTitleComponent
                , creationInformationComponent
                , separatorComponent
                , accountBooksListSeparatorWithTitleComponent
                ,accountBooksList

                , spaceComponent
        );
    }


    public static AccountBookViewer getInstance() {
        if (accountBookViewer == null) {
            accountBookViewer = new AccountBookViewer();
        }
        return accountBookViewer;
    }
}

