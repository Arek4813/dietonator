package controller.dietician;

import database.DbConnector;
import database.dao.DietUserDao;
import database.dao.UserDao;
import database.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import modelfx.DietUserFx;
import modelfx.UserDieticianFx;
import modelfx.UserFx;
import utils.dialog.DialogUtil;

import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerProfileController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label loginLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label weightLabel;

    @FXML
    private Label heightLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label mailLabel;

    private UserFx user;

    private UserDao userDao;

    private DietUserDao dietUserDao;

    @FXML
    public void initialize() {
        userDao = new UserDao();
        dietUserDao = new DietUserDao();
    }

    @FXML
    public void goBackToCustomers() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/customers.fxml"));
        try {
            ((BorderPane)gridPane.getParent()).setCenter(loader.load());
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    public void setUser(UserDieticianFx item) {
        user = new UserFx();
        try {
        ResultSet rs = userDao.getUserByLogin(item.getuLogin());
        while (rs.next()) {
            user.setLogin(rs.getString("userLogin"));
            user.setName(rs.getString("userName"));
            user.setSurname(rs.getString("userSurname"));
            user.setWeight(rs.getFloat("userWeight"));
            user.setHeight(rs.getFloat("userHeight"));
            user.setBirthDate(rs.getDate("userBirthDate"));
            user.setMail(rs.getString("userMail"));
        }
        }
        catch (SQLException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        initializeLabels();
    }

    private void initializeLabels() {
        loginLabel.setText(user.getLogin());
        nameLabel.setText(user.getName());
        surnameLabel.setText(user.getSurname());
        weightLabel.setText(String.valueOf(user.getWeight()));
        heightLabel.setText(String.valueOf(user.getHeight()));
        dateLabel.setText(String.valueOf(user.getBirthDate()));
        mailLabel.setText(user.getMail());
    }
}
