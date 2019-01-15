package vo.filevo;

import java.io.Serializable;

public class LevelStrategyVO implements Serializable{
    private static final long serialVersionUID = 9L;

    private String ID;//分级方案的ID，形式为lv+时间+编号
    private FileType type;//方案的分类
    private FileState fileState;//策略的状态
    private String creator;//该方案的制定者，一般为总经理
    private int LeastLevelToDel;//用户能删除的最高级别客户，即级别大于该数值就不能删除
    private double[] level;//客户各个级别之间的交易金额分界值
    private boolean IsUsing;//该策略是否正在实行
    private String createTime;//策略的创建时间
    private String startTime;//该策略的开始实施时间
    private String endTime;//该策略的结束时间
    private String note;//note

    public LevelStrategyVO(String id,FileType type,FileState fileState,String creator,int leastLevelToDel,double[] level,
                           boolean isUsing,String createTime,String startTime,String endTime,String note){
        this.ID=id;
        this.type=type;
        this.fileState=fileState;
        this.creator=creator;
        this.LeastLevelToDel=leastLevelToDel;
        this.level=level;
        this.IsUsing=isUsing;
        this.createTime=createTime;
        this.startTime=startTime;
        this.endTime=endTime;
        this.note=note;
    }

    public String getID(){
        return this.ID;
    }
    public FileType getType() {
        return this.type;
    }
    public FileState getFileState() {
        return this.fileState;
    }
    public String getCreator() {
        return creator;
    }
    public int getLeastLevelToDel(){
        return this.LeastLevelToDel;
    }
    public double[] getLevel() {
        return this.level;
    }
    public boolean isUsing() {
        return this.IsUsing;
    }
    public String getCreateTime(){
        return this.createTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String getNote(){return this.note;}

}
