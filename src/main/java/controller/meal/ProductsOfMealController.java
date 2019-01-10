package controller.meal;

import controller.AdminPanelController;
import controller.product.ProductEditingController;
import database.dao.ProductMealDao;
import database.model.ProductMeal;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.MealFx;
import modelfx.ProductFx;
import modelfx.ProductMealFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ProductsOfMealController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<ProductMealFx> ingredientsTable;

    @FXML
    private TableColumn<ProductMealFx, String> productColumn;

    @FXML
    private TableColumn<ProductMealFx, Integer> amountColumn;

    @FXML
    private TableColumn<ProductMealFx, ProductMealFx> editColumn;

    @FXML
    private TableColumn<ProductMealFx, ProductMealFx> deleteColumn;

    @FXML
    private TextField productTextField;

    @FXML
    private TextField mealTextField;

    @FXML
    private TextField amountTextField;

    private MealFx mealFx;

    private ProductMealDao productMealDao;

    @FXML
    public void initialize() {
        productMealDao = new ProductMealDao();
        initializeTable();
    }


    @FXML
    public void goBackToMeals() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/meal/mealView.fxml"));
        try {
            ((BorderPane)vBox.getParent()).setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addProductToMeal() {
        try {
            ProductMeal productMeal = new ProductMeal();
            productMeal.setProduct(productTextField.getText());
            productMeal.setMeal(mealFx.getName());
            productMeal.setAmount(Integer.parseInt(amountTextField.getText()));
            productMealDao.addProductToMeal(productMeal);
            refreshTable();
            productTextField.clear();
            amountTextField.clear();
        }
        catch (SQLException | NumberFormatException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        refreshTable();
        productTextField.clear();
        amountTextField.clear();
    }

    public void showIngredientsOfMeal(MealFx item) {
        mealFx = item;
        mealTextField.setText(mealFx.getName());
        mealTextField.setEditable(false);
        refreshTable();
    }

    private void initializeTable() {
        productColumn.setCellValueFactory(cellData -> cellData.getValue().productProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<ProductMealFx, ProductMealFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(ProductMealFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            productMealDao.deleteProductFromMeal(item.getProduct(), item.getMeal());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });

        editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<ProductMealFx, ProductMealFx>() {
            Button button = new Button("EDIT");
            @Override
            protected void updateItem(ProductMealFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/meal/productMealEditing.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        ProductMealEditingController controller = loader.getController();
                        controller.initializeTextFields(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });
    }

    private void refreshTable() {
        try {
            ObservableList<ProductMealFx> products = productMealDao.getProductsOfMeal(mealFx.getName());
            ingredientsTable.setItems(products);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
