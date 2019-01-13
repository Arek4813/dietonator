package controller.logdata;

import database.dao.LogDataDao;
import database.model.LogData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.LogDataFx;
import utils.dialog.DialogUtil;

import java.sql.SQLException;

public class LogDataEditingController {

    @FXML
    private TextField loginEditValueTextField;

    @FXML
    private TextField passwordEditValueTextField;

    @FXML
    private Button editLogDataButton;

    private LogDataDao logDataDao;

    @FXML
    public void initialize() {
        logDataDao=new LogDataDao();
    }





    @FXML
    public void editLogDataOnAction() {
        if (allFieldsAreNotEmpty()) {
            LogData logData = new LogData();
            try {
                logData = getLogData();
                logDataDao.updateLogData(logData);
                closeWindow();
            }
            catch (NumberFormatException | SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    public void initializeFields(LogDataFx item) {
        loginEditValueTextField.setText(item.getLoginValue());
        loginEditValueTextField.setEditable(false);
        passwordEditValueTextField.setText(item.getPasswordValue());
    }

    private LogData getLogData() {
        LogData logData = new LogData();
        logData.setLoginValue(loginEditValueTextField.getText());
        logData.setPasswordValue(passwordEditValueTextField.getText());
        return logData;
    }

    private void closeWindow() {
        Stage stage = (Stage) this.editLogDataButton.getScene().getWindow();
        stage.close();
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(loginEditValueTextField.getText().equals("") ||
                passwordEditValueTextField.getText().equals(""));
        return condition;
    }

}
