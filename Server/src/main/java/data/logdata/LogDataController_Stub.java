package data.logdata;

import dataservice.logdataservice.LogDataService;
import po.logpo.LogPO;

import java.util.ArrayList;

public class LogDataController_Stub implements LogDataService {
    @Override
    public void write(LogPO logPO) {

    }

    @Override
    public ArrayList<LogPO> list() {
        ArrayList<LogPO> pos=new ArrayList<>();
        LogPO po1=new LogPO("2017-12-13 16:31:27","Jane","删除账户Pojo");
        LogPO po2=new LogPO("2017-12-13 11:26:27","Paul","增加商品 大灯");
        pos.add(po1);
        pos.add(po2);
        return pos;
    }

    @Override
    public ArrayList<LogPO> Search(String keywords) {
        ArrayList<LogPO> pos=new ArrayList<>();
        if(keywords.equals("J")){
            LogPO po1=new LogPO("2016-12-13 16:31:27","Jane","修改账户Pojo");
            LogPO po2=new LogPO("2018-12-13 11:26:27","Wong","增加促销策略");
            pos.add(po1);
            pos.add(po2);
        }
        return pos;
    }
}
