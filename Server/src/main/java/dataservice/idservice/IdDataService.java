package dataservice.idservice;

import infor.IndexInfor;

public interface IdDataService {
    IndexInfor read(int ID);
    void update(IndexInfor indexInfor);
}
