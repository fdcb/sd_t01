package example;

import java.io.*;
import java.util.Vector;

/**
 * Created by Filipa on 10/04/2016.
 */
public class ReadWriteFile{

    static public Vector<User> readFileUser(){
        Vector<User> allUsers = new Vector <>();
        File file = new File(User.USER_FILENAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            allUsers = (Vector<User>)is.readObject();
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
            os.writeObject(allUsers);
            os.flush();
        }catch (IOException e){
            System.out.println("Error writing in file.");
        }
    }

    static public Vector<UC> readFileUC(){
        Vector<UC> allUCs = new Vector <>();
        File file = new File(UC.FILE_NAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            allUCs = (Vector<UC>)is.readObject();
        }catch (IOException e){
            System.out.println("Error reading file");
        }
        catch (ClassNotFoundException e){
            System.exit(0);
        }
        return allUCs;
    }

    static public void writeFileUC(Vector<UC> allUCs){
        File file = new File(UC.FILE_NAME);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                    (file)));
            os.writeObject(allUCs);
            os.flush();
        }catch (IOException e){
            System.out.println("Error writing in file.");
        }
    }

    static public Vector<Exercise> readFileExercises(){
        Vector<Exercise> allExercises = new Vector <>();
        File file = new File(Exercise.FILE_NAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            allExercises = (Vector<Exercise>)is.readObject();
        }catch (IOException e){
            System.out.println("Error reading file");
        }
        catch (ClassNotFoundException e){
            System.exit(0);
        }
        return allExercises;
    }

    static public void writeFileExercises(Vector<Exercise> allExercises){
        File file = new File(Exercise.FILE_NAME);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                    (file)));
            os.writeObject(allExercises);
            os.flush();
        }catch (IOException e){
            System.out.println("Error writing in file.");
        }
    }

    static public Vector<Solution> readFileSolution(){
        Vector<Solution> allSolution = new Vector <>();
        File file = new File(Solution.FILE_NAME);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream
                    (file));
            allSolution = (Vector<Solution>)is.readObject();
        }catch (IOException e){
            System.out.println("Error reading file");
        }
        catch (ClassNotFoundException e){
            System.exit(0);
        }
        return allSolution;
    }

    static public void writeFileSolution(Vector<Solution> allSolutions){
        File file = new File(Solution.FILE_NAME);
        try{
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                    (file)));
            os.writeObject(allSolutions);
            os.flush();
        }catch (IOException e){
            System.out.println("Error writing in file.");
        }
    }
}
