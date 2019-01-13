package database.dao;

import database.DbConnector;
import database.model.Dietician;
import database.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoggingDao {

    private Connection connection;

    public LoggingDao() {
        this.connection = DbConnector.getInstance().getConnection();
    }

    public void userAdder(User user) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL addNormalUser(?, ?, ?, ?, ?, ?, ?, ?)" );
        pstm.setString( 1, user.getUserLogin() );
        pstm.setString( 2, user.getUserPassword() );
        pstm.setString( 3, user.getUserName() );
        pstm.setString( 4, user.getUserSurname() );
        pstm.setFloat( 5, user.getUserWeight() );
        pstm.setFloat( 6, user.getUserHeight() );
        pstm.setDate( 7, user.getUserBirthDate() );
        pstm.setString( 8, user.getUserMail() );
        pstm.executeUpdate();
    }

    public void dieticianAdder(Dietician dietician) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL addDietician(?, ?, ?, ?, ?, ?)" );
        pstm.setString( 1, dietician.getDieticianLogin() );
        pstm.setString( 2, dietician.getDieticianPassword() );
        pstm.setString( 3, dietician.getDieticianName());
        pstm.setString( 4, dietician.getDieticianSurname());
        pstm.setDate( 5, dietician.getDieticianBirthDate());
        pstm.setString( 6, dietician.getDieticianMail() );
        pstm.executeUpdate();
    }

    public void userAccountAdder(User user) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL creatingNormalUserAccount(?, ?)" );
        pstm.setString(1, user.getUserLogin() );
        pstm.setString( 2, user.getUserPassword() );
        pstm.executeUpdate();
    }

    public void dieticianAccountAdder(Dietician dietician) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("CALL creatingDieticianAccount(?, ?)" );
        pstm.setString(1, dietician.getDieticianLogin() );
        pstm.setString( 2, dietician.getDieticianPassword() );
        pstm.executeUpdate();
    }

    public String getRole(String login) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "SELECT role FROM logData WHERE loginValue=?" );
        pstm.setString( 1, login );
        ResultSet resultSet = pstm.executeQuery();
        List allRows = new ArrayList();
        while(resultSet.next()){
            allRows.add(resultSet.getString(1));
        }
        return allRows.get(0).toString();
    }

    public boolean isRegistered(String login, String password) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("SELECT loginValue, passwordValue FROM logData WHERE loginValue=? AND passwordValue=?");
        pstm.setString(1, login );
        pstm.setString( 2, password );
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }
}
