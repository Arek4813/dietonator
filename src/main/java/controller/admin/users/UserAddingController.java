package controller.admin.users;

import database.dao.UserDao;
import database.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.MailValidator;
import utils.dialog.DialogUtil;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserAddingController {

    @FXML
    private TextField userLoginTextField;

    @FXML
    private TextField userPasswordTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField userSurnameTextField;

    @FXML
    private TextField userWeightTextField;

    @FXML
    private TextField userHeightTextField;

    @FXML
    private TextField userMailTextField;

    @FXML
    private DatePicker userBirthDateDatePicker;

    private UserDao userDao;

    @FXML
    public void initialize() {
        userDao = new UserDao();
        clearFields();
    }

    @FXML
    public void addUser() {
        MailValidator mailValidator = new MailValidator();
        if (allFieldsAreNotEmpty() && mailValidator.emailValidator(userMailTextField.getText())==true) {
            try {
                User user = new User();
                user.setUserLogin(userLoginTextField.getText());
                user.setUserPassword(userPasswordTextField.getText());
                user.setUserName(userNameTextField.getText());
                user.setUserSurname(userSurnameTextField.getText());
                user.setUserHeight( Float.parseFloat( userHeightTextField.getText() ) );
                user.setUserWeight( Float.parseFloat( userWeightTextField.getText() ) );
                user.setUserBirthDate(userBirthDateDatePicker.getValue().toString());
                user.setUserMail(userMailTextField.getText());
                userDao.addUser(user);
                clearFields();
            } catch (NumberFormatException | SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(userLoginTextField.getText().equals("") ||
                userPasswordTextField.getText().equals("") ||
                userNameTextField.getText().equals("") ||
                userSurnameTextField.getText().equals("") ||
                userHeightTextField.getText().equals("") ||
                userWeightTextField.getText().equals("") ||
                userMailTextField.getText().equals(""));
        return condition;
    }

    private void clearFields() {
        userLoginTextField.clear();
        userPasswordTextField.clear();
        userNameTextField.clear();
        userSurnameTextField.clear();
        userHeightTextField.clear();
        userWeightTextField.clear();
        userBirthDateDatePicker.setValue( LocalDate.now());
        userMailTextField.clear();
    }
}
