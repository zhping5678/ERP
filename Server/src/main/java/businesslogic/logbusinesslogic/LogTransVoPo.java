package businesslogic.logbusinesslogic;

import po.logpo.LogPO;
import vo.logvo.LogVO;

import java.util.ArrayList;

public class LogTransVoPo {

    public LogVO transPoToVo(LogPO po){
        return new LogVO(po.getTime(),po.getId(),po.getEvent());
    }

    ArrayList<LogVO> transPOsToVOs(ArrayList<LogPO> pos){
        ArrayList<LogVO> re=new ArrayList<>();
        for(LogPO p:pos){
            re.add(transPoToVo(p));
        }
        return re;
    }
}
