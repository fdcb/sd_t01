package example;

/**
 * Represents a Solution of a specific {@link Exercise}
 * A solution has an unique code within a {@link Exercise}, the id of the {@link User}
 * posting it, the id of the exercise it belongs to, the id of the UC the
 * exercise is about, a state and the solution of the exercise.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class Solution {
    private String description, state;
    private int cod, userCod, exerciseCod, ucCod;

    /**
     * Used whenever a solution hasn't been seen by the user who posted the
     * exercise.
     * */
    public static final String STATE_UNSEEN = "Unseen";
    /**
     * Used whenever a solution was seen by the user who posted the exercise
     * and evaluated as correct.
     * */
    public static final String STATE_CORRECT = "Correct";
    /**
     * Used whenever a solution was seen by the user who posted the exercise
     * and evaluated as incorrect.
     * */
    public static final String STATE_INCORRECT = "Incorrect";
    /**
     * Used whenever an unregistered users posted a solution.
     * */
    public static final int GUEST_COD = -1;

    /**
     * Creates a new Solution with the given description and code of the user
     * posting it, code of the UC it is related to, the code of the exercise
     * being solved and the code of the solution.
     * Sets the {@link Solution#state} of the Solution to {@value STATE_UNSEEN}
     *
     * @param description	Solution of the exercise
     * @param cod			Code of the solution
     * @param userCod		Code of the user posting the solution
     * @param exerciseCod	Code of the exercised being resolved
     * @param ucCod			Code of the UC the exercise is related to
     */
    public Solution(String description, int cod, int userCod, int exerciseCod, int ucCod) {
        this.description = description;
        this.cod = cod;
        this.userCod = userCod;
        this.exerciseCod = exerciseCod;
        this.ucCod = ucCod;
        this.state = STATE_UNSEEN;
    }

    /**
     * Creates a new Solution with the given description, code of the UC it
     * is related to, the code of the exercise being solved and the code of
     * the solution.
     * Sets the {@link Solution#state} of the Solution to {@value STATE_UNSEEN}
     * Since {@link Solution#userCod} isn't given, assumes users is posting as a guest
     * giving it the {@value GUEST_COD}.
     *
     * @param description	Solution of the exercise
     * @param cod			Code of the solution
     * @param exerciseCod	Code of the exercised being resolved
     * @param ucCod			Code of the UC the exercise is related to
     */
    public Solution(String description, int cod, int exerciseCod, int ucCod) {
        this.description = description;
        this.cod = cod;
        this.exerciseCod = exerciseCod;
        this.ucCod = ucCod;
        this.userCod = GUEST_COD;
        this.state = STATE_UNSEEN;

    }

    /**
     * Gets the Solution posted for a certain exercise.
     *
     * @return The solution for the exercise
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the state of a solution.
     * {@value STATE_UNSEEN} if the solution hasn't been examined yet.
     * {@value STATE_CORRECT} if the solution is correct.
     * {@value STATE_INCORRECT} if the solution wasn't correct.
     *
     * @return The state of the exercise.
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the code of the solution.
     *
     * @return The code of this solution
     */
    public int getCod() {
        return cod;
    }

    /**
     * Gets the identifier of the user who posted this solution.
     *
     * @return The code of the user solving the exercise
     */
    public int getUserCod() {
        return userCod;
    }

    /**
     * Gets the code of the exercise this solution refers to.
     *
     * @return The identifier of the exercise being solved
     */
    public int getExerciseCod() {
        return exerciseCod;
    }

    /**
     * Gets the identifier of the UC to which the exercise being solved belongs.
     * to.
     *
     * @return The code of the UC this solution is about.
     */
    public int getUcCod() {
        return ucCod;
    }

    /**
     * Changes the {@link Solution#state} of the solution to
     * {@value STATE_CORRECT}.
     *
     */
    public void setStateCorrect() {
        this.state = STATE_CORRECT;
    }

    /**
     * Changes the {@link Solution#state} of the solution to
     * {@value STATE_INCORRECT}.
     */
    public void setStateIncorrect() {
        this.state = STATE_INCORRECT;
    }

    /**
     * Checks if this solution was posted by a guest or a registered user.
     * A solution was posted by a guest if the value of
     * {@link Solution#userCod} is {@value GUEST_COD}
     *
     * @return True is solution was posted by guest, false otherwise.
     */
    public boolean postedByGuest(){
        return this.userCod == GUEST_COD;
    }
}