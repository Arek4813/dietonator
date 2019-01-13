package database.dao;

import database.DbConnector;
import database.model.PlanMeal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.PlanMealFx;
import utils.converter.PlanMealConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanMealDao {
    private Connection connection;

    public PlanMealDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void createPlanMeal(PlanMeal planMeal) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_plan_meal(?, ?, ?, ?)");
        stm.setString(1, planMeal.getPlanName());
        stm.setString(2, planMeal.getMealName());
        stm.setString(3, planMeal.getDay());
        stm.setString(4, planMeal.getTime());
        stm.executeUpdate();
    }

    public void deletePlanMeal(String plan, String day, String time) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_plan_meal(?, ?, ?)");
        stm.setString(1, plan);
        stm.setString(2, day);
        stm.setString(3, time);
        stm.executeUpdate();
    }

    public ObservableList<PlanMealFx> selectMealsOfPlan(String plan) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_meals_of_plan(?)");
        stm.setString(1, plan);
        ResultSet resultSet = stm.executeQuery();
        ObservableList<PlanMealFx> meals = FXCollections.observableArrayList();
        while (resultSet.next()) {
            PlanMeal planMeal = new PlanMeal();
            planMeal.setPlanName(resultSet.getString("plan"));
            planMeal.setMealName(resultSet.getString("meal"));
            planMeal.setDay(resultSet.getString("day_of_week"));
            planMeal.setTime(resultSet.getString("time_of_day"));
            PlanMealFx planMealFx = PlanMealConverter.getInstance().convertToPlanMealFx(planMeal);
            meals.add(planMealFx);
        }
        return meals;
    }




}
