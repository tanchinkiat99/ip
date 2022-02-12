package tutu;

import java.io.IOException;

import tutu.data.Storage;
import tutu.data.TaskList;
import tutu.exception.InvalidInputException;
import tutu.task.Deadline;
import tutu.task.Event;
import tutu.task.ToDo;
import tutu.user.Parser;
import tutu.user.Ui;

/** Represents a todo list that takes in command inputs from the user. */
public class Tutu {
    /** TaskList object that stores the tasks added */
    private static TaskList taskList = new TaskList();

    private Ui ui = new Ui();

    public String getResponse(String input, Storage store) {
        String response = "";
        try {
            switch (Parser.checkFunction(input)) {
            case BYE:
                response = "Bye. Hope to see you again soon!";
                break;
            case LIST:
                response = store.list();
                break;
            case MARK:
                int i = Integer.parseInt(input.substring(5));
                taskList.mark(i);
                store.update(taskList);
                break;
            case UNMARK:
                int j = Integer.parseInt(input.substring(7));
                taskList.unmark(j);
                store.update(taskList);
                break;
            case TODO:
                ToDo t = new ToDo(input);
                response = taskList.add(t);
                store.update(taskList);
                break;
            case DEADLINE:
                Deadline dl = new Deadline(input);
                response = taskList.add(dl);
                store.update(taskList);
                break;
            case EVENT:
                Event e = new Event(input);
                response = taskList.add(e);
                store.update(taskList);
                break;
            case DELETE:
                response = taskList.delete(input);
                store.update(taskList);
                break;
            case FIND:
                response = taskList.find(input);
                break;
            default:
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidInputException | IOException e) {
            ui.displayError(e);
            return String.valueOf(e);
        }
        return response;
    }
}
