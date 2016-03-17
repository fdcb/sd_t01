package sample;

/**
 * Created by Filipa on 17/03/2016.
 */
public interface Teste extends java.rmi.Remote{

	String getUsername() throws java.rmi.RemoteException;
	String getName() throws java.rmi.RemoteException;
	String getEmail()  throws java.rmi.RemoteException;
	int getCod()  throws java.rmi.RemoteException;
}
