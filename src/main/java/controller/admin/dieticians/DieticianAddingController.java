package controller.dieticians;

import database.dao.DieticianDao;
import database.model.Dietician;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.MailValidator;
import utils.dialog.DialogUtil;

import java.sql.Date;
import java.sql.SQLException;

public class DieticianAddingController {

    @FXML
    private TextField dieticianLoginTextField;

    @FXML
    private TextField dieticianPasswordTextField;

    @FXML
    private TextField dieticianNameTextField;

    @FXML
    private TextField dieticianSurnameTextField;

    @FXML
    private TextField dieticianMailTextField;

    @FXML
    private DatePicker dieticianBirthDateDatePicker;

    private DieticianDao dieticianDao;

    @FXML
    public void initialize() {
        dieticianDao = new DieticianDao();
        clearFields();
    }

    @FXML
    public void addDietician() {
        MailValidator mailValidator = new MailValidator();
        if (allFieldsAreNotEmpty() && mailValidator.emailValidator(dieticianMailTextField.getText())==true) {
            try {
                Dietician dietician = new Dietician();
                dietician.setDieticianLogin(dieticianLoginTextField.getText());
                dietician.setDieticianPassword(dieticianPasswordTextField.getText());
                dietician.setDieticianName(dieticianNameTextField.getText());
                dietician.setDieticianSurname(dieticianSurnameTextField.getText());
                dietician.setDieticianBirthDate( Date.valueOf( dieticianBirthDateDatePicker.getValue() ) );
                dietician.setDieticianMail(dieticianMailTextField.getText());
                dieticianDao.addDietician(dietician);
                clearFields();
            } catch (NumberFormatException | SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(dieticianLoginTextField.getText().equals("") ||
                dieticianPasswordTextField.getText().equals("") ||
                dieticianNameTextField.getText().equals("") ||
                dieticianSurnameTextField.getText().equals("") ||
                dieticianMailTextField.getText().equals(""));
        return condition;
    }

    private void clearFields() {
        dieticianLoginTextField.clear();
        dieticianPasswordTextField.clear();
        dieticianNameTextField.clear();
        dieticianSurnameTextField.clear();
        dieticianMailTextField.clear();
    }
}
