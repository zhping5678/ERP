package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class TabButton extends LeftButton {
    public TabButton(String buttonName){
        super(buttonName);
        this.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //      System.out.println("clicked");
                if (available&&!selected){
                    setSelected(!selected);
                }

            }
        }));


    }
}
