package database.dao;

import database.DbConnector;
import database.model.Plan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.MealFx;
import modelfx.PlanFx;
import utils.converter.MealConverter;
import utils.converter.PlanConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDao {

    private Connection connection;

    public PlanDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addPlan(Plan plan) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_nutritional_plan(?)");
        stm.setString(1, plan.getName());
        stm.executeUpdate();

        PreparedStatement stm1 = connection.prepareStatement( "INSERT INTO diet_and_user (UserLogin, DietName) VALUES" +
                " " +
                "(?, ?)" );
        stm1.setString( 1, DbConnector.getInstance().getLogin() );
        stm1.setString( 2, plan.getName() );
        stm1.executeUpdate();
    }

    public void updatePlan(String oldName, String newName) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL update_nutritional_plan(?, ?)");
        stm.setString(1, oldName);
        stm.setString(2, newName);
        stm.executeUpdate();
    }

    public void deletePlan(String name) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_nutritional_plan(?)");
        stm.setString(1, name);
        stm.executeUpdate();
    }

    public ObservableList<PlanFx> getPlan(String name) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_nutritional_plan(?)");
        stm.setString(1, name);
        ResultSet resultSet = stm.executeQuery();
        ObservableList<PlanFx> plans = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Plan plan = new Plan();
            plan.setName(resultSet.getString("name"));
            plan.setEnergy(resultSet.getFloat("energy"));
            plan.setFat(resultSet.getFloat("fat"));
            plan.setSaturates(resultSet.getFloat("of_which_saturates"));
            plan.setCarbohydrates(resultSet.getFloat("carbohydrates"));
            plan.setSugars(resultSet.getFloat("of_which_sugars"));
            plan.setProtein(resultSet.getFloat("protein"));
            plan.setSalt(resultSet.getFloat("salt"));
            PlanFx planFx = PlanConverter.getInstance().convertToPlanFx(plan);
            plans.add(planFx);
        }
        return plans;
    }

    public ObservableList<PlanFx> getPlansForUser(String login) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_nutritional_plan_for_user(?)");
        stm.setString(1, login);
        ResultSet resultSet = stm.executeQuery();
        ObservableList<PlanFx> plans = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Plan plan = new Plan();
            plan.setName(resultSet.getString("name"));
            plan.setEnergy(resultSet.getFloat("energy"));
            plan.setFat(resultSet.getFloat("fat"));
            plan.setSaturates(resultSet.getFloat("of_which_saturates"));
            plan.setCarbohydrates(resultSet.getFloat("carbohydrates"));
            plan.setSugars(resultSet.getFloat("of_which_sugars"));
            plan.setProtein(resultSet.getFloat("protein"));
            plan.setSalt(resultSet.getFloat("salt"));
            PlanFx planFx = PlanConverter.getInstance().convertToPlanFx(plan);
            plans.add(planFx);
        }
        return plans;
    }

}
