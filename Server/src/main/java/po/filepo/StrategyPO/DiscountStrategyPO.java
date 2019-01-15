package po.filepo.StrategyPO;

import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Map;

/*
 * @Name DiscountStrategyPO
 * @Description 折扣策略
 * @author zhangping
 * @date 2018/1/4 0004 0:03
 */
public class DiscountStrategyPO {

    private String ID;
    private FileState state;
    private FileType type;
    private String creator;
    private Map<Double,Double> content;
    private boolean IsUsing;
    private String createTime;
    private String startTime;
    private String endTime;
    private String note;

    public DiscountStrategyPO(){}

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

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setType(FileType type) {
        this.type = type;
    }
    public void setState(FileState state) {
        this.state = state;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public void setContent(Map<Double, Double> content) {
        this.content = content;
    }
    public void setIsUsing(boolean isUsing) {
        this.IsUsing=isUsing;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
