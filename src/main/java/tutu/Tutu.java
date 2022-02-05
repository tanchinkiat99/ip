package tutu;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import tutu.data.Storage;
import tutu.data.TaskList;
import tutu.exception.InvalidInputException;
import tutu.task.*;
import tutu.user.Parser;
import tutu.user.Ui;

/** Represents a todo list that takes in command inputs from the user. */
public class Tutu extends Application {
    /** TaskList object that stores the tasks added */
    private static TaskList taskList = new TaskList();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/tanjiro.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/zenitsu.jpg"));

    /**
     * Prints a line of asterisks to separate between user inputs and resulting outputs.
     */
    public static void separator() {
        System.out.println("******************************************");
    }

    public static void main(String[] args) throws InvalidInputException, IOException {
        Storage store = new Storage("./data");
        File f = store.initialize();

        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();

        Ui ui = new Ui();
        String cmd = ui.read();
        while (!cmd.equals("bye")) {
            try {
                switch (Parser.checkFunction(cmd)) {
                    case LIST:
                        store.list();
                        break;
                    case MARK:
                        int i = Integer.parseInt(cmd.substring(5)); // throw NumberFormatException if not valid
                        if (i <= taskList.taskNumber()) {
                            taskList.mark(i);
                            store.update(taskList);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case UNMARK:
                        int j = Integer.parseInt(cmd.substring(7)); // throw NumberFormatException if not valid
                        if (j <= taskList.taskNumber()) {
                            taskList.unmark(j);
                            store.update(taskList);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case TODO:
                        ToDo t = new ToDo(cmd);
                        taskList.add(t);
                        store.update(taskList);
                        break;
                    case DEADLINE:
                        Deadline dl = new Deadline(cmd);
                        taskList.add(dl);
                        store.update(taskList);
                        break;
                    case EVENT:
                        Event e = new Event(cmd);
                        taskList.add(e);
                        store.update(taskList);
                        break;
                    case DELETE:
                        taskList.delete(cmd);
                        store.update(taskList);
                        break;
                    case FIND:
                        taskList.find(cmd);
                        break;
                    default:
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidInputException e) {
                ui.displayError(e);
            }
            cmd = ui.read();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Tutu");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing 's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label Text = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(Text, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Tutu heard: " + input;
    }
}
