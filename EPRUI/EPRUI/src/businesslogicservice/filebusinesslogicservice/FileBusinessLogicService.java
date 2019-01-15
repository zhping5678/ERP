package businesslogicservice.filebusinesslogicservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FileBusinessLogicService {
    ArrayList<ArrayList<String>> listReviewed() throws RemoteException;
}
