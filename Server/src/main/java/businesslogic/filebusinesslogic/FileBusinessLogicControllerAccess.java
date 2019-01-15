package businesslogic.filebusinesslogic;

import java.util.ArrayList;

public interface FileBusinessLogicControllerAccess {
    ArrayList<String> excessOrLoss();
    ArrayList<String> payment();
    ArrayList<String> purchase();
    ArrayList<String> receipt();
    ArrayList<String> sale();
    ArrayList<String> saleReturn();
}
