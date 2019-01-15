package data.clientdata;

import dataservice.clientdataservice.ClientDataService;
import po.clientpo.ClientPO;
import vo.clientvo.ClientIdentity;

import java.util.ArrayList;

public class ClientDataController_Stub implements ClientDataService{
    @Override
    public void write(ClientPO clientPO){}

    @Override
    public ClientPO read(String ID) {
        if(ID=="Jane"){
            ClientPO po1=new ClientPO("Jane","", ClientIdentity.Seller,1,"8829","NJU","110011","7797@qq.com","null",10000.0,0.0,0.0,0.0,"Ping",true,false,false,false);
            return po1;
        }else{
            return null;
        }
    }

    @Override
    public void remove(String ID){}

    @Override
    public void update(ClientPO ID) {

    }

    @Override
    public ArrayList<ClientPO> list() {
        return null;
    }

    @Override
    public ArrayList<ClientPO> searchByKeywords(String keywords) {
        return null;
    }
}
