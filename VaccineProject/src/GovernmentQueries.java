import java.sql.*;
import java.util.ArrayList;

/**
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * @author Rida Errachidi
 * GovernmentQueries connects government register and login to database
 */
public class GovernmentQueries {
    /**
     * maintains database connection
     */
    private final Connection connection; // manages connection

    /**
     * constructor
     */
    public GovernmentQueries() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    /**
     * adds government to database
     * @param newGovernment input government string
     * @return returns true or false
     * @throws SQLException throws SQLException if exists
     */
    public boolean addGovernment(Governments newGovernment) throws SQLException {

        boolean clone = checkClone(newGovernment);

        if (!clone) {
            String sql_command = String.format("INSERT INTO `Governments`(`Government`, `Location`, `Mask Mandate`, `Vaccination`,`LastVaccinAllowed`, `Test upon entry`, `Quarantine`, `Quarantine period`, `Username`, `Password`, `Answer1`, `Answer2`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    newGovernment.getGovernment_name(), newGovernment.getLocation(), newGovernment.getMask_mandate(), newGovernment.getVaccination(),newGovernment.getLast_vaccine() ,newGovernment.getTest_entry(), newGovernment.getQuarantine(),
                    newGovernment.getQuarantine_time(), newGovernment.getUsername(), newGovernment.getPassword(), newGovernment.getAnswer1(), newGovernment.getAnswer2());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * remove government from database
     * @param removedGovernment inputs Government to be removed
     * @throws SQLException throws SQLException if exists
     */
    public void removeGovernment(Governments removedGovernment) throws SQLException {

        String sql_command = String.format("DELETE FROM `Governments` WHERE `Government`='%s'",
                removedGovernment.getGovernment_name());
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_command);

    }

    /**
     * checks if there is a government clone
     * @param clone inputs government to be checked
     * @return returns true or false
     * @throws SQLException throws SQlException if exists
     */
    public boolean checkClone(Governments clone) throws SQLException {

        String sql_command = String.format("SELECT * FROM `Governments` WHERE Government='%s'",
                clone.getGovernment_name());

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        ResultSetMetaData metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(clone.getGovernment_name())){

                    System.out.println("This government already exists");

                    break;
                }
            }
            result_found = true;
        }

        return result_found;
    }

    /**
     * gets Governments
     * @return returns government array
     * @throws SQLException throws SQLException if exists
     */
    public ArrayList<Governments> getGovernments() throws SQLException {
        String sql_command = "SELECT * FROM `Governments`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Governments> governments_array_list = new ArrayList<>();
        while(resultSet.next()) {
            governments_array_list.add(new Governments(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),resultSet.getString(10), resultSet.getString(11), resultSet.getString(12)));
        }
        return governments_array_list;
    }

    /**
     * searches for specific Governments by name
     * @param charsSearched the chars to use in the search
     * @return an ArrayList of all the Governments that resulted in the search
     * @throws SQLException exception thrown if SQL error occurs
     */
    public ArrayList<Governments> searchAndSetGovernments(String charsSearched) throws SQLException {
        String sql_command = "SELECT * FROM `Governments` WHERE Government LIKE '%" + charsSearched + "%';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Governments> governmentsArrayList = new ArrayList<>();while(resultSet.next()) {
            governmentsArrayList.add(new Governments(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8)));
        }
        return  governmentsArrayList;
    }

    /**
     * gets governments that have specific Requirements
     * @param vaccination vaccine requirement or not
     * @param masks mask requirement or not
     * @param test test requirement or not
     * @return an ArrayList of all the Governments that resulted in the search
     * @throws SQLException exception thrown if SQL error occurs
     */
    public ArrayList<Governments> getGovernmentsWithSpecificReqs(boolean vaccination, boolean masks, boolean test) throws SQLException {
        String sql_command = "SELECT * FROM `Governments`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Governments> governmentsArrayList = new ArrayList<>();
        ArrayList<Governments> government_array_list_new = new ArrayList<>();
        while(resultSet.next()) {
            governmentsArrayList.add(new Governments(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),resultSet.getString(10),resultSet.getString(11), resultSet.getString(12)));
        }
        for(int i = 0; i < governmentsArrayList.size(); i++)
        {
            if(vaccination && masks && test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Required") && governmentsArrayList.get(i).getMask_mandate().equals("Required") && governmentsArrayList.get(i).getTest_entry().equals("Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(vaccination && masks && !test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Required") && governmentsArrayList.get(i).getMask_mandate().equals("Required") && governmentsArrayList.get(i).getTest_entry().equals("Not Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(vaccination && !masks && !test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Required") && governmentsArrayList.get(i).getMask_mandate().equals("Not Required") && governmentsArrayList.get(i).getTest_entry().equals("Not Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(!vaccination && !masks && !test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Not Required") && governmentsArrayList.get(i).getMask_mandate().equals("Not Required") && governmentsArrayList.get(i).getTest_entry().equals("Not Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(!vaccination && masks && test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Not Required") && governmentsArrayList.get(i).getMask_mandate().equals("Required") && governmentsArrayList.get(i).getTest_entry().equals("Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(!vaccination && !masks && test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Not Required") && governmentsArrayList.get(i).getMask_mandate().equals("Not Required") && governmentsArrayList.get(i).getTest_entry().equals("Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(!vaccination && masks && !test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Not Required") && governmentsArrayList.get(i).getMask_mandate().equals("Required") && governmentsArrayList.get(i).getTest_entry().equals("Not Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
            else if(vaccination && !masks && test)
            {
                if(governmentsArrayList.get(i).getVaccination().equals("Required") && governmentsArrayList.get(i).getMask_mandate().equals("Not Required") && governmentsArrayList.get(i).getTest_entry().equals("Required"))
                {
                    government_array_list_new.add(governmentsArrayList.get(i));
                }
            }
        }
        return government_array_list_new;
    }


    /**
     * tests queries
     * @param args input args
     * @throws SQLException SQLException thrown if exists
     */
    public static void main(String[] args) throws SQLException {

        Governments gov= new Governments("United States","North America","No","Optional","Past week","Required","No","N/A","america","trust","capitalism","money");
        GovernmentQueries queries = new GovernmentQueries();
        queries.addGovernment(gov);
    }


}
