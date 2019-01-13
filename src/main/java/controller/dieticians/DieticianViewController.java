package controller.dieticians;

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

public class DieticianViewController {

    @FXML
    private TableView<DieticianFx> dieticianTable;

    @FXML
    private TableColumn<DieticianFx, String> dieticianLogin;

    @FXML
    private TableColumn<DieticianFx, String> dieticianPassword;

    @FXML
    private TableColumn<DieticianFx, String> dieticianName;

    @FXML
    private TableColumn<DieticianFx, String> dieticianSurname;

    @FXML
    private TableColumn<DieticianFx, String> dieticianMail;

    @FXML
    private TableColumn<DieticianFx, Date> dieticianBirthDate;

    @FXML
    private TableColumn<DieticianFx, DieticianFx> deleteColumn;

    @FXML
    private TableColumn<DieticianFx, DieticianFx> editColumn;

    @FXML
    private TableColumn<DieticianFx, DieticianFx> usersForDieticianColumn;

    private DieticianDao dieticianDao;

    @FXML
    public void initialize() {
        dieticianDao = new DieticianDao();
        dieticianLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        dieticianName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        dieticianSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        dieticianBirthDate.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        dieticianMail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
        deleteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        deleteColumn.setCellFactory(cellData -> new TableCell<DieticianFx, DieticianFx>() {
            Button button = new Button("X");
            @Override
            protected void updateItem(DieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            dieticianDao.deleteDietician(item.getLogin());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<DieticianFx, DieticianFx>() {
            Button button = new Button("EDIT");
            @Override
            protected void updateItem(DieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource( "/fxml/admin/dieticians/dieticiansEditing.fxml" ));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        DieticianEditingController controller = loader.getController();
                        controller.initializeFields(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality( Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });
        usersForDieticianColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        usersForDieticianColumn.setCellFactory(cellData -> new TableCell<DieticianFx, DieticianFx>() {
            Button button = new Button("SHOW");
            @Override
            protected void updateItem(DieticianFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource( "/fxml/admin/dieticians/dieticiansUsers.fxml" ));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        DieticianUsersController controller = loader.getController();
                        controller.showUsersOfDietician(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality( Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });
        refreshTable();

    }

    public void refreshTable() {
        ObservableList<DieticianFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = dieticianDao.getDieticians();
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
