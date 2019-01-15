package po.filepo.StrategyPO;

import infor.ProductItem;
import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.Set;

/*
 * @Name GiftStrategyPO
 * @Description 赠品策略，赠品策略可以有很多，每个策略有一个金额标准和对应的赠品清单
 * @author zhangping
 * @date 2018/1/3 0003 20:39
 */
public class GiftStrategyPO {

    private String id;
    private double amount;
    private FileState state;
    private FileType type;
    private String creator;
    private Set<ProductItem> gifts;
    private boolean IsUsing;
    private String createTime;
    private String startTime;
    private String endTime;
    private String note;

    public GiftStrategyPO(){}

    public String getId() {
        return id;
    }
    public double getAmount() {
        return amount;
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
    public Set<ProductItem> getGifts() {
        return gifts;
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
    public void setAmount(double amount) {
        this.amount = amount;
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
    public void setGifts(Set<ProductItem> gifts) {
        this.gifts = gifts;
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

