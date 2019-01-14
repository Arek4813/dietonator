package controller.admin.admins;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminsContoller {

    @FXML
    private BorderPane borderPane;

    @FXML
    private void adminAdding() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/admins/adminsAdding.fxml" ));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/admins/adminsView.fxml" ));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
