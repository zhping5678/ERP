package vo.filevo;

import java.io.Serializable;
import java.util.Map;

public class DiscountStrategyVO implements Serializable{

    private static final long serialVersionUID=39L;

    private String ID;//单据ID，格式da+年月日+编号
    private FileState state;//单据状态，草稿，回收站，已使用
    private FileType type;//单据类型，这里是Discount
    private String creator;//创建者，一般为总经理
    private Map<Double,Double> content;//内容，即多少钱对应可以打多少折
    private boolean IsUsing;//是否正在运行
    private String createTime;//创建时间
    private String startTime;//开始启用时间
    private String endTime;//停止时间
    private String note;//备注

    public DiscountStrategyVO(String id,FileState state,FileType type,String creator,Map<Double,Double> content,boolean isUsing,
                              String createTime,String startTime,String endTime,String note){
        this.ID=id;
        this.state=state;
        this.type=type;
        this.creator=creator;
        this.content=content;
        this.IsUsing=isUsing;
        this.createTime=createTime;
        this.startTime=startTime;
        this.endTime=endTime;
        this.note=note;
    }

    public String getID() {
        return ID;
    }
    public FileState getState() {
        return state;
    }
    public FileType getType() {
        return type;
    }
    public Map<Double, Double> getContent() {
        return content;
    }
    public String getCreator() {
        return creator;
    }
    public boolean getIsUsing() {
        return IsUsing;
    }
    public String getCreateTime(){
        return createTime;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getNote() {
        return note;
    }

}
