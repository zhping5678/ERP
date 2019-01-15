package businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic;

import businesslogicservice.myfilebusinesslogicservice.AccountManBusinessLogicService;
import infor.Infor;
import infor.SheetList;
import vo.filevo.PaymentVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Receipt_Driver {
    public static void main(String args[]) throws RemoteException{
        AccountManBusinessLogicService a=new AccountManBusinessLogicController();
//        ArrayList<Transfer> transfers=new ArrayList<>();
//        Transfer transfer1=new Transfer();
//        Transfer transfer2=new Transfer();
//        transfer1.setMoney(1000.0);
//        transfer1.setNote("");
//        transfer2.setMoney(100.0);
//        transfer2.setNote("xxx");
//        transfers.add(transfer1);
//        transfers.add(transfer2);
//        ArrayList<Infor> infor=new ArrayList<>();
//        Infor infor1=new Infor();
//        infor1.setCheckerID("x");
//        infor1.setCheckerTime("xx");
//        infor1.setRemark("xxx");
//        infor1.setResult("refuse");
//        Infor infor2=new Infor();
//        infor2.setCheckerID("x");
//        infor2.setCheckerTime("xxx");
//        infor2.setRemark("xxx");
//        infor2.setResult("approve");
//        infor.add(infor1);
//        infor.add(infor2);
//
//        ReceiptVO vo=new ReceiptVO("rc-20180104-12","xxx","Jane","Wang","Png",transfers,10000.0, "Draft","xxx",infor);
//        a.modReceipt("Jane",vo);

//        a.addReceipt("Jane");
//        a.addReceipt("Jane");
//        a.delReceipte("Jane","rc-20180103-12");
//        a.addReceipt("Jane");
//        a.modReceipt("Jane",vo);
//        a.findReceipt("rc-20180103-12");
//        a.addPayment("Jane",1);
//        a.delPayment("Jane","pm-20180104-9");

        //a.addReceipt("Jane");

//        ArrayList<Infor> infor2=new ArrayList<>();
//        Infor infor3=new Infor();
//        infor3.setCheckerID("x");
//        infor3.setCheckerTime("xx");
//        infor3.setRemark("xxx");
//        infor3.setResult("refuse");
//        Infor infor4=new Infor();
//        infor4.setCheckerID("x");
//        infor4.setCheckerTime("xxx");
//        infor4.setRemark("xxx");
//        infor4.setResult("approve");
//        infor2.add(infor3);
//        infor2.add(infor4);
////
//        ArrayList<SheetList> sheetLists=new ArrayList<>();
//        SheetList sheetList1=new SheetList(1000.0,"");
//        SheetList sheetList2=new SheetList(100.0,"null");
//        sheetLists.add(sheetList1);
//        sheetLists.add(sheetList2);
//        PaymentVO paymentVO=new PaymentVO("xxx","rb-20180104-10","Jane","Paul","Ping",sheetLists,10000.0,"State","",infor2);
//        a.modPayment("Jane",paymentVO);
//        PaymentVO paymentVO=a.findPayment("pm-20180104-10");
//        ArrayList<String> array=a.showTrash();
//        for(String aa:array){
//            System.out.println(aa);
//        }
//        a.recoverPayment("Jane","rb-20180104-10");
//        a.addPayment("Jane",0);
        ArrayList<String> xx=a.showDraft();
        for(String x:xx){
            System.out.println(x+"aaaaaaaaaaaaaaaa");
        }
    }
}
