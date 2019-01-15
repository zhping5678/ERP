package pkg.ui.clientui;

import pkg.unclassified.MiddlePane;

public class MiddlePaneForClient extends MiddlePane {

    private ClientAccordion clientAccordion;
    public static MiddlePaneForClient middlePaneForClient;

    private MiddlePaneForClient(){
        super(true);
        clientAccordion =ClientAccordion.getInstance();
        setMiddleBelowPane(clientAccordion);
    }
    public static MiddlePaneForClient getInstance(){
        if (middlePaneForClient ==null){
            middlePaneForClient =new MiddlePaneForClient();

        }
        return middlePaneForClient;

    }
}
