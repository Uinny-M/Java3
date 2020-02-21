package Less_2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConEx {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostname = "localhost";
        String dbName = "YourBD";
        String user = "root";
        String password = "root";

        return getMySQLConnection(hostname, dbName, user, password);
    }

    private static Connection getMySQLConnection(
            String hostname,
            String dbName,
            String user,
            String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbName;
        Connection connection = DriverManager.getConnection(connectionURL, user, password);
        return connection;
    }
}
