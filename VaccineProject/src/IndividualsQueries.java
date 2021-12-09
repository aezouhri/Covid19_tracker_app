import java.sql.*;
import java.util.ArrayList;

/**
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * @author Rida Errachidi
 * Connects individuals to sql database
 */
public class IndividualsQueries {
    /**
     * maintains database connection
     */
    private final Connection connection;
    /**
     * sql command
     */
    private String sql_command;
    /**
     * sql statement
     */
    private Statement statement;
    /**
     * sql result set
     */
    private ResultSet resultSet;
    private ResultSetMetaData metaData;

    /**
     * constructor
     */
    public IndividualsQueries() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    /**
     * adds individuals and returns true if there isnt a clone
     *
     * @param newPerson input new individual
     * @return returns true or false
     * @throws SQLException calls SQLException if exists
     */
    public boolean addIndividuals(Individuals newPerson) throws SQLException {

        boolean clone = checkClone(newPerson);

        if (!clone) {
            sql_command = String.format("INSERT INTO `Individuals` (`FirstName`, `LastName`, `Date of Birth`, `SSN`, `Vaccinated`, `First Dose`, `Second Dose`, `Booster`,`LastVaccine`, `Wear mask`, `User Name`, `Password`,`answer1`,`answer2`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    newPerson.getFirst_name(), newPerson.getLast_name(), newPerson.getDoB(), newPerson.getSSN(), newPerson.getVaccinated(), newPerson.getFirst_dose(),
                    newPerson.getSecond_dose(), newPerson.getBooster(), newPerson.getLast_shot(), newPerson.getWear_mask(), newPerson.getUser_name(), newPerson.getPassword(), newPerson.getAnswer1(), newPerson.getAnswer2());
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql_command);
            return true;
        }
        return false;

    }

    /**
     * check if there is a clone in individual
     *
     * @param clone input clone
     * @return return true or false
     * @throws SQLException throws SQLException if it exists
     */
    public boolean checkClone(Individuals clone) throws SQLException {

        sql_command = String.format("SELECT * FROM `Individuals` WHERE FirstName='%s' AND LastName='%s' AND `Date of Birth`='%s' AND `SSN`='%s'",
                clone.getFirst_name(), clone.getLast_name(), clone.getDoB(), clone.getSSN());

        statement = connection.createStatement();

        resultSet = statement.executeQuery(sql_command);

        boolean result_found = false;

        metaData = resultSet.getMetaData();

        int col_number = metaData.getColumnCount();


        while (resultSet.next() && !result_found) {
            for (int i = 1; i <= col_number; i++) {
                if (resultSet.getString(i).equals(clone.getSSN())) {

                    System.out.println("This person already exists");

                    break;
                }
            }
            result_found = true;
        }

        return result_found;
    }

    /**
     * removes individual from databse
     *
     * @param removedPerson input individual
     * @throws SQLException throws SQLException if exists
     */
    public void removeIndividuals(Individuals removedPerson) throws SQLException {

        String sql_command = String.format("DELETE FROM `Individuals` WHERE `FirstName`='%s' AND `LastName`='%s' AND `Date of Birth`='%s' AND `SSN`='%s'",
                removedPerson.getFirst_name(), removedPerson.getLast_name(), removedPerson.getDoB(), removedPerson.getSSN());
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql_command);

    }


    /**
     * get individual from database
     *
     * @return return individual array
     * @throws SQLException throws SQLException if exists
     */
    public ArrayList<Individuals> getIndividuals() throws SQLException {
        String sql_command = "SELECT * FROM `Individuals`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_command);
        ResultSetMetaData metaData = resultSet.getMetaData();
        ArrayList<Individuals> individual_array_list = new ArrayList<>();
        while (resultSet.next()) {
            individual_array_list.add(new Individuals(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14)));
        }
        return individual_array_list;
    }

    /**
     * used to test stuff out
     *
     * @param args input args
     * @throws SQLException throws SQLException if exists
     */
    public static void main(String[] args) throws SQLException {

        IndividualsQueries individualsQueries = new IndividualsQueries();
        Individuals person = new Individuals("Ridae", "Errachidi", "08/08/2001", "8569", "Yes", "KJ4458", "KL4567", "", "No", "Past week", "rerrachidi", "goat2", "answer11", "answer22");
        individualsQueries.addIndividuals(person);
    }

}
