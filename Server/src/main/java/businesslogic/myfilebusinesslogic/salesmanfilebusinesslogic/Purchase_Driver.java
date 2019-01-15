package businesslogic.myfilebusinesslogic.salesmanfilebusinesslogic;

import businesslogicservice.myfilebusinesslogicservice.SalesmanFileBusinessLogicService;
import infor.Infor;
import po.filepo.SaleReturnPO;
import vo.filevo.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Purchase_Driver {
    public static void main(String[] args) throws RemoteException{
        String[] x={"NJ/GreenLamp/NANA","NANA台灯","#660099","0","0","0","无"};
        String[] y={"test2","Philips","big","500","4","510","无"};
        String[] z={"test3","Philips","big","500","5","510","无"};
//        ArrayList<String> infor=new ArrayList<>();
//        infor.add("1111");
//        infor.add("2222");
        ArrayList<String[]> array1=new ArrayList<>();
        array1.add(x);
//        array1.add(y);
//        array1.add(z);
        Map<String,Integer> con=new HashMap<>();
//        con.put("pp-20180104-1",1000);
//        con.put("pp-20180104-2",2000);
        SalesmanFileBusinessLogicService s=new SalesmanFileBusinessLogicController();
        //s.createSaleAndPurchase("Jane",FileType.PURCHASE);
//        SalesmanFileBusinessLogicController s2=new SalesmanFileBusinessLogicController();
//        String saleid=s.createSaleAndPurchase("zp",FileType.PURCHASE);
//        System.out.println(saleid);
//        //System.out.println(saleid);
        ArrayList<Infor> i=new ArrayList<>();
        //String newid=s.createSaleAndPurchase("Wang",FileType.PURCHASE);
//        Infor p=new Infor();
//        p.setCheckerID("zp");
//        p.setCheckerTime("1233-1221");
//        p.setResult("dis");
//        p.setRemark("note");
//        i.add(p);
////        PurchaseReturnVO prvo=new PurchaseReturnVO("", FileType.PURCHASERETURN,"zp","zp","null",
//                "16:47","null", FileState.DRAFT,"001","001",
//                array1,100,i);
//        PurchaseVO pvo=new PurchaseVO(newid, FileType.PURCHASE,"","","null",
//                "","null", FileState.DRAFT,"","",
//                array1,0,i);
//        s.modifyPurchase("Wang",newid,pvo);
////        SaleVO svo=new SaleVO("sa2018011315",FileType.SALE,FileState.DRAFT,"zp","zp","note","",
//                "","paul","NJU",array1,"gift1",null,100,
//                500,con,200,300,4000,i);
////        SaleReturnVO srvo=new SaleReturnVO(saleid,FileType.SALERETURN,FileState.DRAFT,"zp","zp","note","",
//                "","NJU","jane",array1,1000,i);
        //s.create("zp",pvo);
//        String id1=s.createSaleAndPurchase("zp",FileType.PURCHASERETURN);
//        String id2=s.createSaleAndPurchase("zp",FileType.PURCHASE);
//        System.out.println("preturn:"+id1);
//        System.out.println("purchase"+id2);
        //s.modifyPurchaseReturn("zp",id1,prvo);
//        s.modifyPurchase("zp",saleid,pvo);
        //s.modifySale("zp",svo);
        //s.delete("zp","pr-20180105-3");
//        for(PurchaseReturnPO p:s2.listPurchaseReturn()){
//            System.out.println(p.getID());
//        }
//        for (PurchasePO p:s2.listPurchase()){
//            System.err.println(p.getID());
//        }
        //System.out.println(FileType.PURCHASE.toString());
//        SaleVO saleVO=s.findSale("sa2018011315");
//        System.out.println(s.findSale("sa2018011315").getID()+" "+s.findSale("sa2018011315").getOperator()+s.findSale("sa2018011315").getVoucher());
//        for(Infor in:saleVO.getInformation()){
//            System.out.println(in.getResult());
//        }
//        PurchaseVO purchaseVO=s.findPurchase("pc201801133");
//        System.out.println(purchaseVO.getCreateTime());
        PurchaseReturnVO pvo=s.findPurchaseReturn("pr201801131");

        for(String[] sss:pvo.getProductItems()){
            System.err.println(sss[0]);
        }
    }

}
