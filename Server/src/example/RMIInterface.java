package example;

import java.rmi.RemoteException;
import java.util.Vector;

public interface RMIInterface extends java.rmi.Remote{


    User validateUser(String name, String password) throws RemoteException;
    User registerUser(String name, String password, String email)
            throws RemoteException;
    UC addUC(String className, String uniName) throws RemoteException;
    Vector<UC> getAllUC() throws RemoteException;
    Vector<Exercise> getExercisesFromUC(int ucCod) throws RemoteException;
    void addExercise(int ucCod, int userId, String description) throws
            RemoteException;

   //public java.util.Date getDate() throws java.rmi.RemoteException;
}
