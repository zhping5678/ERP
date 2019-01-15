package pkg.ui.fileui;

import pkg.unclassified.MiddlePane;

public class MiddlePaneForFile extends MiddlePane {
    private FileAccordion fileAccordion;
    public static MiddlePaneForFile middlePaneForFile;

    private MiddlePaneForFile(){
        super(true);
        fileAccordion=FileAccordion.getInstance();
        setMiddleBelowPane(fileAccordion);
    }
    public static MiddlePaneForFile getInstance(){
        if (middlePaneForFile==null){
            middlePaneForFile=new MiddlePaneForFile();

        }
        return middlePaneForFile;

    }

}
