package controller.admin.users;


import database.dao.DietUserDao;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelfx.DietUserFx;
import modelfx.UserFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;


public class PlansOfUserController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<DietUserFx> plansOfUser;

    @FXML
    private TableColumn<DietUserFx, String> userColumn;

    @FXML
    private TableColumn<DietUserFx, String> planColumn;

    @FXML
    private TableColumn<DietUserFx, String> dieticianColumn;

    @FXML
    private TableColumn<DietUserFx, DietUserFx> deleteColumn;

    @FXML
    private TextField userTextField;

    @FXML
    private TextField dieticianTextField;

    @FXML
    private TextField planTextField;

    private DietUserDao dietUserDao;

    private UserFx userFx;

    @FXML
    public void initialize() {
        dietUserDao = new DietUserDao();
        initializeTable();
    }

    @FXML
    public void goBackToUsers() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/users/users.fxml"));
        try {
            ((BorderPane)vBox.getParent()).setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addPlanToUser() {
        if (allFieldsAreNotEmpty()) {
            try {
                dietUserDao.addPlanByDieticianToUser(userTextField.getText(), planTextField.getText(), dieticianTextField.getText());
                refreshTable();
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
        else if (userAndPlanAreNotEmpty()) {
            try {
                dietUserDao.addPlanToUser(userTextField.getText(), planTextField.getText());
                refreshTable();
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
        dieticianTextField.clear();
        planTextField.clear();
    }


    private void initializeTable() {
        userColumn.setCellValueFactory(cellData -> cellData.getValue().userProperty());
        planColumn.setCellValueFactory(cellData -> cellData.getValue().planProperty());
        dieticianColumn.setCellValueFactory(cellData -> cellData.getValue().dieticianProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<DietUserFx, DietUserFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(DietUserFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            dietUserDao.deletePlanFromUser(item.getUser(), item.getPlan());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
    }

    public void initializeUser(UserFx item) {
        this.userFx = item;
        refreshTable();
        userTextField.setText(userFx.getLogin());
        userTextField.setEditable(false);
    }

    private void refreshTable() {
        try {
            ObservableList<DietUserFx> mealsOfPlan = dietUserDao.selectPlansOfUser(userFx.getLogin());
            plansOfUser.setItems(mealsOfPlan);
        }
        catch (SQLException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    private boolean allFieldsAreNotEmpty() {
        return !(userTextField.getText().isEmpty() || planTextField.getText().isEmpty() || dieticianTextField.getText().isEmpty());
    }

    private boolean userAndPlanAreNotEmpty() {
        return !(userTextField.getText().isEmpty() || planTextField.getText().isEmpty());
    }
}
