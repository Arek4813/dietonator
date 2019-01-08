package utils.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

public class DialogUtil {
    private static DialogUtil INSTANCE;

    private DialogUtil() {}

    public static DialogUtil getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DialogUtil();
        return INSTANCE;
    }

    public void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText("Something went wrong!");

        TextArea textArea = new TextArea(error);
        textArea.setEditable(false);
        errorAlert.getDialogPane().setContent(textArea);

        errorAlert.showAndWait();
    }
}
