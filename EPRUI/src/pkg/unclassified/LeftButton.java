package pkg.unclassified;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class LeftButton extends Button {
    protected boolean selected=false;
    protected boolean available=true;
    protected boolean mouseIn=false;
    public LeftButton(){
        this("Left Button");
    }
    public LeftButton(String buttonName) {



        super(buttonName);

      //  this.setEffect(new GaussianBlur());/*for test*/


        this.setPrefSize(Parameters.LEFT_BUTTON_WIDTH, Parameters.LEFT_BUTTON_HEIGHT);
        this.setStyle("-fx-background-color:" + Parameters.CSS_NJU_PURPLE + ";"
               + "-fx-text-fill: white;"
               + "-fx-background-radius: 0;"
               + "-fx-min-height: "+Parameters.CSS_LEFT_BUTTON_HEIGHT+";"
               + "-fx-padding: -1.3 0 0 0;"
               +"-fx-border-style: none;"
               +"-fx-border-color: transparent;"
               +"-fx-focus-traversable: false;"
               +"-fx-font-size:"+ Parameters.CSS_FONT_SIZE+";"
        );
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseIn=true;
                if (available&&!selected){
                    LeftButton sourceButton=(LeftButton)(event.getSource());
                    sourceButton.setStyle(sourceButton.getStyle()
                            +"-fx-background-color:"+Parameters.CSS_LIGHT_PURPLE+";"
                    );
                }

            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
           //     System.out.println("pressed");
                if ((available)&&(!selected)){
                    LeftButton sourceButton=(LeftButton)(event.getSource());
                    sourceButton.setStyle(sourceButton.getStyle()
                            +"-fx-background-color:"+Parameters.CSS_DEEP_PURPLE+";"
                    );
                }
            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
          //    System.out.println("released");
                if (available&&!selected&&mouseIn){
                    LeftButton sourceButton = (LeftButton) (event.getSource());
                    sourceButton.setStyle(sourceButton.getStyle()
                            + "-fx-background-color:" + Parameters.CSS_LIGHT_PURPLE + ";"
                    );
                }
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseIn=false;
                setSelected();
                setAvailable();
            }
        });
    }
    public void setAvailable(){
        this.setAvailable(this.available);
    }
    public void setAvailable(boolean available){
        this.available=available;
        if (available){
            if (!selected)
                this.setStyle(this.getStyle()
                    +"-fx-text-fill:white;"
                    +"-fx-background-color: "+Parameters.CSS_NJU_PURPLE+";"
                );
            else {
                this.setStyle(this.getStyle()
                        +"-fx-text-fill:"+Parameters.CSS_NJU_PURPLE+";"
                        +"-fx-background-color: "+Parameters.CSS_MIDDLE_PANE_GRAY+";"
                );
            }
        }else{
            if (!selected){
                this.setStyle(this.getStyle()
                        +"-fx-text-fill:"+Parameters.CSS_UNAVAILABLE_BUTTON_TEXT_FILL+";"
                        +"-fx-background-color: "+Parameters.CSS_NJU_PURPLE+";"
                );
            }else{
                this.setStyle(this.getStyle()
                        +"-fx-text-fill:"+Parameters.CSS_NJU_PURPLE+";"
                        +"-fx-background-color: "+Parameters.CSS_MIDDLE_PANE_GRAY+";"
                );
            }
        }

    }
    public void setSelected(){
        setSelected(this.selected);
    }
    public void setSelected(boolean selected){
        this.selected=selected;
        setAvailable(this.available);
    }
    public boolean isSelected(){
        return this.selected;
    }
    public boolean isAvailable(){
        return this.available;
    }





}
