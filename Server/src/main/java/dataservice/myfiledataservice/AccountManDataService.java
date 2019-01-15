package dataservice.myfiledataservice;

import po.filepo.PaymentPO;
import po.filepo.ReceiptPO;
import vo.utilityvo.ResultMessage;

import java.util.ArrayList;

public interface AccountManDataService {
    void writeReceipt(ReceiptPO receiptPO);
    ReceiptPO readReceipt(String ID);
    void updateReceipt(ReceiptPO receiptPO);
    void removeReceipt(String ID);
    ArrayList<ReceiptPO> listReceipt();

    void writePayment(PaymentPO paymentPO);
    PaymentPO readPayment(String ID);
    void updatePayment(PaymentPO paymentPO);
    void removePayment(String ID);
    ArrayList<PaymentPO> listPayment();

    ArrayList<String> dateRange(String start,String end);
}
