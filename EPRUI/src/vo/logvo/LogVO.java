package vo.logvo;

import java.io.Serializable;
/*
 * @Name LogVO
 * @Description
 * @author zhangping
 * @date 2017/12/3 0003 0:11
 */

public class LogVO implements Serializable {

    private static final long serialVersionUID=3L;
    private String time;//操作时间
    private String userId;//操作员ID
    private String event;//操作事件

    public LogVO(String time,String id,String event){
        this.time=time;
        this.userId=id;
        this.event=event;
    }

    public String getTime() {
        return this.time;
    }

    public String getId() {
        return this.userId;
    }

    public String getEvent() {
        return this.event;
    }

    public String toString(){
        return this.time+"  "+this.userId+"  "+this.event;
    }
}
