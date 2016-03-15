package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
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
			Parent root = FXMLLoader.load(Main.class.getResource
					(filename));
			stage.setScene(new Scene(root));
			stage.setTitle(windowTitle);
			stage.show();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

    public static void main(String[] args) {
        launch(args);
    }

}
