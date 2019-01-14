package controller.admin.dieticians;

import database.dao.UserDieticianDao;
import database.model.UserDietician;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelfx.DieticianFx;
import modelfx.UserDieticianFx;
import utils.dialog.DialogUtil;

import java.sql.SQLException;

public class DieticianUsersController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<UserDieticianFx> DUTable;

    @FXML
    private TableColumn<UserDieticianFx, String> uLoginColumn;

    @FXML
    private TableColumn<UserDieticianFx, String> dLoginColumn;

    @FXML
    private TableColumn<UserDieticianFx, UserDieticianFx> deleteColumn;

    @FXML
    private TextField uLoginTextField;

    @FXML
    private TextField dLoginTextField;

    private DieticianFx dieticianFx;

    private UserDieticianDao userDieticianDao;

    @FXML
    public void initialize() {
        userDieticianDao = new UserDieticianDao();
        uLoginColumn.setCellValueFactory( cellData -> cellData.getValue().uLoginProperty());
        dLoginColumn.setCellValueFactory( cellData -> cellData.getValue().dLoginProperty() );
        initializeTable();
    }

    @FXML
    public void addDU() {
        try {
            UserDietician userDietician = new UserDietician();
            userDietician.setuLogin(uLoginTextField.getText());
            userDietician.setdLogin(dLoginTextField.getText());
            userDieticianDao.addUDConnection(uLoginTextField.getText(), dLoginTextField.getText());
            refreshTable();
            uLoginTextField.clear();
            //dLoginTextField.clear();
        }
        catch (SQLException | NumberFormatException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        refreshTable();
        uLoginTextField.clear();
       // dLoginTextField.clear();
    }

    private void initializeTable() {
        uLoginColumn.setCellValueFactory(cellData -> cellData.getValue().uLoginProperty());
        dLoginColumn.setCellValueFactory(cellData -> cellData.getValue().dLoginProperty());
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
                            userDieticianDao.removeUDConnection(item.getuLogin(), item.getdLogin());
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
            ObservableList<UserDieticianFx> products = userDieticianDao.getUsersAndDieticiansForDietician(dieticianFx.getLogin());
            DUTable.setItems(products);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showUsersOfDietician(DieticianFx item) {
        dieticianFx = item;
        dLoginTextField.setText( item.getLogin() );
        dLoginTextField.setEditable( false );
        refreshTable();
    }
}
