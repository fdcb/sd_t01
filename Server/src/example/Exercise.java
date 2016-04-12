package example;

import java.io.Serializable;

/**
 * Represents an Exercise related to a specific {@link UC}
 * An exercise has an unique code within a {@link UC}, the id of the {@link User}
 * posting it, a state, the description of the exercise and the number of
 * solutions existent related to this exercise.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class Exercise implements Serializable{

    private String state;
    private String description;
    private int userCod, ucCod, cod, solutionCount = 0;

    /**
     * Used whenever a correct answer was given for the exercise.
     * */
    public static final String STATE_CLOSED = "Closed";
    /**
     * Used while there isn't any correct answer for the exercise.
     * */
    public static final String STATE_OPEN =	"Open";

    public static final String FILE_NAME = "Exercises.dat";

    /**
     * Creates a new Exercise with the given description and code of the user
     * posting it, code of the UC it is related to and the code of the exercise.
     *
     * @param description	Description of the exercise
     * @param userCod		Code to identify the user posting the exercise
     * @param ucCod			Code to identify the UC this exercise is related to
     * @param cod			Identifier of this exercise within the UC.
     */
    public Exercise(String description, int userCod, int ucCod, int cod) {
        this.description = description;
        this.userCod = userCod;
        this.ucCod = ucCod;
        this.cod = cod;
        this.state = STATE_OPEN;
    }

    /**
     * Gets the description of this exercise.
     *
     * @return The exercise's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the {@link Exercise#state} of this exercise.
     * {@value STATE_CLOSED} if the exercise is closed.
     * {@value STATE_OPEN} if the exercise is open.
     *
     * @return The state of the exercise
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the code of the user who posted this exercise.
     *
     * @return Identifier of the exercise's author
     */
    public int getUserCod() {
        return userCod;
    }

    /**
     * Gets the code of this exercise's UC.
     *
     * @return Code of the UC this exercise belongs to.
     */
    public int getUcCod() {
        return ucCod;
    }

    /**
     * Gets the identifier of this exercise.
     *
     * @return Code of the exercise
     */
    public int getCod() {
        return cod;
    }

    /**
     * Changes the exercise {@link Exercise#state} to {@value STATE_CLOSED}.
     */
    public void setStateClosed() {
        this.state = STATE_CLOSED;
    }

    /**
     * Gets the total number of solutions posted for this exercise.
     *
     * @return The number of solutions for this exercise.
     */
    public int getSolutionCount() {
        return solutionCount;
    }

    /**
     * Increases the number of solutions posted for this exercise by one.
     */
    public void addSolutionCount() {
        this.solutionCount++;
    }
}