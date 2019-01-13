package database.dao;

import database.DbConnector;
import database.model.Admin;

import java.sql.*;

public class AdminsDao {

    private Connection connection;

    public AdminsDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addAdmin(Admin admin) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL addAdmin(?, ?)");
        pstm.setString( 1, admin.getAdminLogin());
        pstm.setString( 2, admin.getAdminPassword());
        pstm.executeUpdate();

        PreparedStatement pstm1 = connection.prepareStatement( "CALL creatingAdminAccount(?, ?)" );
        pstm1.setString(1, admin.getAdminLogin() );
        pstm1.setString( 2, admin.getAdminPassword() );
        pstm1.executeUpdate();
    }

    public void deleteAdmin(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL dropAdmin(?)");
        stm.setString(1, login);
        stm.executeUpdate();

        PreparedStatement pstm1 = connection.prepareStatement( "CALL removingAccount(?)" );
        pstm1.setString(1, login);
        pstm1.executeUpdate();
    }


    public void updateAdmin(Admin admin) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL editAdmin(?, ?)");
        stm.setString(1, admin.getAdminLogin());
        stm.setString(2, admin.getAdminPassword());
        stm.executeUpdate();
    }

    public ResultSet getAdmins() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM admin");
        return resultSet;
    }

    public ResultSet getAdmins(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM admin WHERE adminLogin=?");
        stm.setString(1, login);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }
}
