package database.dao;

import database.DbConnector;
import database.model.UserDietician;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.UserDieticianFx;
import utils.converter.UserDieticianConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDieticianDao {

    private Connection connection;

    public UserDieticianDao() {
        connection= DbConnector.getInstance().getConnection();
    }

    public void addUDConnection(String uL, String dL) throws SQLException {
        PreparedStatement stm = connection.prepareStatement( "CALL addingUserAndDieticianConnection(?, ?)" );
        stm.setString( 1, uL);
        stm.setString( 2, dL);
        stm.executeUpdate();
    }

    public void removeUDConnection(String uL, String dL) throws SQLException {
        PreparedStatement stm = connection.prepareStatement( "CALL removingUserAndDieticianConnection(?, ?)" );
        stm.setString( 1, uL);
        stm.setString( 2, dL);
        stm.executeUpdate();
    }

    public ObservableList<UserDieticianFx> getUsersAndDieticiansForUser(String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CALL writeDieticiansForUser(?)");
        statement.setString( 1, login );
        ResultSet resultSet = statement.executeQuery();
        ObservableList<UserDieticianFx> products = FXCollections.observableArrayList();
        while (resultSet.next()) {
            UserDietician userDietician = new UserDietician();
            userDietician.setuLogin(resultSet.getString( "UserLogin" ));
            userDietician.setdLogin(resultSet.getString("DieticianLogin"));
            UserDieticianFx userDieticianFx = UserDieticianConverter.getInstance().convertToUserDieticianFx(userDietician);
            products.add(userDieticianFx);
        }
        return products;
    }

    public ObservableList<UserDieticianFx> getUsersAndDieticiansForDietician(String login) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CALL writeUsersForDietician(?)");
        statement.setString( 1, login );
        ResultSet resultSet = statement.executeQuery();
        ObservableList<UserDieticianFx> products = FXCollections.observableArrayList();
        while (resultSet.next()) {
            UserDietician userDietician = new UserDietician();
            userDietician.setdLogin(resultSet.getString( "DieticianLogin" ));
            userDietician.setuLogin(resultSet.getString("UserLogin"));
            UserDieticianFx userDieticianFx = UserDieticianConverter.getInstance().convertToUserDieticianFx(userDietician);
            products.add(userDieticianFx);
        }
        return products;
    }

}
