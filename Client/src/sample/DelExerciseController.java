package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class with the methods to help with the DeleteExercise scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class DelExerciseController implements Initializable{

    @FXML private Button delExerciseNoButton;
    @FXML private Button delExerciseYesButton;

    /**
     * Title of the DeleteExercise window.
     */
    public static final String WINDOW_TITLE = "delete a Exercise";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "DeleteExercise.fxml" ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assertAll();
        noButtonActionPerformed();
        yesButtonActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert delExerciseNoButton!= null : "fx:id=\"delExerciseNoButton\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
        assert delExerciseYesButton!= null : "fx:id=\"delExerciseYesButton\"" +
                " was not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link DelExerciseController#delExerciseNoButton} so that it
     * returns to the login window when clicked.
     */
    private void noButtonActionPerformed(){
        delExerciseNoButton.setOnAction(event ->
                Main.gotoNewScene((Stage) delExerciseNoButton.getScene().getWindow(),
                        Main.L_FXML, LoginController.WINDOW_TITLE,0,"")
        );
    }

    /**
     * Changes the {@link DelExerciseController#delExerciseYesButton} so that it
     * deletes the selected exercise.
     */
    private void yesButtonActionPerformed(){
       delExerciseYesButton.setOnAction(event ->{
            String exercise = delExerciseYesButton.getText();
            System.out.println(exercise);
        });
    }
}
