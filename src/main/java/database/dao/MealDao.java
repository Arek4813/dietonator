package database.dao;

import database.DbConnector;
import database.model.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.MealFx;
import utils.converter.MealConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MealDao {
    private Connection connection;

    public MealDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addMeal(Meal meal) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_meal(?)");
        stm.setString(1, meal.getName());
        stm.executeUpdate();
    }

    public void updateMeal(String oldMeal, String newMeal) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL update_meal(?, ?)");
        stm.setString(1, oldMeal);
        stm.setString(2, newMeal);
        stm.executeUpdate();
    }

    public void deleteMeal(String mealName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_meal(?)");
        stm.setString(1, mealName);
        stm.executeUpdate();
    }

    public ObservableList<MealFx> getMeal(String mealName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_meal(?)");
        stm.setString(1, mealName);
        ResultSet resultSet = stm.executeQuery();
        return getListOfMeals(resultSet);
    }

    public ObservableList<MealFx> getMeals() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_meal('')");
        ResultSet resultSet = stm.executeQuery();
        return getListOfMeals(resultSet);
    }

    private ObservableList<MealFx> getListOfMeals(ResultSet resultSet) throws SQLException {
        ObservableList<MealFx> mealList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Meal meal = new Meal();
            meal.setName(resultSet.getString("name"));
            meal.setEnergy(resultSet.getFloat("energy"));
            meal.setFat(resultSet.getFloat("fat"));
            meal.setSaturates(resultSet.getFloat("of_which_saturates"));
            meal.setCarbohydrates(resultSet.getFloat("carbohydrates"));
            meal.setSugars(resultSet.getFloat("of_which_sugars"));
            meal.setProtein(resultSet.getFloat("protein"));
            meal.setSalt(resultSet.getFloat("salt"));
            MealFx mealFx = MealConverter.getInstance().convertToMealFx(meal);
            mealList.add(mealFx);
        }
        return mealList;
    }
}
