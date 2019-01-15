package infor;

import java.io.Serializable;

public class Infor implements Serializable{
    private static final long serialVersionUID=2000L;

    private long inforID;
    private String checkerID;
    private String checkerTime;
    private String remark;
    private String result;

    public Infor(){}

    public long getInforID() {
        return inforID;
    }

    public void setInforID(long inforID) {
        this.inforID = inforID;
    }

    public String getCheckerID() {
        return checkerID;
    }

    public void setCheckerID(String checkerID) {
        this.checkerID = checkerID;
    }

    public String getCheckerTime() {
        return checkerTime;
    }

    public void setCheckerTime(String checkerTime) {
        this.checkerTime = checkerTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
