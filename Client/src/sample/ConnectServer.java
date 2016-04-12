package sample;

import example.RMIInterface;
import example.*;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Vector;

/**
 * Created by Filipa on 10/04/2016.
 */
public class ConnectServer{

    static public User validateUser(String userName, String password){

        try {
            return Main.myServerObject.validateUser(userName,password);

        } catch(Exception e) {
            System.out.println("VU: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public User registerUser(String userName, String password,
                                    String email){
        try {
            return Main.myServerObject.registerUser(userName, password, email);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public UC addUC(String className, String uniName){
        try {
            return Main.myServerObject.addUC(className,uniName);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public Vector<UC> getAllUC(){
        try {
            return Main.myServerObject.getAllUC();
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }
}
