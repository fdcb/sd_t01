package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

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
}
