package tutu;

import java.io.IOException;

import javafx.scene.image.Image;

import tutu.data.Storage;
import tutu.data.TaskList;
import tutu.exception.InvalidInputException;
import tutu.task.*;
import tutu.user.Parser;
import tutu.user.Ui;

/** Represents a todo list that takes in command inputs from the user. */
public class Tutu {
    /** TaskList object that stores the tasks added */
    private static TaskList taskList = new TaskList();

    private Image user = new Image(this.getClass().getResourceAsStream("/images/tanjiro.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/zenitsu.jpg"));
    private Ui ui = new Ui();

    /**
     * Prints a line of asterisks to separate between user inputs and resulting outputs.
     */
    public static void separator() {
        System.out.println("******************************************");
    }

    public static void main(String[] args) throws InvalidInputException, IOException {
//        Storage store = new Storage("./data");
//        File f = store.initialize();
//
//        Tutu.separator();
//        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
//        Tutu.separator();
//
//        Ui ui = new Ui();
//        String cmd = ui.read();
//        while (!cmd.equals("bye")) {
//            try {
//                switch (Parser.checkFunction(cmd)) {
//                    case LIST:
//                        store.list();
//                        break;
//                    case MARK:
//                        int i = Integer.parseInt(cmd.substring(5)); // throw NumberFormatException if not valid
//                        if (i <= taskList.taskNumber()) {
//                            taskList.mark(i);
//                            store.update(taskList);
//                        } else {
//                            System.out.println("Oops! Index is out of range");
//                        }
//                        break;
//                    case UNMARK:
//                        int j = Integer.parseInt(cmd.substring(7)); // throw NumberFormatException if not valid
//                        if (j <= taskList.taskNumber()) {
//                            taskList.unmark(j);
//                            store.update(taskList);
//                        } else {
//                            System.out.println("Oops! Index is out of range");
//                        }
//                        break;
//                    case TODO:
//                        ToDo t = new ToDo(cmd);
//                        taskList.add(t);
//                        store.update(taskList);
//                        break;
//                    case DEADLINE:
//                        Deadline dl = new Deadline(cmd);
//                        taskList.add(dl);
//                        store.update(taskList);
//                        break;
//                    case EVENT:
//                        Event e = new Event(cmd);
//                        taskList.add(e);
//                        store.update(taskList);
//                        break;
//                    case DELETE:
//                        taskList.delete(cmd);
//                        store.update(taskList);
//                        break;
//                    case FIND:
//                        taskList.find(cmd);
//                        break;
//                    default:
//                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                }
//            } catch (InvalidInputException e) {
//                ui.displayError(e);
//            }
//            cmd = ui.read();
//        }
//        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getResponse(String input, Storage store) {
        if (!input.equals("bye")) {
            String response = "";
            try {
                switch (Parser.checkFunction(input)) {
                    case LIST:
                        response = store.guiList();
                        break;
                    case MARK:
                        int i = Integer.parseInt(input.substring(5)); // throw NumberFormatException if not valid
                        if (i <= taskList.taskNumber()) {
                            response = taskList.guiMark(i);
                            store.update(taskList);
                        } else {
                            response = "Oops! Index is out of range";
                        }
                        break;
                    case UNMARK:
                        int j = Integer.parseInt(input.substring(7)); // throw NumberFormatException if not valid
                        if (j <= taskList.taskNumber()) {
                            response = taskList.guiUnmark(j);
                            store.update(taskList);
                        } else {
                            response = "Oops! Index is out of range";
                        }
                        break;
                    case TODO:
                        ToDo t = new ToDo(input);
                        response = taskList.guiAdd(t);
                        store.update(taskList);
                        break;
                    case DEADLINE:
                        Deadline dl = new Deadline(input);
                        response = taskList.guiAdd(dl);
                        store.update(taskList);
                        break;
                    case EVENT:
                        Event e = new Event(input);
                        response = taskList.guiAdd(e);
                        store.update(taskList);
                        break;
                    case DELETE:
                        response = taskList.guiDelete(input);
                        store.update(taskList);
                        break;
                    case FIND:
                        response = taskList.guiFind(input);
                        break;
                    default:
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidInputException | IOException e) {
                ui.displayError(e);
            }
            return response;
        } else {
            return "Bye. Hope to see you again soon!";
        }
    }
}
