package po.filepo.StrategyPO;

import vo.filevo.FileState;
import vo.filevo.FileType;

/*
 * @Name LevelStrategyPO
 * @Description 由总经理制定的客户等级评定策略，将客户分为六个等级，普通用户和一到五星级用户
 * @author zhangping
 * @date 2017/12/3 0003 17:01
 *
 */
public class LevelStrategyPO{

    private String ID;//分级方案的ID，形式为lv+时间+编号
    private FileType type;
    private FileState fileState;
    private String creator;//该方案的制定者，一般为总经理
    private int LeastLevelToDel;//用户能删除的最高级别客户，即级别大于该数值就不能删除
    private double level1;//客户各个级别之间的交易金额分界值
    private double level2;
    private double level3;
    private double level4;
    private boolean IsUsing;//该策略是否正在实行
    private String createTime;//策略的创建时间
    private String startTime;//该策略的开始实施时间
    private String endTime;//该策略的结束时间
    private String note;//note

    public LevelStrategyPO(){}

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
    public double getLevel1() {
        return this.level1;
    }
    public double getLevel2() {
        return this.level2;
    }
    public double getLevel3() {
        return this.level3;
    }
    public double getLevel4() {
        return this.level4;
    }
    public boolean getIsUsing() {
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
    public String getNote() {
        return this.note;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public void setType(FileType type) {
        this.type = type;
    }
    public void setFileState(FileState fileState){
        this.fileState=fileState;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public void setLeastLevelToDel(int level){
        this.LeastLevelToDel=level;
    }
    public void setLevel1(double level1) {
        this.level1 = level1;
    }
    public void setLevel2(double level2) {
        this.level2 = level2;
    }
    public void setLevel3(double level3) {
        this.level3 = level3;
    }
    public void setLevel4(double level4) {
        this.level4 = level4;
    }
    public void setIsUsing(boolean isUsing){
        this.IsUsing=isUsing;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public void setStartTime(String startTime){

        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime =endTime;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
