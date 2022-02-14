package tutu;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tutu.data.Storage;

/**
 * Controller for tutu.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tutu duke;

    private Storage store;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/tanjiro.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/zenitsu.png"));

    public MainWindow() {}

    /**
     * Initializes the chat box interface.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello! I'm Mr. Tutu!\nWhat can I do for you?", dukeImage)
        );
        this.store = new Storage("./data");
        try {
            File f = store.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDuke(Tutu d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input, store);
        if (response != "") {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
