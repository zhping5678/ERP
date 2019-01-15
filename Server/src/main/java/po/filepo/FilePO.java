package po.filepo;
/**
 *@Name: FilePO
 *@Description:
 *@Author: Jane
 @Date: 2017/12/14 15:20
 */

import po.userpo.UserPO;
import vo.filevo.FileState;
import vo.filevo.FileType;

import java.util.ArrayList;

public class FilePO {
    private String tempID;//Including the information of creator,file type and creation time.
    private String ID;//单据通过审批后根据单据类型，通过审批时间和编号生成
    private FileType fileType;//单据类型
    private UserPO operator;//操作员
    private String note;//备注
    private String createTime;//单据或者策略的创建时间
    private String passTime;//如果是策略类文件，创建时间不是passTime，开始使用时间才是
    private FileState state;//单据和策略的状态，包括草稿状态，提交等待审批，和已经通过审批状态，通过审批则不能再修改
    private ArrayList<String> information;//单据的经手信息

    public FilePO(){}

    public FilePO(String tempID,String ID,FileType type,UserPO operator,String note,String createTime,String passTime,FileState state){
        this.tempID=tempID;
        this.ID=ID;
        this.fileType=type;
        this.operator=operator;
        this.note=note;
        this.createTime=createTime;
        this.passTime=passTime;
        this.state=state;
    }
    public String getTempID() {
        return tempID;
    }

    public void setTempID(String tempID) {
        this.tempID = tempID;
    }

    public String  getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public UserPO getOperator(){
        return operator;
    }

    public void setOperator(UserPO operator) {
        this.operator = operator;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public FileState getState() {
        return state;
    }

    public void setState(FileState state) {
        this.state = state;
    }
}
