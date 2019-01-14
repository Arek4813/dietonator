package controller.admin;

import database.DbConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import utils.dialog.DialogUtil;

import java.awt.*;
import java.io.IOException;

public class AdminPanelController {
    private static final String CATEGORIES = "/fxml/admin/category/categoryView.fxml";
    private static final String PRODUCTS = "/fxml/admin/product/product.fxml";
    private static final String MEAL = "/fxml/admin/meal/mealView.fxml";
    private static final String PLAN = "/fxml/admin/plan/planView.fxml";
    private static final String USERS = "/fxml/admin/users/users.fxml";
    private static final String DIETICIANS = "/fxml/admin/dieticians/dieticians.fxml";
    private static final String ADMINS = "/fxml/admin/admins/admins.fxml";
    private static final String LOGDATA = "/fxml/admin/logdata/logData.fxml";

    @FXML
    private BorderPane borderPane;

    @FXML
    public void showCategories() {
        setCenter(CATEGORIES);
    }

    @FXML
    public void showProducts() {
        setCenter(PRODUCTS);
    }

    @FXML
    public void showMeals() {
        setCenter(MEAL);
    }

    @FXML
    public void showPlans() {
        setCenter(PLAN);
    }

    @FXML
    public void showUsers() { setCenter(USERS);}

    @FXML
    public void showDieticians() { setCenter(DIETICIANS);}

    @FXML
    public void showAdmins() { setCenter(ADMINS);}

    @FXML
    public void showLogData() { setCenter(LOGDATA);}

    @FXML
    public void backup() {
        String backupFile = "dietonator.sql";
        String executedCommand = "mysqldump -u root -p password dietonator > " + backupFile;
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executedCommand);
            int processFinished = runtimeProcess.waitFor();
            if (processFinished == 0)
                return;
            else
                throw new IOException("Something went wrong");
        }
        catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        } catch (InterruptedException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    @FXML
    public void restore() {
        String backupFile = "dietonator.sql";
        String executedCommand = "mysql -u root -p password dietonator < " + backupFile;
        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executedCommand);
            int processFinished = runtimeProcess.waitFor();
            if (processFinished == 0)
                return;
            else
                throw new IOException("Something went wrong");
        }
        catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        } catch (InterruptedException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }

    @FXML
    public void logOut() {
        DbConnector.getInstance().closeConnection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loggingView/mainLoggingView.fxml"));
        Pane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        Scene userScene = new Scene(borderPane);
        Main.setScene(null);
        Main.setScene(userScene);
    }

    public void setCenter(String pathToCenterController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToCenterController));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
        borderPane.setCenter(parent);
    }

}
