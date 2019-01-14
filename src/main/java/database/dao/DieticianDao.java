package database.dao;

import database.DbConnector;
import database.model.Dietician;

import java.sql.*;

public class DieticianDao {


    private Connection connection;

    public DieticianDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addDietician(Dietician dietician) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement( "CALL addDietician(?, ?, ?, ?, ?, ?)" );
        pstm.setString( 1, dietician.getDieticianLogin() );
        pstm.setString( 2, dietician.getDieticianPassword() );
        pstm.setString( 3, dietician.getDieticianName() );
        pstm.setString( 4, dietician.getDieticianSurname() );
        pstm.setDate( 5, Date.valueOf(dietician.getDieticianBirthDate().toLocalDate()));
        pstm.setString( 6, dietician.getDieticianMail());
        pstm.executeUpdate();

        PreparedStatement pstm1 = connection.prepareStatement( "CALL creatingDieticianAccount(?, ?)" );
        pstm1.setString(1, dietician.getDieticianLogin() );
        pstm1.setString( 2, dietician.getDieticianPassword());
        pstm1.executeUpdate();
    }

    public void deleteDietician(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL dropDietician(?)");
        stm.setString(1, login);
        stm.executeUpdate();

        PreparedStatement stm1 = connection.prepareStatement( "CALL removingAccount(?)" );
        stm1.setString( 1, login );
        stm1.executeUpdate();
    }


    public void updateDietician(Dietician dietician) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL editDietician(?, ?, ?, ?, ?, ?)");
        stm.setString( 1, dietician.getDieticianLogin() );
        stm.setString( 2, dietician.getDieticianPassword() );
        stm.setString( 3, dietician.getDieticianName() );
        stm.setString( 4, dietician.getDieticianSurname() );
        stm.setDate( 5, Date.valueOf(dietician.getDieticianBirthDate().toLocalDate()));
        stm.setString( 6, dietician.getDieticianMail());
        stm.executeUpdate();
    }

    public ResultSet getDieticians() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM dieticians");
        return resultSet;
    }

    public ResultSet getDieticiansForUser(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM dieticians WHERE dieticianLogin=?");
        stm.setString(1, login);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }



}
