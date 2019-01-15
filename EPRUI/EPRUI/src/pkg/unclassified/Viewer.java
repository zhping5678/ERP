package pkg.unclassified;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Viewer extends PrettyScrollPane{
    VBox vBox=new VBox();
    public Viewer(){
        this.setContent(vBox);
        vBox.setFocusTraversable(false);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setStyle(this.getStyle()
              //  +"-fx-background-color:white;"
                +"-fx-focus-color:transparent;"
                +"-fx-background-insets: 0;"/*IMPORTANT DISCOVERY!!!!!!!!!!!!!!!!!*/
          //      +"-fx-border-color: red;"
        );

    }
    public void readOnly(String fileid){


    }
    public void readAndEdit(String fileid){

    }
    public void review(String field){

    }
    public void save(){

    }
    public void submit(){

    }
}
