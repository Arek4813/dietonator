package database.dao;

import database.DbConnector;
import database.model.DietUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelfx.DietUserFx;
import utils.converter.DietUserConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DietUserDao {
    private Connection connection;

    public DietUserDao() {
        connection = DbConnector.getInstance().getConnection();
    }

    public void addPlanToUser(String user, String plan) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_plan_for_user(?, ?)");
        stm.setString(1, user);
        stm.setString(2, plan);
        stm.executeUpdate();
    }

    public void addPlanByDieticianToUser(String user, String plan, String dietician) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL create_plan_for_user_by_dietician(?, ?, ?)");
        stm.setString(1, user);
        stm.setString(2, plan);
        stm.setString(3, dietician);
        stm.executeUpdate();
    }

    public void deletePlanFromUser(String user, String plan) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL delete_users_plan(?, ?)");
        stm.setString(1, user);
        stm.setString(2, plan);
        stm.executeUpdate();
    }

    public ObservableList<DietUserFx> selectPlansOfUser(String user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_plans_of_user(?)");
        stm.setString(1, user);
        return getListOfPlans(stm);
    }

    public ObservableList<DietUserFx> selectPlansForUserByDietician(String user, String dietician) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_plans_for_user_by_dietician(?, ?)");
        stm.setString(1, user);
        stm.setString(2, dietician);
        return getListOfPlans(stm);
    }

    public ObservableList<DietUserFx> selectPlansByDietician(String dietician) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("CALL select_plans_by_dietician(?)");
        stm.setString(1, dietician);
        return getListOfPlans(stm);
    }

    private ObservableList<DietUserFx> getListOfPlans(PreparedStatement stm) throws SQLException {
        ResultSet resultSet = stm.executeQuery();
        ObservableList<DietUserFx> plans = FXCollections.observableArrayList();
        while (resultSet.next()) {
            DietUser dietUser = new DietUser();
            dietUser.setUser(resultSet.getString("UserLogin"));
            dietUser.setPlan(resultSet.getString("DietName"));
            dietUser.setDietician(resultSet.getString("DieticianLogin"));
            DietUserFx dietUserFx = DietUserConverter.getInstance().convertToDietUserFx(dietUser);
            plans.add(dietUserFx);
        }
        return plans;
    }
}
