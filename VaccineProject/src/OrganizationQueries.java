import java.sql.*;
import java.util.ArrayList;

/**
 * @author Rida Errachidi
 * @author Ben DeSollar
 * @author Adnane Ezouhri
 * OrganizationQueries connects Organization to sql database
 */
public class OrganizationQueries {

    /**
     * manages connection
     */
    private final Connection connection;

    /**
     * constructor
     */
    public OrganizationQueries() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    /**
     * add a new organization to databse
     *
     * @param newOrganization input new organization object
     * @return returns true or false
     * @throws SQLException throws SQLException if exists
     */
    public boolean addOrganization(Organization newOrganization) throws SQLException {

        boolean clone = checkClone(newOrganization);
        if (!clone) {
            String sql_command = String.format("INSERT INTO `Organizations` (`CompanyName`, `WebSite`, `Location`, `Phone`, `Vaccine Required`, `Mask Required`, `Test Required`, `username`, `password`, `Answer 1`, `Answer 2`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    newOrganization.getOrganization_name(), newOrganization.getWebsite_link(), newOrganization.getLocation(), newOrganization.getPhone_number(), newOrganization.getVaccine_required(), newOrganization.getMask_required(),
                    newOrganization.getTest_required(), newOrganization.getUsername(), newOrganization.getPassword(), newOrganization.getAnswer1(), newOrganization.getAnswer2());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            return true;
        }
        return false;
    }

    /**
     * removes organization from database
     *
     * @param removedOrganization input organization object
     * @throws SQLException throws SQLException if exists
     */
    public void removeOrganizations(Organization removedOrganization) throws SQLException {

        String sql_command = String.format("DELETE FROM `Organizations` WHERE `CompanyName`='%s'",
                removedOrganization.getOrganization_name());
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_command);

    }

    /**
     * checks if there is an organization clone
     *
     * @param clone input organization object
     * @return returns true or false
     * @throws SQLException throws SQLException if exists
     */
    public boolean checkClone(Organization clone) throws SQLException {

        String sql_command = String.format("SELECT * FROM `Organizations` WHERE CompanyName='%s'",
                clone.getOrganization_name());

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        ResultSetMetaData metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(clone.getOrganization_name())) {

                    System.out.println("This government already exists");

                    break;
                }
            }
            result_found = true;
        }

        return result_found;
    }

    /**
     * adds new organization
     *
     * @param args input string
     * @throws SQLException throws SQLException if exists
     */
    public static void main(String[] args) throws SQLException {

        Organization place = new Organization("hanout", "https://walgreens.com", "52", "Coralville", "No", "Yes", "No", "walgreens", "12345", "ouioui", "nonon");
        OrganizationQueries queries = new OrganizationQueries();
        queries.addOrganization(place);
    }

    /**
     * get organization from database
     *
     * @return return organization from database
     * @throws SQLException throws SQLException if exists
     */
    public ArrayList<Organization> getOrganization() throws SQLException {
        String sql_command = "SELECT * FROM `Organizations`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Organization> organization_array_list = new ArrayList<>();
        while (resultSet.next()) {
            organization_array_list.add(new Organization(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        return organization_array_list;
    }

    /**
     * Search for organization and set it
     *
     * @param charsSearched searches organization name
     * @return return organization
     * @throws SQLException throws SQLException if exists
     */
    public ArrayList<Organization> searchAndSetOrganizations(String charsSearched) throws SQLException {
        String sql_command = "SELECT * FROM `Organizations` WHERE CompanyName LIKE '%" + charsSearched + "%';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Organization> organization_array_list = new ArrayList<>();
        while (resultSet.next()) {
            organization_array_list.add(new Organization(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        return organization_array_list;
    }

    /**
     * gets all the organizations that fit the parameters of the requirements checked for
     *
     * @param vaccination vaccine requirement or not
     * @param masks       mask requirement or not
     * @param test        test requirement or not
     * @return an arraylist of all of the organizations that fit within that specific search
     * @throws SQLException throws SQLException if exists
     */
    public ArrayList<Organization> getOrganizationWithSpecificReqs(boolean vaccination, boolean masks, boolean test) throws SQLException {
        String sql_command = "SELECT * FROM `Organizations`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Organization> organization_array_list = new ArrayList<>();
        ArrayList<Organization> organization_array_list_new = new ArrayList<>();
        while (resultSet.next()) {
            organization_array_list.add(new Organization(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
        }
        for (int i = 0; i < organization_array_list.size(); i++) {
            if (vaccination && masks && test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Required") && organization_array_list.get(i).getMask_required().equals("Required") && organization_array_list.get(i).getTest_required().equals("Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (vaccination && masks && !test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Required") && organization_array_list.get(i).getMask_required().equals("Required") && organization_array_list.get(i).getTest_required().equals("Not Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (vaccination && !masks && !test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Required") && organization_array_list.get(i).getMask_required().equals("Not Required") && organization_array_list.get(i).getTest_required().equals("Not Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (!vaccination && !masks && !test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Not Required") && organization_array_list.get(i).getMask_required().equals("Not Required") && organization_array_list.get(i).getTest_required().equals("Not Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (!vaccination && masks && test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Not Required") && organization_array_list.get(i).getMask_required().equals("Required") && organization_array_list.get(i).getTest_required().equals("Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (!vaccination && !masks && test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Not Required") && organization_array_list.get(i).getMask_required().equals("Not Required") && organization_array_list.get(i).getTest_required().equals("Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (!vaccination && masks && !test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Not Required") && organization_array_list.get(i).getMask_required().equals("Required") && organization_array_list.get(i).getTest_required().equals("Not Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            } else if (vaccination && !masks && test) {
                if (organization_array_list.get(i).getVaccine_required().equals("Required") && organization_array_list.get(i).getMask_required().equals("Not Required") && organization_array_list.get(i).getTest_required().equals("Required")) {
                    organization_array_list_new.add(organization_array_list.get(i));
                }
            }
        }
        return organization_array_list_new;
    }

}
