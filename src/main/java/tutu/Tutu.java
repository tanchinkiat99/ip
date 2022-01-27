package tutu;

import java.io.File;
import java.io.IOException;
import tutu.data.Storage;
import tutu.data.TaskList;
import tutu.exception.InvalidInputException;
import tutu.task.*;
import tutu.user.Parser;
import tutu.user.Ui;

public class Tutu {
    private static TaskList taskList = new TaskList();

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
}
