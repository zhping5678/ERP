package po.messagepo;

import java.util.HashSet;
import java.util.Set;

/*
 *@Name: MessagePO
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/5 14:23
 */
public class MessagePO {
    private String time;//消息的发送时间
    private long id;//消息编号，不显示,自动生成
    private String sender;//消息的发起者
    private String event;//消息内容
    private Set<String> receiver=new HashSet<>();//消息的接收者
    private boolean canDel;//有些置顶消息不能删除

    public MessagePO(){}

    public String getTime(){
        return this.time;
    }
    public void setTime(String time){
        this.time=time;
    }

    public long getId(){
        return this.id;
    }
    public void setId(long i){
        this.id=i;
    }

    public String getSender() {
        return this.sender;
    }
    public void setSender(String upo){
        this.sender=upo;
    }

    public String getEvent(){
        return this.event;
    }
    public void setEvent(String e){
        this.event=e;
    }

    public Set<String> getReceiver() {
        return this.receiver;
    }
    public void setReceiver(Set<String> receiver) {
        this.receiver = receiver;
    }

    public boolean getCanDel(){
        return this.canDel;
    }
    public void setCanDel(boolean can){
        this.canDel=can;
    }

}
