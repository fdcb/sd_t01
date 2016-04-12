package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import example.*;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 * Class with the methods to help with the SearchExercise scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class SearchExerciseController implements Initializable{

    @FXML private Button searchSEButton;
    @FXML private Button openSEButton;
    @FXML private Button closeSEButton;
    @FXML private Button addNewSEButton;
    @FXML private Button backSEButton;
    @FXML private GridPane exerciseSEGridPane;
    @FXML private TextField exeNumberSETextField;

    /**
     * Title of the SearchExercise window.
     */
    public static final String WINDOW_TITLE = "List of Exercises";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "SearchExercise.fxml" ;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assertAll();
        backButtonActionPerformed();
        addNewButtonActionPerformed();
        addButtonsToGridLayout(new int[]{1,2,3,4,5,62,21,31,2,4});
        openButtonActionPerformed();
        closeButtonActionPerformed();
        searchButtonActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert searchSEButton != null : "fx:id=\"searchSEButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert backSEButton != null : "fx:id=\"backSEButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert addNewSEButton != null : "fx:id=\"addNewSEButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert openSEButton != null : "fx:id=\"openSEButton\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
        assert closeSEButton != null : "fx:id=\"closeSEButton\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
        assert exeNumberSETextField != null : "fx:id=\"exeNumberSETextField" +
                "\" was not injected: check your FXML file '" + FILE_NAME +
                "'.";
        assert exerciseSEGridPane != null : "fx:id=\"exerciseSEGridPane\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link SearchExerciseController#backSEButton} so that it
     * returns to the previous menu.
     */
    private void backButtonActionPerformed(){
        backSEButton.setOnAction(event -> {
            Stage stage = (Stage) backSEButton.getScene().getWindow();
            stage.close();
            System.exit(0);
        });
    }

    /**
     * Changes the {@link SearchExerciseController#searchSEButton} so that it
     * returns to the previous menu.
     */
    private void searchButtonActionPerformed(){
        searchSEButton.setOnAction(event -> {
            System.out.print(exeNumberSETextField.getText());
            Stage stage = (Stage) backSEButton.getScene().getWindow();
            stage.close();
            System.exit(0);
        });
    }

    /**
     * Changes the {@link SearchExerciseController#addNewSEButton} so that it
     * sends us to the addNewUC scene.
     */
    private void addNewButtonActionPerformed(){
        addNewSEButton.setOnAction(event ->
                Main.gotoNewScene((Stage) addNewSEButton.getScene().getWindow(),
                        Main.AE_FXML, AddExerciseController.WINDOW_TITLE)
        );
    }

    /**
     * Changes the {@link SearchExerciseController#openSEButton} so that it
     * shows us only the exercises tagged as {@value Exercise#STATE_OPEN}
     */
    private void openButtonActionPerformed(){
        openSEButton.setOnAction(event -> {
            exerciseSEGridPane.getChildren().clear();
            addButtonsToGridLayout(new int[]{3, 2, 4, 1});
            }
        );
    }

    /**
     * Changes the {@link SearchExerciseController#closeSEButton} so that it
     * shows us only the exercises tagged as {@value Exercise#STATE_CLOSED}
     */
    private void closeButtonActionPerformed(){
        closeSEButton.setOnAction(event -> {
            exerciseSEGridPane.getChildren().clear();
            addButtonsToGridLayout(new int[]{2,3,4,1,12,235,231,3,23, 2,
                    4, 1});
                }
        );
    }

    /**
     * Creates a button with the number of the exercise.
     *
     * @param exerciseNumber number of the exercise to write on the button
     * @return a button with the number of the exercise
     */
    private Button createButton(String exerciseNumber){
        Button button = new Button(exerciseNumber);
        button.setOnAction(event ->
                Main.gotoNewScene((Stage) addNewSEButton.getScene().getWindow(),
                        Main.AE_FXML, AddExerciseController.WINDOW_TITLE));
        return button;
    }

    /**
     * Changes the {@link SearchExerciseController#exerciseSEGridPane} in
     * order to have the buttons for all the exercises of that uc.
     */
    private void addButtonsToGridLayout(int[] numbers){
       /* String[] names = {"nome1", "nome2", "nome3", "nome4","nome5",
       "1nome1", "1nome2", "1nome3", "1nome4","1ndddddddome5"};*/
        int gridSize = (int)Math.ceil(Math.sqrt(numbers.length));
        int row = 0, col = 0;
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMaxWidth(USE_PREF_SIZE);

        RowConstraints rc = new RowConstraints();
        rc.setMaxHeight(50);

        exerciseSEGridPane.setHgap(10);
        exerciseSEGridPane.setVgap(10);
        exerciseSEGridPane.getColumnConstraints().add(0,cc);
        exerciseSEGridPane.getRowConstraints().add(0,rc);

        System.out.print(gridSize);
        for (int i = 0; i < numbers.length; i++){
            exerciseSEGridPane.add(createButton(Integer.toString(numbers[i]))
                    ,col,row);

            if(row == 0)
                exerciseSEGridPane.getColumnConstraints().add(col + 1, cc);
            if(col + 1 > gridSize - 1){
                exerciseSEGridPane.getRowConstraints().add(row + 1, rc);
                row++;
                col = 0;
            }
            else
                col++;
        }
    }
}
