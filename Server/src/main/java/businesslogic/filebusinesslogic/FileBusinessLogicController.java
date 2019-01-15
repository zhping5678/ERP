package businesslogic.filebusinesslogic;

import businesslogicservice.filebusinesslogicservice.FileBusinessLogicService;
import data.filedata.FileDataController;
import dataservice.filedataservice.FileDataService;
import util.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FileBusinessLogicController extends UnicastRemoteObject implements FileBusinessLogicService,FileBusinessLogicControllerAccess{
    FileDataService fileDataService;
    public FileBusinessLogicController() throws RemoteException{
        fileDataService=new FileDataController();
    }
    @Override
    public ArrayList<ArrayList<String>> listReviewed() throws RemoteException {
        ArrayList<ArrayList<String>> res=new ArrayList<>();
        ArrayList<String> excessOrLoss=this.excessOrLoss();
        ArrayList<String> payment=this.payment();
        ArrayList<String> purchase=this.purchase();
        ArrayList<String> receipt=this.receipt();
        ArrayList<String> sale=this.sale();
        ArrayList<String> saleReturn=this.saleReturn();
        res.add(excessOrLoss);
        res.add(payment);
        res.add(purchase);
        res.add(receipt);
        res.add(sale);
        res.add(saleReturn);
        return res;
    }

    @Override
    public ArrayList<String> excessOrLoss() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> excessOrLoss=fileDataService.listExcessPO();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return excessOrLoss;
    }

    @Override
    public ArrayList<String> payment() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> payment=fileDataService.listPaymentPO();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return payment;
    }

    @Override
    public ArrayList<String> purchase() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> purchase=fileDataService.listPurchasepo();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return purchase;
    }

    @Override
    public ArrayList<String> receipt() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> receipt=fileDataService.listReceiptPO();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return receipt;
    }

    @Override
    public ArrayList<String> sale() {
        HibernateUtil.getCurrentSession().beginTransaction();
        ArrayList<String> sale=fileDataService.listSalePO();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return sale;
    }

    @Override
    public ArrayList<String> saleReturn() {
        HibernateUtil.getCurrentSession().getTransaction();
        ArrayList<String> saleReturn=fileDataService.listSaleReturnPO();
        HibernateUtil.getCurrentSession().getTransaction().commit();
        return saleReturn;
    }
}
