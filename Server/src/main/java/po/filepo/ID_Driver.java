package po.filepo;

import util.IDUtil;
import vo.filevo.FileType;

public class ID_Driver {
    public static void main(String[] args){
        System.out.println(new IDUtil().getID(FileType.PAYMENT));
    }
}
