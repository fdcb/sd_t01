package sample;

import example.Exercise;
import example.UC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 * Created by Filipa on 12/04/2016.
 */
public class AddUCController implements Initializable{

    @FXML private Button confirmAUButton;
    @FXML private Button cancelAUButton;
    @FXML private TextField classNameAUTextField;
    @FXML private TextField uniNameAUTextField;
    @FXML private Label flagAULabel;

    /**
     * Title of the SearchExercise window.
     */
    public static final String WINDOW_TITLE = "Add new Class";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "AddUC.fxml" ;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assertAll();
        flagAULabel.setVisible(false);
        confirmButtonActionPerformed();
        cancelButtonActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert confirmAUButton != null : "fx:id=\"confirmAUButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert cancelAUButton != null : "fx:id=\"cancelAUButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert classNameAUTextField != null : "fx:id=\"classNameAUTextField\"" +
                " was not injected: check your FXML file '" + FILE_NAME + "'.";
        assert uniNameAUTextField != null : "fx:id=\"uniNameAUTextField\" was" +
                " not injected: check your FXML file '" + FILE_NAME + "'.";
        assert flagAULabel != null : "fx:id=\"flagAULabel\" was" +
                " not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link SearchExerciseController#backSEButton} so that it
     * returns to the previous menu.
     */
    private void cancelButtonActionPerformed(){
        cancelAUButton.setOnAction(event -> {
            Stage stage = (Stage) cancelAUButton.getScene().getWindow();
            Main.gotoNewScene(stage, Main.SUC_FXML, SearchUcController
                    .WINDOW_TITLE, 0, "");
        });
    }

    /**
     * Changes the {@link SearchExerciseController#searchSEButton} so that it
     * returns to the previous menu.
     */
    private void confirmButtonActionPerformed(){
        confirmAUButton.setOnAction(event -> {
            String className = classNameAUTextField.getText(),
                    uniName = uniNameAUTextField.getText();
            UC newUC = ConnectServer.addUC(className,uniName);
            if(newUC != null){
                Stage stage = (Stage)confirmAUButton.getScene().getWindow();
                Main.gotoNewScene(stage,Main.AE_FXML,AddExerciseController
                        .WINDOW_TITLE, newUC.getCod() , Main.UC);
            }
            else
                flagAULabel.setVisible(true);
        });
    }
}
