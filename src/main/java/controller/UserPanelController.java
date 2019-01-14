package controller;

import database.DbConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import main.Main;
import utils.dialog.DialogUtil;

import java.io.IOException;

public class UserPanelController {

    private static final String CATEGORIES = "/fxml/user/category/categoryView.fxml";
    private static final String PRODUCTS = "/fxml/user/product/productView.fxml";
    private static final String MEAL = "/fxml/user/meal/mealView.fxml";
    private static final String PLAN = "/fxml/user/nutrPlan/planView.fxml";
    private static final String DIETICIANS = "/fxml/user/dieticians/myDieticiansView.fxml";
    private static final String PROFILE = "/fxml/user/profile/myProfileView.fxml";


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
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
