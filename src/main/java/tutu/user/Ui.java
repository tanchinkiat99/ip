package tutu.user;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * To handle user interactions.
 */
public class Ui {
    /**
     * Shows an alert message explaining error.
     * @param e Exception thrown due to invalid input.
     */
    public static void showErrorAlert(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Your input is invalid!");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    /**
     * Exits the application.
     */
    public static void exit() throws InterruptedException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit Mr. Tutu?");
        alert.setContentText("Click \'OK\' to proceed!");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

}
