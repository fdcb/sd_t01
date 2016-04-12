package example;

import java.rmi.RemoteException;

public interface RMIInterface extends java.rmi.Remote{

   User validateUser(String name, String password) throws RemoteException;
   User registerUser(String name, String password, String email)
           throws RemoteException;
   UC addUC(String className, String uniName) throws RemoteException;
   //public java.util.Date getDate() throws java.rmi.RemoteException;
}
