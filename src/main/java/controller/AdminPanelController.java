package controller;

import database.dao.CategoryDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminPanelController {
    private static final String CATEGORIES = "/fxml/admin/categoryView.fxml";

    @FXML
    private BorderPane borderPane;

    @FXML
    public void showCategories() {
        setCenter(CATEGORIES);
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
