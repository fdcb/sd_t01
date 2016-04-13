package sample;

import example.Exercise;
import example.Solution;
import example.UC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Class with the methods to help with the CheckSolutions scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class CheckSolutionsController implements Initializable{

    @FXML private Label solutionCSLabel;
    @FXML private Button correctCSButton;
    @FXML private Button wrongCSButton;
    @FXML private Button backCSButton;

    Solution solution;
    /**
     * Title of the CheckSolutions SearchUC window.
     */
    public static final String WINDOW_TITLE = "Exercise's Solution";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "CheckSolutions.fxml" ;

    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assertAll();
        solution = ConnectServer.getSolution();
        solutionCSLabel.setText(solution.getDescription());
        Vector<Exercise> exerciseVector = ConnectServer.getExerciseFromUC();
        int i = 0;
        for(; i < exerciseVector.size(); i++)
            if(exerciseVector.elementAt(i).getCod() == Main.exercise_id)
                break;
        if(exerciseVector.elementAt(i).getUserCod() != Main.user_id){
            correctCSButton.setVisible(false);
            wrongCSButton.setVisible(false);
        }

        correctButtonActionPerformed();
        wrongButtonActionPerformed();
        backButtonActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */

    private void assertAll(){
        assert solutionCSLabel != null : "fx:id=\"solutionCSLabel\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert correctCSButton != null : "fx:id=\"correctCSButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert wrongCSButton != null : "fx:id=\"wrongCSButton\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
        assert backCSButton != null : "fx:id=\"backCSButton\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link CheckSolutionsController#backCSButton} so that it
     * returns to the previous menu.
     */
    private void backButtonActionPerformed(){
        backCSButton.setOnAction(event ->
                Main.gotoNewScene((Stage)backCSButton.getScene().getWindow(),
                        Main.SS_FXML, SearchSolutionController.WINDOW_TITLE, 0, "")
        );
    }

    /**
     * Changes the {@link CheckSolutionsController#wrongCSButton} so that it
     * returns to the previous menu.
     */
    private void wrongButtonActionPerformed(){
        wrongCSButton.setOnAction(event -> {
            ConnectServer.changeSolutionState(Solution.STATE_INCORRECT);
            Main.gotoNewScene((Stage)correctCSButton.getScene().getWindow(),
                    Main.SS_FXML, SearchSolutionController.WINDOW_TITLE, 0 , "");
        }
        );
    }

    /**
     * Changes the {@link CheckSolutionsController#wrongCSButton} so that it
     * returns to the previous menu.
     */
    private void correctButtonActionPerformed(){
        correctCSButton.setOnAction(event -> {
            ConnectServer.changeSolutionState(Solution.STATE_CORRECT);
            Main.gotoNewScene((Stage)correctCSButton.getScene().getWindow(),
                    Main.SS_FXML, SearchSolutionController.WINDOW_TITLE, 0 , "");
        }
        );
    }
}
