package businesslogic.myfilebusinesslogic.warehousemanfilebusinesslogic;


import vo.filevo.ExcessVO;
import infor.Infor;

import java.util.ArrayList;


public interface WarehouseManBusinessLogicControllerAccess {

    void approveExcessOrLoss(String userID, String fileID, boolean result, Infor infor);
    ArrayList<String> waitReview();

    ExcessVO showExcessDetail(String id);

}
