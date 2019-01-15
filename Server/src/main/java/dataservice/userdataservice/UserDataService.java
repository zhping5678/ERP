package dataservice.userdataservice;

import po.userpo.UserPO;

import java.util.ArrayList;

public interface UserDataService{

    void write(UserPO userPO);
    UserPO read(String ID);
    void remove(String ID);
    void update(UserPO po);
    ArrayList<UserPO> list();
    ArrayList<UserPO> searchByKeywords(String keywords);

}
