package controller;

import database.DbConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerPanelController {

    ObservableList<String> accountOptions = FXCollections.observableArrayList("User", "Dietician");

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
        if(registerLogin.getText().equals("") || registerPassword.getText().equals("") || registerName.getText().equals("") || registerSurname.getText().equals("") || registerBirthDatePicker.getValue()==null || registerMail.getText().equals("")) {
            Alert alert = new Alert( Alert.AlertType.INFORMATION, "One of textfield is empty!", ButtonType.OK );
            alert.showAndWait();
        }
        else if(registerChoiceBox.getSelectionModel().getSelectedItem()=="User") {
            if(registerHeight.getText().equals("") || registerWeight.getText().equals("")) {
                Alert alert = new Alert( Alert.AlertType.INFORMATION, "One of textfield is empty!", ButtonType.OK );
                alert.showAndWait();
            }
        }
        else {
            System.out.println("Wszystko ok");
            // przesłanie danych do bazyyyy - 2x procedurka
        }
    }

    private void weightAndHeightBlocker() {
        registerHeight.setDisable(true);
        registerWeight.setDisable(true);
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
