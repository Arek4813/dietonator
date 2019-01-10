package controller.meal;

import controller.AdminPanelController;
import controller.product.ProductEditingController;
import database.dao.MealDao;
import database.model.Meal;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.MealFx;
import modelfx.ProductFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class MealViewController {

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
    private TableColumn<MealFx, MealFx> editTableColumn;

    @FXML
    private TableColumn<MealFx, MealFx> deleteTableColumn;

    @FXML
    private TableColumn<MealFx, MealFx> ingredientsTableColumn;

    @FXML
    private TextField addMealTextField;

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
    }

    private void refreshTable() {
        try {
            mealTableView.setItems(mealDao.getMeals());
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
        deleteTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteTableColumn.setCellFactory(cellData -> new TableCell<MealFx, MealFx>() {
            Button button = new Button("X");
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
                        try {
                            mealDao.deleteMeal(item.getName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        editTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editTableColumn.setCellFactory(cellData -> new TableCell<MealFx, MealFx>() {
            Button button = new Button("EDIT");
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/meal/mealEditing.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        MealEditingController controller = loader.getController();
                        controller.initializeField(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/meal/productsOfMealView.fxml"));
                        try {
                            borderPane.setCenter(loader.load());
                            ProductsOfMealController controller = loader.getController();
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
