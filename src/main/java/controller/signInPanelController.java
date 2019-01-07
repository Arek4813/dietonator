package controller;

import database.DbConnector;
import database.dao.LogDataDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class signInPanelController {


    public TextField getEnterLogin() {
        return enterLogin;
    }

    public void setEnterLogin(TextField enterLogin) {
        this.enterLogin = enterLogin;
    }

    public PasswordField getEnterPassword() {
        return enterPassword;
    }

    public void setEnterPassword(PasswordField enterPassword) {
        this.enterPassword = enterPassword;
    }

    @FXML
    private TextField enterLogin;

    @FXML
    private PasswordField enterPassword;

    @FXML
    private Button signInButton;

    @FXML
    private void signInButtonOnAction() {
        if(enterLogin.getText().equals(" ") || enterPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "One of textfield is empty!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            DbConnector.getInstance().connectWithDatabase(getEnterLogin().getText(), getEnterPassword().getText());
            System.out.println("ELO");
            //logDataDao.normalSelect();
        }
    }
}
