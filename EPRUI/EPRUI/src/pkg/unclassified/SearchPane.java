package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class SearchPane extends AnchorPane{
    TextField searchField=new TextField();
    Button clearButton;
    public SearchPane(){
        this.getChildren().add(searchField);
        searchField.setPromptText("Search");
        searchField.setStyle("-fx-background-color: "+Parameters.CSS_MIDDLE_PANE_GRAY+";"
                +"-fx-background-radius: 0;"
                +"-fx-pref-height:"+Parameters.CSS_LEFT_BUTTON_HEIGHT+";"
                +"-fx-min-height:"+Parameters.CSS_LEFT_BUTTON_HEIGHT+";"
                +"-fx-max-height:"+Parameters.CSS_LEFT_BUTTON_HEIGHT+";"
                +"-fx-pref-width: "+Parameters.CSS_MIDDLE_PREF_WIDTH+";"
                +"-fx-min-width: "+Parameters.CSS_MIDDLE_PREF_WIDTH+";"
                +"-fx-max-width: "+Parameters.CSS_MIDDLE_MAX_WIDTH+";"
                +"-fx-border-color:transparent;"
                +"-fx-prompt-text-fill: "+Parameters.CSS_SEARCH_PROMPT+";"
                +"-fx-text-fill: black;"
                +"-fx-padding: 0 27 0 37;"
                +"-fx-background-image: url(/pkg/image/SearchIcon.png);"
                +"-fx-background-repeat: no-repeat;"
                +"-fx-background-size: 16;"
                +"-fx-background-position: 16 7;"
        );

        searchField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue)setActiveIcon();
                else setInactiveIcon();
            }
        });
    }

    public void setActiveIcon(){

        searchField.setStyle(searchField.getStyle()
                +"-fx-background-image: url(/pkg/image/SearchIconPurple.png);"
        );




    }
    public void setInactiveIcon(){
        searchField.setStyle(searchField.getStyle()
                +"-fx-background-image: url(/pkg/image/SearchIcon.png);"

        );

    }
}
