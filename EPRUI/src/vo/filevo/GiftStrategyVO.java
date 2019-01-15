package vo.filevo;


import java.io.Serializable;
import java.util.ArrayList;

public class GiftStrategyVO implements Serializable{
    private static final long serialVersionUID=32L;

    private String id;//策略的ID
    private double amount;//满足的金额
    private FileState state;//策略的状态，草稿？回收站？已使用？
    private FileType type;//策略类型，这里是赠品策略
    private String creator;//策略的创建者
    private ArrayList<String[]> gifts;//该策略中的赠品清单，需要id(0)，name(1)，型号(2)，数量(3)，备注(4)
    private boolean IsUsing;//策略是否正在使用
    private String createTime;//策略的创建时间
    private String startTime;//策略的开始使用时间
    private String endTime;//策略的结束时间
    private String note;//备注

    public GiftStrategyVO(String id, double amount, FileState state, FileType type, String creator, ArrayList<String[]> gifts,
                          boolean isUsing, String createTime, String startTime, String endTime, String note){
        this.id=id;
        this.amount=amount;
        this.state=state;
        this.type=type;
        this.creator=creator;
        this.gifts=gifts;
        this.IsUsing=isUsing;
        this.createTime=createTime;
        this.startTime=startTime;
        this.endTime=endTime;
        this.note=note;
    }

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
    public ArrayList<String[]> getGifts() {
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
}