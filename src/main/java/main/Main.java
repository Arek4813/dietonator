package main;

import database.DbConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage myStage;

    public static void main(String[] args) {
        launch( args );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        myStage=primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource( "/fxml/loggingView/mainLoggingView.fxml" ));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.show();
    }

    public static void setScene(Scene scene) {
        myStage.setScene(scene);
    }
}
