package vo.uservo;

import java.io.Serializable;

/*
 * @Name UserVO
 * @Description 给系统管理员提供的Vo
 * @author zhangping
 * @date 2017/12/15 0015 22:05
 *
 */
public class UserVO implements Serializable{
    private static final long serialVersionUID=4L;

    private String ID;//用户ID
    private String name;//用户的名字
    private String password;//用户密码
    private Position position;//用户的职位
    private boolean state;//用户的状态，即登录状态或非登录状态
    private boolean right;//用户是否具有该职位的某种权限
    private boolean isBan;//用户是否已经被禁用



    public UserVO(String id, String name,String pass, Position pos, boolean state, boolean right, boolean isBan){

        this.ID=id;
        this.name=name;
        this.password=pass;
        this.position=pos;
        this.state=state;
        this.right=right;
        this.isBan=isBan;

    }

    public String getID(){
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean getState(){
        return this.state;
    }

    public boolean getRight(){
        return this.right;
    }

    public boolean isBan() {
        return this.isBan;
    }

    public String toString(){
        return ("ID:"+this.ID+"名字："+this.name+",职位:"+this.position.getPosition());
    }
}
