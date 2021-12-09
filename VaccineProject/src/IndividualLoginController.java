
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Rida Errachidi
 * @author Ben DeSollar
 * @author Adnane Ezouhri
 * Individual Login Controller controlls the Individual Login page and uses SceneController and IndividualLogin.fxml
 */
public class IndividualLoginController implements Initializable {


    /**
     * new stage
     */
    private Stage stage;
    /**
     * new scene
     */
    private Scene scene;
    /**
     * account name
     */
    @FXML
    private Label account_name_label;
    /**
     * first name
     */
    @FXML
    private Label first_name_label;
    /**
     * last name
     */
    @FXML
    private Label last_name_label;
    /**
     * options drop down menu
     */
    @FXML
    private ComboBox<String> options_combo_box;
    /**
     * organization button
     */
    @FXML
    private Button OrganizationButton;
    /**
     * government button
     */
    @FXML
    private Button GovernmentButton;
    /**
     * search text field
     */
    @FXML
    private TextField searchTextField;
    /**
     * Table view for log in page
     */
    @FXML
    private TableView<Organization> TableViewTable;
    /**
     * company name column
     */
    @FXML
    private TableView<Organization> TableViewOrganizations;

    @FXML
    private TableColumn<Organization, String> CompanyNameColumn;
    /**
     * website column
     */
    @FXML
    private TableColumn<Organization, Hyperlink> WebsiteColumn;
    /**
     * location column
     */
    @FXML
    private TableColumn<Organization, String> LocationColumn;
    /**
     * phone number column
     */
    @FXML
    private TableColumn<Organization, String> PhoneColumn;
    /**
     * vaccine column
     */
    @FXML
    private TableColumn<Organization, String>VaccineColumn;
    /**
     * mask column
     */
    @FXML
    private TableColumn<Organization, String>MaskColumn;
    /**
     * covid test column
     */
    @FXML
    private TableColumn<Organization, String>TestColumn;
    /**
     * quarantine column
     */
    @FXML
    private TableView<Governments> TableViewGovernments;

    /**
     * Table for government data
     */
    @FXML
    private TableColumn<Governments, String> CompanyNameColumn1;

    /**
     * Location column
     */
    @FXML
    private TableColumn<Governments, String> LocationColumn1;

    /**
     * vaccine column
     */
    @FXML
    private TableColumn<Governments, String>VaccineColumn1;

    /**
     * mask column
     */
    @FXML
    private TableColumn<Governments, String>MaskColumn1;

    /**
     * test column
     */
    @FXML
    private TableColumn<Governments, String>TestColumn1;

    /**
     * Quarantine Column
     */
    @FXML
    private TableColumn<Governments, String> QuarantineColumn11;

    /**
     * Quarantine Column for Quarantine length
     */
    @FXML
    private TableColumn<Governments, String> QuarantineColumn2;

    /**
     * checkbox for vaccination requirement search
     */
    @FXML
    private CheckBox vaccination_check_box;

    /**
     * checkbox for mask requirement search
     */
    @FXML
    private CheckBox mask_check_box;

    /**
     * check box for testing requirement search
     */
    @FXML
    private CheckBox testing_check_box;

    /**
     * label for what type of database to search in
     */
    @FXML
    private Label search_label;

    /**
     * organization data to be put in table
     */
    private final ObservableList<Organization> organization_data =
            FXCollections.observableArrayList();

    /**
     * government data to be put into table
     */
    private final ObservableList<Governments> government_data =
            FXCollections.observableArrayList();
    /**
     * new SceneController
     */
    private static final SceneController SceneSelector= new SceneController();
    /**
     * new organization queries
     */
    private static final OrganizationQueries organizationQueries = new OrganizationQueries();
    /**
     * new governmentQuarries object
     */
    public static final GovernmentQueries governmentQuarries = new GovernmentQueries();

    /**
     * individual object for logged in user
     */
    private Individuals individualLoggedIn;

    /**
     * which database the user wantsto look through
     */
    private String database;

    /**
     * options drop down menu choices
     */

    public String[] option_menu_choices = {"Select Options", "Settings", "Logout"};

    /**
     * display table cells
     * @throws SQLException throws SQLExcetion if exists
     */
    public void displayTableCells() throws SQLException {
        if(database.equals("Organizations")) {
            search_label.setText("Search Organizations:");
            organization_data.clear();
            CompanyNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("organization_name"));

            WebsiteColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, Hyperlink>("website_link"));

            LocationColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("location"));

            PhoneColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("phone_number"));


            VaccineColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("vaccine_required"));


            MaskColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("mask_required"));


            TestColumn.setCellValueFactory(
                    new PropertyValueFactory<Organization, String>("test_required"));

            ArrayList<Organization> organizationArrayList = organizationQueries.getOrganization();
            organization_data.addAll(organizationArrayList);

            TableViewOrganizations.setItems(organization_data);
            TableViewOrganizations.getSelectionModel().setCellSelectionEnabled(true);
        }
        else
        {
            search_label.setText("Search Governments:");
            government_data.clear();
            CompanyNameColumn1.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("government_name"));
            LocationColumn1.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("location"));
            VaccineColumn1.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("vaccination"));
            MaskColumn1.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("mask_mandate"));
            TestColumn1.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("test_entry"));
            QuarantineColumn11.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("quarantine"));
            QuarantineColumn2.setCellValueFactory(
                    new PropertyValueFactory<Governments, String>("quarantine_time"));

            ArrayList<Governments> governmentsArrayList = governmentQuarries.getGovernments();
            government_data.addAll(governmentsArrayList);

            TableViewGovernments.setItems(government_data);
            TableViewGovernments.getSelectionModel().setCellSelectionEnabled(true);
        }
    }

    /**
     * search Text
     * @param e input event
     * @throws SQLException throws SQLExcetion if exists
     */
    @FXML
    public void searchText(KeyEvent e) throws SQLException {
        if(database.equals("Organizations")) {
            organization_data.clear();
            ArrayList<Organization> organizationArrayList = organizationQueries.searchAndSetOrganizations(searchTextField.getText());
            organization_data.addAll(organizationArrayList);
        }
        else
        {
            government_data.clear();
            ArrayList<Governments> governmentsArrayList = governmentQuarries.searchAndSetGovernments(searchTextField.getText());
            government_data.addAll(governmentsArrayList);
        }
    }

    /**
     * goes to settings
     * @param actionEvent input event
     */
    @FXML
    public void settingsComboBoxOptionPressed(javafx.event.ActionEvent actionEvent) throws IOException {
        if(options_combo_box.getSelectionModel().getSelectedItem().equals("Settings"))
        {
            SceneSelector.switchToIndividualSettings(actionEvent);
        }
        else if(options_combo_box.getSelectionModel().getSelectedItem().equals("Logout"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("StartingPage.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * initializes labels
     * @param url takes url from file
     * @param resourceBundle takes resourceBundle from file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        individualLoggedIn = SceneSelector.getCurrentIndividual();
        first_name_label.setText(individualLoggedIn.getFirst_name());
        last_name_label.setText(individualLoggedIn.getLast_name());
        account_name_label.setText(individualLoggedIn.getUser_name());
        TableViewOrganizations.setVisible(false);
        TableViewGovernments.setVisible(false);
        options_combo_box.getItems().addAll(option_menu_choices);
        options_combo_box.getSelectionModel().selectFirst();
    }
    //javafx.event.ActionEvent actionEvent

    /**
     * selects Organizations or Governments database
     * @param actionEvent input event
     */
    public void selectDatabase(javafx.event.ActionEvent actionEvent) {
        database = ((Button)actionEvent.getSource()).getText();
        if(database.equals("Organizations"))
        {
            TableViewOrganizations.setVisible(true);
            TableViewGovernments.setVisible(false);
        }
        else if(database.equals("Government"))
        {
            TableViewGovernments.setVisible(true);
            TableViewOrganizations.setVisible(false);
        }
        try {
            displayTableCells();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * checks which boxes are checked for a filter
     * @param event event that occurs when action is performed
     * @throws SQLException error thrown when errors occurs within SQL call
     */
    public void checkBoxesClicked(javafx.event.ActionEvent event) throws SQLException {
        if(database.equals("Organizations"))
        {
            organization_data.clear();
            ArrayList<Organization> organizationArrayList = organizationQueries.getOrganizationWithSpecificReqs(vaccination_check_box.isSelected(), mask_check_box.isSelected(), testing_check_box.isSelected());
            organization_data.addAll(organizationArrayList);
        }
        else {
            government_data.clear();
            ArrayList<Governments> governmentsArrayList = governmentQuarries.getGovernmentsWithSpecificReqs(vaccination_check_box.isSelected(), mask_check_box.isSelected(), testing_check_box.isSelected());
            government_data.addAll(governmentsArrayList);
        }
    }
}