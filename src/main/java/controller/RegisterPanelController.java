package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegisterPanelController {


    ObservableList<String> accountOptions = FXCollections.observableArrayList("User", "Dietician");

    @FXML
    private TextField enterLogin;

    @FXML
    private TextField enterPassword;

    @FXML
    private Button signInButton;

    @FXML
    private ChoiceBox registerChoiceBox;

    @FXML
    private TextField registerLogin;

    @FXML
    private TextField registerPassword;

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


}
