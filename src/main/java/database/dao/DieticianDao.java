package database.dao;

import database.DbConnector;

import java.sql.*;

public class DieticianDao {
    private Connection connection;

    /*public DieticianDao() {
        this.connection = DbConnector.getInstance().getConnection();
    }*/

    public void registerDietician(String dieticianLogin, String dieticianName, String dieticianSurname, String dieticianBirthDate, String dieticianMail) {
        /*try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("CALL addDietician(dieticianLogin, dieticianName, dieticianSurname, " +
                    "dieticianBirthDate, dieticianMail)")
            CallableStatement cStmnt = connection.prepareCall("{call addDietician(?, ?, ?, ?, ?)}" );
            //tutaj coś wstawiac czy jak ?

            } catch (SQLException e) {

        }*/
    }
}
