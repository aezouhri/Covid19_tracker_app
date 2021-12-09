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
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Rida Errachidi
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * Individual Registration Page uses SceneController object and IndividualRegistration.fxml
 */
public class IndividualRegistrationController implements Initializable {

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
     * declare new SceneController object
     */
    SceneController finishIndividualRegistration = new SceneController();

    /**
     * first name text field
     */
    @FXML
    private TextField first_name_text_field;
    /**
     * mask mandate drop down menu
     */
    @FXML
    private ComboBox<String> mask_combo_box;
    /**
     * security question 1 answer
     */
    @FXML
    private TextField security_question1_text_field;
    /**
     * security question 2 answer
     */
    @FXML
    private TextField security_question2_text_field;
    /**
     * social security field
     */
    @FXML
    private TextField ssn_text_field;
    /**
     * Are you vaccinated drop down menu
     */
    @FXML
    private ComboBox<String> vaccine_combo_box;
    /**
     * first vaccination lot number
     */
    @FXML
    private TextField vaccination_lot1_text_field;
    /**
     * second vaccination lot number
     */
    @FXML
    private TextField vaccination_lot2_text_field;
    /**
     * booster shot text field
     */
    @FXML
    private TextField booster_text_field;
    /**
     * register individual
     */
    @FXML
    private Button individualRegisteredButton;
    /**
     * date of birth of individual
     */
    @FXML
    private TextField dob_text_field;
    /**
     * password field
     */
    @FXML
    private PasswordField password_password_field;
    /**
     * password strength notification
     */
    @FXML
    private Label password_strength_label;
    /**
     * re enter password field
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
     * registration failed notification
     */
    @FXML
    private Label registration_failed_label;
    /**
     * last vaccination date drop down menu
     */
    @FXML
    private ComboBox<String> last_vaccination_date_combo_box;
    /**
     * password requirement label
     */
    @FXML
    private Label password_reqs_label;
    /**
     * password strength label
     */
    @FXML
    private Label password_strength_label_text;
    /**
     * re enter password notification
     */
    @FXML
    private Label reenter_password_label;
    /**
     * exit registration screen button
     */
    @FXML
    public Button exit_button;
    /**
     * passwords match true or false for re-entry
     */
    private boolean passwords_match;
    /**
     * Vaccinated or not
     */
    private String[] vaccination_answer_options = {"No", "Yes"};
    /**
     * Date of last vaccine
     */
    private String[] last_vaccination_date_options = {"Two Weeks or More", "10 - 13 Days", "7 - 9 Days", "Under 7 Days"};
    /**
     * does individual wear masks
     */
    private String[] mask_preferences_options = {"No Preference", "Will Wear", "Will Not Wear"};


    /**
     * Registers a new individual and goes back to home page
     *
     * @param event input event
     * @throws IOException  throws IOException if exists
     * @throws SQLException throws SQLException if exists
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
     * initializes dropdown menus
     *
     * @param url            inputs url from file
     * @param resourceBundle inputs resource bundle from file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccine_combo_box.getItems().addAll(vaccination_answer_options);
        vaccine_combo_box.getSelectionModel().selectFirst();
        last_vaccination_date_combo_box.getItems().addAll(last_vaccination_date_options);
        last_vaccination_date_combo_box.getSelectionModel().selectFirst();
        mask_combo_box.getItems().addAll(mask_preferences_options);
        mask_combo_box.getSelectionModel().selectFirst();
        registration_failed_label.setVisible(false);
    }

    /**
     * Checks and displays password strength
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
     * check if password passes security check
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
        if (password_password_field.getText().equals(reentered_password_password_field.getText())) {
            reenter_password_label.setText("Reenter Password: Matches");
            passwords_match = true;
        } else {
            passwords_match = false;
            reenter_password_label.setText("Reenter Password: Doesn't Match");
        }
    }

    /**
     * exit Registration page and go to Home Page
     *
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void exitRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
