package businesslogic.accountbusinesslogic;

import po.accountpo.AccountPO;
import vo.accountvo.AccountVO;
import vo.utilityvo.ResultMessage;

import java.util.ArrayList;

public interface AccountBusinessLogicControllerAccess {
    ResultMessage modifyAccountMoney(String ID,double money);

    ResultMessage modifyIsOnService(String ID,boolean isOnService);

    ResultMessage modifyCanDel(String ID,boolean canDel);

    ArrayList<AccountPO> list();

    ArrayList<AccountVO> listAccountVO();
}
