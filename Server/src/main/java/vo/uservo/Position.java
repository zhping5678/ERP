package vo.uservo;

public enum Position {
    Warehouseman("库存人员"),
    Salesman("进销人员"),
    FinancialOfficer("财务人员"),
    Manager("总经理"),
    Administrator("系统管理员");

    private String position;

    Position(String pos){
        this.position=pos;
    }

    public String getPosition(){
        return this.position;
    }

    public void setPosition(String pos){
        this.position=pos;
    }
}
