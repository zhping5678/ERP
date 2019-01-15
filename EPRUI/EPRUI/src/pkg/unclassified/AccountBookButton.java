package pkg.unclassified;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class AccountBookButton extends AnchorPane {
    boolean pressed;
    Label label;

    public AccountBookButton(String name) {

        pressed = false;
        this.setStyle(this.getStyle()
                + "-fx-background-image:url(/pkg/image/AccountBookGray.png);"
                + "-fx-pref-width: 100;"
                +"-fx-max-width:100;"
                + "-fx-pref-height: 124;"
                + "-fx-background-size:80;"
                + "-fx-background-position: 8 8;"
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


        label = new Label(name);
        label.setFocusTraversable(false);
        AnchorPane.setBottomAnchor(label,0.0);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle(label.getStyle()
                +"-fx-background-color:transparent;"
                + "-fx-max-width: 100;"
                +"-fx-pref-height:40;"
                +"-fx-border-color: transparent;"

        );
        label.setWrapText(true);
        this.getChildren().add(label);


    }

    public void onMouseEntered(){
        if (!pressed){
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AccountBookBlack.png);"


            );
        }

    }
    public void onMouseExited(){
        if(!pressed){
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AccountBookGray.png);"

            );
        }

    }
    public void onMouseClicked(){
        if (!pressed){
            pressed=true;
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AccountBookPurple.png);"

            );

        }else{
            pressed=false;
            this.setStyle(this.getStyle()
                    + "-fx-background-image:url(/pkg/image/AccountBookBlack.png);"

            );

        }

    }
}







