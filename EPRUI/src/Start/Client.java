package Start;

import Login.main;
import businesslogicservice.warehousebusinesslogicservice.WarehouseBusinessLogicService;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {


    public static void main(String[] args){
        try {
           WarehouseBusinessLogicService remote= (WarehouseBusinessLogicService) Naming.lookup("rmi://localhost:8888/WarehouseBusinessLogicService");
           remote.connectionTest();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        /*
        main main =new main();
        main.start(new Stage());
        */
    }
}
