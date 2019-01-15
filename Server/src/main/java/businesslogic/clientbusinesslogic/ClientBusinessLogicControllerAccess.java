package businesslogic.clientbusinesslogic;
/*
 *@Name: ClientBusinessLogicControllerAccess
 *@Description: 单据通过审批后，自动修改客户的应收应付
 *@Author: Jane
 @Date: 2017/12/14 19:30
 */

import po.clientpo.ClientPO;
import vo.clientvo.ClientVO;

import java.util.ArrayList;

public interface ClientBusinessLogicControllerAccess {

    void modifyReceiveMoney(String userID,double money);

    void modifyPayMoney(String userID,double money);

    void modifyClientCanDel(String ID,boolean canDel);

    void modifyIsOnService(String ID,boolean isOnService);

    void modifyClientLevel(String ID);

    ArrayList<ClientPO> list();

    ArrayList<ClientVO> listSaler();
    ArrayList<ClientVO> listSupplier();
    ArrayList<ClientVO> listBoth();

    ClientPO findByID(String id);

}
