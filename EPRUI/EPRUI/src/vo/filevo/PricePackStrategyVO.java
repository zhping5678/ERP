package vo.filevo;

import java.io.Serializable;
import java.util.ArrayList;


public class PricePackStrategyVO implements Serializable {
    private static final long serialVersionUID=90L;

    private String id;
    private FileState state;
    private FileType type;
    private String creator;
    private ArrayList<String[]> commodityGroup;//特价包商品组合，商品ID(0)，商品名字(1)，型号(2)，数量(3)，价格(4)，备注(5)
    private boolean IsUsing;
    private String createTime;
    private String startTime;
    private String endTime;
    private String note;

    public PricePackStrategyVO(String id,FileType type,FileState state,String creator,ArrayList<String[]> commodityGroup,
                               boolean isUsing,String createTime,String startTime,String endTime,String note){
        this.id=id;
        this.state=state;
        this.type=type;
        this.creator=creator;
        this.commodityGroup=commodityGroup;
        this.IsUsing=isUsing;
        this.createTime=createTime;
        this.startTime=startTime;
        this.endTime=endTime;
        this.note=note;
    }

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
    public ArrayList<String[]> getCommodityGroup() {
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

}
