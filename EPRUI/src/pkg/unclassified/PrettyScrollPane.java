package pkg.unclassified;

import javafx.scene.control.ScrollPane;

public class PrettyScrollPane extends ScrollPane {

    public PrettyScrollPane() {
        super();

        this.getStylesheets().add("/pkg/stylesheet/PrettyScrollPane.css");

        this.setFitToWidth(true);
    }
}