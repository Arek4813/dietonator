package controller.user.product;

import controller.admin.product.ProductEditingController;
import database.dao.ProductDao;
import database.model.Category;
import database.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.CategoryFx;
import modelfx.ProductFx;
import utils.converter.ProductConverter;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductController {

    @FXML
    private TableView<ProductFx> productTable;

    @FXML
    private TableColumn<ProductFx, String> nameColumn;

    @FXML
    private TableColumn<ProductFx, CategoryFx> categoryColumn;

    @FXML
    private TableColumn<ProductFx, Integer> energyColumn;

    @FXML
    private TableColumn<ProductFx, Integer> fatColumn;

    @FXML
    private TableColumn<ProductFx, Integer> saturatesColumn;

    @FXML
    private TableColumn<ProductFx, Integer> carbohydratesColumn;

    @FXML
    private TableColumn<ProductFx, Integer> sugarsColumn;

    @FXML
    private TableColumn<ProductFx, Integer> proteinColumn;

    @FXML
    private TableColumn<ProductFx, Integer> saltColumn;

    @FXML
    private TextField searchProductTextField;

    private ProductDao productDao;

    @FXML
    public void initialize() {
        productDao = new ProductDao();
        initializeTable();
        refreshTable();
    }

    @FXML
    public void searchProduct() {
        ObservableList<ProductFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = productDao.getProducts(searchProductTextField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
        searchProductTextField.clear();
    }

    public void refreshTable() {
        ObservableList<ProductFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = productDao.getProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
    }

    private void insertProductIntoTheTable(ObservableList<ProductFx> data, ResultSet rs) {
        ProductConverter converter = ProductConverter.getInstance();

        try {
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name"));
                Category category = new Category();
                category.setName(rs.getString("category"));
                product.setCategory(category);
                product.setEnergy(rs.getInt("energy"));
                product.setFat(rs.getInt("fat"));
                product.setSaturates(rs.getInt("of_which_saturates"));
                product.setCarbohydrates(rs.getInt("carbohydrates"));
                product.setSugars(rs.getInt("of_which_sugars"));
                product.setProtein(rs.getInt("protein"));
                product.setSalt(rs.getInt("salt"));
                data.add(converter.convertToProductFx(product));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        productTable.setItems(data);
    }

    private void initializeTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        energyColumn.setCellValueFactory(cellData -> cellData.getValue().energyProperty().asObject());
        fatColumn.setCellValueFactory(cellData ->cellData.getValue().fatProperty().asObject());
        saturatesColumn.setCellValueFactory(cellData -> cellData.getValue().saturatesProperty().asObject());
        carbohydratesColumn.setCellValueFactory(cellData -> cellData.getValue().carbohydratesProperty().asObject());
        sugarsColumn.setCellValueFactory(cellData -> cellData.getValue().sugarsProperty().asObject());
        proteinColumn.setCellValueFactory(cellData -> cellData.getValue().proteinProperty().asObject());
        saltColumn.setCellValueFactory(cellData -> cellData.getValue().saltProperty().asObject());
    }
}
