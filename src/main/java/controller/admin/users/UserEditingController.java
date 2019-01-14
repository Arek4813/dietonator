package controller.admin.users;

import database.dao.UserDao;
import database.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.UserFx;
import utils.MailValidator;
import utils.dialog.DialogUtil;

import java.sql.Date;
import java.sql.SQLException;

public class UserEditingController {

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

    @FXML
    private Button updateButton;

    private UserDao userDao;

    @FXML
    public void initialize() {
        userDao = new UserDao();
    }

    @FXML
    public void updateUser() {
        MailValidator mailValidator = new MailValidator();
        if (allFieldsAreNotEmpty() && mailValidator.emailValidator(userMailTextField.getText())==true) {
            User user = null;
            try {
                user = getUser();
                userDao.updateUser(user);
                closeWindow();
            }
            catch (NumberFormatException | SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    public void initializeFields(UserFx item) {
        userLoginTextField.setText(item.getLogin());
        userLoginTextField.setEditable(false);
        userNameTextField.setText(String.valueOf(item.getName()));
        userSurnameTextField.setText(String.valueOf(item.getSurname()));
        userWeightTextField.setText(String.valueOf(item.getWeight()));
        userHeightTextField.setText(String.valueOf(item.getHeight()));
        //userBirthDateDatePicker.setValue(item.getBirthDate().toLocalDate());
        userMailTextField.setText(String.valueOf(item.getMail()));
    }

    private User getUser() throws NumberFormatException {
        User user = new User();
        user.setUserLogin(userLoginTextField.getText());
        user.setUserName(userNameTextField.getText());
        user.setUserSurname(userSurnameTextField.getText());
        user.setUserHeight(Float.parseFloat(userHeightTextField.getText()));
        user.setUserWeight(Float.parseFloat(userWeightTextField.getText()));
        user.setUserBirthDate( Date.valueOf(userBirthDateDatePicker.getValue() ) );
        user.setUserMail(userMailTextField.getText());
        return user;
    }

    private void closeWindow() {
        Stage stage = (Stage) this.updateButton.getScene().getWindow();
        stage.close();
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(userLoginTextField.getText().equals("") ||
                userNameTextField.getText().equals("") ||
                userSurnameTextField.getText().equals("") ||
                userHeightTextField.getText().equals("") ||
                userWeightTextField.getText().equals("") ||
                userMailTextField.getText().equals(""));
        return condition;
    }
}
