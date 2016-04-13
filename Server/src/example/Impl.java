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
public class Impl extends UnicastRemoteObject implements RMIInterface{

    public Impl(String name) throws RemoteException{
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

    public User validateUser(String name, String password){

        Vector<User> allUsers = ReadWriteFile.readFileUser();
        for(int i = 0; i < allUsers.size(); i++)
            if(allUsers.elementAt(i).getUsername().equals(name) && allUsers
                    .elementAt(i).getPassword().equals(password))
                return allUsers.elementAt(i);

        return null;
    }

    public User registerUser(String name, String password, String email){

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

    public UC addUC(String className, String uniName){
        Vector<UC> allUCs = ReadWriteFile.readFileUC();
        for(int i = 0; i < allUCs.size(); i++)
            if(allUCs.elementAt(i).getClass().equals(className))
                return null;

        UC newUC = null;
        if(uniName.equals(""))
            newUC = new UC(className);
        else
            newUC = new UC(className,uniName);
        allUCs.add(newUC);
        ReadWriteFile.writeFileUC(allUCs);
        return newUC;
    }

    public Vector<UC> getAllUC(){
        return ReadWriteFile.readFileUC();
    }

    public Vector<Exercise> getExercisesFromUC(int ucCod){
        Vector<Exercise> allExercises = ReadWriteFile.readFileExercises(),
                exercisesFromUC = new Vector <>();
        for(int i = 0; i < allExercises.size(); i++)
            if(allExercises.elementAt(i).getUcCod() == ucCod)
                exercisesFromUC.add(allExercises.elementAt(i));
        return exercisesFromUC;
    }

    public void addExercise(int ucCod, int userId, String description){
        Vector<Exercise> allExercise = ReadWriteFile.readFileExercises();
        Vector<UC> ucVector = ReadWriteFile.readFileUC();
        int i = 0;
        for(; i < ucVector.size(); i++)
            if(ucVector.elementAt(i).getCod() == ucCod)
                break;
        Exercise exercise = new Exercise(description,userId,ucCod,ucVector
                .elementAt(i).getExerciseCount());
        ucVector.elementAt(i).addExerciseCount();
        allExercise.add(exercise);
        ReadWriteFile.writeFileExercises(allExercise);
        ReadWriteFile.writeFileUC(ucVector);
    }

    public Vector<Solution> getSolutionsFromExercise(int exerCod, int ucCod){
        Vector<Solution> allSolutions = ReadWriteFile.readFileSolution(),
                solutionsFromExercise = new Vector <>();
        int i = 0;
        for(; i < allSolutions.size(); i++)
            if(allSolutions.elementAt(i).getExerciseCod() == exerCod &&
                    allSolutions.elementAt(i).getUcCod() == ucCod)
                solutionsFromExercise.add(allSolutions.elementAt(i));
        return solutionsFromExercise;
    }

    public void addSolution(String description, int cod, int userCod,
                            int exerciseCod, int ucCod){
        
    }
}
