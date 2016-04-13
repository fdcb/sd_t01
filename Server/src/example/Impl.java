package example;

import java.io.*;
import java.nio.file.Files;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
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

    public User registerUser(String name, String password, String email) {
        Vector<User> allUsers = ReadWriteFile.readFileUser();
        User tempUser;
        if(allUsers != null)
            for(int i = 0; i < allUsers.size(); i++)
                if(allUsers.elementAt(i).getUsername().equals(name) || allUsers
                        .elementAt(i).getEmail().equals(email))
                    return null;

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
            newUC = new UC(className, allUCs.size() + 1);
        else
            newUC = new UC(className,uniName, allUCs.size() + 1);
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

    public void addSolution(String description, int userCod,
                            int exerciseCod, int ucCod){
        Vector<Solution> allSolutions = ReadWriteFile.readFileSolution();
        Vector<Exercise> allExercises = ReadWriteFile.readFileExercises();
        int i = 0;
        for(; i < allExercises.size(); i++)
            if(allExercises.elementAt(i).getCod() == exerciseCod &&
                    allExercises.elementAt(i).getUcCod() == ucCod)
                break;
        Solution solution;
        allExercises.elementAt(i).addSolutionCount();
        int position = allExercises.elementAt(i).getSolutionCount();
        if(userCod == Solution.GUEST_COD)
            solution = new Solution(description, position, exerciseCod, ucCod);
        else
            solution = new Solution(description, position, userCod,exerciseCod,ucCod);
        allSolutions.add(solution);
        ReadWriteFile.writeFileExercises(allExercises);
        ReadWriteFile.writeFileSolution(allSolutions);
    }

    public void changeSolutionState(int exerciseCod, int ucCod, int solCod,
                                    String state){
        Vector<Solution> allSolutions = ReadWriteFile.readFileSolution();
        int i = 0;
        for(; i < allSolutions.size(); i++)
            if(allSolutions.elementAt(i).getCod() == solCod &&
                    allSolutions.elementAt(i).getExerciseCod() == exerciseCod &&
                    allSolutions.elementAt(i).getUcCod() == ucCod) {
                if (state.equals(Solution.STATE_CORRECT))
                    allSolutions.elementAt(i).setStateCorrect();
                else
                    allSolutions.elementAt(i).setStateIncorrect();
                break;
            }
        ReadWriteFile.writeFileSolution(allSolutions);
    }

    public Solution getSolution(int exerciseCod, int ucCod, int solCod){
        Vector<Solution> solutionsFromExercise = getSolutionsFromExercise
                (exerciseCod,ucCod);
        for(int i = 0; i < solutionsFromExercise.size(); i++)
            if(solutionsFromExercise.elementAt(i).getCod() == solCod)
                return solutionsFromExercise.elementAt(i);
        return null;
    }

    public void changeExerciseState(int exerciseCod, int ucCod){
        Vector<Exercise> exerciseVector = ReadWriteFile.readFileExercises();
        for(int i = 0; i < exerciseVector.size(); i++)
            if(exerciseVector.elementAt(i).getUcCod() == ucCod &&
                    exerciseVector.elementAt(i).getCod() == exerciseCod){
                exerciseVector.elementAt(i).setStateClosed();
                break;
            }
        ReadWriteFile.writeFileExercises(exerciseVector);
    }
}
