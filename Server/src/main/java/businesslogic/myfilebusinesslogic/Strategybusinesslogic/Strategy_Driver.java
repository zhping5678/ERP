package businesslogic.myfilebusinesslogic.Strategybusinesslogic;

import businesslogicservice.myfilebusinesslogicservice.StrategyBusinessLogicService;
import vo.filevo.FileState;
import vo.filevo.FileType;
import vo.filevo.GiftStrategyVO;
import vo.filevo.LevelStrategyVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Strategy_Driver {
    public static void main(String[] args) throws RemoteException{
        StrategyBusinessLogicService s=new StrategyBusinessLogicController();
        double[] levels={100,200,300,400};
        String newid="";//=s.createNewStrategy("zp", FileType.GIFT);
        LevelStrategyVO lvo=new LevelStrategyVO(newid,FileType.LEVEL, FileState.DRAFT,"creator",3,levels, false,
                "","","","zanwu");
       // s.modifyLevelStrategy("zp",lvo);
        ArrayList<String[]> gifts=new ArrayList<>();
        String[] s1={"001","name1","big","1","no"};
        String[] s2={"002","name1","small","2","no"};
        gifts.add(s1);
        gifts.add(s2);
        GiftStrategyVO gvo=new GiftStrategyVO(newid,500,FileState.DRAFT,FileType.LEVEL,"zp",
                gifts,false,"","", "","zanwu");
        //System.out.println(s.modifyGiftStrategy("zp",gvo));
//        System.out.println(newid);
        Map<Double,Double> con=new HashMap<>();
        con.put(100.0,1000.0);
        con.put(200.0,2000.0);
//        s.modifyDiscountStrategy(newid,"wu",con);
//        ArrayList<String[]> group=new ArrayList<>();
//        group.add(new String[]{"0001","name1","big","1","10.0","note1"});
//        group.add(new String[]{"0002","name2","small","2","20.0","note2"});
//        s.modifyPricePackStrategy(newid,"wu",group);
        //s.modifyVoucherStrategy(newid,"wu",con);
       //System.out.println(s.startStrategy("zp","vc-20180104-1"));
        for(String ss:s.listDraftStrategy()){
            System.out.println(ss);
        }
    }
}
