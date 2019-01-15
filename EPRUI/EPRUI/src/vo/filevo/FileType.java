package vo.filevo;

public enum FileType {
    SALE("sa",1),
    PURCHASE("pc",2),
    SALERETURN("sr",3),
    PURCHASERETURN("pr",4),
    PAYMENT("pm",5),
    TRANSFER("tf",6),
    RECEIPT("rc",7),
    REIMBURSEMENT("rb",9),
    LEVEL("lv",10),
    GIFT("gf",11),
    VOUCHER("vc",12),
    PRICEPACK("pp",13),
    DISCOUNT("da",14),
    EXCESS("ec",15),//报溢单
    LOSS("ls",16);//报损单

    private String expression;
    private int index;

    private FileType(String expression,int index){
        this.expression=expression;
        this.index=index;
    }

    public String getExpression(){
        return expression;
    }

    public void setExpression(String expression){
        this.expression=expression;
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index=index;
    }
}
