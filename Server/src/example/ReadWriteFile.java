package example;

import java.io.*;
import java.util.Vector;

/**
 * Created by Filipa on 10/04/2016.
 */
public class ReadWriteFile{

    static public Vector<User> readFileUser(){

        Vector<User> allUsers = new Vector <>();
        User tempUser;

        File file = new File(User.USER_FILENAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            allUsers.add((User)is.readObject());
          /*  while((tempUser = (User)is.readObject())!=null){
                allUsers.add(tempUser);
                System.out.println("whyyy");
            }*/
        }catch (IOException e){
            System.out.println("Error reading file");
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
            System.out.println("Error writing in file.");
        }
    }
}
