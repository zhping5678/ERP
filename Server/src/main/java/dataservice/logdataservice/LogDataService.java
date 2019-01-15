package dataservice.logdataservice;

import po.logpo.LogPO;

import java.util.ArrayList;

public interface LogDataService{

    void write(LogPO logPO);
    ArrayList<LogPO> list();
    ArrayList<LogPO> Search(String keywords);
}


