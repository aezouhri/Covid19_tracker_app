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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Rida Errachidi
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * Government Registration Controller controls the government registration page
 */
public class GovernmentRegController implements Initializable {

    /**
     * new government query called
     */
    private final GovernmentQueries GovernmentQueries = new GovernmentQueries();
    /**
     * government name
     */
    @FXML
    private TextField government_name_text_field;
    /**
     * mask required drop down
     */
    @FXML
    private ComboBox<String> mask_required_combo_box;
    /**
     * vaccination drop down menu
     */
    @FXML
    private ComboBox<String> vaccination_combo_box;

    /**
     * cancel registration button
     */
    @FXML
    private Button cancel_registration_button;
    /**
     * register government button
     */
    @FXML
    private Button registerGovButton;
    /**
     * password strength notification
     */
    @FXML
    private Label password_strength_label;
    /**
     * password strength color notification
     */
    @FXML
    private Label password_strength_color;
    /**
     * password re entry
     */
    @FXML
    private PasswordField password_reentered_password_field;
    /**
     * security answer 1
     */
    @FXML
    private TextField security_answer_text_field;
    /**
     * security answer 2
     */
    @FXML
    private TextField security2_answer_text_field;
    /**
     * username text field
     */
    @FXML
    private TextField username_text_field;
    /**
     * location of government
     */
    @FXML
    private TextField location_text_field;
    /**
     * covid drop down menu
     */
    @FXML
    private ComboBox<String> covid_test_entry_combo_box;
    /**
     * quarantine required drop down menu
     */
    @FXML
    private ComboBox<String> quarantine_required_combo_box;
    /**
     * quarantine length drop down menu
     */
    @FXML
    private ComboBox<String> quarantine_length_combo_box;
    /**
     * vaccine time drop down menu
     */
    @FXML
    private ComboBox<String> vaccine_time_required;
    /**
     * government password
     */
    @FXML
    private PasswordField Government_password_field;
    /**
     * registration label notification
     */
    @FXML
    private Label registration_label_failed;
    /**
     * re enter password
     */
    @FXML
    private Label reentered_password_label;
    /**
     * passwords match
     */
    private boolean passwords_match;
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
     * vaccine answer options
     */
    @FXML
    private final String[] vaccination_answer_options = {"Required", "Not Required"};
    /**
     * covid test requirement options
     */
    private final String[] covid_test_options = {"Required", "Not required"};
    /**
     * quarantine answer options
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
     * mask preference dptions
     */
    private String[] mask_preferences_options = {"Required", "Not Required"};


    /**
     * registers a new government and returns back to home page
     * @param event input event
     * @throws IOException calls IOException if exists
     * @throws SQLException calls SQLException if exists
     */
    public void registerNewGov(ActionEvent event) throws IOException, SQLException {
        if(passwordPassesSecurityCheck() && passwords_match) {
            Governments new_government = new Governments(government_name_text_field.getText(), location_text_field.getText(), mask_required_combo_box.getSelectionModel().getSelectedItem(), vaccination_combo_box.getSelectionModel().getSelectedItem(), vaccine_time_required.getSelectionModel().getSelectedItem(), covid_test_entry_combo_box.getSelectionModel().getSelectedItem(), quarantine_required_combo_box.getSelectionModel().getSelectedItem(), quarantine_length_combo_box.getSelectionModel().getSelectedItem(), username_text_field.getText(), Government_password_field.getText(), security_answer_text_field.getText(), security2_answer_text_field.getText());
            GovernmentQueries GovernmentsQueries = new GovernmentQueries();
            if (!GovernmentsQueries.addGovernment(new_government)) {
                registration_label_failed.setVisible(true);
            }
            Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            registration_label_failed.setText("Password Did not Meet Requirements");
            registration_label_failed.setVisible(true);
        }
    }

    /**
     * initialize government drop down menus
     * @param url input file url
     * @param resourceBundle input file resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vaccination_combo_box.getItems().addAll(vaccination_answer_options);
        vaccination_combo_box.getSelectionModel().selectFirst();
        vaccine_time_required.getItems().addAll(last_vaccination_date_options);
        vaccine_time_required.getSelectionModel().selectFirst();
        mask_required_combo_box.getItems().addAll(mask_preferences_options);
        mask_required_combo_box.getSelectionModel().selectFirst();
        quarantine_required_combo_box.getItems().addAll(quarantine_answer_options);
        quarantine_required_combo_box.getSelectionModel().selectFirst();
        quarantine_length_combo_box.getItems().addAll(quarantine_time_options);
        quarantine_length_combo_box.getSelectionModel().selectFirst();
        covid_test_entry_combo_box.getItems().addAll(covid_test_options);
        covid_test_entry_combo_box.getSelectionModel().selectFirst();
        registration_label_failed.setVisible(false);

    }

    /**
     * cancel and return Home
     * @param event input event
     * @throws IOException throws IOException if exists
     */
    public void cancelGovRegistration(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * makes the quarantine length dropdown visible
     * @param e input e
     * @throws IOException throws IOException if exists
     */
    public void quarantine_length_Visible(ActionEvent e ) throws IOException{
        String quarantine_required = quarantine_required_combo_box.getValue();

        if (quarantine_required.equals("Yes")){
            quarantine_length_combo_box.setDisable(false);
            quarantine_length_combo_box.setVisible(true);
        }
        else if (quarantine_required.equals("No")){
            quarantine_length_combo_box.setDisable(true);
        }
    }

    /**
     * shows password strength
     * @param event input event
     */
    public void passwordStrengthTester(KeyEvent event)
    {
        String password_entered = Government_password_field.getText();
        System.out.println(password_entered.length());
        passwordReenteredMatches();
        if(password_entered.length() < 4)
        {
            password_strength_label.setText("Password Strength: Poor");
            password_strength_color.setStyle("-fx-border-color:red; -fx-background-color: red;");
        }
        else if(password_entered.length() >= 6 && password_entered.length() < 8)
        {
            if(passwordPassesSecurityCheck()) {
                password_strength_label.setText("Password Strength: Medium");
                password_strength_color.setStyle("-fx-border-color:orange; -fx-background-color: orange;");
            }
            else
            {
                password_strength_label.setText("Password Strength: Poor");
                password_strength_color.setStyle("-fx-border-color:red; -fx-background-color: red;");
            }
        }
        else if(password_entered.length() <= 15){
            if(passwordPassesSecurityCheck()) {
                password_strength_label.setText("Password Strength: Good");
                password_strength_color.setStyle("-fx-border-color:green; -fx-background-color: green;");
            }
            else
            {
                password_strength_label.setText("Password Strength: Poor");
                password_strength_color.setStyle("-fx-border-color:red; -fx-background-color: red;");
            }
        }
        else{
            password_strength_label.setText("Password is too long");
            String password_cutoff = Government_password_field.getText().substring(0, Government_password_field.getLength() - 1);
            Government_password_field.setText(password_cutoff);
            Government_password_field.positionCaret(Government_password_field.getLength());
        }
    }

    /**
     * checks if password is eligible
     * @return returns true or false
     */
    public boolean passwordPassesSecurityCheck()
    {
        if(Government_password_field.getText().length() < 6)
        {
            return false;
        }
        else if(Government_password_field.getText().length() >= 6 && Government_password_field.getText().length() <=15)
        {
            if(hasSpecialChar(Government_password_field.getText()) && hasUpperCase(Government_password_field.getText()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * checks if password has an upper case letter
     * @param string_to_check input string
     * @return returns true or false
     */
    public boolean hasUpperCase(String string_to_check)
    {
        for(int i = 0; i < string_to_check.length(); i++){
            if(Character.isUpperCase(string_to_check.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if string has a special character
     * @param string_to_check input string
     * @return returns true or false
     */
    public boolean hasSpecialChar(String string_to_check)
    {
        Character[] special_chars_to_look_for = {'!', '@', '#', '%', '^', '&', '*', '?', '$', '<', '<', '(', '"', ';', '{', '}', '_', '-', '+', '=', '`', '~', '/', '|','.', ',', '[', ']'};
        for(int i = 0; i < string_to_check.length(); i++){
            for(int k = 0; k < special_chars_to_look_for.length; k++) {
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
    public void passwordReenteredMatches()
    {
        if(Government_password_field.getText().equals(password_reentered_password_field.getText()))
        {
            reentered_password_label.setText("Reenter Password: Matches");
            passwords_match = true;
        }
        else
        {
            passwords_match = false;
            reentered_password_label.setText("Reenter Password: Doesn't Match");
        }
    }

}
