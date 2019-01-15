package vo.messagevo;

import java.io.Serializable;

/*
 *@Name: MessageVO
 *@Description: 将消息的生成时间同时作为该消息的id.
 *@Author: Jane
 *@Date: 2017/12/5 14:29
 */
public class MessageVO implements Serializable{
    private static final long serialVersionUID=8L;

    private String time;//消息产生时间
    private long id;//消息的ID，作为数据库中存储的唯一标识，对于用户没有实际意义，在界面不用显示
    private String sender;//消息的发起者
    private String event;//消息内容
    private boolean canDel;//消息是否为置顶消息，是否可以删除，因为在界面上删除就是真的删除了

    public MessageVO(String time,long id,String sender,String e,boolean canDel){

        this.time = time;
        this.id=id;
        this.sender=sender;
        this.event=e;
        this.canDel=canDel;
    }

    public String getTime(){
        return this.time;
    }

    public long getId(){
        return this.id;
    }

    public String getSender() {
        return this.sender;
    }

    public String getEvent(){
        return this.event;
    }

    public boolean getCanDel(){
        return this.canDel;
    }

    public String toString(){
        return this.sender+" send "+this.event+" at "+this.time+".";
    }
}
