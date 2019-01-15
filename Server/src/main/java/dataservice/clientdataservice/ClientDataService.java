package dataservice.clientdataservice;

/*
 * @Name ClientDataService
 * @Description 作为数据层提供给业务逻辑层的接口
 * @author zhangping
 * @date 2017/11/29 0029 14:40
 */
import po.clientpo.ClientPO;

import java.util.ArrayList;

public interface ClientDataService{

    void write(ClientPO clientPO);
    ClientPO read(String ID);
    void remove(String ID);
    void update(ClientPO clientPO);
    ArrayList<ClientPO> list();
    ArrayList<ClientPO> searchByKeywords(String keywords);

}
