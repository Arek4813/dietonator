package controller;

import database.DbConnector;
import database.dao.LogDataDao;
import database.model.Dietician;
import database.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.converter.DateInRegistrationConverter;

import java.sql.SQLException;
import java.util.Date;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPanelController {

    ObservableList<String> accountOptions = FXCollections.observableArrayList("User", "Dietician");

    LogDataDao logDataDao;

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
        if(emailValidator()==false) {
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
            this.logDataDao=new LogDataDao();
            DateInRegistrationConverter dateInRegistrationConverter = new DateInRegistrationConverter();
            if (registerChoiceBox.getSelectionModel().getSelectedItem()=="User") {
                User user = new User();
                user.setUserLogin(registerLogin.getText());
                user.setUserPassword(registerPassword.getText());
                user.setUserName(registerName.getText());
                user.setUserSurname(registerSurname.getText());
                user.setUserWeight(Float.parseFloat(registerWeight.getText()));
                user.setUserHeight(Float.parseFloat(registerHeight.getText()));
                Date date = Date.from(registerBirthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                user.setUserBirthDate(dateInRegistrationConverter.dateConverter(date));
                user.setUserMail(registerMail.getText());
                try {
                    logDataDao.userAdder(user);
                    logDataDao.userAccountAdder(user);
                    succesInRegistration();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with user registration", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            else if (registerChoiceBox.getSelectionModel().getSelectedItem()=="Dietician") {
                Dietician dietician = new Dietician();
                dietician.setDieticianLogin(registerLogin.getText());
                dietician.setDieticianPassword(registerPassword.getText());
                dietician.setDieticianName(registerName.getText());
                dietician.setDieticianSurname(registerSurname.getText());
                Date date = Date.from(registerBirthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                dietician.setDieticianBirthDate(dateInRegistrationConverter.dateConverter(date));
                dietician.setDieticianMail(registerMail.getText());
                try {
                    logDataDao.dieticianAdder(dietician);
                    logDataDao.dieticianAccountAdder(dietician);
                    succesInRegistration();
                } catch (SQLException e) {
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

    private boolean emailValidator() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(getRegisterMail().getText());
        if (matcher.find() && matcher.group().equals(getRegisterMail().getText())) {
            return true;
        }
        else
            return false;
    }
}
