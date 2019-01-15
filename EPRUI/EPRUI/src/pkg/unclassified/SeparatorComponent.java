package pkg.unclassified;

import javafx.scene.control.Separator;

public class SeparatorComponent extends Separator {
    public SeparatorComponent(){
        this.setStyle(
                this.getStyle()
               +"-fx-padding:0 25 15 25;"

        );
        this.getStylesheets().add("/pkg/stylesheet/SeparatorComponent.css");
    }
}
