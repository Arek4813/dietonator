package controller.admin.product;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ProductController {

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {
        productView();
    }

    @FXML
    public void productAdding() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/product/productAdding.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin/product/productView.fxml"));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
