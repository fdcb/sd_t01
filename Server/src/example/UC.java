package example;

import java.io.Serializable;

/**
 * Represents a class from a university.
 * A class has an unique code, a name, an university name and the number of
 * exercises existent in the platform related to this UC.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class UC implements Serializable{
    private String name, uni;
    private static int cod = 0;
    private int exerciseCount = 0;

    /**
     * Used when the user doesn't specify the university name.
     * */
    public static final String NO_UNIVERSITY = "Unknown";

    public static final String FILE_NAME = "Uc.dat";

    /**
     * Creates a new UC with the given UC name and University name.
     *
     * @param name	Name of the UC
     * @param uni	Name of the University
     */
    public UC(String name, String uni){
        this.name = name;
        this.uni = uni;
        this.cod++;
    }

    /**
     * Creates a new UC with the given UC name.
     * Sets {@link UC#uni} has {@value NO_UNIVERSITY}.
     *
     * @param name	Name of the UC
     */
    public UC(String name){
        this.name = name;
        this.uni = NO_UNIVERSITY;
        this.cod++;
    }

    /**
     * Gets the university name of this UC.
     *
     * @return	University's name
     */
    public String getUni() {
        return uni;
    }

    /**
     * Gets the name of this UC.
     *
     * @return UC's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the code of this UC.
     *
     * @return The code of this UC
     */
    public static int getCod() {
        return cod;
    }

    /**
     * Gets the number of existent exercises related with this UC.
     *
     * @return Number of exercises
     */
    public int getExerciseCount() {
        return exerciseCount;
    }

    /**
     * Increases the number of exercises related with this UC by one.
     */
    public void addExerciseCount() {
        this.exerciseCount++;
    }
}