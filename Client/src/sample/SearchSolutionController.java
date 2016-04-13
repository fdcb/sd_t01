package sample;

import example.Solution;
import example.UC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 * Created by Filipa on 13/04/2016.
 */
public class SearchSolutionController implements Initializable{

    @FXML private Button addSSButton;
    @FXML private Button cancelSSButton;
    @FXML private Button correctSSButton;
    @FXML private Button wrongSSButton;
    @FXML private Button unvalidatedSSButton;
    @FXML private GridPane solutionSSGridPane;
    @FXML private Label exerciseSSLabel;

    /**
     * Title of the SearchUC window.
     */
    public static final String WINDOW_TITLE = "List of Solutions";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "SearchSolutions.fxml" ;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assertAll();
        backButtonActionPerformed();
        addNewButtonActionPerformed();
        checkBoxActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert cancelSSButton != null : "fx:id=\"cancelSSButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert correctSSButton != null : "fx:id=\"correctSSButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert wrongSSButton != null : "fx:id=\"wrongSSButton\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
        assert unvalidatedSSButton != null : "fx:id=\"unvalidatedSSButton\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
        assert solutionSSGridPane != null : "fx:id=\"solutionSSGridPane\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
        assert exerciseSSLabel != null : "fx:id=\"exerciseSSLabel\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
        assert addSSButton != null : "fx:id=\"addSSButton\" " +
                "was not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link SearchUcController#backSUButton} so that it returns
     * to the previous menu.
     */
    private void backButtonActionPerformed(){
        cancelSSButton.setOnAction(event -> {
            Stage stage = (Stage) cancelSSButton.getScene().getWindow();
            Main.gotoNewScene(stage,Main.SE_FXML,SearchExerciseController
                    .WINDOW_TITLE);
        });
    }

    /**
     * Changes the {@link SearchUcController#addNewSUButton} so that it sends
     * us to the addNewUC scene.
     */
    private void addNewButtonActionPerformed(){
        addSSButton.setOnAction(event ->
                Main.gotoNewScene((Stage) addSSButton.getScene().getWindow(),
                        Main.AS_FXML, AddSolutionController.WINDOW_TITLE)
        );
    }

    /**
     * Creates a button with the uc name.
     *
     * @param solution object of the UC
     * @return a button with the name of the UC
     */
    private Button createButton(Solution solution){
        Button button = new Button(Integer.toString(solution.getCod()));

        button.setOnAction(event ->{
            Main.solution_id = solution.getCod();
            Main.gotoNewScene((Stage) addSSButton.getScene().getWindow(),
                        Main.CS_FXML, CheckSolutionsController.WINDOW_TITLE);
        });
        return button;
    }

    /**
     * Changes the {@link SearchUcController#ucNamesSUGridPane} in order to
     * have the buttons for all the UC's with exercises in the platform.
     */
    private void addButtonsToGridLayout(Vector<UC> allUC){
        if(allUC.size() == 0) return;

        int gridSize = (int)Math.ceil(Math.sqrt(allUC.size()));
        int row = 0, col = 0;
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMaxWidth(USE_PREF_SIZE);

        RowConstraints rc = new RowConstraints();
        rc.setMaxHeight(50);

        ucNamesSUGridPane.setHgap(10);
        ucNamesSUGridPane.setVgap(10);
        ucNamesSUGridPane.getColumnConstraints().add(0,cc);
        ucNamesSUGridPane.getRowConstraints().add(0,rc);

        System.out.print(gridSize);
        for (int i = 0; i < allUC.size(); i++){
            ucNamesSUGridPane.add(createButton(allUC.elementAt(i)),col,row);
            if(row == 0)
                ucNamesSUGridPane.getColumnConstraints().add(col + 1, cc);
            if(col + 1 > gridSize - 1){
                ucNamesSUGridPane.getRowConstraints().add(row + 1, rc);
                row++;
                col = 0;
            }
            else
                col++;
        }
    }

    private void checkBoxActionPerformed(){
        exerciseSUCheckBox.setOnAction(event -> {
            if(exerciseSUCheckBox.isSelected()){
                ucNamesSUGridPane.getChildren().clear();
                Vector<UC> ucWithExercise = new Vector <>();
                for(int i = 0; i < ucVector.size(); i++)
                    if(ucVector.elementAt(i).getExerciseCount() != 0)
                        ucWithExercise.add(ucVector.elementAt(i));
                addButtonsToGridLayout(ucWithExercise);
                return;
            }
            ucNamesSUGridPane.getChildren().clear();
            addButtonsToGridLayout(ucVector);
        });
    }
}
}
