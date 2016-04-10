package example;

import java.io.*;
import java.util.Vector;

/**
 * Created by Filipa on 10/04/2016.
 */
public class ReadWriteFile{

    static public Vector<User> readFileUser(){

        Vector<User> allUsers = null;
        User tempUser;

        File file = new File(User.USER_FILENAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            while((tempUser = (User)is.readObject())!=null)
                allUsers.add(tempUser);
        }catch (IOException e){
            return null;
        }
        catch (ClassNotFoundException e){
            System.exit(0);
        }

        return allUsers;
    }

    static public void writeFileUser(Vector<User> allUsers){

        File file = new File(User.USER_FILENAME);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                    (file)));
            for(int i = 0; i < allUsers.size(); i++)
                os.writeObject(allUsers.elementAt(i));
            os.flush();
        }catch (IOException e){
            System.exit(0);
        }
    }
}
