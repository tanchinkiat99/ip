package tutu.exception;

import javafx.scene.control.Alert;

/** Represents an Exception that is thrown when the user input is invalid. */
public class InvalidInputException extends Exception {
    /**
     * Constructor to create an InvalidInputException object.
     * @param message Command input from user.
     */
    public InvalidInputException(String message) {
        super(message);
    }

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
}
