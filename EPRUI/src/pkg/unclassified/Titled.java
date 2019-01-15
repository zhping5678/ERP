package pkg.unclassified;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TitledPane;

public abstract class Titled extends TitledPane {
    public Titled(){

        this.getStylesheets().add("/pkg/stylesheet/Titled.css");
        this.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
              //  collapsible(!newValue);

            }
        });


    }
    private void collapsible(boolean collapsible){
        this.setCollapsible(collapsible);

    }



}
