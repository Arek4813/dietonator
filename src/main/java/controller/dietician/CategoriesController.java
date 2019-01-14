package controller.dietician;

import database.dao.CategoryDao;
import database.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import modelfx.CategoryFx;
import utils.converter.CategoryConverter;
import utils.dialog.DialogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesController {
    @FXML
    private TableView<CategoryFx> categoryTable;

    @FXML
    private TableColumn<CategoryFx, String> nameColumn;

    @FXML
    private TextField searchCategoryTextField;

    private CategoryDao categoryDao;

    @FXML
    public void initialize() {
        categoryDao = new CategoryDao();
        initializeTable();
    }

    @FXML
    public void searchCategory() {
        refreshTable(searchCategoryTextField.getText());
        searchCategoryTextField.clear();
    }

    public void initializeTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        refreshTable(searchCategoryTextField.getText());
    }

    public void refreshTable(String text) {
        try {
            ResultSet resultSet = categoryDao.getCategories(text);
            ObservableList<CategoryFx> categories = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString("name"));
                CategoryFx categoryFx = new CategoryFx();
                categoryFx = CategoryConverter.getInstance().convertToCategoryFx(category);
                categories.add(categoryFx);
            }
            categoryTable.setItems(categories);
        }
        catch (SQLException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }
}
