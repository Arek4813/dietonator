package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ProductController {

    @FXML
    private BorderPane borderPane;

    @FXML
    public void productAdding() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/productAdding.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }

    @FXML
    public void productView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/productView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
