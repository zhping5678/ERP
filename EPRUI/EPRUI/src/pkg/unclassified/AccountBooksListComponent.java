package pkg.unclassified;

import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class AccountBooksListComponent extends FlowPane {
    public ArrayList<AccountBookButton> accountBookButtonArrayList=new ArrayList<>();
    public AddAccountBookButton addAccountBookButton=new AddAccountBookButton();
    public AccountBooksListComponent(){

        /*for test*/
        for (int i=0;i<20;i++){
            accountBookButtonArrayList.add(new AccountBookButton("2017-12-12 13:13:13"));
        }
        this.getChildren().addAll(accountBookButtonArrayList);
        this.getChildren().add(addAccountBookButton);
        this.setStyle(this.getStyle()
                +"-fx-background-color:white;"
                +"-fx-padding: 20 50 0 50;"
        );
        this.setPrefWidth(100000);
        this.setHgap(10);




    }
}
