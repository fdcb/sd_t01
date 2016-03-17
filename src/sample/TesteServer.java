package sample;

import java.net.MalformedURLException;
import java.rmi.*;
/**
 * Created by Filipa on 17/03/2016.
 */
public class TesteServer {

	public TesteServer(){
		System.setSecurityManager(new RMISecurityManager());
		try{
			Teste a = new User("ana","aninha");
			Naming.rebind("rmi://localhost:1099/TesteService",a);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
