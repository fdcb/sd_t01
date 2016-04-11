package sample;
import example.RMIInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class Main extends Application {

	public static RMIInterface myServerObject;
    public static URL AE_FXML = null;
    public static URL AS_FXML = null;
    public static URL CS_FXML = null;
    public static URL DE_FXML = null;
    public static URL L_FXML = null;
    public static URL R_FXML = null;
    public static URL SE_FXML = null;
    public static URL SUC_FXML = null;

    @Override
    public void start(Stage primaryStage){
		//gotoNewScene(primaryStage, LoginController.FILE_NAME,
			//	LoginController.WINDOW_TITLE);
        try {
            Parent root = FXMLLoader.load(getClass().getResource
                    (LoginController.FILE_NAME));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(LoginController.WINDOW_TITLE);
            primaryStage.show();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

	/**
	 * Changes to a different scene/ creates a new scene given a certain fxml
	 * file.
	 *
	 * @param stage			Current stage.
	 * @param filename		Name of the fxml file to be loaded.
	 * @param windowTitle	Name of the desired window title.
	 */
	public static void gotoNewScene(Stage stage, String filename, String
			windowTitle){
		try {
            Parent root = FXMLLoader.load(Main.class
                    .getResource(filename));
			stage.setScene(new Scene(root));
			stage.setTitle(windowTitle);
			stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

    public static void main(String[] args) {
       // connectServer();
        AE_FXML = Main.class.getClass().getResource(AddExerciseController.FILE_NAME);
        AS_FXML = Main.class.getClass().getResource(AddSolutionController
                .FILE_NAME);
        CS_FXML = Main.class.getClass().getResource(CheckSolutionsController
                .FILE_NAME);
        DE_FXML = Main.class.getClass().getResource(DelExerciseController
                .FILE_NAME);
        L_FXML = Main.class.getClass().getResource(LoginController.FILE_NAME);
        R_FXML = Main.class.getClass().getResource(RegisterController
                .FILE_NAME);
        SE_FXML = Main.class.getClass().getResource(SearchExerciseController
                .FILE_NAME);
        SUC_FXML = Main.class.getClass().getResource(SearchUcController
                .FILE_NAME);

        String serverName = "";
	    System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
			    "\\java.policy");
	    System.setSecurityManager(new RMISecurityManager());
	    try {
		    serverName = java.net.InetAddress.getLocalHost().getHostName();
	    } catch(Exception e){
		    e.printStackTrace();
	    }
	    if (serverName.equals("") ) {
		    System.out.println("usage: java Client < host running RMI server>");
		    System.exit(0);
	    }
	    try {
		    //bind server object to object in client
		    myServerObject =
				    (RMIInterface) Naming.lookup("//"+serverName+"/Impl");
		    //invoke method on server object

	    } catch(Exception e) {
		    System.out.println("VU: Exception occured: " + e);
		    System.exit(0);
	    }
        launch(args);

    }
}
