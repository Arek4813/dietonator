package controller.dietician;

import database.dao.MealDao;
import database.model.Meal;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import modelfx.MealFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class MealsController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableView mealTableView;

    @FXML
    private TableColumn<MealFx, String> nameTableColumn;

    @FXML
    private TableColumn<MealFx, Float> energyTableColumn;

    @FXML
    private TableColumn<MealFx, Float> fatTableColumn;

    @FXML
    private TableColumn<MealFx, Float> saturatesTableColumn;

    @FXML
    private TableColumn<MealFx, Float> carbohydratesTableColumn;

    @FXML
    private TableColumn<MealFx, Float> sugarsTableColumn;

    @FXML
    private TableColumn<MealFx, Float> proteinTableColumn;

    @FXML
    private TableColumn<MealFx, Float> saltTableColumn;

    @FXML
    private TableColumn<MealFx, MealFx> ingredientsTableColumn;

    @FXML
    private TextField addMealTextField;

    @FXML
    private TextField mealNameTextField;

    private MealDao mealDao;

    @FXML
    public void initialize() {
        mealDao = new MealDao();
        initializeTable();
        refreshTable();
    }

    @FXML
    public void addMeal() {
        String name = addMealTextField.getText();
        if (!name.equals("")) {
            Meal meal = new Meal();
            meal.setName(name);
            try {
                mealDao.addMeal(meal);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            refreshTable();
        }
        addMealTextField.clear();
    }

    @FXML
    public void searchMeal() {
        refreshTableByName(mealNameTextField.getText());
        mealNameTextField.clear();
    }

    private void refreshTable() {
        try {
            mealTableView.setItems(mealDao.getMeals());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTableByName(String name) {
        try {
            mealTableView.setItems(mealDao.getMeal(name));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeTable() {
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        energyTableColumn.setCellValueFactory(cellData -> cellData.getValue().energyProperty().asObject());
        fatTableColumn.setCellValueFactory(cellData -> cellData.getValue().fatProperty().asObject());
        saturatesTableColumn.setCellValueFactory(cellData -> cellData.getValue().saturatesProperty().asObject());
        carbohydratesTableColumn.setCellValueFactory(cellData -> cellData.getValue().carbohydratesProperty().asObject());
        sugarsTableColumn.setCellValueFactory(cellData -> cellData.getValue().sugarsProperty().asObject());
        proteinTableColumn.setCellValueFactory(cellData -> cellData.getValue().proteinProperty().asObject());
        saltTableColumn.setCellValueFactory(cellData-> cellData.getValue().saltProperty().asObject());

        ingredientsTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        ingredientsTableColumn.setCellFactory(cellData -> new TableCell<MealFx, MealFx>() {
            Button button = new Button("SHOW");
            @Override
            protected void updateItem(MealFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/productsOfMeal.fxml"));
                        try {
                            borderPane.setCenter(loader.load());
                            MealsProductsController controller = loader.getController();
                            controller.showIngredientsOfMeal(item);
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
    }

}
