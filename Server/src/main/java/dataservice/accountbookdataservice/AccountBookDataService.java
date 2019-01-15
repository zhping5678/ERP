package dataservice.accountbookdataservice;

import infor.AccountBookInfor;

import java.util.ArrayList;

public interface AccountBookDataService {

    void write(AccountBookInfor accountBookPO);
    AccountBookInfor read(String ID);
    ArrayList<AccountBookInfor> list();

}


