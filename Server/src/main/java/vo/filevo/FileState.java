package vo.filevo;

public enum FileState {
    DRAFT("DRAFT",1),//草稿状态，未提交，或是提交被驳回
    WAITREVIEW("WAITREVIEW",2),//单据已经提交，等待审核
    REVIEWED("REVIEWED",3),//单据通过审核
    TRASH("TRASH",4);//单据被删除，位于回收站 ;

    private String expression;
    private int index;

    FileState(String expression, int index){
        this.expression=expression;
        this.index=index;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
