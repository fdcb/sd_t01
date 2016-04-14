package sample;

import example.Exercise;
import example.Solution;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    @FXML private Button closeSSButton;

    private Vector<Solution> allSolutions, correctSolutions, wrongSolutions,
            unvalidatedSolutions;
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
        allSolutions = ConnectServer.getSolutionsFromExercise();
        Vector<Exercise> exerciseVector = ConnectServer.getExerciseFromUC();
        int position = -1;
        for(int i = 0; i < exerciseVector.size(); i++)
            if(exerciseVector.elementAt(i).getCod() == Main.exercise_id){
                exerciseSSLabel.setText(exerciseVector.elementAt(i)
                        .getDescription());
                position = i;
                break;
            }
        if(exerciseVector.elementAt(position).getState().equals(Exercise.STATE_CLOSED))
            addSSButton.setVisible(false);
        addButtonsToGridLayout(allSolutions);
        correctSolutions = new Vector <>();
        wrongSolutions = new Vector <>();
        unvalidatedSolutions = new Vector <>();
        for(int i = 0; i < allSolutions.size(); i++){
            if(allSolutions.elementAt(i).getState().equals(Solution
                    .STATE_CORRECT))
                correctSolutions.add(allSolutions.elementAt(i));
            else if(allSolutions.elementAt(i).getState().equals(Solution
                    .STATE_INCORRECT))
                wrongSolutions.add(allSolutions.elementAt(i));
            else
                unvalidatedSolutions.add(allSolutions.elementAt(i));
        }

        System.out.print("hello?");
        if(unvalidatedSolutions.size() != 0 || exerciseVector.elementAt(position).
                getUserCod() != Main.user_id)
            closeSSButton.setVisible(false);
        closeButtonActionPerformed();
        unvalidatedButtonActionPerformed();
        wrongButtonActionPerformed();
        correctButtonActionPerformed();
        backButtonActionPerformed();
        addNewButtonActionPerformed();
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
        assert closeSSButton != null : "fx:id=\"closeSSButton\" " +
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
                    .WINDOW_TITLE,0,"" );
        });
    }


    private void closeButtonActionPerformed(){
        closeSSButton.setOnAction(event -> {
            ConnectServer.changeExerciseState();
            Stage stage = (Stage) closeSSButton.getScene().getWindow();
            Main.gotoNewScene(stage,Main.SE_FXML,SearchExerciseController
                    .WINDOW_TITLE,0,"" );
        });
    }

    /**
     * Changes the {@link SearchUcController#addNewSUButton} so that it sends
     * us to the addNewUC scene.
     */
    private void addNewButtonActionPerformed(){
        addSSButton.setOnAction(event ->
                Main.gotoNewScene((Stage) addSSButton.getScene().getWindow(),
                        Main.AS_FXML, AddSolutionController.WINDOW_TITLE,0,"")
        );
    }

    private void correctButtonActionPerformed(){
        correctSSButton.setOnAction(event -> {
            solutionSSGridPane.getChildren().clear();
            addButtonsToGridLayout(correctSolutions);
        });
    }

    private void wrongButtonActionPerformed(){
        wrongSSButton.setOnAction(event -> {
            solutionSSGridPane.getChildren().clear();
            addButtonsToGridLayout(wrongSolutions);
        });
    }

    private void unvalidatedButtonActionPerformed(){
        unvalidatedSSButton.setOnAction(event -> {
            solutionSSGridPane.getChildren().clear();
            addButtonsToGridLayout(unvalidatedSolutions);
        });
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
            Main.gotoNewScene((Stage) addSSButton.getScene().getWindow(),
                    Main.CS_FXML, CheckSolutionsController.WINDOW_TITLE,
                    solution.getCod(), Main.SOLUTION);
        });
        return button;
    }

    /**
     * Changes the {@link SearchUcController#ucNamesSUGridPane} in order to
     * have the buttons for all the UC's with exercises in the platform.
     */
    private void addButtonsToGridLayout(Vector<Solution> solutionVector){
        if(solutionVector.size() == 0) return;

        int gridSize = (int)Math.ceil(Math.sqrt(solutionVector.size()));
        int row = 0, col = 0;
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMaxWidth(USE_PREF_SIZE);

        RowConstraints rc = new RowConstraints();
        rc.setMaxHeight(50);

        solutionSSGridPane.setHgap(10);
        solutionSSGridPane.setVgap(10);
        solutionSSGridPane.getColumnConstraints().add(0,cc);
        solutionSSGridPane.getRowConstraints().add(0,rc);

        for (int i = 0; i < solutionVector.size(); i++){
            solutionSSGridPane.add(createButton(solutionVector.elementAt(i))
                    ,col,row);
            if(row == 0)
                solutionSSGridPane.getColumnConstraints().add(col + 1, cc);
            if(col + 1 > gridSize - 1){
                solutionSSGridPane.getRowConstraints().add(row + 1, rc);
                row++;
                col = 0;
            }
            else
                col++;
        }
    }
}
