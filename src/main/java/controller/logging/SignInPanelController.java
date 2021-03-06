package controller.logging;

import database.DbConnector;
import database.dao.LoggingDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.sql.SQLException;

public class SignInPanelController {

    LoggingDao loggingDao;

    public TextField getEnterLogin() {
        return enterLogin;
    }

    public PasswordField getEnterPassword() {
        return enterPassword;
    }

    @FXML
    private TextField enterLogin;

    @FXML
    private PasswordField enterPassword;

    @FXML
    private Button signInButton;

    @FXML
    private void signInButtonOnAction() {
        if(enterLogin.getText().equals("") || enterPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "One of textfield is empty!", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            DbConnector.getInstance().connectWithDatabase("root", "rootpassword");
            this.loggingDao =new LoggingDao();
            try {
                if (loggingDao.isRegistered(getEnterLogin().getText(), getEnterPassword().getText())==true) {
                    if(loggingDao.getRole(getEnterLogin().getText()).equals("USER")) {
                        logger("/fxml/user/mainUserView.fxml");
                    }
                    else if (loggingDao.getRole(getEnterLogin().getText()).equals("DIETICIAN")){
                        logger("/fxml/dietician/dieticianView.fxml");
                    }
                    else if (loggingDao.getRole(getEnterLogin().getText()).equals("ADMIN")) {
                        logger( "/fxml/admin/adminView.fxml" );
                    }
                }
                else {
                    Alert alert = new Alert( Alert.AlertType.ERROR, "This data don't exist in database", ButtonType.OK );
                    alert.showAndWait();
                    enterLogin.clear();
                    enterPassword.clear();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logger(String path) throws IOException {
        DbConnector.getInstance().closeConnection();
        DbConnector.getInstance().connectWithDatabase(getEnterLogin().getText(), getEnterPassword().getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Pane borderPane = loader.load();
        Scene userScene = new Scene(borderPane);
        Main.setScene(userScene);
    }
}
