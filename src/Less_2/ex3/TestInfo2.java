package Less_2.ex3;

import java.sql.*;

public class TestInfo2 {
    public static void init() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }

    public static void statementsOld() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();

            statement = connection.createStatement();
            statement.execute("create table user(" +
                    "id integer primary key auto_increment, " +
                    "name varchar(100));");

            statement.execute("insert into user(name) values('borya'),('petya')");
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null)
                try { statement.close(); }
                catch (SQLException ignore) { }
            if (connection != null)
                try { connection.close(); }
                catch (SQLException ignore) { }
        }
    }

    public static void statements(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table user(" +
                    "id integer primary key auto_increment, " +
                    "name varchar(100));");

            statement.execute("insert into user(name) values('borya'),('petya')");
        }
    }

    public static void resultSet(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
            }
            System.out.println("----------------");
        }
    }

    public static void prepare(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection
                .prepareStatement("insert into user(id,name) values(?,?)")) {

            statement.setInt(1, 3);
            statement.setString(2, "fedya");
            statement.executeUpdate();

            statement.setInt(1, 4);
            statement.setString(2, "misha");
            statement.addBatch();
            statement.setInt(1, 5);
            statement.setString(2, "grisha");
            statement.addBatch();
            statement.executeBatch();
        }
    }

    public static void transactions(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            try {
                statement.execute("insert into user(name) values('kesha')");
                connection.commit();
            } catch (SQLException e)  {
                connection.rollback();
            }

            connection.setAutoCommit(true);
        }
    }

    public static void main(String[] args) throws Exception {
        init();

        try (Connection connection = getConnection()) {
            statements(connection);
            resultSet(connection);

            prepare(connection);
            resultSet(connection);

            transactions(connection);
            resultSet(connection);
        }
    }
}
