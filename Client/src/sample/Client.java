package sample;

import java.rmi.*;
import java.util.Date;

public class Client{
    public static void main(String[] argv) {
        String serverName = "";
        System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
                "\\java.policy");
        System.setSecurityManager(new RMISecurityManager());
        if (argv.length != 1) {
            try {
                serverName = java.net.InetAddress.getLocalHost().getHostName();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        else {
            serverName = argv[0];
        }
        if (serverName.equals( "") ) {
            System.out.println("usage: java Client < host running RMI server>");
            System.exit(0);
        }
        try {
            //bind server object to object in client
            RMIInterface myServerObject =
                    (RMIInterface) Naming.lookup("//"+serverName+"/Impl");
            //invoke method on server object
            Date d = myServerObject.getDate();
            System.out.println("Date on server is " + d);
        } catch(Exception e) {
            System.out.println("Exception occured: " + e);
            System.exit(0);
        }
        System.out.println("RMI connection successful");
    }
}
