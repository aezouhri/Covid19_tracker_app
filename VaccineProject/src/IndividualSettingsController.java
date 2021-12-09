import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * @author Rida Errachidi
 * @author Ben DeSollar
 * @author Adnane Ezouhri
 * IndividualSettings Controller for individual settings page implements initalizable
 */
public class IndividualSettingsController implements Initializable {
    /**
     * connects to SQL server
     */
    private final DatabaseConnection datacon = new DatabaseConnection();
    /**
     * gets to connection to SQL
     */
    private final Connection connection = datacon.getConnection();

    /**
     * Original SSN of a person
     */
    private String original_ssn = "";


    /**
     * new stage
     */
    private Stage stage;
    /**
     * new scene
     */
    private Scene scene;
    /**
     * new root
     */
    private Parent root;
    /**
     * call scene controller object
     */
    SceneController sceneController = new SceneController();
    /**
     * first name text
     */
    @FXML
    private TextField first_name_text_field;
    /**
     * mask mandate drop down menu
     */
    @FXML
    private ComboBox<String> mask_combo_box;
    /**
     * security answer 1
     */
    @FXML
    private TextField security_question1_text_field;
    /**
     * security answer 2
     */
    @FXML
    private TextField security_question2_text_field;
    /**
     * last 4 digits of social text field
     */
    @FXML
    private TextField ssn_text_field;
    /**
     * vaccine mandate drop down menu
     */
    @FXML
    private ComboBox<String> vaccine_combo_box;
    /**
     * first vaccine number
     */
    @FXML
    private TextField vaccination_lot1_text_field;
    /**
     * second vaccine number
     */
    @FXML
    private TextField vaccination_lot2_text_field;
    /**
     * booster shot number (optional)
     */
    @FXML
    private TextField booster_text_field;
    /**
     * individual registered button
     */
    @FXML
    private Button individualRegisteredButton;
    /**
     * date of birth entry
     */
    @FXML
    private TextField dob_text_field;
    /**
     * password text field
     */
    @FXML
    private PasswordField password_password_field;
    /**
     * password strength check
     */
    @FXML
    private Label password_strength_label;
    /**
     * re enter password section
     */
    @FXML
    private PasswordField reentered_password_password_field;
    /**
     * username text field
     */
    @FXML
    private TextField username_text_field;
    /**
     * last name text field
     */
    @FXML
    private TextField last_name_text_field;
    /**
     * registration failed label
     */
    @FXML
    private Label registration_failed_label;
    /**
     * last vaccination date drop menu
     */
    @FXML
    private ComboBox<String> last_vaccination_date_combo_box;
    /**
     * password required label
     */
    @FXML
    private Label password_reqs_label;
    /**
     * password strength notification
     */
    @FXML
    private Label password_strength_label_text;
    /**
     * re enter password notification
     */
    @FXML
    private Label reenter_password_label;
    /**
     * exit button
     */
    @FXML
    public Button exit_button;
    /**
     * true or false if passwords match
     */
    private boolean passwords_match;

    /**
     * vaccine answer options
     */
    private String[] vaccination_answer_options = {"No", "Yes"};
    /**
     * last vaccine take time options
     */
    private String[] last_vaccination_date_options = {"Two Weeks or More", "10 - 13 Days", "7 - 9 Days", "Under 7 Days"};
    /**
     * mask preference options
     */
    private String[] mask_preferences_options = {"No Preference", "Will Wear", "Will Not Wear"};

    private Individuals individual;

    /**
     * register new individual into database and go back home
     *
     * @param event input event
     * @throws IOException  calls IOException if exists
     * @throws SQLException calls SQLException if exists
     */
    public void registerNewIndividual(ActionEvent event) throws IOException, SQLException {
        if (passwordPassesSecurityCheck() && passwords_match) {
            Individuals new_individual = new Individuals(first_name_text_field.getText(), last_name_text_field.getText(), dob_text_field.getText(), ssn_text_field.getText(), vaccine_combo_box.getSelectionModel().getSelectedItem(), vaccination_lot1_text_field.getText(), vaccination_lot2_text_field.getText(), booster_text_field.getText(), last_vaccination_date_combo_box.getSelectionModel().getSelectedItem(), mask_combo_box.getSelectionModel().getSelectedItem(), username_text_field.getText(), password_password_field.getText(), security_question1_text_field.getText(), security_question2_text_field.getText());
            IndividualsQueries individualsQueries = new IndividualsQueries();
            if (!individualsQueries.addIndividuals(new_individual)) {
                registration_failed_label.setVisible(true);
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } else {
            registration_failed_label.setText("Password Did not Meet Requirements");
            registration_failed_label.setVisible(true);
        }
    }

    /**
     * initialize individual settings page
     *
     * @param url            input url
     * @param resourceBundle input resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        individual = sceneController.getCurrentIndividual();
        vaccine_combo_box.getItems().addAll(vaccination_answer_options);
        vaccine_combo_box.getSelectionModel().select(individual.getVaccinated());
        last_vaccination_date_combo_box.getItems().addAll(last_vaccination_date_options);
        last_vaccination_date_combo_box.getSelectionModel().select(individual.getLast_shot());
        mask_combo_box.getItems().addAll(mask_preferences_options);
        mask_combo_box.getSelectionModel().select(individual.getWear_mask());
        registration_failed_label.setVisible(false);
        username_text_field.setText(individual.getUser_name());
        password_password_field.setText(individual.getPassword());
        reentered_password_password_field.setText(individual.getPassword());
        first_name_text_field.setText(individual.getFirst_name());
        last_name_text_field.setText(individual.getLast_name());
        dob_text_field.setText(individual.getDoB());
        ssn_text_field.setText(individual.getSSN());
        vaccination_lot1_text_field.setText(individual.getFirst_dose());
        vaccination_lot2_text_field.setText(individual.getSecond_dose());
        security_question1_text_field.setText(individual.getAnswer1());
        security_question2_text_field.setText(individual.getAnswer2());

        original_ssn = ssn_text_field.getText();
    }

    /**
     * check strength of password
     *
     * @param event input event
     */
    public void passwordStrengthTester(KeyEvent event) {
        String password_entered = password_password_field.getText();
        System.out.println(password_entered.length());
        passwordReenteredMatches();
        if (password_entered.length() < 4) {
            password_strength_label_text.setText("Password Strength: Poor");
            password_strength_label.setStyle("-fx-border-color:red; -fx-background-color: red;");
        } else if (password_entered.length() >= 6 && password_entered.length() < 8) {
            if (passwordPassesSecurityCheck()) {
                password_strength_label_text.setText("Password Strength: Medium");
                password_strength_label.setStyle("-fx-border-color:orange; -fx-background-color: orange;");
            } else {
                password_strength_label_text.setText("Password Strength: Poor");
                password_strength_label.setStyle("-fx-border-color:red; -fx-background-color: red;");
            }
        } else if (password_entered.length() <= 15) {
            if (passwordPassesSecurityCheck()) {
                password_strength_label_text.setText("Password Strength: Good");
                password_strength_label.setStyle("-fx-border-color:green; -fx-background-color: green;");
            } else {
                password_strength_label_text.setText("Password Strength: Poor");
                password_strength_label.setStyle("-fx-border-color:red; -fx-background-color: red;");
            }
        } else {
            password_strength_label_text.setText("Password is too long");
            String password_cutoff = password_password_field.getText().substring(0, password_password_field.getLength() - 1);
            password_password_field.setText(password_cutoff);
            password_password_field.positionCaret(password_password_field.getLength());
        }
    }

    /**
     * check if password is legitimate
     *
     * @return returns true or false
     */
    public boolean passwordPassesSecurityCheck() {
        if (password_password_field.getText().length() < 6) {
            return false;
        } else if (password_password_field.getText().length() >= 6 && password_password_field.getText().length() <= 15) {
            if (hasSpecialChar(password_password_field.getText()) && hasUpperCase(password_password_field.getText())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * check if the password has an upper case letter
     *
     * @param string_to_check input string
     * @return return true or false
     */
    public boolean hasUpperCase(String string_to_check) {
        for (int i = 0; i < string_to_check.length(); i++) {
            if (Character.isUpperCase(string_to_check.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if string has a special character
     *
     * @param string_to_check input string
     * @return returns true or false
     */
    public boolean hasSpecialChar(String string_to_check) {
        Character[] special_chars_to_look_for = {'!', '@', '#', '%', '^', '&', '*', '?', '$', '<', '<', '(', '"', ';', '{', '}', '_', '-', '+', '=', '`', '~', '/', '|', '.', ',', '[', ']'};
        for (int i = 0; i < string_to_check.length(); i++) {
            for (int k = 0; k < special_chars_to_look_for.length; k++) {
                if (string_to_check.charAt(i) == special_chars_to_look_for[k]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check if password re entered matches
     */
    public void passwordReenteredMatches() {
        if (password_password_field.getText().equals(reentered_password_password_field.getText())) {
            reenter_password_label.setText("Reenter Password: Matches");
            passwords_match = true;
        } else {
            passwords_match = false;
            reenter_password_label.setText("Reenter Password: Doesn't Match");
        }
    }

    /**
     * exit registration page and go back to home page
     *
     * @param event input event
     * @throws IOException calls IOException if exists
     */
    public void exitRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updateAccount(ActionEvent event) throws SQLException {

        String sql_command = String.format("UPDATE `Individuals` SET `FirstName`='%s',`LastName`='%s',`Date of Birth`='%s',`SSN`='%s',`Vaccinated`='%s',`First Dose`='%s',`Second Dose`='%s',`Booster`='%s',`LastVaccine`='%s',`Wear mask`='%s',`User Name`='%s',`Password`='%s',`answer1`='%s',`answer2`='%s' WHERE `SSN`='%s'",
                first_name_text_field.getText(), last_name_text_field.getText(), dob_text_field.getText(), ssn_text_field.getText(), vaccine_combo_box.getSelectionModel().getSelectedItem(), vaccination_lot1_text_field.getText(), vaccination_lot2_text_field.getText(), booster_text_field.getText(), last_vaccination_date_combo_box.getSelectionModel().getSelectedItem(), mask_combo_box.getSelectionModel().getSelectedItem(), username_text_field.getText(), password_password_field.getText(),
                security_question1_text_field.getText(), security_question2_text_field.getText(), original_ssn);

        if (!checkDuplicate(ssn_text_field.getText()) || original_ssn.equals(ssn_text_field.getText())) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            original_ssn = ssn_text_field.getText();
        } else {
            registration_failed_label.setText("This Person already exists in the database.");

        }
    }

    /**
     * checks duplicate
     *
     * @param newSSN new SSN of a person
     * @return true if a duplicate is present
     * @throws SQLException
     */
    public boolean checkDuplicate(String newSSN) throws SQLException {

        String sql_command = String.format("SELECT `SSN` FROM `Individuals` WHERE `SSN`='%s'", newSSN);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        ResultSetMetaData metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(newSSN)) {

                    registration_failed_label.setText("This Person already exists");

                    break;
                }
            }
            result_found = true;
        }
        return result_found;
    }

}
