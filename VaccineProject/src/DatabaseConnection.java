import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Adnane Ezouhri
 * @author Ben DeSollar
 * @author Rida Errachidi
 * Database Connection connects program to sql database
 */
public class DatabaseConnection {
    /**
     * database url
     */
    private static final String URL = "jdbc:mysql://s-l112.engr.uiowa.edu:3306/swd_db029";
    /**
     * username to database
     */
    private static final String USERNAME = "swd_group029";
    /**
     * password to database
     */
    private static final String PASSWORD = "wegoated9";
    /**
     * manages connection
     */
    private Connection connection;

    /**
     * Constructor tries to connect
     */
    public DatabaseConnection(){
        try {
            connection =
                    DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * gets connection
     * @return returns database connection
     */
    public Connection getConnection() {
        return connection;
    }
}
