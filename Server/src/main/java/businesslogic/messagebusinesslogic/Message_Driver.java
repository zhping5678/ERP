package businesslogic.messagebusinesslogic;

import businesslogicservice.messagebusinesslogicservice.MessageBusinessLogicService;
import vo.messagevo.MessageVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Message_Driver {
    public static void main(String [] args) throws RemoteException{
        MessageBusinessLogicControllerAccess m=new MessageBusinessLogicController();
        MessageBusinessLogicService a=new MessageBusinessLogicController();
        ArrayList<String> receive=new ArrayList<>();
        receive.add("Jane");
        receive.add("Paul");
        receive.add("Wang");
        receive.add("Ping");
        m.addMessage("Paul","停用分级策略1",receive,true);
        m.addMessage("Jane","开始期初建账",receive,true);
        m.addMessage("Wang","库存报警","Ping",true);
        //m.addMessage("zj","testMessageSearch",receive,false);
//        for(MessageVO v:a.showMyReceiveMessage("44444")){
//            System.out.println(v.toString());
//        }
//        a.removeMessage(4,"222");
    }
}
