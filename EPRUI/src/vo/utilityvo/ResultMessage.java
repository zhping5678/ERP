package vo.utilityvo;

/*
 * @Name ResultMessage
 * @Description 作为对某些数据进行操作的结果返回值
 * @author zhangping
 * @date 2017/11/29 0029 14:18
 */

public enum ResultMessage {

    addSuccess("增加成功！",1),
    exist("已存在！",2),
    delSuccess("删除成功！",3),
    noPower("没有权限！",4),
    onDeal("使用中！",5),
    modiSuccess("修改成功！",6),
    recoverSuccess("还原成功！",7),
    RemoteException("远程连接出现异常！",8),
    loginSuccess("登录成功！",9),
    logoutSuccess("成功退出！",10),
    IDError("ID错误！",11),
    PasswordError("密码错误！",12),
    banSuccess("禁用成功！",13),
    Fail("失败！",14),
    differPass("新密码与确认密码不一致！",15),
    stopSuccess("停用成功！",16),
    startSuccess("启用成功！",17),
    commitSuccess("提交成功！",18),
    ReviewSuccess("审批成功！",19);

    private String expression;
    private int index;

    private ResultMessage(String expression,int index){
        this.expression = expression;
        this.index = index;
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
