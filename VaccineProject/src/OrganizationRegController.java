import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Rida Errachidi
 * @author Ben DeSollar
 * @author Adnane Ezouhri
 * Organization Regulation Controller controls the Organization registration page
 */
public class OrganizationRegController implements Initializable {

    /**
     * exit button
     */
    public Button exit_button;
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
     * new SceneController declared
     */
    SceneController finishGovernmentRegistration = new SceneController();
    /**
     * security question 1 answer
     */
    @FXML
    public TextField answer1_area;
    /**
     * security question 2 answer
     */
    @FXML
    public TextField answer2_area;
    /**
     * registration label failed notification
     */
    @FXML
    public Label registration_failed_label;
    /**
     * password requirements notification
     */
    @FXML
    public Label password_reqs_label;
    /**
     * password strength notification
     */
    @FXML
    public Label password_strength_label_text;
    /**
     * organization name
     */
    @FXML
    public TextField organization_name;
    /**
     * mask drop down
     */
    @FXML
    public ComboBox<String> mask_combobox;
    /**
     * vaccine requirement drop down
     */
    @FXML
    public ComboBox<String> vaccine_combobox;
    /**
     * password strength notification
     */
    @FXML
    public Label password_strength_label;
    /**
     * register organization button
     */
    @FXML
    private Button registerOrgButton;
    /**
     * phone number
     */
    @FXML
    public TextField phone_number_area;
    /**
     * address
     */
    @FXML
    public TextField address_area;
    /**
     * website
     */
    @FXML
    public TextField website_area;
    /**
     * password
     */
    @FXML
    public PasswordField password_area;
    /**
     * username
     */
    @FXML
    public TextField username_area;
    /**
     * test drop down
     */
    @FXML
    public ComboBox<String> test_combobox;
    /**
     * re entered password
     */
    @FXML
    public PasswordField re_entered_password_area;
    /**
     * re entered password strength label
     */
    @FXML
    public Label re_entered_password_strength_label_text;
    /**
     * vaccine answer options
     */
    private String[] vaccination_answer_options = {"Required", "Not Required"};
    /**
     * mask preference options
     */
    private String[] mask_preferences_options = {"Required", "Not required"};

    /**
     * registers a new organization and returns to home page
     *
     * @param event input event
     * @throws IOException  throws IOException if exists
     * @throws SQLException throws SQLException if exists
     */
    @FXML
    public void registerNewOrg(ActionEvent event) throws IOException, SQLException {

        Organization newOrg = new Organization(organization_name.getText(), website_area.getText(), phone_number_area.getText(), address_area.getText(), vaccine_combobox.getSelectionModel().getSelectedItem(), mask_combobox.getSelectionModel().getSelectedItem(), test_combobox.getSelectionModel().getSelectedItem(), username_area.getText(), password_area.getText(), answer1_area.getText(), answer2_area.getText());
        OrganizationQueries orgQuery = new OrganizationQueries();
        if (!orgQuery.addOrganization(newOrg)) {
            registration_failed_label.setVisible(true);
        } else {

            Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    /**
     * initialze organization regulation controller
     *
     * @param url            input url from file
     * @param resourceBundle input resourceBundle from file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vaccine_combobox.getItems().addAll(vaccination_answer_options);
        vaccine_combobox.getSelectionModel().selectFirst();
        test_combobox.getItems().addAll(vaccination_answer_options);
        test_combobox.getSelectionModel().selectFirst();
        mask_combobox.getItems().addAll(mask_preferences_options);
        mask_combobox.getSelectionModel().selectFirst();
        registration_failed_label.setVisible(false);
    }

    /**
     * tests password strength
     *
     * @param event input event
     */
    public void passwordStrengthTester(KeyEvent event) {
        String password_entered = password_area.getText();
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
            String password_cutoff = password_area.getText().substring(0, password_area.getLength() - 1);
            password_area.setText(password_cutoff);
            password_area.positionCaret(password_area.getLength());
        }
    }

    /**
     * check if password is eligible
     *
     * @return return true or false
     */
    public boolean passwordPassesSecurityCheck() {
        if (password_area.getText().length() < 6) {
            return false;
        } else if (password_area.getText().length() >= 6 && password_area.getText().length() <= 15) {
            if (hasSpecialChar(password_area.getText()) && hasUpperCase(password_area.getText())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * check if password has upper case
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
     * checks if password has special character
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
     * check if reentered password matches
     */
    public void passwordReenteredMatches() {
        if (password_area.getText().equals(re_entered_password_area.getText())) {
            re_entered_password_strength_label_text.setText("Reenter Password:Matches");
        } else {
            re_entered_password_strength_label_text.setText("Reenter Password:Doesn't Match");
        }
    }

    /**
     * exit registration page
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
