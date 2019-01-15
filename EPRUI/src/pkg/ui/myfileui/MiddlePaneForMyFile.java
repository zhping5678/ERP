package pkg.ui.myfileui;

import pkg.unclassified.MiddlePane;
import pkg.unclassified.*;

public class MiddlePaneForMyFile extends MiddlePane {
    private MyFileAccordion myFileAccordion;
    public static MiddlePaneForMyFile middlePaneForMyFile;

    private MiddlePaneForMyFile(){
        super(true);
        myFileAccordion=MyFileAccordion.getInstance();
        setMiddleBelowPane(myFileAccordion);
    }
    public static MiddlePaneForMyFile getInstance(){
        if (middlePaneForMyFile==null){
            middlePaneForMyFile=new MiddlePaneForMyFile();

        }
        return middlePaneForMyFile;

    }

}
