package pkg.unclassified;

import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public abstract class MiddlePane extends VBox {
    public SearchPane searchPane;
    public Region middleBelowPane;
    public MiddlePane(Boolean hasSearchPane){
        this.setPrefWidth(Parameters.MIDDLE_PREF_WIDTH);
        this.setMinWidth(Parameters.MIDDLE_PREF_WIDTH);
        this.setMaxWidth(Parameters.MIDDLE_MAX_WIDTH);
        if(hasSearchPane){
            searchPane=new SearchPane();
            this.getChildren().add(searchPane);
        }
        setStyle("-fx-background-color: "+Parameters.CSS_MIDDLE_PANE_GRAY+";"
                +"-fx-border-color: null"

        );
    }

    public void setMiddleBelowPane(Region middleBelowPane) {
        this.middleBelowPane = middleBelowPane;
        this.getChildren().add(middleBelowPane);
        VBox.setVgrow(middleBelowPane,Priority.ALWAYS);
    }


}
