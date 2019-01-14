package database.dao;

import database.DbConnector;
import database.model.Category;
import database.model.Product;

import java.sql.*;

public class ProductDao {
    private Connection connection;

    public ProductDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addProduct(Product product) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_product(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setString(1, product.getName());
        stm.setString(2, product.getCategory().getName());
        stm.setFloat(3, product.getEnergy());
        stm.setFloat(4, product.getFat());
        stm.setFloat(5, product.getSaturates());
        stm.setFloat(6, product.getCarbohydrates());
        stm.setFloat(7, product.getSugars());
        stm.setFloat(8, product.getProtein());
        stm.setFloat(9, product.getSalt());
        stm.executeUpdate();
    }

    public void deleteProduct(String productName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_product(?)");
        stm.setString(1, productName);
        stm.executeUpdate();
    }

    public void updateProduct(Product product) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL update_product(?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stm.setString(1, product.getName());
        stm.setString(2, product.getCategory().getName());
        stm.setFloat(3, product.getEnergy());
        stm.setFloat(4, product.getFat());
        stm.setFloat(5, product.getSaturates());
        stm.setFloat(6, product.getCarbohydrates());
        stm.setFloat(7, product.getSugars());
        stm.setFloat(8, product.getProtein());
        stm.setFloat(9, product.getSalt());
        stm.executeUpdate();
    }

    public ResultSet getProducts() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_all_products");
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }

    public ResultSet getProducts(String name) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_product(?)");
        stm.setString(1, name);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }
}
