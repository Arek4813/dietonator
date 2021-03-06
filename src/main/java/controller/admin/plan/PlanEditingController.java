package controller.admin.plan;

import database.dao.PlanDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.PlanFx;

import java.sql.SQLException;

public class PlanEditingController {

    @FXML
    private TextField nameTextField;

    private PlanDao planDao;
    private String oldName;

    @FXML
    public void initialize() {
        planDao = new PlanDao();
    }

    @FXML
    public void updateMeal() {
        String newName = nameTextField.getText();
        if (!newName.equals("") && !newName.equals(oldName)) {
            try {
                planDao.updatePlan(oldName, newName);
                closeWindow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initializeField(PlanFx item) {
        nameTextField.setText(item.getName());
        oldName = item.getName();
    }

    private void closeWindow() {
        Stage stage = (Stage) this.nameTextField.getScene().getWindow();
        stage.close();
    }
}
