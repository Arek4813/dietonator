package controller.dietician;

import database.DbConnector;
import database.dao.DieticianDao;
import database.model.Dietician;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modelfx.DieticianFx;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileController {

    @FXML
    private Label loginLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label birthDateLabel;

    @FXML
    private Label mailLabel;

    private DieticianDao dieticianDao;

    @FXML
    public void initialize() {
        dieticianDao = new DieticianDao();
        try {
            ResultSet resultSet = dieticianDao.getDieticians(DbConnector.getInstance().getLogin());
            while (resultSet.next()) {
                loginLabel.setText(resultSet.getString("dieticianLogin"));
                nameLabel.setText(resultSet.getString("dieticianName"));
                surnameLabel.setText(resultSet.getString("dieticianSurname"));
                birthDateLabel.setText(resultSet.getString("dieticianBirthDate"));
                mailLabel.setText(resultSet.getString("dieticianMail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
