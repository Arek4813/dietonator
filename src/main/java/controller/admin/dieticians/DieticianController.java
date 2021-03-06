package controller.admin.dieticians;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DieticianController {

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize() {
        adminView();
    }

    @FXML
    private void dieticianAdding() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/dieticians/dieticiansAdding.fxml" ));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }

    @FXML
    private void adminView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/dieticians/dieticiansView.fxml" ));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
