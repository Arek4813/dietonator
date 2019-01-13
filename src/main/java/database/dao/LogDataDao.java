package database.dao;

import database.DbConnector;
import database.model.LogData;

import java.sql.*;

public class LogDataDao {

    private Connection connection;

    public LogDataDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void deleteLogData(String login, String password, String role) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL removeLogData(?, ?, ?)");
        stm.setString(1, login);
        stm.setString( 2, password );
        stm.setString( 3, role );
        stm.executeUpdate();
    }


    public void updateLogData(LogData logData) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL editLogData(?, ?)");
        stm.setString(1, logData.getLoginValue());
        stm.setString( 2, logData.getPasswordValue());
        stm.executeUpdate();

        PreparedStatement stm1 = connection.prepareStatement( "CALL editingAccount(?, ?)" );
        stm1.setString( 1, logData.getLoginValue() );
        stm1.setString( 2, logData.getPasswordValue() );
        stm1.executeUpdate();
    }

    public ResultSet getLogData() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM logData");
        return resultSet;
    }
}
