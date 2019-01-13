package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UserPanelController {

    private static final String CATEGORIES = "/fxml/user/categoryView.fxml";
    private static final String PRODUCTS = "/fxml/user/productView.fxml";
    private static final String MEAL = "/fxml/user/mealView.fxml";
    private static final String PLAN = "/fxml/user/nutrPlanView.fxml";
    private static final String DIETICIANS = "/fxml/user/myDieticiansView.fxml";
    private static final String PROFILE = "/fxm/user/myProfileView.fxml";


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
    public void showDieticians() { setCenter(DIETICIANS);}

    @FXML
    public void showMyProfile() { setCenter( PROFILE );}

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
