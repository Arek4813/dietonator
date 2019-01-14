package controller.admin.users;

import database.dao.UserDao;
import database.model.User;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelfx.UserFx;
import utils.converter.UserConverter;
import utils.dialog.DialogUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserViewController {

    @FXML
    private TableView<UserFx> userTable;

    @FXML
    private TableColumn<UserFx, String> userLogin;

    @FXML
    private TableColumn<UserFx, String> userPassword;

    @FXML
    private TableColumn<UserFx, String> userName;

    @FXML
    private TableColumn<UserFx, String> userSurname;

    @FXML
    private TableColumn<UserFx, Float> userHeight;

    @FXML
    private TableColumn<UserFx, Float> userWeight;

    @FXML
    private TableColumn<UserFx, String> userMail;

    @FXML
    private TableColumn<UserFx, Date> userBirthDate;

    @FXML
    private TableColumn<UserFx, UserFx> deleteColumn;

    @FXML
    private TableColumn<UserFx, UserFx> editColumn;

    @FXML
    private TableColumn<UserFx, UserFx> dieticiansForUserColumn;

    @FXML
    private TableColumn<UserFx, UserFx> plansOfUser;

    private UserDao userDao;

    @FXML
    public void initialize() {
        userDao=new UserDao();
        userLogin.setCellValueFactory(cellData -> cellData.getValue().loginProperty());
        userName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        userSurname.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        userHeight.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
        userWeight.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        userBirthDate.setCellValueFactory(cellData -> cellData.getValue().birthDateProperty());
        userMail.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
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
                            userDao.deleteUser(item.getLogin());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                    });
                }
            }
        });
        editColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        editColumn.setCellFactory(cellData -> new TableCell<UserFx, UserFx>() {
            Button button = new Button("EDIT");
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
                        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/users/usersEditing.fxml" ));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        UserEditingController controller = loader.getController();
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
        dieticiansForUserColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        dieticiansForUserColumn.setCellFactory(cellData -> new TableCell<UserFx, UserFx>() {
            Button button = new Button("SHOW");
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
                        FXMLLoader loader =
                                new FXMLLoader(getClass().getResource( "/fxml/admin/users/usersDieticians.fxml" ));
                        Scene scene = null;
                        try {
                            scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        UserDieticiansController controller = loader.getController();
                        controller.showDieticiansOfUser(item);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality( Modality.APPLICATION_MODAL);
                        stage.showAndWait();
                        refreshTable();
                    });
                }
            }
        });
        plansOfUser.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));
        plansOfUser.setCellFactory(cellData -> new TableCell<UserFx, UserFx>() {
            Button button = new Button("SHOW DETAILS");
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
                            showPlansOfUser(item);
                        } catch (IOException e) {
                            DialogUtil.getInstance().errorDialog(e.getMessage());
                        }
                        refreshTable();
                    });
                }
            }
        });
        refreshTable();

    }

    private void showPlansOfUser(UserFx item) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/users/plansOfUser.fxml"));
        ((BorderPane)(this.userTable.getParent().getParent().getParent())).setCenter(loader.load());
        PlansOfUserController controller = loader.getController();
        controller.initializeUser(item);
    }

    public void refreshTable() {
        ObservableList<UserFx> data = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = userDao.getUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProductIntoTheTable(data, rs);
    }

    private void insertProductIntoTheTable(ObservableList<UserFx> data, ResultSet rs) {
        UserConverter converter = UserConverter.getInstance();

        try {
            while (rs.next()) {
                User user = new User();
                user.setUserLogin(rs.getString( "userLogin" ));
                user.setUserName(rs.getString( "userName" ));
                user.setUserSurname(rs.getString( "userSurname" ));
                user.setUserWeight(rs.getFloat( "userWeight" ));
                user.setUserHeight(rs.getFloat( "userHeight" ));
                user.setUserBirthDate(rs.getDate( "userBirthDate" ));
                user.setUserMail(rs.getString( "userMail" ));
                data.add(converter.convertToUserFx(user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userTable.setItems(data);
    }

}
