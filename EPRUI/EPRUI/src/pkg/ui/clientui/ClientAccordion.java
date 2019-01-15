package pkg.ui.clientui;

import pkg.unclassified.BetterAccordion;

public class ClientAccordion extends BetterAccordion {
    private static ClientAccordion clientAccordion;

    public BuyerTitledWithList buyerTitledWithList =BuyerTitledWithList.getInstance();
    public SellerTitledWithList sellerTitledWithList =SellerTitledWithList.getInstance();
    public BuyerAndSellerTitledWithList buyerAndSellerTitledWithList =BuyerAndSellerTitledWithList.getInstance();




    private ClientAccordion(){
        this.getPanes().addAll(buyerTitledWithList, sellerTitledWithList, buyerAndSellerTitledWithList);
        this.setExpandedPane(buyerTitledWithList);





    }

    public static  ClientAccordion getInstance(){
        if (clientAccordion ==null){
            clientAccordion =new ClientAccordion();
        }
        return clientAccordion;
    }


}
