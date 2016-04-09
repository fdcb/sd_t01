package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

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
        backButtonActionPerformed();
        addNewButtonActionPerformed();
        addButtonstoGridLayout();
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
                    AddExerciseController.FILE_NAME,
                    AddExerciseController.WINDOW_TITLE)
       );
    }

    /**
     * Creates a button with the uc name.
     *
     * @param ucName name of the UC
     * @return a button with the name of the UC
     */
    private Button createButton(String ucName){
        Button button = new Button(ucName);
        button.setOnAction(event ->
            Main.gotoNewScene((Stage) addNewSUButton.getScene().getWindow(),
                    AddExerciseController.FILE_NAME,
                    AddExerciseController.WINDOW_TITLE));
        return button;
    }

    private void addButtonstoGridLayout(){
        String[] names = {"nome1", "nome2", "nome3", "nome4","nome5", "1nome1",
                "1nome2", "1nome3", "1nome4","1ndddddddome5"};
        int gridSize = (int)Math.ceil(Math.sqrt(names.length));
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
        for (int i = 0; i < names.length; i++){
            ucNamesSUGridPane.add(createButton(names[i]),col,row);

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
}