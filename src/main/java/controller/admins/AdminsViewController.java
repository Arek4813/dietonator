package controller.admins;

import database.dao.AdminsDao;
import database.model.Admin;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelfx.AdminFx;
import utils.converter.AdminsConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminsViewController {

    @FXML
    private TableView<AdminFx> adminTable;

    @FXML
    private TableColumn<AdminFx, String> adminLogin;

    @FXML
    private TableColumn<AdminFx, AdminFx> deleteColumn;

    private AdminsDao adminsDao;

    @FXML
    public void initialize() {
        adminsDao=new AdminsDao();
        adminLogin.setCellValueFactory(cellData -> cellData.getValue().adminLoginProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<AdminFx, AdminFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(AdminFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            adminsDao.deleteAdmin(item.getAdminLogin());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        refreshTable();
    }


    public void refreshTable() {
        ObservableList<AdminFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = adminsDao.getAdmins();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
    }

    private void insertProductIntoTheTable(ObservableList<AdminFx> data, ResultSet rs) {
        AdminsConverter converter = AdminsConverter.getInstance();

        try {
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminLogin(rs.getString( "adminLogin" ));
                data.add(converter.convertToAdminFx(admin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adminTable.setItems(data);
    }
}
