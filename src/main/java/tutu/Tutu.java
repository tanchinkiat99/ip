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

    /** Response message for invalid user input */
    private static final String INVALID_INPUT = "OOPS! I'm sorry, but I don't know what that means";

    public String getResponse(String input, Storage store) {
        String response = "";
        try {
            switch (Parser.checkFunction(input)) {
            case BYE:
                Ui.exit();
                break;
            case LIST:
                response = store.list();
                break;
            case MARK:
                int i = Integer.parseInt(input.substring(5));
                response = taskList.mark(i);
                store.update(taskList);
                break;
            case UNMARK:
                int j = Integer.parseInt(input.substring(7));
                response = taskList.unmark(j);
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
            case SCHEDULE:
                response = taskList.getScheduleFor(input);
                break;
            default:
                throw new InvalidInputException(INVALID_INPUT);
            }
        } catch (InvalidInputException | IOException | InterruptedException e) {
            Ui.showErrorAlert(e);
        }
        return response;
    }
}
