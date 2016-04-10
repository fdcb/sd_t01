package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class with the methods to help with the AddExercise scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class AddExerciseController implements Initializable {

	@FXML private TitledPane addExerciseTP;
	@FXML private TextArea addExerciseTA;
	@FXML private Button submitAEButton;
	@FXML private Button cancelAEButton;

	/**
	 * Title of the AddExercise window.
	 */
	public static final String WINDOW_TITLE = "Add a new Exercise";
	/**
	 * Name of the fxml file this class is associated with.
	 */
	public static final String FILE_NAME = "AddExercise.fxml" ;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assertAll();
		cancelButtonActionPerformed();
		submitButtonActionPerformed();
	}

	/**
	 *  Injects all {@link FXML} variables.
	 */
	private void assertAll(){
		assert addExerciseTP != null : "fx:id=\"addExerciseTP\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert addExerciseTA != null : "fx:id=\"addExerciseTA\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert submitAEButton != null : "fx:id=\"warningErrorL\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert cancelAEButton != null : "fx:id=\"cancelAEButton\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
	}

	/**
	 * Changes the {@link AddExerciseController#cancelAEButton} so that it
	 * returns to the login window when clicked.
	 */
	private void cancelButtonActionPerformed(){
		cancelAEButton.setOnAction(event ->
			Main.gotoNewScene((Stage) cancelAEButton.getScene().getWindow(),
					LoginController.FILE_NAME, LoginController.WINDOW_TITLE)
		);
	}
	/**
	 * Changes the {@link AddExerciseController#submitAEButton} so that it
	 * adds a new exercise when clicked.
	 */
	private void submitButtonActionPerformed(){
		submitAEButton.setOnAction(event ->{
			String exercise = addExerciseTA.getText();
			System.out.println(exercise);
		});
	}

}
