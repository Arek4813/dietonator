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
        stm.setInt(3, product.getEnergy());
        stm.setInt(4, product.getFat());
        stm.setInt(5, product.getSaturates());
        stm.setInt(6, product.getCarbohydrates());
        stm.setInt(7, product.getSugars());
        stm.setInt(8, product.getProtein());
        stm.setInt(9, product.getSalt());
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
        stm.setInt(3, product.getEnergy());
        stm.setInt(4, product.getFat());
        stm.setInt(5, product.getSaturates());
        stm.setInt(6, product.getCarbohydrates());
        stm.setInt(7, product.getSugars());
        stm.setInt(8, product.getProtein());
        stm.setInt(9, product.getSalt());
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
