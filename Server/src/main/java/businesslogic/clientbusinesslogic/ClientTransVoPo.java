package businesslogic.clientbusinesslogic;

import po.clientpo.ClientPO;
import vo.clientvo.ClientVO;

public class ClientTransVoPo {

    public ClientVO transPoToVo(ClientPO po){
        return new ClientVO(po.getID(),po.getName(),po.getCategory(),po.getLevel(),po.getAddress(),
                po.getPhoneNum(),po.getEmail(),po.getPostcode(),po.getStaff(),po.getNote(),po.getQuota(),po.getToPay(),
                po.getToCollect(),po.getTotalAmount(),po.getIsBan());
    }


    /*public ArrayList<ClientVO> transPOsToVOs(ArrayList<ClientPO> pos){
        ArrayList<ClientVO> vos=new ArrayList<>();
        for(ClientPO p:pos){
            vos.add(transPoToVo(p));
        }
        return vos;
    }*/

}