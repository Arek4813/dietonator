package database.dao;

import database.DbConnector;
import database.model.User;

import java.sql.*;

public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL addNormalUser(?, ?, ?, ?, ?, ?, ?, ?)" );
        pstm.setString( 1, user.getUserLogin());
        pstm.setString( 2, user.getUserPassword() );
        pstm.setString( 3, user.getUserName() );
        pstm.setString( 4, user.getUserSurname() );
        pstm.setFloat( 5, user.getUserWeight() );
        pstm.setFloat( 6, user.getUserHeight() );
        pstm.setDate( 7, Date.valueOf(user.getUserBirthDate().toLocalDate()));
        pstm.setString( 8, user.getUserMail() );
        pstm.executeUpdate();

        PreparedStatement pstm1 = connection.prepareStatement( "CALL creatingNormalUserAccount(?, ?)" );
        pstm1.setString(1, user.getUserLogin() );
        pstm1.setString( 2, user.getUserPassword() );
        pstm1.executeUpdate();
    }

    public void deleteUser(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL dropNormalUser(?)");
        stm.setString(1, login);
        stm.executeUpdate();

        PreparedStatement stm1 = connection.prepareStatement( "CALL removingAccount(?)" );
        stm1.setString( 1, login );
        stm1.executeUpdate();
    }


    public void updateUser(User user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL editNormalUser(?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setString(1, user.getUserLogin());
        stm.setString(2, user.getUserPassword());
        stm.setString(3, user.getUserName());
        stm.setString(4, user.getUserSurname());
        stm.setFloat(5, user.getUserWeight());
        stm.setFloat(6, user.getUserHeight());
        stm.setDate(7, Date.valueOf(user.getUserBirthDate().toLocalDate()));
        stm.setString(8, user.getUserMail());
        stm.executeUpdate();
    }

    public ResultSet getUsers() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM normal_users");
        //ResultSet resultSet = stm.executeQuery("CALL getUsers");
        return resultSet;
    }

    public ResultSet getUsers(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM normal_users WHERE userLogin=?");
        stm.setString(1, login);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }

}
