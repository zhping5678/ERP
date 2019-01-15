package businesslogic.myfilebusinesslogic.accountmanfilebusinesslogic;

import po.filepo.PaymentPO;
import po.filepo.ReceiptPO;
import vo.filevo.PaymentVO;
import vo.filevo.ReceiptVO;

import java.util.ArrayList;

public class TransReceiptAndPaymentVoPo {
    ReceiptVO receiptPoToVo(ReceiptPO receiptPO){
        ReceiptVO vo=new ReceiptVO(receiptPO.getId(),receiptPO.getCreateTime(),receiptPO.getClientID(),receiptPO.getAccountID(),receiptPO.getOperatorID(),new ArrayList<>(receiptPO.getTransferList()),receiptPO.getSumMoney(),receiptPO.getState(),receiptPO.getRemark(),new ArrayList<>(receiptPO.getInformation()));
        return vo;
    }

    ArrayList<ReceiptVO> receiptPosToVos(ArrayList<ReceiptPO>receiptPOS){
        ArrayList<ReceiptVO> receiptVOS=new ArrayList<>();
        for(ReceiptPO po:receiptPOS){
            receiptVOS.add(this.receiptPoToVo(po));
        }
        return receiptVOS;
    }

    PaymentVO paymentPoToVo(PaymentPO paymentPO){
        PaymentVO paymentVO=new PaymentVO(paymentPO.getCreateTime(),paymentPO.getPaymentID(),paymentPO.getOperator(),paymentPO.getReceicerID(),paymentPO.getAccountID(),new ArrayList<>(paymentPO.getSheet()),paymentPO.getTotalAmount(),paymentPO.getState(),paymentPO.getRemark(),new ArrayList<>(paymentPO.getInformation()));
        return paymentVO;
    }

    ArrayList<PaymentVO> paymentPosToVos(ArrayList<PaymentPO> paymentPOS){
        ArrayList<PaymentVO> paymentVOS=new ArrayList<>();
        for(PaymentPO po:paymentPOS){
            paymentVOS.add(this.paymentPoToVo(po));
        }
        return paymentVOS;
    }
}
