package controller.admin.product;

import database.dao.CategoryDao;
import database.dao.ProductDao;
import database.model.Category;
import database.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelfx.CategoryFx;
import modelfx.ProductFx;
import utils.converter.CategoryConverter;
import utils.dialog.DialogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductEditingController {

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

    @FXML
    private Button updateButton;

    private ProductDao productDao;

    @FXML
    public void initialize() {
        productDao = new ProductDao();
    }

    @FXML
    public void updateProduct() {
        if (allFieldsAreNotEmpty()) {
            Product product = null;
            try {
                product = getProduct();
                productDao.updateProduct(product);
                closeWindow();
            }
            catch (NumberFormatException | SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
    }

    public void initializeFields(ProductFx item) {
        nameTextField.setText(item.getName());
        try {
            insertCategoriesInChoiceBox(item.getCategory());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameTextField.setEditable(false);
        energyTextField.setText(String.valueOf(item.getEnergy()));
        fatTextField.setText(String.valueOf(item.getFat()));
        saturatesTextField.setText(String.valueOf(item.getSaturates()));
        carbohydratesTextField.setText(String.valueOf(item.getCarbohydrates()));
        sugarsTextField.setText(String.valueOf(item.getSugars()));
        proteinTextField.setText(String.valueOf(item.getProtein()));
        saltTextField.setText(String.valueOf(item.getSalt()));
    }

    private Product getProduct() {
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
        return product;
    }

    private void closeWindow() {
        Stage stage = (Stage) this.updateButton.getScene().getWindow();
        stage.close();
    }

    private void insertCategoriesInChoiceBox(CategoryFx item) throws SQLException {
        CategoryDao categoryDao = new CategoryDao();
        CategoryFx selectedCategory = null;
        ResultSet resultSet = categoryDao.getCategories();
        ObservableList<CategoryFx> categoryList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Category category = new Category();
            category.setName(resultSet.getString("name"));
            CategoryFx categoryFx = CategoryConverter.getInstance().convertToCategoryFx(category);
            categoryList.add(categoryFx);
            if (categoryFx.getName().equals(item.getName()))
                selectedCategory = categoryFx;
        }
        categoryChoiceBox.setItems(categoryList);
        categoryChoiceBox.getSelectionModel().select(selectedCategory);
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
}
