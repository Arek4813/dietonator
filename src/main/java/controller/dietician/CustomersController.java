package controller.dietician;

import database.DbConnector;
import database.dao.DietUserDao;
import database.dao.UserDao;
import database.dao.UserDieticianDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelfx.DietUserFx;
import modelfx.UserDieticianFx;
import modelfx.UserFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CustomersController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<UserDieticianFx> customersTable;

    @FXML
    private TableColumn<UserDieticianFx, String> loginColumn;

    @FXML
    private TableColumn<UserDieticianFx, UserDieticianFx> viewProfileColumn;

    @FXML
    private TableColumn<UserDieticianFx, UserDieticianFx> deleteColumn;

    @FXML
    private TableColumn<UserDieticianFx, UserDieticianFx> plansColumn;

    @FXML
    private TextField customerTextField;

    private UserDao userDao;

    private DietUserDao dietUserDao;

    private UserDieticianDao userDieticianDao;

    @FXML
    public void initialize() {
        userDao = new UserDao();
        userDieticianDao = new UserDieticianDao();
        dietUserDao = new DietUserDao();
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
        customerTextField.clear();
        refreshTable();
    }

    public void initializeColumns() {
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().uLoginProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<UserDieticianFx, UserDieticianFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(UserDieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            userDieticianDao.removeUDConnection(item.getuLogin(), DbConnector.getInstance().getLogin());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        viewProfileColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        viewProfileColumn.setCellFactory(cellData -> new TableCell<UserDieticianFx, UserDieticianFx>() {
            Button button = new Button("VIEW PROFILE");
            @Override
            protected void updateItem(UserDieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/customerProfile.fxml"));
                        try {
                            ((BorderPane)vBox.getParent()).setCenter(loader.load());
                            CustomerProfileController controller = loader.getController();
                            controller.setUser(item);
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });

        plansColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        plansColumn.setCellFactory(cellData -> new TableCell<UserDieticianFx, UserDieticianFx>() {
            Button button = new Button("VIEW PLANS");
            @Override
            protected void updateItem(UserDieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/customerPlans.fxml"));
                        try {
                            ((BorderPane)vBox.getParent()).setCenter(loader.load());
                            CustomerPlansController controller = loader.getController();
                            controller.setUser(item);
                        } catch (IOException e) {
                            e.printStackTrace();
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });
    }

    private void refreshTable() {
        try {
            customersTable.setItems(userDieticianDao.getUsersAndDieticiansForDietician(DbConnector.getInstance().getLogin()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
