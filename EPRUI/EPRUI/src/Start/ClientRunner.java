package Start;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Occupations.WarehouseManagerMain;
import javafx.stage.Stage;


public class ClientRunner {
	private RemoteHelper remoteHelper;

	public ClientRunner() {
		linkToServer();
	}

	private void linkToServer() {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://172.28.134.219:6666/ServiceFactory"));
			//Naming.lookup("rmi://localhost:8888/ServiceFactory");
			System.out.println("linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	/*
	public void test(){
		try {
			System.out.println(remoteHelper.getUserService().login("admin", "123456a"));
			System.out.println(remoteHelper.getIOService().writeFile("2", "admin", "testFile"));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	*/

	public static void main(String[] args){
		ClientRunner cr = new ClientRunner();
		//cr.test();
	}
}
