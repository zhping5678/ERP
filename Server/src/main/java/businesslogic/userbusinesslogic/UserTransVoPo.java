package businesslogic.userbusinesslogic;

import po.userpo.UserPO;
import vo.uservo.UserVO;

import java.util.ArrayList;

/*
 * @Name UserTransVoPo
 * @Description
 * @author zhangping
 * @date 2017/12/1 0:57
 */

public class UserTransVoPo {


    public UserVO transPoToVo(UserPO po){
        return new UserVO(po.getID(),po.getName(),po.getPassword(),po.getPosition(),po.getState(),po.getRight(),po.getIsBan());
    }


    ArrayList<UserVO> transPoToVos(ArrayList<UserPO> pos){
        ArrayList<UserVO> vos=new ArrayList<>();
        for(UserPO p:pos){
            vos.add(transPoToVo(p));
        }
        return vos;
    }

}
