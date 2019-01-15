package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AddAccountBookButton extends AnchorPane {
    boolean pressed;


    public AddAccountBookButton() {

        pressed = false;
        this.setStyle(this.getStyle()
                + "-fx-background-image:url(/pkg/image/AddGray.png);"
                + "-fx-pref-width: 100;"
                +"-fx-max-width:100;"
                + "-fx-pref-height: 124;"
                + "-fx-background-size:60;"
                + "-fx-background-position: 20 20;"
                + "-fx-background-repeat: no-repeat;"
                +"-fx-border-color: transparent;"
                +"-fx-background-radius: 7;"
                +"-fx-background-color: white;"
        );
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onMouseEntered();
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onMouseExited();
            }
        });
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onMouseClicked();
            }
        });


    }

    public void onMouseEntered(){
        if (!pressed){
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AddBlack.png);"


            );
        }

    }
    public void onMouseExited(){
        if(!pressed){
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AddGray.png);"

            );
        }

    }
    public void onMouseClicked(){


    }
}






