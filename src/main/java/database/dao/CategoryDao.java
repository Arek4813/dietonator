package database.dao;

import database.DbConnector;
import database.model.Category;

import java.sql.*;

public class CategoryDao {
    private Connection connection;

    public CategoryDao() {
        this.connection = DbConnector.getInstance().getConnection();
    }

    public void addCategory(Category category) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_category(?)");
        stm.setString(1, category.getName());
        stm.executeUpdate();

    }

    public ResultSet getCategories() throws SQLException {
        ResultSet resultSet = null;
        Statement stm = connection.createStatement();
        resultSet = stm.executeQuery("CALL select_all_categories");
        return  resultSet;
    }

    public void deleteCategory(String name) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_category(?)");
        stm.setString(1, name);
        stm.executeUpdate();

    }

    public ResultSet getCategories(String name) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_category(?)");
        stm.setString(1, name);
        ResultSet resultSet = stm.executeQuery();
        return resultSet;
    }
}
