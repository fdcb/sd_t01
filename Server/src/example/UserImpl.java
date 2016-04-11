package example;

import java.io.*;
import java.nio.file.Files;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Created by Filipa on 10/04/2016.
 */
public class UserImpl extends UnicastRemoteObject implements RMIInterface{
    final public String name = "UserImpl";

    public UserImpl(String name) throws RemoteException{
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

    public User validateUser(String name, String password) throws
            RemoteException{

        Vector<User> allUsers = ReadWriteFile.readFileUser();
        if(allUsers == null) System.out.println("doing something wrong");
        for(int i = 0; i < allUsers.size(); i++){
            if(allUsers.elementAt(i).getUsername().equals(name) && allUsers
                    .elementAt(i).getPassword().equals(password))
                return allUsers.elementAt(i);
        }
        return null;
    }

    public User registerUser(String name, String password, String email) throws
            RemoteException{

        Vector<User> allUsers = ReadWriteFile.readFileUser();
        User tempUser;
        if(allUsers != null)
            for(int i = 0; i < allUsers.size(); i++)
                if(allUsers.elementAt(i).getUsername().equals(name) || allUsers
                        .elementAt(i).getEmail().equals(email))
                    return allUsers.elementAt(i);

        if(email.equals(""))
            tempUser = new User(name, password);
        else
            tempUser = new User(name, password, email);
        if(allUsers == null)
            allUsers = new Vector <>();
        allUsers.add(tempUser);
        ReadWriteFile.writeFileUser(allUsers);
        return tempUser;
    }
}
