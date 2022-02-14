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
}
