import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * @author Rida Errachidi
 * Controls the government login page
 */

public class GovernmentLoginController implements Initializable {
    /**
     * connects to SQL server
     */
    private final DatabaseConnection datacon = new DatabaseConnection();
    /**
     * gets to connection to SQL
     */
    private final Connection connection = datacon.getConnection();
    /**
     * original name of the government
     */
    private String original_name = "";
    /**
     * private stage to create a new stage
     */
    private Stage stage;
    /**
     * private scene to create a new scene
     */
    private Scene scene;
    /**
     * private root to create new scene with
     */
    private Parent root;
    /**
     * government name
     */
    @FXML
    private TextField government_name_text_field;

    /**
     * the mask required check box
     */
    @FXML
    private ComboBox<String> mask_required_combo_box;

    /**
     * the vaccination required check box
     */
    @FXML
    private ComboBox<String> vaccination_combo_box;

    /**
     * register the government
     */
    @FXML
    private Button registerGovButton;
    /**
     * the first security question text field
     */
    @FXML
    private TextField security_answer_text_field;
    /**
     * the second security question text field
     */
    @FXML
    private TextField security2_answer_text_field;
    /**
     * username text field
     */
    @FXML
    private TextField username_text_field;

    /**
     * location text field
     */
    @FXML
    private TextField location_text_field;

    /**
     * covid test requirement combo box
     */
    @FXML
    private ComboBox<String> covid_test_entry_combo_box;

    /**
     * quarantine requirement combo box
     */
    @FXML
    private ComboBox<String> quarantine_required_combo_box;

    /**
     * quarantine requirement length combo box
     */
    @FXML
    private ComboBox<String> quarantine_length_combo_box;

    /**
     * vaccine requirement combo box
     */
    @FXML
    private ComboBox<String> vaccine_time_required;

    /**
     * password field
     */
    @FXML
    private PasswordField Government_password_field;
    /**
     * registration label
     */
    @FXML
    private Label registration_label_failed;
    /**
     * controls which screen is shown
     */
    SceneController finishGovernmentRegistration = new SceneController();

    /**
     * options for vaccination reqs
     */
    @FXML
    private final String[] vaccination_answer_options = {"No", "Yes"};

    /**
     * covid testing options
     */
    private final String[] covid_test_options = {"Required", "Not required"};

    /**
     * quarantine options
     */
    private String[] quarantine_answer_options = {"No", "Yes"};

    /**
     * quarantine time options
     */
    private String[] quarantine_time_options = {"14 or More Days", "10-13 days", "7-9 Days", "Under 7 Days"};

    /**
     * last vaccination date options
     */
    private String[] last_vaccination_date_options = {"Two Weeks or More", "10 - 13 Days", "7-9 Days", "Under 7 Days"};

    /**
     * mask preferences options
     */
    private String[] mask_preferences_options = {"No", "Yes"};

    /**
     * password strength label
     */
    @FXML
    private Label password_strength_label;

    /**
     * password strength label
     */
    @FXML
    private Label password_strength_label_text;

    /**
     * password field
     */
    @FXML
    private PasswordField reentered_password_password_field;

    /**
     * checks if passwords match
     */
    private boolean passwords_match;

    /**
     * label for reenter password
     */
    @FXML
    private Label reenter_password_label;

    /**
     * initalizes controler
     *
     * @param url            used for files
     * @param resourceBundle used for files
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Governments governments = finishGovernmentRegistration.getGovernments();
        vaccination_combo_box.getItems().addAll(vaccination_answer_options);
        vaccination_combo_box.getSelectionModel().select(governments.getVaccination());
        vaccine_time_required.getItems().addAll(last_vaccination_date_options);
        vaccine_time_required.getSelectionModel().select(governments.getLast_vaccine());
        mask_required_combo_box.getItems().addAll(mask_preferences_options);
        mask_required_combo_box.getSelectionModel().select(governments.getMask_mandate());
        quarantine_required_combo_box.getItems().addAll(quarantine_answer_options);
        quarantine_required_combo_box.getSelectionModel().select(governments.getQuarantine());
        quarantine_length_combo_box.getItems().addAll(quarantine_time_options);
        quarantine_length_combo_box.getSelectionModel().select(governments.getQuarantine_time());
        covid_test_entry_combo_box.getItems().addAll(covid_test_options);
        covid_test_entry_combo_box.getSelectionModel().select(governments.getTest_entry());
        registration_label_failed.setVisible(false);
        quarantine_length_combo_box.setVisible(false);

        original_name = government_name_text_field.getText();
        government_name_text_field.setText(governments.getGovernment_name());
        username_text_field.setText(governments.getUsername());
        Government_password_field.setText(governments.getPassword());
        reentered_password_password_field.setText(governments.getPassword());
        location_text_field.setText(governments.getLocation());
        security_answer_text_field.setText(governments.getAnswer1());
        security2_answer_text_field.setText(governments.getAnswer2());
        original_name = government_name_text_field.getText();
    }

    /**
     * gets length of quarantine
     *
     * @param e event handler
     * @throws IOException error handling
     */
    public void quarantine_length_Visible(ActionEvent e) throws IOException {
        String quarantine_required = quarantine_required_combo_box.getValue();

        if (quarantine_required.equals("Yes")) {
            quarantine_length_combo_box.setDisable(false);
            quarantine_length_combo_box.setVisible(true);
        } else if (quarantine_required.equals("No")) {
            quarantine_length_combo_box.setDisable(true);
            quarantine_length_combo_box.setVisible(false);
        }
    }

    /**
     * updates account
     *
     * @param event event handler
     * @throws SQLException error handler
     */
    @FXML
    public void updateAccount(ActionEvent event) throws SQLException {
//        if(passwords_match && passwordPassesSecurityCheck()) {
        String sql_command = String.format("UPDATE `Governments` SET `Government`='%s',`Location`='%s',`Mask Mandate`='%s',`Vaccination`='%s',`LastVaccinAllowed`='%s',`Test upon entry`='%s',`Quarantine`='%s',`Quarantine period`='%s',`Username`='%s',`Password`='%s',`Answer1`='%s',`Answer2`='%s' WHERE `Government`='%s'",
                government_name_text_field.getText(), location_text_field.getText(), mask_required_combo_box.getSelectionModel().getSelectedItem(), vaccination_combo_box.getSelectionModel().getSelectedItem(), vaccine_time_required.getSelectionModel().getSelectedItem(), covid_test_entry_combo_box.getSelectionModel().getSelectedItem(), quarantine_required_combo_box.getSelectionModel().getSelectedItem(), quarantine_length_combo_box.getSelectionModel().getSelectedItem(), username_text_field.getText(), Government_password_field.getText(), security_answer_text_field.getText(), security2_answer_text_field.getText(), original_name);

        if (!checkDuplicate(government_name_text_field.getText())) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            original_name = government_name_text_field.getText();

        } else {
            registration_label_failed.setText("This Government name is already picked");
        }
    }
//        else
//        {
//            registration_label_failed.setText("Check Password");
//        }
//    }

    /**
     * checks duplicate
     *
     * @param newGovName new gov name
     * @return true if a duplicate is present
     * @throws SQLException
     */
    public boolean checkDuplicate(String newGovName) throws SQLException {


        String sql_command = String.format("SELECT `Government` FROM `Governments` WHERE `Government`='%s'", newGovName);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        ResultSetMetaData metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(newGovName)) {
                    registration_label_failed.setText("This Government name already exists");
                    break;
                }
            }
            result_found = true;
        }
        return result_found;
    }


    /**
     * Checks and displays password strength
     *
     * @param event input event
     */
    public void passwordStrengthTester(KeyEvent event) {
        String password_entered = Government_password_field.getText();
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
            String password_cutoff = Government_password_field.getText().substring(0, Government_password_field.getLength() - 1);
            Government_password_field.setText(password_cutoff);
            Government_password_field.positionCaret(Government_password_field.getLength());
        }
    }

    /**
     * check if password passes security check
     *
     * @return returns true or false
     */
    public boolean passwordPassesSecurityCheck() {
        if (Government_password_field.getText().length() < 6) {
            return false;
        } else if (Government_password_field.getText().length() >= 6 && Government_password_field.getText().length() <= 15) {
            if (hasSpecialChar(Government_password_field.getText()) && hasUpperCase(Government_password_field.getText())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * checks if password has an upper case letter
     *
     * @param string_to_check input string
     * @return returns true or false
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
     * checks if string has a special character
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
     * check if reentered password matches input password
     */
    public void passwordReenteredMatches() {
        if (Government_password_field.getText().equals(reentered_password_password_field.getText())) {
            reenter_password_label.setText("Reenter Password: Matches");
            passwords_match = true;
        } else {
            passwords_match = false;
            reenter_password_label.setText("Reenter Password: Doesn't Match");
        }
    }

    public void exitRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}