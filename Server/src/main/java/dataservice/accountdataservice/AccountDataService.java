package dataservice.accountdataservice;

/*
 *@Name: AccountDataService
 *@Description:
 *@Author: Jane
 *@Date: 2017/12/3 1:51
 */

import po.accountpo.AccountPO;

import java.util.ArrayList;

public interface AccountDataService{
    void write(AccountPO po);
    AccountPO read(String ID);
    void remove(String ID);
    ArrayList<AccountPO> showList();
    ArrayList<AccountPO> findByKeywords(String keywords);
    void update(AccountPO po);

//    public ArrayList<String> test(String a);
}

