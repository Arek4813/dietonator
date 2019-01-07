import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch( args );
    }

    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene();
        primaryStage.setScene( scene );
        primaryStage.setTitle( "ELO" );
        primaryStage.show();
    }
}
