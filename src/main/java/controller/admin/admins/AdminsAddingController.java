package controller.admin.admins;

import database.dao.AdminsDao;
import database.model.Admin;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.dialog.DialogUtil;

import java.sql.SQLException;

public class AdminsAddingController {

    @FXML
    private TextField adminLoginTextField;

    @FXML
    private TextField adminPasswordTextField;

    private AdminsDao adminsDao;

    @FXML
    public void initialize() {
        adminsDao = new AdminsDao();
        clearFields();
    }

    @FXML
    public void addAdmin() {
        if (allFieldsAreNotEmpty()) {
            Admin admin = new Admin();
            admin.setAdminLogin(adminLoginTextField.getText());
            admin.setAdminPassword(adminPasswordTextField.getText());
            try {
                adminsDao.addAdmin(admin);
                clearFields();
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(adminLoginTextField.getText().equals("") ||
                adminPasswordTextField.getText().equals(""));
        return condition;
    }

    private void clearFields() {
        adminLoginTextField.clear();
        adminPasswordTextField.clear();
    }
}
