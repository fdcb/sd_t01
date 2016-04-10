package sample;

import example.RMIInterface;
import example.User;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by Filipa on 10/04/2016.
 */
public class ConnectServer{

    static public User validateUser(String userName, String password){
        String serverName = "";
        System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
                "\\java.policy");
        System.setSecurityManager(new RMISecurityManager());
        try {
            serverName = java.net.InetAddress.getLocalHost().getHostName();
        } catch(Exception e){
            e.printStackTrace();
        }
        if (serverName.equals("") ) {
            System.out.println("usage: java Client < host running RMI server>");
            System.exit(0);
        }
        try {
            //bind server object to object in client
            RMIInterface myServerObject =
                    (RMIInterface) Naming.lookup("//"+serverName+"/Impl");
            //invoke method on server object
            return myServerObject.validateUser(userName,password);

        } catch(Exception e) {
            System.out.println("Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public User registerUser(String userName, String password,
                                    String email){
        String serverName = "";
        System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
                "\\java.policy");
        System.setSecurityManager(new RMISecurityManager());
        try {
            serverName = java.net.InetAddress.getLocalHost().getHostName();
        } catch(Exception e){
            e.printStackTrace();
        }
        if (serverName.equals("") ) {
            System.out.println("usage: java Client < host running RMI server>");
            System.exit(0);
        }
        try {
            //bind server object to object in client
            RMIInterface myServerObject =
                    (RMIInterface) Naming.lookup("//"+serverName+"/Impl");
            //invoke method on server object
            return myServerObject.registerUser(userName, password, email);

        } catch(Exception e) {
            System.out.println("Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }
}
