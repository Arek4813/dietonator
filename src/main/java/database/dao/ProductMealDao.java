package database.dao;

import database.DbConnector;
import database.model.ProductMeal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.ProductMealFx;
import utils.converter.ProductMealConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMealDao {
    private Connection connection;

    public ProductMealDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addProductToMeal(ProductMeal productMeal) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_product_meal(?, ?, ?)");
        stm.setString(1, productMeal.getProduct());
        stm.setString(2, productMeal.getMeal());
        stm.setInt(3, productMeal.getAmount());
        stm.executeUpdate();
    }

    public void updateProductInMeal(ProductMeal productMeal) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL update_product_meal(?, ?, ?)");
        stm.setString(1, productMeal.getProduct());
        stm.setString(2, productMeal.getMeal());
        stm.setInt(3, productMeal.getAmount());
        stm.executeUpdate();
    }

    public void deleteProductFromMeal(String productName, String mealName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_product_meal(?, ?)");
        stm.setString(1, productName);
        stm.setString(2, mealName);
        stm.executeUpdate();
    }

    public ObservableList<ProductMealFx> getProductsOfMeal(String mealName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_products_of_meal(?)");
        stm.setString(1, mealName);
        ResultSet resultSet = stm.executeQuery();
        ObservableList<ProductMealFx> products = FXCollections.observableArrayList();
        while (resultSet.next()) {
            ProductMeal productMeal = new ProductMeal();
            productMeal.setProduct(resultSet.getString("p.name"));
            productMeal.setMeal(mealName);
            productMeal.setAmount(resultSet.getInt("amount"));
            ProductMealFx productMealFx = ProductMealConverter.getInstance().convertToProductMealFx(productMeal);
            products.add(productMealFx);
        }
        return products;
    }
}
