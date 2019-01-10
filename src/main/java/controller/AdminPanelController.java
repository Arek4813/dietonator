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
