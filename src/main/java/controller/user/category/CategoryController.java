package controller.user.category;

import database.dao.CategoryDao;
import database.model.Category;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelfx.CategoryFx;
import utils.converter.CategoryConverter;
import utils.dialog.DialogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryController {

    @FXML
    private TableView<CategoryFx> categoryTable;

    @FXML
    private TableColumn<CategoryFx, String> nameColumn;

    @FXML
    private TableColumn<CategoryFx, CategoryFx> deleteColumn;

    @FXML
    private TextField searchCategoryTextField;

    private CategoryDao categoryDao;

    @FXML
    public void initialize() {
        categoryDao = new CategoryDao();
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        refreshTable();
    }

    @FXML
    public void searchCategory() {
        String name = searchCategoryTextField.getText();
        filterTable(name);
        searchCategoryTextField.clear();
    }

    public void refreshTable() {
        ObservableList<CategoryFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = categoryDao.getCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertCategoriesIntoTheTable(data, rs);
    }

    public void filterTable(String name) {
        ObservableList<CategoryFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = categoryDao.getCategories(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertCategoriesIntoTheTable(data, rs);
    }

    private void insertCategoriesIntoTheTable(ObservableList<CategoryFx> data, ResultSet rs) {
        CategoryConverter converter = CategoryConverter.getInstance();

        try {
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString("name"));
                data.add(converter.convertToCategoryFx(category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        categoryTable.setItems(data);
    }
}
