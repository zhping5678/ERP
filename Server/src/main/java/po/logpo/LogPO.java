package po.logpo;


public class LogPO {

    private String time;//操作时间
    private String id;//操作员ID
    private String event;//操作事件

    public LogPO(){}
    public LogPO(String time, String id,String event){
        this.time=time;
        this.id=id;
        this.event=event;
    }

    public String getTime(){
        return this.time;
    }

    public String getId() {
        return this.id;
    }

    public String getEvent() {
        return this.event;
    }

    public void setTime(String time){
        this.time=time;
    }

    public void setId(String ID){
        this.id=ID;
    }

    public void setEvent(String event){
        this.event=event;
    }
}
