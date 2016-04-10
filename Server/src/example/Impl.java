package example;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/*

public class Impl extends UnicastRemoteObject implements RMIInterface
{
    public Impl(String name) throws RemoteException {
        super();
        try {
            Naming.rebind(name, this);
        }
        catch (Exception e) {
            if (e instanceof RemoteException)
                throw (RemoteException)e;
            else
                throw new RemoteException(e.getMessage());
        }
    }
    public java.util.Date getDate() {
        System.out.println(" Método remoto -- Impl.getDate()");
        return new java.util.Date();
    }
}
*/