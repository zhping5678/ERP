package businesslogic.messagebusinesslogic;

import po.messagepo.MessagePO;
import vo.messagevo.MessageVO;


public class MessageTransVOPO {

    public MessageVO transPoToVo(MessagePO po){
        return new MessageVO(po.getTime(),po.getId(),po.getSender(),po.getEvent(),po.getCanDel());
    }

}
