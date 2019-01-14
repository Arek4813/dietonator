package database.dao;

import com.mysql.cj.protocol.Resultset;
import database.DbConnector;
import database.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.UserFx;

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
        pstm.setString( 7, user.getUserBirthDate());
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
        stm.setString(7, user.getUserBirthDate());
        stm.setString(8, user.getUserMail());
        stm.executeUpdate();
    }

    public ResultSet getUsers() throws SQLException {
        Statement stm = connection.createStatement();
        ResultSet resultSet = stm.executeQuery("SELECT * FROM normal_users");
        return resultSet;
    }

    public ResultSet getUserByLogin(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM normal_users WHERE userLogin=?");
        stm.setString(1, login);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }

    public ObservableList<UserFx> getDieticiansCustomers(String dieticianLogin) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT nu.userLogin, userName, userSurname, userWeight, userHeight, userBirthDate, userMail FROM" +
                " normal_users nu INNER JOIN users_and_dieticians ud ON nu.userLogin = ud.UserLogin WHERE ud.DieticianLogin=?");
        stm.setString(1, dieticianLogin);
        ResultSet resultSet = stm.executeQuery();
        ObservableList<UserFx> users = FXCollections.observableArrayList();
        while (resultSet.next()) {
            UserFx userFx = new UserFx();
            userFx.setLogin(resultSet.getString("userLogin"));
            userFx.setName(resultSet.getString("userName"));
            userFx.setSurname(resultSet.getString("userSurname"));
            userFx.setHeight(resultSet.getFloat("userHeight"));
            userFx.setWeight(resultSet.getFloat("userWeight"));
            userFx.setBirthDate(resultSet.getString("userBirthDate"));
            userFx.setMail(resultSet.getString("userMail"));
            users.add(userFx);
        }
        return users;
    }

}
