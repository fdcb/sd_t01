package sample;

import example.Exercise;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Class with the methods to help with the AddSolution scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class AddSolutionController implements Initializable{

    @FXML private Button submitASButton;
    @FXML private Button cancelASButton;
    @FXML private TextArea solutionASTextArea;
    @FXML private Label exerDescASLabel;

    /**
     * Title of the Add Solution window.
     */
    public static final String WINDOW_TITLE = "Add a new Solution";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "AddSolution.fxml";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assertAll();
        Vector<Exercise> exerciseVector = ConnectServer.getExerciseFromUC();
        for(int i = 0; i < exerciseVector.size(); i++)
            if(exerciseVector.elementAt(i).getCod() == Main.exercise_id){
                exerDescASLabel.setText(exerciseVector.elementAt(i)
                        .getDescription());
                break;
            }
        cancelButtonActionPerformed();
        submitButtonActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert submitASButton != null : "fx:id=\"submitASButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert cancelASButton != null : "fx:id=\"cancelASButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert solutionASTextArea != null : "fx:id=\"solutionASTextArea\" was" +
                " not injected: check your FXML file '" + FILE_NAME + "'.";
        assert exerDescASLabel != null : "fx:id=\"exerDescASLabel\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link AddSolutionController#cancelASButton} so that it
     * returns to the login window when clicked.
     */
    private void cancelButtonActionPerformed(){
        cancelASButton.setOnAction(event ->
                Main.gotoNewScene((Stage) cancelASButton.getScene().getWindow(),
                        Main.L_FXML, LoginController.WINDOW_TITLE,0,"")
        );
    }

    /**
     * Changes the {@link AddSolutionController#cancelASButton} so that it
     *  adds a new solution to the exercise.
     */
    private void submitButtonActionPerformed(){
        submitASButton.setOnAction(event ->{
            String exercise = solutionASTextArea.getText();
            ConnectServer.addSolution(exercise);
            Main.gotoNewScene((Stage)solutionASTextArea.getScene().getWindow
                    (),Main.SS_FXML,SearchSolutionController.WINDOW_TITLE,0,
                    "");
        });
    }

}
