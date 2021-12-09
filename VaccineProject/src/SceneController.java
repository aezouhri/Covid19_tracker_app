import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Rida Errachidi
 * @author Ben DeSollar
 * @author Adnane Ezouhri
 * SceneController controls switching between login and registration pages. This object is called in several other controllers
 */
public class SceneController implements Initializable {
    /**
     * new stage for gui
     */
    private Stage stage;
    /**
     * new scene for gui
     */
    private Scene scene;
    /**
     * new root for gui
     */
    private Parent root;
    /**
     * new individuals query object
     */
    private final IndividualsQueries individualsQueries = new IndividualsQueries();
    /**
     * new individuals object
     */
    private static Individuals currentIndividual;
    /**
     * new government queries object
     */
    private final GovernmentQueries governmentsQueries = new GovernmentQueries();
    /**
     * new governments object
     */
    private static Governments governments;
    /**
     * new organization queries
     */
    private final OrganizationQueries organizationQueries = new OrganizationQueries();
    /**
     * new organization object
     */
    private static Organization organization;
    /**
     * new username text field
     */
    @FXML
    private TextField usernameTextField;
    /**
     * new password text field
     */
    @FXML
    private PasswordField passwordTextField;
    /**
     * new drop down menu to choose type of registration/login
     */
    @FXML
    private ChoiceBox<String> myChoiceBox;

    /**
     * login in button
     */
    @FXML
    private Button StartingLogin;
    /**
     * different login types
     */
    private String[] LoginType = {"Organization", "Government", "Individual"};

    /**
     * government getter
     *
     * @return
     */
    public Governments getGovernments() {
        return governments;
    }

    /**
     * sets government
     *
     * @param governments input new government
     */
    public void setGovernments(Governments governments) {
        SceneController.governments = governments;
    }

    /**
     * gets organization
     *
     * @return returns organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * sets organization
     *
     * @param organization input new organization
     */
    public void setOrganization(Organization organization) {
        SceneController.organization = organization;
    }

    /**
     * get current indiviudal
     *
     * @return return current individual
     */
    public Individuals getCurrentIndividual() {
        return currentIndividual;
    }

    /**
     * set current individual
     *
     * @param currentIndividual sets current individual
     */
    public void setCurrentIndividual(Individuals currentIndividual) {
        SceneController.currentIndividual = currentIndividual;
    }

    /**
     * switch to select registration type page
     *
     * @param event input even
     * @throws IOException throws IOException if exists
     */
    public void switchToSelectRegistrationType(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SelectRegistrationType.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Individual Registration page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToIndividualRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IndividualRegistration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Organization Registration page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToOrgRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrganizationRegistration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Government Registration page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToGovRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GovernmentRegistration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Individual Login page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToIndividualLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IndividualLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Government Login page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToGovernmentLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GovernmentLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Organization Login page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToOrganizationLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrganizationLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * switch to Individuals settings page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void switchToIndividualSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IndividualSettings.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * when you log in checks username if its an organization, government, or individual and goes to respective page
     *
     * @param event input event
     * @throws SQLException throws SQLException if exists
     * @throws IOException  throws IOException if exists
     */
    public void login(ActionEvent event) throws SQLException, IOException {
        String database_selection = myChoiceBox.getValue();
        boolean connection_successful = false;
        if (database_selection.equals("Individual")) {
            String username_entered = usernameTextField.getText();
            String password_entered = passwordTextField.getText();
            ArrayList<Individuals> individualsArrayList = individualsQueries.getIndividuals();
            for (int i = 0; i < individualsArrayList.size(); i++) {
                if (username_entered.equals(individualsArrayList.get(i).getUser_name()) && password_entered.equals(individualsArrayList.get(i).getPassword())) {
                    connection_successful = true;
                    System.out.println("Login Success");
                    setCurrentIndividual(individualsArrayList.get(i));
                    break;
                }
            }
            if (!connection_successful) {
                System.out.println("Username and/or Password was incorrect");
            } else {
                switchToIndividualLogin(event);
            }
        } else if (database_selection.equals("Government")) {
            String username_entered = usernameTextField.getText();
            String password_entered = passwordTextField.getText();
            ArrayList<Governments> governmentsArrayList = governmentsQueries.getGovernments();
            for (int i = 0; i < governmentsArrayList.size(); i++) {
                if (username_entered.equals(governmentsArrayList.get(i).getUsername()) && password_entered.equals(governmentsArrayList.get(i).getPassword())) {
                    connection_successful = true;
                    System.out.println("Login Success");
                    setGovernments(governmentsArrayList.get(i));
                    break;
                }
            }
            if (!connection_successful) {
                System.out.println("Username and/or Password was incorrect");
            } else {
                switchToGovernmentLogin(event);
            }
        } else if (database_selection.equals("Organization")) {
            String username_entered = usernameTextField.getText();
            String password_entered = passwordTextField.getText();
            ArrayList<Organization> organizationArrayList = organizationQueries.getOrganization();
            for (int i = 0; i < organizationArrayList.size(); i++) {
                if (username_entered.equals(organizationArrayList.get(i).getUsername()) && password_entered.equals(organizationArrayList.get(i).getPassword())) {
                    connection_successful = true;
                    System.out.println("Login Success");
                    setOrganization(organizationArrayList.get(i));
                    break;
                }
            }
            if (!connection_successful) {
                System.out.println("Username and/or Password was incorrect");
            } else {
                switchToOrganizationLogin(event);
            }
        }
    }

    /**
     * initalize dropdown at startup page
     *
     * @param url            input url from file
     * @param resourceBundle input resource bundle from file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myChoiceBox.getItems().addAll(LoginType);
        myChoiceBox.getSelectionModel().selectFirst();
    }
}
