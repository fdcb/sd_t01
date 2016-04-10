package sample;
import example.RMIInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
		//gotoNewScene(primaryStage, LoginController.FILE_NAME,
			//	LoginController.WINDOW_TITLE);
	    gotoNewScene(primaryStage, LoginController.FILE_NAME,
                LoginController.WINDOW_TITLE);
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
			Parent root = FXMLLoader.load(sample.Main.class.getResource
					(filename));
			stage.setScene(new Scene(root));
			stage.setTitle(windowTitle);
			stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

    public static void main(String[] args) {
	    String serverName = "";
	    System.setProperty("java.security.policy", "C:\\T01SD\\Client\\src" +
			    "\\java.policy");
	    System.setSecurityManager(new RMISecurityManager());
	    if (args.length != 1) {
		    try {
			    serverName = java.net.InetAddress.getLocalHost().getHostName();
		    } catch(Exception e) {
			    e.printStackTrace();
		    }
	    }
	    else {
		    serverName = args[0];
	    }
	    if (serverName.equals( "") ) {
		    System.out.println("usage: java Client < host running RMI server>");
		    System.exit(0);
	    }
	    try {
		    //bind server object to object in client
		    RMIInterface myServerObject =
				    (RMIInterface) Naming.lookup("//"+serverName+"/Impl");
		    //invoke method on server object
		    Date d = myServerObject.getDate();
		    System.out.println("Date on server is " + d);
            launch(args);
	    } catch(Exception e) {
		    System.out.println("Exception occured: " + e);
		    System.exit(0);
	    }
	    System.out.println("RMI connection successful");
    }
}
