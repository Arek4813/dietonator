package controller.dieticians;

import database.dao.DieticianDao;
import database.model.Dietician;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.DieticianFx;
import utils.MailValidator;
import utils.dialog.DialogUtil;

import java.sql.Date;
import java.sql.SQLException;

public class DieticianEditingController {

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

    @FXML
    private Button updateButton;

    private DieticianDao dieticianDao;

    @FXML
    public void initialize() {
        dieticianDao = new DieticianDao();
    }

    @FXML
    public void updateDietician() {
        MailValidator mailValidator = new MailValidator();
        if (allFieldsAreNotEmpty() && mailValidator.emailValidator(dieticianMailTextField.getText())==true) {
            Dietician dietician = null;
            try {
                dietician = getDietician();
                dieticianDao.updateDietician(dietician);
                closeWindow();
            }
            catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    public void initializeFields(DieticianFx item) {
        dieticianLoginTextField.setText(item.getLogin());
        dieticianLoginTextField.setEditable(false);
        dieticianNameTextField.setText(String.valueOf(item.getName()));
        dieticianSurnameTextField.setText(String.valueOf(item.getSurname()));
        //dieticianBirthDateDatePicker.setValue(item.getBirthDate().toLocalDate());
        dieticianMailTextField.setText(String.valueOf(item.getMail()));
    }

    private Dietician getDietician() {
        Dietician dietician = new Dietician();
        dietician.setDieticianLogin(dieticianLoginTextField.getText());
        dietician.setDieticianName(dieticianNameTextField.getText());
        dietician.setDieticianSurname(dieticianSurnameTextField.getText());
        dietician.setDieticianBirthDate( Date.valueOf(dieticianBirthDateDatePicker.getValue() ) );
        dietician.setDieticianMail(dieticianMailTextField.getText());
        return dietician;
    }

    private void closeWindow() {
        Stage stage = (Stage) this.updateButton.getScene().getWindow();
        stage.close();
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(dieticianLoginTextField.getText().equals("") ||
                dieticianNameTextField.getText().equals("") ||
                dieticianSurnameTextField.getText().equals("") ||
                dieticianMailTextField.getText().equals(""));
        return condition;
    }
}
