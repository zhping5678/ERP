package Start;


import businesslogicservice.ServiceFactory;
import businesslogicservice.logbusinesslogicservice.LogBusinessLogicService;
import businesslogicservice.userbusinesslogicservice.UserBusinessLogicService;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}

	/*
	public IOService getIOService(){
		return (IOService)remote;
	}
	
	public UserService getUserService(){
		return (UserService)remote;
	}
	*/

	public ServiceFactory getServiceFactory(){
		return (ServiceFactory)remote;
	}

	public LogBusinessLogicService getLogBussinessLogicService(){
		LogBusinessLogicService l=null;
		try {
			l= ((ServiceFactory) remote).getLogBusinessLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return l;
	}

	public UserBusinessLogicService getUSerBussinessLogicService(){
		UserBusinessLogicService l=null;
		try {
			l=((ServiceFactory) remote).getUserBusinessLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return l;
	}
	
}
