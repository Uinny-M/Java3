package ru.gb.jt.chat.server.core;

import java.sql.*;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized static void changeNick(String newNick, String login) { //внесение нового ника в базу
        System.out.println("sql");
        String query = String.format("UPDATE users SET nickname='%s' WHERE login='%s'", newNick, login);
        try {
            statement.addBatch(query);
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password) {
        String query = String.format("select nickname from users where login='%s' and password='%s'", login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
