package businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic;

import infor.Infor;
import po.filepo.PaymentPO;
import po.filepo.ReceiptPO;
import vo.filevo.PaymentVO;
import vo.filevo.ReceiptVO;
import vo.utilityvo.ResultMessage;

import java.util.ArrayList;

public interface AccountManBusinessLogicControllerAccess {

    void approveReceipt(String userID, String receiptID, boolean toApprove, Infor infor);

    void approvePayment(String userID, String paymentID, boolean toApprove, Infor infor);

    ArrayList<String> WaitReview();

    ReceiptVO showReceiptDetail(String id);

    PaymentVO showPaymentDetail(String id);
}
