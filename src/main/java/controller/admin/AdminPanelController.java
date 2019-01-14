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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String executedCommand = "mysqldump -u *** -p*** dietonator > " + backupFile;
        executeBashCommand(executedCommand);
    }

    @FXML
    public void restore() {
        String backupFile = "dietonator.sql";
        String executedCommand = "mysql -u *** -p*** dietonator < " + backupFile;
        executeBashCommand(executedCommand);
    }

    public boolean executeBashCommand(String command) {
        boolean success = false;
        System.out.println("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }

            b.close();
            success = true;
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return success;
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
