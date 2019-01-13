package database;

import controller.logging.SignInPanelController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static DbConnector INSTANCE;
    private final String HOST = "jdbc:mysql://dietonatorinstance.cda86uz9e1tq.us-east-1.rds.amazonaws.com:3306/Dietonator";
    private String login;
    private String pass;
    private Connection connection;
    private SignInPanelController signInController;

    private DbConnector() {
        this.signInController=new SignInPanelController();
    }

    public static DbConnector getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DbConnector();
        return INSTANCE;
    }

    public Connection getConnection() {
        if (connection == null)
            connectWithDatabase(login, pass);
        return connection;
    }

    public void connectWithDatabase(String login, String pass) {
        try {
            connection=DriverManager.getConnection(HOST, login, pass);
            this.login=login;
            this.pass=pass;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with database connection", ButtonType.OK);
            alert.showAndWait();
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

