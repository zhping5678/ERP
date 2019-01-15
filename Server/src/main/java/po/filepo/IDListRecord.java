package po.filepo;

import vo.filevo.FileType;

public class IDListRecord {
    private int number;
    private FileType type;

    public IDListRecord(int num,FileType t){
        this.number=num;
        this.type=t;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int num2){
        this.number=num2;
    }

    public FileType getType() {
        return this.type;
    }

}
