package po.userpo;

import vo.uservo.Position;

public class UserPO {

    private String ID;//用户的ID
    private String name;//用户的名字
    private String password;//用户密码
    private Position position;//用户的职位
    private boolean state;//用户的状态，即登录状态或非登录状态
    private boolean right;//用户是否具有该职位的某种权限
    private boolean isBan;//用户是否已经被禁用（变灰）
    private boolean CanDel;//用户是否已经产生过业务记录，决定用户是否能彻底删除
    private boolean isOn;//用户是否有未完成的交易，如果有，则不能删除

    public UserPO() {
    }

    public UserPO(String id,String name, String pass, Position pos, boolean state, boolean right, boolean isBan,boolean CanDel, boolean isOn){

        this.ID=id;
        this.name=name;
        this.password=pass;
        this.position=pos;
        this.state=state;
        this.right=right;
        this.isBan=isBan;
        this.CanDel=CanDel;
        this.isOn=isOn;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String ID){
        this.ID=ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position=position;
    }

    public boolean getState(){
        return this.state;
    }

    public void setState(boolean state){
        this.state=state;
    }

    public boolean getRight(){
        return this.right;
    }

    public void setRight(boolean right){
        this.right=right;
    }

    public boolean getIsBan(){
        return this.isBan;
    }

    public void setIsBan(boolean isBan){
        this.isBan=isBan;
    }

    public boolean isCanDel(){
        return this.CanDel;
    }

    public void setCanDel(boolean canDel){
        this.CanDel=canDel;
    }

    public boolean getIsOn(){
        return this.isOn;
    }

    public void setIsOn(boolean isOn){
        this.isOn=isOn;
    }

    public String toString(){
        return this.ID+" "+this.name+" "+this.position;
    }
}
