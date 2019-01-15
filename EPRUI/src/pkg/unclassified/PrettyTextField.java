package pkg.unclassified;


import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;

public class PrettyTextField extends TextField {
    public PrettyTextField(){
        this("");
    }
    public PrettyTextField(String s){
        super(s);

/*
        this.setStyle(this.getStyle()
               +"-fx-accent:"+Parameters.CSS_NJU_PURPLE+";" //Change the color of selected part of text
               +"-fx-focus-traversable: true;"
               +"-fx-background-color:white;"
               +"-fx-padding:3 6 3 6;"
               +"-fx-border-color: #e1e1e1;"
               +"-fx-border-radius: 4;"

        );

*/
        this.getStylesheets().add("/pkg/stylesheet/PrettyTextField.css");

        this.addEventFilter(ContextMenuEvent.CONTEXT_MENU_REQUESTED, Event::consume);/*remove the menu when clicking the right mouse button*/
    }
}
