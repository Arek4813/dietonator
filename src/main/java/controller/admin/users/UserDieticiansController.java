package controller.admin.users;

import database.dao.UserDieticianDao;
import database.model.UserDietician;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import modelfx.UserDieticianFx;
import modelfx.UserFx;
import utils.dialog.DialogUtil;

import java.sql.SQLException;

public class UserDieticiansController {

    @FXML
    private VBox vBox;

    @FXML
    private TableView<UserDieticianFx> UDTable;

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

    private UserFx userFx;

    private UserDieticianDao userDieticianDao;

    @FXML
    public void initialize() {
        userDieticianDao = new UserDieticianDao();
        uLoginColumn.setCellValueFactory( cellData -> cellData.getValue().uLoginProperty());
        dLoginColumn.setCellValueFactory( cellData -> cellData.getValue().dLoginProperty() );
        initializeTable();
    }

    @FXML
    public void addUD() {
        try {
            UserDietician userDietician = new UserDietician();
            userDietician.setuLogin(uLoginTextField.getText());
            userDietician.setdLogin(dLoginTextField.getText());
            userDieticianDao.addUDConnection(uLoginTextField.getText(), dLoginTextField.getText());
            refreshTable();
            //uLoginTextField.clear();
            dLoginTextField.clear();
        }
        catch (SQLException | NumberFormatException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        refreshTable();
        //uLoginTextField.clear();
        dLoginTextField.clear();
    }

   /* public void initializeFields() {
        uLoginTextField.setText();
        dLoginTextField.setText();
    }*/

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
            ObservableList<UserDieticianFx> products = userDieticianDao.getUsersAndDieticiansForUser(userFx.getLogin());
            UDTable.setItems(products);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showDieticiansOfUser(UserFx item) {
        userFx = item;
        uLoginTextField.setText( item.getLogin() );
        uLoginTextField.setEditable( false );
        refreshTable();
    }



}
