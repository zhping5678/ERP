package businesslogic.userbusinesslogic;

/*
 *@Name:
 *@Description:
 *@Author: Jane
 @Date: 2017/12/15 1:52
 */

import java.util.ArrayList;

public interface UserBusinessLogicControllerAccess {
    boolean getUserRight(String userID);
    ArrayList<String> getUserList();
    ArrayList<String> getWarehousemanList();
    ArrayList<String> getSalesmanList();
    ArrayList<String> getFinancialOfficerList();
    String getManageID();
}
