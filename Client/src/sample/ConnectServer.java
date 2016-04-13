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

    static public Vector<Exercise> getExerciseFromUC(){
        try {
            return Main.myServerObject.getExercisesFromUC(Main.uc_id);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public void addExercise(String description){
        try {
            Main.myServerObject.addExercise(Main.uc_id, Main.user_id,
                    description);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
    }

    static public Vector<Solution> getSolutionsFromExercise(){
        try {
            return Main.myServerObject.getSolutionsFromExercise(Main
                    .exercise_id, Main.uc_id);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public void addSolution(String description){
        try {
            Main.myServerObject.addSolution(description,Main.user_id,Main
                    .exercise_id,Main.uc_id);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
    }

    static public void changeSolutionState(String state){
            try {
            Main.myServerObject.changeSolutionState(Main.exercise_id, Main
                    .uc_id, Main.solution_id, state);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
    }

    static public Solution getSolution(){
        try {
            return Main.myServerObject.getSolution(Main.exercise_id, Main
                    .uc_id, Main.solution_id);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
        return null;
    }

    static public void changeExerciseState(){
        try {
            Main.myServerObject.changeExerciseState(Main.exercise_id,
                    Main.uc_id);
        } catch(Exception e) {
            System.out.println("CS: Exception occured: " + e);
            System.exit(0);
        }
    }

}
