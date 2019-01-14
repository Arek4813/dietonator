package controller.user.dieticians;

import controller.admin.dieticians.DieticianEditingController;
import controller.admin.dieticians.DieticianUsersController;
import database.DbConnector;
import database.dao.DieticianDao;
import database.model.Dietician;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.DieticianFx;
import utils.converter.DieticianConverter;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDieticiansViewController {

    @FXML
    private TableView<DieticianFx> dieticianTable;

    @FXML
    private TableColumn<DieticianFx, String> dieticianLogin;

    @FXML
    private TableColumn<DieticianFx, String> dieticianName;

    @FXML
    private TableColumn<DieticianFx, String> dieticianSurname;

    @FXML
    private TableColumn<DieticianFx, String> dieticianMail;

    @FXML
    private TableColumn<DieticianFx, Date> dieticianBirthDate;

    private DieticianDao dieticianDao;

    @FXML
    public void initialize() {
        dieticianDao = new DieticianDao();
        dieticianLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        dieticianName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dieticianSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        dieticianBirthDate.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        dieticianMail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        refreshTable();

    }

    public void refreshTable() {
        ObservableList<DieticianFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            //rs = dieticianDao.getDieticians();
            rs = dieticianDao.getDieticiansOfChosenUser( DbConnector.getInstance().getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
    }

    private void insertProductIntoTheTable(ObservableList<DieticianFx> data, ResultSet rs) {
        DieticianConverter converter = DieticianConverter.getInstance();

        try {
            while (rs.next()) {
                Dietician dietician = new Dietician();
                dietician.setDieticianLogin(rs.getString( "dieticianLogin" ));
                dietician.setDieticianName(rs.getString( "dieticianName" ));
                dietician.setDieticianSurname(rs.getString( "dieticianSurname" ));
                dietician.setDieticianBirthDate(rs.getDate( "dieticianBirthDate" ));
                dietician.setDieticianMail(rs.getString( "dieticianMail" ));
                data.add(converter.convertToDieticianFx(dietician));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dieticianTable.setItems(data);
    }
}
