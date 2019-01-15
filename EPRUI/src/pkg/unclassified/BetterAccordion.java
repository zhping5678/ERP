package pkg.unclassified;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class BetterAccordion extends Accordion {
    public BetterAccordion(){
        this.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
            @Override public void changed(ObservableValue<? extends TitledPane> property, final TitledPane oldPane, final TitledPane newPane) {
                if (oldPane != null) oldPane.setCollapsible(true);
                if (newPane != null) Platform.runLater(new Runnable() { @Override public void run() {
                    newPane.setCollapsible(false);
                }});
            }
        });
    }
}
