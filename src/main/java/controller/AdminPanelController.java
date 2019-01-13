package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

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

    public void setCenter(String pathToCenterController) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathToCenterController));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }

}
