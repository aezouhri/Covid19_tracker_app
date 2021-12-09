import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Ben DeSollar
 * @author Rida Errachidi
 * @author Adnane Ezouhri
 * Main class runs program
 */
public class Main extends Application {
    /**
     * start method calls the GUI
     *
     * @param primaryStage input stage
     * @throws Exception throws Exception if exists
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login Now");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * launches program
     *
     * @param args an array of strings that can be passed in when starting the main program
     */
    public static void main(String[] args) {

        launch(args);
    }


}
