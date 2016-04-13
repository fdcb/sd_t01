package sample;

import example.Solution;
import example.UC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import static javafx.scene.layout.Region.USE_PREF_SIZE;

/**
 * Class with the methods to help with the SearchUC scene.
 *
 * @author Filipa Brito
 * @author André Ramos
 */
public class SearchUcController implements Initializable{
    @FXML private Button addNewSUButton;
    @FXML private Button backSUButton;
    @FXML private GridPane ucNamesSUGridPane;
    @FXML private CheckBox exerciseSUCheckBox;

    Vector<UC> ucVector;

    /**
     * Title of the SearchUC window.
     */
    public static final String WINDOW_TITLE = "List of UC's";
    /**
     * Name of the fxml file this class is associated with.
     */
    public static final String FILE_NAME = "SearchUC.fxml" ;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources){
        assertAll();
        if(Main.user_id == Solution.GUEST_COD)
            addNewSUButton.setVisible(false);
        backButtonActionPerformed();
        addNewButtonActionPerformed();
        ucVector = ConnectServer.getAllUC();
        addButtonsToGridLayout(ucVector);
        checkBoxActionPerformed();
    }

    /**
     *  Injects all {@link FXML} variables.
     */
    private void assertAll(){
        assert backSUButton != null : "fx:id=\"backSUButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert addNewSUButton != null : "fx:id=\"addNewSUButton\" was not " +
                "injected: check your FXML file '" + FILE_NAME + "'.";
        assert ucNamesSUGridPane != null : "fx:id=\"ucNamesSUGridPane\" was " +
                "not injected: check your FXML file '" + FILE_NAME + "'.";
        assert exerciseSUCheckBox != null : "fx:id=\"exerciseSUCheckBox\" was" +
                " not injected: check your FXML file '" + FILE_NAME + "'.";
    }

    /**
     * Changes the {@link SearchUcController#backSUButton} so that it returns
     * to the previous menu.
     */
    private void backButtonActionPerformed(){
       backSUButton.setOnAction(event -> {
            Stage stage = (Stage) backSUButton.getScene().getWindow();
            stage.close();
            System.exit(0);
        });
    }

    /**
     * Changes the {@link SearchUcController#addNewSUButton} so that it sends
     * us to the addNewUC scene.
     */
    private void addNewButtonActionPerformed(){
       addNewSUButton.setOnAction(event ->
            Main.gotoNewScene((Stage) addNewSUButton.getScene().getWindow(),
                    Main.AUC_FXML, AddUCController.WINDOW_TITLE, 0, "")
       );
    }

    /**
     * Creates a button with the uc name.
     *
     * @param oUC object of the UC
     * @return a button with the name of the UC
     */
    private Button createButton(UC oUC){
        Button button = new Button(oUC.getName());
        button.setOnAction(event -> {
            int uc_id = -1;
            String ucName = button.getText();
            for(int i = 0; i < ucVector.size(); i++)
                if(ucVector.elementAt(i).getName().equals(ucName)){
                    uc_id = ucVector.elementAt(i).getCod();
                    break;
            }
            Main.gotoNewScene((Stage) addNewSUButton.getScene().getWindow(),
                    Main.SE_FXML, SearchExerciseController.WINDOW_TITLE,
                    uc_id, Main.UC);
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