package vo.clientvo;

public enum ClientIdentity {
    Seller("销售商"),
    Supplier("供应商"),
    SellerAndSupplier("销售商&&供应商");

    private String identity;

    ClientIdentity(String identity){
        this.identity=identity;
    }

    public String getIdentity(){
        return this.identity;
    }
}
