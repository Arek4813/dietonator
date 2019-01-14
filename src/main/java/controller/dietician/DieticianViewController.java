package controller.dietician;

import database.DbConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import utils.dialog.DialogUtil;

import java.io.IOException;

public class DieticianViewController {
    private static String PROFILE = "/fxml/dietician/profile.fxml";
    private static String CUSTOMERS = "/fxml/dietician/customers.fxml";
    private static String CATEGORIES = "/fxml/dietician/categories.fxml";
    private static String PRODUCTS = "/fxml/dietician/products.fxml";
    private static String MEALS = "/fxml/dietician/meals.fxml";
    private static String PLANS = "/fxml/dietician/plans.fxml";

    @FXML
    public void initialize() {
        show(PROFILE);
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    public void showMyProfile() {
        show(PROFILE);
    }

    @FXML
    public void showMyCustomers() {
        show(CUSTOMERS);
    }

    @FXML
    public void showCategories() {
        show(CATEGORIES);
    }

    @FXML
    public void showProducts() {
        show(PRODUCTS);
    }

    @FXML
    public void showMeals() {
        show(MEALS);
    }

    @FXML
    public void showNutritionalPlans() {
        show(PLANS);
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

    private void show(String directory) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(directory));
        try {
            borderPane.setCenter(loader.load());
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }
}
