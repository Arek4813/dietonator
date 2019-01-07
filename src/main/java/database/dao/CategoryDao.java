package database.dao;

import database.DbConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryDao {
    private Connection connection;

    public CategoryDao() {
        this.connection = DbConnector.getInstance().getConnection();
    }

    public void showCategories() {
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("CALL select_all_categories");

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
