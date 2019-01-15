package pkg.unclassified;

import javafx.scene.layout.HBox;


public class SpaceComponent extends HBox {


    public SpaceComponent(){
        this(37);


    }
    public SpaceComponent(int height){
        this.setPrefHeight(height);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
        );
    }
}
