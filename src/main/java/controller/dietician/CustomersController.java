package controller.dietician;

import database.DbConnector;
import database.dao.UserDao;
import database.dao.UserDieticianDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modelfx.UserFx;

import java.sql.Date;
import java.sql.SQLException;

public class CustomersController {

    @FXML
    private TableView<UserFx> customersTable;

    @FXML
    private TableColumn<UserFx, String> loginColumn;

    @FXML
    private TableColumn<UserFx, String> nameColumn;

    @FXML
    private TableColumn<UserFx, String> surnameColumn;

    @FXML
    private TableColumn<UserFx, Float> weightColumn;

    @FXML
    private TableColumn<UserFx, Float> heightColumn;

    @FXML
    private TableColumn<UserFx, String> birthDateColumn;

    @FXML
    private TableColumn<UserFx, String> mailColumn;

    @FXML
    private TableColumn<UserFx, UserFx> deleteColumn;

    @FXML
    private TextField customerTextField;

    private UserDao userDao;

    private UserDieticianDao userDieticianDao;

    @FXML
    public void initialize() {
        userDao = new UserDao();
        userDieticianDao = new UserDieticianDao();
        initializeColumns();
        refreshTable();
    }

    @FXML
    public void addCustomer() {
        String login = customerTextField.getText();
        try {
            userDieticianDao.addUDConnection(login, DbConnector.getInstance().getLogin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTable();
    }

    public void initializeColumns() {
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        weightColumn.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        heightColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        birthDateColumn.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        mailColumn.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<UserFx, UserFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(UserFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            userDieticianDao.removeUDConnection(item.getLogin(), DbConnector.getInstance().getLogin());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
    }

    private void refreshTable() {
        try {
            customersTable.setItems(userDao.getDieticiansCustomers(DbConnector.getInstance().getLogin()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
