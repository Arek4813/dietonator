package controller.admin.logdata;

import database.dao.LogDataDao;
import database.model.LogData;
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
import modelfx.LogDataFx;
import utils.converter.LogDataConverter;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogDataController {

    @FXML
    private TableView<LogDataFx> logDataTable;

    @FXML
    private TableColumn<LogDataFx, String> loginColumn;

    @FXML
    private TableColumn<LogDataFx, String> passwordColumn;

    @FXML
    private TableColumn<LogDataFx, String> roleColumn;

    @FXML
    private TableColumn<LogDataFx, LogDataFx> editColumn;

    private LogDataDao logDataDao;

    @FXML
    public void initialize() {
        logDataDao=new LogDataDao();
        loginColumn.setCellValueFactory(cellData -> cellData.getValue().loginValueProperty());
        passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordValueProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleEnumValueProperty());
        editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<LogDataFx, LogDataFx>() {
            Button button = new Button("EDIT");
            @Override
            protected void updateItem(LogDataFx item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource( "/fxml/admin/logdata/logDataEditing.fxml" ));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        LogDataEditingController controller = loader.getController();
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
        refreshTable();

    }

    public void refreshTable() {
        ObservableList<LogDataFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = logDataDao.getLogData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
    }

    private void insertProductIntoTheTable(ObservableList<LogDataFx> data, ResultSet rs) {
        LogDataConverter converter = LogDataConverter.getInstance();

        try {
            while (rs.next()) {
                LogData logData = new LogData();
                logData.setLoginValue(rs.getString( "loginValue" ));
                logData.setPasswordValue(rs.getString( "passwordValue" ));
                logData.setRoleEnumValue(rs.getString("role" ) );
                data.add(converter.convertToLogDataFx(logData));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logDataTable.setItems(data);
    }


}
