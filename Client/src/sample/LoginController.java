package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ResourceBundle;
import example.*;

/**
 * Class with the methods to help with the Login scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class LoginController implements Initializable{

	@FXML private Label guestUserL;
	@FXML private Label registerL;
	@FXML private Label warningErrorL;
	@FXML private Button cancelButton;
	@FXML private Button confirmButton;
	@FXML private TextField usernameTF;
	@FXML private PasswordField passwordF;


	/**
	 * Title of the Login window.
	 */
	public static final String WINDOW_TITLE = "Login";
	/**
	 * Name of the fxml file this class is associated with.
	 */
	public static final String FILE_NAME = "Login.fxml" ;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources){
		assertAll();
		warningErrorL.setVisible(false);
		setGuestUserLabelToClickable();
		setRegisterLabelToClickable();
		cancelButtonActionPerformed();
		confirmButtonActionPerformed();
	}

	/**
	 *  Injects all {@link FXML} variables.
	 */
	private void assertAll(){
		assert guestUserL != null : "fx:id=\"guestUserL\" was not injected: " +
				"check your FXML file '" + FILE_NAME + "'.";
		assert registerL != null : "fx:id=\"registerL\" was not injected: " +
				"check your FXML file '" + FILE_NAME + "'.";
		assert warningErrorL != null : "fx:id=\"warningErrorL\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert cancelButton != null : "fx:id=\"cancelButton\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert confirmButton != null : "fx:id=\"confirmButton\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert usernameTF != null : "fx:id=\"usernameTF\" was not injected: " +
				"check your FXML file '" + FILE_NAME + "'.";
		assert passwordF != null : "fx:id=\"passwordF\" was not injected: " +
				"check your FXML file '" + FILE_NAME + "'.";
	}

	/**
	 * Changes the {@link LoginController#guestUserL} so that it allows the
	 * user to click it and use our software as a guest user.
	 */
	private void setGuestUserLabelToClickable(){
		guestUserL.setOnMouseClicked(event ->
			Main.gotoNewScene((Stage) guestUserL.getScene().getWindow(),
			Main.SUC_FXML, SearchUcController.WINDOW_TITLE, Solution
							.GUEST_COD, Main.USER)
		);
	}

	/**
	 * Changes the {@link LoginController#registerL} so that it allows the
	 * user to click it register as a new client.
	 */
	private void setRegisterLabelToClickable(){
		registerL.setOnMouseClicked(event ->
			Main.gotoNewScene((Stage) registerL.getScene().getWindow(),
				Main.R_FXML, RegisterController.WINDOW_TITLE, 0, "")
		);
	}

	/**
	 * Changes the {@link LoginController#cancelButton} so that it closes the
	 * application when clicked.
	 */
	private void cancelButtonActionPerformed(){
		cancelButton.setOnAction(event -> {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
			System.exit(0);
		});
	}

	/**
	 * Changes the {@link LoginController#confirmButton} so that it verifies
	 * the users info. If the info is correct then allows the user to use our
	 * platform, else it will inform the user that his information is incorrect.
	 */
	private void confirmButtonActionPerformed(){
		confirmButton.setOnAction(event -> {

			String username = usernameTF.getText(),
					password = passwordF.getText();
            User user = ConnectServer.validateUser(username, password);
			if(user == null)
				warningErrorL.setVisible(true);
			else{
                Stage stage = (Stage) confirmButton.getScene().getWindow();
				Main.gotoNewScene(stage,Main.SUC_FXML,SearchUcController
                        .WINDOW_TITLE,user.getCod(),Main.USER);
			}
		});
	}
}
