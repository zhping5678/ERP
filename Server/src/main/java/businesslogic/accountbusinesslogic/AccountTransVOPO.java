package businesslogic.accountbusinesslogic;

import po.accountpo.AccountPO;
import vo.accountvo.AccountVO;


/*
 *@Name: AccountTransVOPO
 *@Description: VOPO数据之间的转换
 *@author: Jane
 *@Date: Created in 2017/11/29 19:50
 */
public class AccountTransVOPO {

    //AccountPo转换成AccountVO
    public AccountVO transPoToVo(AccountPO po){
        return new AccountVO(po.getID(),po.getName(),po.getMoney(),po.getIsBan());
    }

    //PO集合转换成VO集合
    /*public ArrayList<AccountVO> transPOsToVOs(ArrayList<AccountPO> pos){
        ArrayList<AccountVO> vos=new ArrayList<>();
        for(AccountPO p:pos){
            vos.add(transPoToVo(p));
        }
        return vos;
    }*/

}
