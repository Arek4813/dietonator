package controller.admin.meal;

import database.dao.MealDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.MealFx;

import java.sql.SQLException;

public class MealEditingController {

    @FXML
    private TextField nameTextField;

    private MealDao mealDao;
    private String oldName;

    @FXML
    public void initialize() {
        mealDao = new MealDao();
    }

    @FXML
    public void updateMeal() {
        String newName = nameTextField.getText();
        if (!newName.equals("") && !newName.equals(oldName)) {
            try {
                mealDao.updateMeal(oldName, newName);
                closeWindow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void initializeField(MealFx item) {
        nameTextField.setText(item.getName());
        oldName = item.getName();
    }

    private void closeWindow() {
        Stage stage = (Stage) this.nameTextField.getScene().getWindow();
        stage.close();
    }
}
