package data.userdata;

import dataservice.userdataservice.UserDataService;
import po.userpo.UserPO;
import vo.uservo.Position;

import java.util.ArrayList;

public class UserDataController_Stub implements UserDataService{
    @Override
    public void write(UserPO userPO) {
        System.out.println("Data:write");
    }

    @Override
    public UserPO read(String ID) {
        System.out.println("Data:read");
        if(ID.equals("001")){
            return null;
        }else {
            return new UserPO("001", "name001","pass001", Position.Salesman, false, true, false, false, false);
        }
    }

    @Override
    public void remove(String ID) {
        System.out.println("Data:remove");
    }

    @Override
    public void update(UserPO po) {

    }

    @Override
    public ArrayList<UserPO> list() {
        System.out.println("Data:list");
        ArrayList<UserPO> users=new ArrayList<>();
        UserPO upo1=new UserPO("001","name001","pass001", Position.Salesman,false,true,false,false,false);
        UserPO opo2=new UserPO("002","name002","pass002", Position.Warehouseman,false,true,false,false,false);
        return users;
    }

    @Override
    public ArrayList<UserPO> searchByKeywords(String keywords) {
        System.out.println("Data:searchKeywords");
        ArrayList<UserPO> users=new ArrayList<>();
        UserPO upo1=new UserPO("001","name001","pass001", Position.Salesman,false,true,false,false,false);
        UserPO opo2=new UserPO("002","name002","pass002", Position.Warehouseman,false,true,false,false,false);
        return users;
    }
}
