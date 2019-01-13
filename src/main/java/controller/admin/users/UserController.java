package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class UserController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private void userAdding() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/users/usersAdding.fxml" ));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }

    @FXML
    private void userView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/admin/users/usersView.fxml" ));
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        borderPane.setCenter(parent);
    }
}
