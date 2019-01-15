package po.filepo.StrategyPO;

import infor.ProductItem;
import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Set;

/*
 * @Name PricePackStrategyPO
 * @Description 特价包方案
 * @author zhangping
 * @date 2018/1/4 0004 12:26
 */
public class PricePackStrategyPO{

    private String id;
    private FileState state;
    private FileType type;
    private String creator;
    private Set<ProductItem> commodityGroup;
    private boolean IsUsing;
    private String createTime;
    private String startTime;
    private String endTime;
    private String note;

    public PricePackStrategyPO(){}

    public String getId() {
        return id;
    }
    public FileType getType() {
        return type;
    }
    public String getCreator() {
        return creator;
    }
    public FileState getState() {
        return state;
    }
    public Set<ProductItem> getCommodityGroup() {
        return commodityGroup;
    }
    public boolean getIsUsing() {
        return IsUsing;
    }
    public String getCreateTime() {
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

    public void setId(String id) {
        this.id = id;
    }
    public void setType(FileType type){
        this.type=type;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public void setState(FileState state){
        this.state=state;
    }
    public void setCommodityGroup(Set<ProductItem> commodityGroup) {
        this.commodityGroup = commodityGroup;
    }
    public void setIsUsing(boolean using) {
        this.IsUsing = using;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime=endTime;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
