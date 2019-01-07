package database;

import controller.signInPanelController;

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
    private signInPanelController signInController;

    private DbConnector() {
        this.signInController=new signInPanelController();
    }

    public static DbConnector getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DbConnector();
        return INSTANCE;
    }

    public Connection getConnection(String login, String pass) {
        if (connection == null)
            connectWithDatabase(login, pass);
        return connection;
    }

    /*public void connectWithDatabase() {
        try {
            connection = DriverManager.getConnection(HOST, signInController.getEnterLogin().getText(), signInController.getEnterPassword().getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void connectWithDatabase(String login, String pass) {
        try {
            connection=DriverManager.getConnection(HOST, login, pass);
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

