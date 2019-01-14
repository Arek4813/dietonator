package controller.admin.product;

import database.dao.CategoryDao;
import database.dao.ProductDao;
import database.model.Category;
import database.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modelfx.CategoryFx;
import utils.converter.CategoryConverter;
import utils.dialog.DialogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ProductAddingController {

    @FXML
    private TextField nameTextField;

    @FXML
    private ChoiceBox<CategoryFx> categoryChoiceBox;

    @FXML
    private TextField energyTextField;

    @FXML
    private TextField fatTextField;

    @FXML
    private TextField saturatesTextField;

    @FXML
    private TextField carbohydratesTextField;

    @FXML
    private TextField sugarsTextField;

    @FXML
    private TextField proteinTextField;

    @FXML
    private TextField saltTextField;

    private ProductDao productDao;

    @FXML
    public void initialize() {
        productDao = new ProductDao();
        try {
            insertCategoriesInChoiceBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        clearFields();
    }

    @FXML
    public void addProduct() {
        if (allFieldsAreNotEmpty()) {
            Product product = new Product();
            product.setName(nameTextField.getText());

            CategoryFx categoryFx = categoryChoiceBox.getSelectionModel().getSelectedItem();

            product.setCategory(CategoryConverter.getInstance().convertToCategory(categoryFx));
            product.setEnergy(Float.parseFloat(energyTextField.getText()));
            product.setFat(Float.parseFloat(fatTextField.getText()));
            product.setSaturates(Float.parseFloat(saturatesTextField.getText()));
            product.setCarbohydrates(Float.parseFloat(carbohydratesTextField.getText()));
            product.setSugars(Float.parseFloat(sugarsTextField.getText()));
            product.setProtein(Float.parseFloat(proteinTextField.getText()));
            product.setSalt(Float.parseFloat(saltTextField.getText()));
            try {
                productDao.addProduct(product);
                clearFields();
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    private boolean allFieldsAreNotEmpty() {
        boolean condition = !(nameTextField.getText().equals("") ||
                            categoryChoiceBox.getSelectionModel().isEmpty() ||
                            energyTextField.getText().equals("") ||
                            fatTextField.getText().equals("") ||
                            saturatesTextField.getText().equals("") ||
                            carbohydratesTextField.getText().equals("") ||
                            sugarsTextField.getText().equals("") ||
                            proteinTextField.getText().equals("") ||
                            saltTextField.getText().equals(""));
        return condition;
    }

    private void insertCategoriesInChoiceBox() throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        ResultSet resultSet = categoryDao.getCategories();
        ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Category category = new Category();
            category.setName(resultSet.getString("name"));
            categoryList.add(CategoryConverter.getInstance().convertToCategoryFx(category));
        }
        categoryChoiceBox.setItems(categoryList);
    }

    private void clearFields() {
        nameTextField.clear();
        energyTextField.clear();
        categoryChoiceBox.getSelectionModel().clearSelection();
        fatTextField.clear();
        saturatesTextField.clear();
        carbohydratesTextField.clear();
        sugarsTextField.clear();
        proteinTextField.clear();
        saltTextField.clear();
    }
}
