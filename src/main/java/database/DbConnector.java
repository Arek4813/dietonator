package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static DbConnector INSTANCE;
    private final String HOST = "jdbc:mysql://dietonatorinstance.cda86uz9e1tq.us-east-1.rds.amazonaws.com:3306/Dietonator";
    private final String USER = "root";
    private final String PASSWORD = "rootpassword";
    private Connection connection;

    private DbConnector() {}

    public static DbConnector getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DbConnector();
        return INSTANCE;
    }

    public Connection getConnection() {
        if (connection == null)
            connectWithDatabase();
        return connection;
    }

    public void connectWithDatabase() {
        try {
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

