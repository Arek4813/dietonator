package controller.user.profile;

import database.DbConnector;
import database.dao.UserDao;
import database.dao.UserDieticianDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelfx.UserFx;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyProfileViewController {

    @FXML
    private TextField userLoginTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField userSurnameTextField;

    @FXML
    private TextField userHeightTextField;

    @FXML
    private TextField userWeightTextField;

    @FXML
    private TextField userMailTextField;

    @FXML
    private DatePicker userBirthDatePicker;

    private UserDao userDao;

    public void initialize() {
        userDao = new UserDao();
        try {
            ResultSet resultSet = userDao.getUserByLogin( DbConnector.getInstance().getLogin());
            while (resultSet.next()) {
                userLoginTextField.setText(resultSet.getString("userLogin"));
                userNameTextField.setText(resultSet.getString("userName"));
                userSurnameTextField.setText(resultSet.getString("userSurname"));
                userHeightTextField.setText(resultSet.getString( "userHeight" ));
                userWeightTextField.setText(resultSet.getString( "userWeight" ));
                //userBirthDatePicker.set(resultSet.getString("dieticianBirthDate"));
                userMailTextField.setText(resultSet.getString("userMail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
