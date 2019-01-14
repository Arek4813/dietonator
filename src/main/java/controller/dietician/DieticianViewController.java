package controller.dietician;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
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

    private void show(String directory) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(directory));
        try {
            borderPane.setCenter(loader.load());
        } catch (IOException e) {
            DialogUtil.getInstance().errorDialog(e.getMessage());
        }
    }
}
