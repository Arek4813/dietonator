package controller.dietician;

import database.DbConnector;
import database.dao.DietUserDao;
import database.model.DietUser;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelfx.DietUserFx;
import modelfx.UserDieticianFx;
import modelfx.UserFx;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerPlansController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<DietUserFx> planTable;

    @FXML
    private TableColumn<DietUserFx, String> planColumn;

    @FXML
    private TableColumn<DietUserFx, DietUserFx> deleteColumn;

    @FXML
    private TextField planTextField;

    private DietUserDao dietUserDao;

    private UserFx user;

    @FXML
    public void initialize() {
        dietUserDao = new DietUserDao();
        initializeTable();
        refreshTable();
    }

    @FXML
    public void addPlan() {
        String plan = planTextField.getText();
        if (!plan.isEmpty()) {
            try {
                dietUserDao.addPlanByDieticianToUser(user.getLogin(), plan, DbConnector.getInstance().getLogin());
            } catch (SQLException e) {
                DialogUtil.getInstance().errorDialog(e.getMessage());
            }
        }
        planTextField.clear();
    }

    @FXML
    public void goBackToCustomers() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dietician/customers.fxml"));
        try {
            ((BorderPane)vBox.getParent()).setCenter(loader.load());
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    public void setUser(UserDieticianFx item) {
        this.user = new UserFx();
        user.setLogin(item.getuLogin());
    }

    private void initializeTable() {
        planColumn.setCellValueFactory(cellData -> cellData.getValue().planProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<DietUserFx, DietUserFx>() {
            javafx.scene.control.Button button = new Button("X");
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
                            dietUserDao.deletePlanFromUser(user.getLogin(), item.getPlan());
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
            planTable.setItems(dietUserDao.selectPlansOfUser(user.getLogin()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
