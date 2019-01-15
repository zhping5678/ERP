package dataservice.filedataservice;


import po.filepo.FileListPO;
import po.filepo.FilePO;

import java.util.ArrayList;

public interface FileDataService {
    public void write(FilePO filePO);
    public FilePO read(String ID);
    public void remove(String ID);
    public ArrayList<String> listExcessPO();
    public ArrayList<String> listPaymentPO();
    public ArrayList<String> listPurchasepo();
    public ArrayList<String> listReceiptPO();
    public ArrayList<String> listSalePO();
    public ArrayList<String> listSaleReturnPO();
}
