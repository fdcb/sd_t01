package sample;

import java.net.MalformedURLException;
import java.rmi.*;
/**
 * Created by Filipa on 17/03/2016.
 */
public class TesteServer {

	public TesteServer(){
	//	System.setProperty("java.rmi.server.hostname","192.168.206.96");
		System.setProperty("java.security.policy", "file:/C:/T01SD/java" +
				".policy");

		System.setSecurityManager(new RMISecurityManager());
		try{
		//
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			//Naming.rebind("rmi://localhost:1099/TesteService",a);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	/*	try {
			//Teste a = new Teste("Server");
			//User a = new User("ana","aninha");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		/* catch (MalformedURLException e) {
			e.printStackTrace();
		}*/
	}

	public static void main(String[] args) {
		//launch(args);
		new TesteServer();
	}
}
