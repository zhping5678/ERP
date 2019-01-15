package pkg.ui.accountui;

import javafx.collections.ObservableList;
import pkg.unclassified.ItemText;
import vo.accountvo.AccountVO;

public interface AccountUIControllerAccess {
    public static AccountUIControllerAccess accountUIControllerAccess = AccountUIController.getInstance();
    public ObservableList<ItemText> getAccountList();
    public AccountVO getAccountVO(String id);
}
