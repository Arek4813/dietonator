package controller.admin.product;

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

public class ProductViewController {

    @FXML
    private TableView<ProductFx> productTable;

    @FXML
    private TableColumn<ProductFx, String> nameColumn;

    @FXML
    private TableColumn<ProductFx, CategoryFx> categoryColumn;

    @FXML
    private TableColumn<ProductFx, Float> energyColumn;

    @FXML
    private TableColumn<ProductFx, Float> fatColumn;

    @FXML
    private TableColumn<ProductFx, Float> saturatesColumn;

    @FXML
    private TableColumn<ProductFx, Float> carbohydratesColumn;

    @FXML
    private TableColumn<ProductFx, Float> sugarsColumn;

    @FXML
    private TableColumn<ProductFx, Float> proteinColumn;

    @FXML
    private TableColumn<ProductFx, Float> saltColumn;

    @FXML
    private TableColumn<ProductFx, ProductFx> deleteColumn;

    @FXML
    private TableColumn<ProductFx, ProductFx> editColumn;

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
                product.setEnergy(rs.getFloat("energy"));
                product.setFat(rs.getFloat("fat"));
                product.setSaturates(rs.getFloat("of_which_saturates"));
                product.setCarbohydrates(rs.getFloat("carbohydrates"));
                product.setSugars(rs.getFloat("of_which_sugars"));
                product.setProtein(rs.getFloat("protein"));
                product.setSalt(rs.getFloat("salt"));
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
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<ProductFx, ProductFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(ProductFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            productDao.deleteProduct(item.getName());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<ProductFx, ProductFx>() {
            Button button = new Button("EDIT");
            @Override
            protected void updateItem(ProductFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/product/productEditing.fxml"));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        ProductEditingController controller = loader.getController();
                        controller.initializeFields(item);
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


}
