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

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * @author Rida Errachidi
 * OrganizationLoginController controls the organization page and uses the OrganizationLogin.fxml file
 */
public class OrganizationLoginController implements Initializable {


    private final DatabaseConnection datacon = new DatabaseConnection();
    private final Connection connection = datacon.getConnection();
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
     * grab the SceneController that has methods to switch to different pages
     */
    SceneController sceneController = new SceneController();

    /**
     * settings changed notificatoin
     */
    @FXML
    private Label settings_changed_label;

    /**
     * log out button when you want to leave organization login page
     */
    @FXML
    private Button logout_button;

    /**
     * organization security answer 1
     */
    @FXML
    public TextField answer1_area;

    /**
     * organization security answer 2
     */
    @FXML
    public TextField answer2_area;

    /**
     * password required notification
     */
    @FXML
    public Label password_reqs_label;

    /**
     * tells how strong inputted password is
     */
    @FXML
    public Label password_strength_label_text;

    /**
     * organization's name
     */
    @FXML
    public TextField organization_name;

    /**
     * drop down menu for mask mandate
     */
    @FXML
    public ComboBox<String> mask_combobox;

    /**
     * drop down menu for vaccine requirement
     */
    @FXML
    public ComboBox<String> vaccine_combobox;

    /**
     * password strength notification
     */
    @FXML
    public Label password_strength_label;
    /**
     * change organization's settings
     */
    @FXML
    private Button change_settings_button;
    /**
     * phone number for organization
     */
    @FXML
    public TextField phone_number_area;
    /**
     * address for organization
     */
    @FXML
    public TextField address_area;
    /**
     * organization's website
     */
    @FXML
    public TextField website_area;
    /**
     * organization's password
     */
    @FXML
    public PasswordField password_area;
    /**
     * organization's username
     */
    @FXML
    public TextField username_area;
    /**
     * covid test drop down menu
     */
    @FXML
    public ComboBox<String> test_combobox;
    /**
     * re enter password/ verify password
     */
    @FXML
    public PasswordField re_entered_password_area;
    /**
     * display if password is strong enough
     */
    @FXML
    public Label re_entered_password_strength_label_text;
    /**
     * vaccine requirement answer options
     */
    private String[] vaccination_answer_options = {"Required", "Not Required"};
    /**
     * mask mandate options
     */
    private String[] mask_preferences_options = {"Required", "Not required", "Optional"};


    /**
     * new organization
     */
    private Organization organization;

    /**
     * registers a new government and returns back to home page
     *
     * @param event new event
     * @throws IOException  throws IOException if occurs
     * @throws SQLException throws SQLException if occurs
     */
    @FXML
    public void registerNewOrg(ActionEvent event) throws IOException, SQLException {
        Organization newOrg = new Organization(organization_name.getText(), website_area.getText(), phone_number_area.getText(), address_area.getText(), vaccine_combobox.getSelectionModel().getSelectedItem(), mask_combobox.getSelectionModel().getSelectedItem(), test_combobox.getSelectionModel().getSelectedItem(), username_area.getText(), password_area.getText(), answer1_area.getText(), answer2_area.getText());
        OrganizationQueries orgQuery = new OrganizationQueries();
        if (!orgQuery.addOrganization(newOrg)) {
            settings_changed_label.setVisible(true);
        } else {

            Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    /**
     * initalizbles the inputs for text boxes and drop down menus
     *
     * @param url            gathers url of files
     * @param resourceBundle gathers resources of file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        organization = sceneController.getOrganization();
        vaccine_combobox.getItems().addAll(vaccination_answer_options);
        vaccine_combobox.getSelectionModel().select(organization.getVaccine_required());
        test_combobox.getItems().addAll(vaccination_answer_options);
        test_combobox.getSelectionModel().select(organization.getTest_required());
        mask_combobox.getItems().addAll(mask_preferences_options);
        mask_combobox.getSelectionModel().select(organization.getMask_required());
        organization_name.setText(organization.getOrganization_name());
        username_area.setText(organization.getUsername());
        password_area.setText(organization.getPassword());
        re_entered_password_area.setText(organization.getPassword());
        website_area.setText(organization.getWebsite_link());
        phone_number_area.setText(organization.getPhone_number());
        address_area.setText(organization.getLocation());
        answer1_area.setText(organization.getAnswer1());
        answer2_area.setText(organization.getAnswer2());
        settings_changed_label.setVisible(false);

        original_name = organization_name.getText();
    }

    /**
     * tests strength of inputted password
     *
     * @param event takes in event
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
     * checks if password is value
     *
     * @return returns true or false
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
     * checks if inputted password has an upper case letter
     *
     * @param string_to_check input password
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
     * checks if password has a special character
     *
     * @param string_to_check inputs string
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
     * checks if re-entered password matches entered password
     */
    public void passwordReenteredMatches() {
        if (password_area.getText().equals(re_entered_password_area.getText())) {
            re_entered_password_strength_label_text.setText("Reenter Password:Matches");
        } else {
            re_entered_password_strength_label_text.setText("Reenter Password:Doesn't Match");
        }
    }

    /**
     * logs out of Organization Login page and returns to Home page
     *
     * @param event input button clicked
     * @throws IOException throws IOException if thrown
     */
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * updates the organizations settings
     *
     * @param event event to handle action performed
     * @throws SQLException SQL error thrown if it occurs
     */
    public void updateAccount(ActionEvent event) throws SQLException {

        String sql_command = String.format("UPDATE `Organizations` SET `CompanyName`='%s',`WebSite`='%s',`Location`='%s',`Phone`='%s',`Vaccine Required`='%s',`Mask Required`='%s',`Test Required`='%s',`username`='%s',`password`='%s',`Answer 1`='%s',`Answer 2`='%s' WHERE `CompanyName`='%s'",
                organization_name.getText(), website_area.getText(), address_area.getText(), phone_number_area.getText(), vaccine_combobox.getSelectionModel().getSelectedItem(), mask_combobox.getSelectionModel().getSelectedItem(), test_combobox.getSelectionModel().getSelectedItem(), username_area.getText(), password_area.getText(), answer1_area.getText(), answer2_area.getText(), original_name);
        if (!checkDuplicate(organization_name.getText())) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            original_name = organization_name.getText();
        } else {
            settings_changed_label.setText("This Government already exists in the DataBase");
        }
    }

    public boolean checkDuplicate(String newGovName) throws SQLException {

        String sql_command = String.format("SELECT `CompanyName` FROM `Organizations` WHERE `CompanyName`='%s'", newGovName);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        ResultSetMetaData metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(newGovName)) {

                    System.out.println("This organization already exists");

                    break;
                }
            }
            result_found = true;
        }
        return result_found;
    }


}
