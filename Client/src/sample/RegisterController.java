package sample;

import example.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class with the methods to help with the Register scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class RegisterController implements Initializable{

	@FXML private Button regCancelButton;
	@FXML private Button regConfirmButton;
	@FXML private TextField regUsernameTF;
	@FXML private TextField emailTF;
	@FXML private PasswordField regPasswordF;
	@FXML private Label warningUsername;
	@FXML private Label warningRequired;

	/**
	 * Title of the Register window.
	 */
	public static final String WINDOW_TITLE = "Register";
	/**
	 * Name of the fxml file this class is associated with.
	 */
	public static final String FILE_NAME = "Register.fxml" ;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		assertAll();
		warningRequired.setVisible(false);
		warningUsername.setVisible(false);
		cancelButtonActionPerformed();
		confirmButtonActionPerformed();
	}

	/**
	 *  Injects all {@link FXML} variables.
	 */
	private void assertAll(){
		assert warningUsername != null : "fx:id=\"warningUsername\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert warningRequired != null : "fx:id=\"warningRequired\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert emailTF != null : "fx:id=\"emailTF\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert regCancelButton != null : "fx:id=\"regCancelButton\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert regConfirmButton != null : "fx:id=\"regConfirmButton\" was not" +
				" injected: check your FXML file '" + FILE_NAME + "'.";
		assert regUsernameTF != null : "fx:id=\"regUsernameTF\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
		assert regPasswordF != null : "fx:id=\"regPasswordF\" was not " +
				"injected: check your FXML file '" + FILE_NAME + "'.";
	}

	/**
	 * Changes the {@link RegisterController#regCancelButton} so that it
	 * returns to the login window when clicked.
	 */
	private void cancelButtonActionPerformed(){
		regCancelButton.setOnAction(event ->
                Main.gotoNewScene((Stage) regCancelButton.getScene().getWindow()
                        ,Main.L_FXML, LoginController.WINDOW_TITLE,0,"")
		);
	}

	/**
	 * Changes the {@link RegisterController#regConfirmButton} so that it
	 * verifies the users input. Creates a new user if the input qualifies.
	 */
	private void confirmButtonActionPerformed(){
		regConfirmButton.setOnAction(event -> {
			String username = regUsernameTF.getText(),
					password = regPasswordF.getText(),
					email = emailTF.getText();
            User oUser = ConnectServer.registerUser(username,password,email);
			if(username.equals("") || password.equals("")) {
				warningRequired.setVisible(true);
			}
            else if(oUser == null)
                warningUsername.setVisible(true);
			else
                Main.gotoNewScene((Stage) regConfirmButton.getScene()
                        .getWindow(), Main.SUC_FXML, SearchUcController
		                .WINDOW_TITLE,oUser.getCod(),Main.USER );
		});
	}
}
