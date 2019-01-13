package controller.logging;

import database.DbConnector;
import database.dao.LoggingDao;
import database.model.Dietician;
import database.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.MailValidator;
import utils.converter.DateConverter;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

public class RegisterPanelController {

    ObservableList<String> accountOptions = FXCollections.observableArrayList("User", "Dietician");

    LoggingDao loggingDao;

    @FXML
    private ChoiceBox registerChoiceBox;

    @FXML
    private TextField registerLogin;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerName;

    @FXML
    private TextField registerSurname;

    @FXML
    private TextField registerWeight;

    @FXML
    private TextField registerHeight;

    @FXML
    private DatePicker registerBirthDatePicker;

    @FXML
    private TextField registerMail;

    public TextField getRegisterMail() {
        return registerMail;
    }

    public void setRegisterMail(TextField registerMail) {
        this.registerMail = registerMail;
    }

    @FXML
    private Button registerButton;

    @FXML
    private void initialize() {
        registerChoiceBox.setItems(accountOptions);
        registerChoiceBox.setOnAction( new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                choiceProfile();
            }
        } );
        registerChoiceBox.setValue("User");
    }

    @FXML
    private void registerButtonOnAction() {
        MailValidator mailValidator = new MailValidator();
        if(mailValidator.emailValidator(getRegisterMail().getText())==false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Bad format of email", ButtonType.OK);
            alert.showAndWait();
        }
        else if(registerLogin.getText().equals("") || registerPassword.getText().equals("") || registerName.getText().equals("") || registerSurname.getText().equals("") || registerBirthDatePicker.getValue()==null || registerMail.getText().equals("")) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION, "Textfield can't be empty!", ButtonType.OK );
            alert.showAndWait();
        }
        else if(registerChoiceBox.getSelectionModel().getSelectedItem()=="User" && (registerHeight.getText().equals("") || registerWeight.getText().equals(""))) {
                Alert alert = new Alert( Alert.AlertType.ERROR, "Textfield can't be empty!", ButtonType.OK );
                alert.showAndWait();
        }
        else {
            DbConnector.getInstance().connectWithDatabase("root", "rootpassword");
            this.loggingDao =new LoggingDao();
            DateConverter dateInRegistrationConverter = new DateConverter();
            if (registerChoiceBox.getSelectionModel().getSelectedItem()=="User") {
                try {
                    User user = new User();
                    user.setUserLogin(registerLogin.getText());
                    user.setUserPassword(registerPassword.getText());
                    user.setUserName(registerName.getText());
                    user.setUserSurname(registerSurname.getText());
                    user.setUserWeight(Float.parseFloat(registerWeight.getText()));
                    user.setUserHeight(Float.parseFloat(registerHeight.getText()));
                    Date date = Date.from(registerBirthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    user.setUserBirthDate(dateInRegistrationConverter.dateinRegistrationConverter(date));
                    user.setUserMail(registerMail.getText());
                    loggingDao.userAdder(user);
                    loggingDao.userAccountAdder(user);
                    succesInRegistration();
                } catch (NumberFormatException | SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with user registration", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            else if (registerChoiceBox.getSelectionModel().getSelectedItem()=="Dietician") {
                try {
                    Dietician dietician = new Dietician();
                    dietician.setDieticianLogin(registerLogin.getText());
                    dietician.setDieticianPassword(registerPassword.getText());
                    dietician.setDieticianName(registerName.getText());
                    dietician.setDieticianSurname(registerSurname.getText());
                    Date date = Date.from(registerBirthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    dietician.setDieticianBirthDate(dateInRegistrationConverter.dateinRegistrationConverter(date));
                    dietician.setDieticianMail(registerMail.getText());
                    loggingDao.dieticianAdder(dietician);
                    loggingDao.dieticianAccountAdder(dietician);
                    succesInRegistration();
                } catch (NumberFormatException | SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with dietician registration",
                            ButtonType.OK);
                    alert.showAndWait();
                }
            }
            DbConnector.getInstance().closeConnection();

        }
    }

    private void succesInRegistration() {
        Alert alert = new Alert( Alert.AlertType.INFORMATION, "Correct registration :)" );
        alert.showAndWait();
        registerLogin.clear();
        registerPassword.clear();
        registerName.clear();
        registerSurname.clear();
        registerWeight.clear();
        registerHeight.clear();
        registerBirthDatePicker.setValue(null);
        registerMail.clear();
    }

    private void weightAndHeightBlocker() {
        registerHeight.setDisable(true);
        registerWeight.setDisable(true);
        registerHeight.clear();
        registerWeight.clear();
    }

    private void weightAndHeightEnabler() {
        registerHeight.setDisable(false);
        registerWeight.setDisable(false);
    }

    private void choiceProfile() {
        if(this.registerChoiceBox.getSelectionModel().getSelectedItem()=="Dietician") {
            weightAndHeightBlocker();
        }
        else if(this.registerChoiceBox.getSelectionModel().getSelectedItem()=="User") {
            weightAndHeightEnabler();
        }
    }
}
