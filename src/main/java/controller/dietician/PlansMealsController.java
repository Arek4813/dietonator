package controller.dietician;

import database.dao.PlanMealDao;
import database.model.PlanMeal;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelfx.PlanFx;
import modelfx.PlanMealFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class PlansMealsController {
    @FXML
    private VBox vBox;

    @FXML
    private TableView<PlanMealFx> mealsOfPlanTable;

    @FXML
    private TableColumn<PlanMealFx, String> dayColumn;

    @FXML
    private TableColumn<PlanMealFx, String> timeColumn;

    @FXML
    private TableColumn<PlanMealFx, String> mealColumn;

    @FXML
    private TextField mealTextField;

    @FXML
    private TextField planTextField;

    @FXML
    private ChoiceBox<String> dayChoiceBox;

    @FXML
    private ChoiceBox<String> timeChoiceBox;

    private PlanFx planFx;

    private PlanMealDao planMealDao;

    @FXML
    public void initialize() {
        planMealDao = new PlanMealDao();
        initializeTable();
        initializeChoiceBoxes();
    }

    @FXML
    public void addMealToPlan() {
        if (!mealTextField.getText().isEmpty()) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setPlanName(planTextField.getText());
            planMeal.setMealName(mealTextField.getText());
            planMeal.setDay(dayChoiceBox.getSelectionModel().getSelectedItem());
            planMeal.setTime(timeChoiceBox.getSelectionModel().getSelectedItem());
            try {
                planMealDao.createPlanMeal(planMeal);
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
            refreshTable();
            mealTextField.clear();
        }
    }

    @FXML
    public void goBackToPlans() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/plans.fxml"));
        try {
            ((BorderPane)vBox.getParent()).setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDetails(PlanFx item) {
        planFx = item;
        refreshTable();
        planTextField.setText(item.getName());
        planTextField.setEditable(false);
    }

    private void initializeTable() {
        dayColumn.setCellValueFactory(cellData -> cellData.getValue().dayProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        mealColumn.setCellValueFactory(cellData -> cellData.getValue().mealProperty());
    }

    private void initializeChoiceBoxes() {
        ObservableList<String> days = FXCollections.observableArrayList();
        days.addAll("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        ObservableList<String> times = FXCollections.observableArrayList();
        times.addAll("Breakfast", "Elevenses", "Dinner", "Tea", "Supper");
        dayChoiceBox.setItems(days);
        timeChoiceBox.setItems(times);
    }

    private void refreshTable() {
        try {
            ObservableList<PlanMealFx> mealsOfPlan = planMealDao.selectMealsOfPlan(planFx.getName());
            mealsOfPlanTable.setItems(mealsOfPlan);
        }
        catch (SQLException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }
}
