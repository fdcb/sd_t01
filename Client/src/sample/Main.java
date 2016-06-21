package sample;
import example.RMIInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
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
    public static URL AUC_FXML = null;
    public static URL SS_FXML = null;

    public static int user_id;
    public static int uc_id;
    public static int exercise_id;
    public static int solution_id;

	final public static String USER = "User";
    final public static String UC = "Uc";
    final public static String EXERCISE = "Exercise";
    final public static String SOLUTION = "Solution";

    @Override
    public void start(Stage primaryStage){
		//gotoNewScene(primaryStage, LoginController.FILE_NAME,
			//	LoginController.WINDOW_TITLE);
        try {
            Parent root = FXMLLoader.load(L_FXML);
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
	public static void gotoNewScene(Stage stage, URL filename, String
			windowTitle, int cod, String object){
        switch (object){
            case USER: user_id = cod; break;
            case UC: uc_id = cod; break;
            case EXERCISE: exercise_id = cod; break;
            case SOLUTION: solution_id = cod; break;
        }

		try {
            Parent root = FXMLLoader.load(filename);
			stage.setScene(new Scene(root));
			stage.setTitle(windowTitle);
			stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

    public static void main(String[] args) {
       // connectServer();
        saveFXML();
        String serverName = "";
	    System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
			    "\\java.policy");
	    System.setSecurityManager(new RMISecurityManager());
	    try {
			InetAddress address = java.net.Inet4Address.getByAddress(new byte[] {(byte) 192,(byte) 168, 43, 5});
			serverName = address.getHostName();
            //   serverName = java.net.InetAddress.getLocalHost().getHostName();
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

	private static void saveFXML(){
		AE_FXML = AddExerciseController.class.getResource(AddExerciseController
                .FILE_NAME);
		AS_FXML = AddSolutionController.class.getResource
                (AddSolutionController.FILE_NAME);
		CS_FXML = CheckSolutionsController.class.getResource
                (CheckSolutionsController.FILE_NAME);
		DE_FXML = DelExerciseController.class.getResource(DelExerciseController
				.FILE_NAME);
		L_FXML = LoginController.class.getResource(LoginController.FILE_NAME);
		R_FXML = RegisterController.class.getResource(RegisterController
				.FILE_NAME);
		SE_FXML = SearchUcController.class.getResource(SearchExerciseController
				.FILE_NAME);
		SUC_FXML = SearchUcController.class.getResource(SearchUcController
				.FILE_NAME);
        AUC_FXML = AddUCController.class.getResource(AddUCController
                .FILE_NAME);
        SS_FXML = SearchSolutionController.class.getResource
                (SearchSolutionController.FILE_NAME);
	}
}
