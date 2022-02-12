package tutu.data;

import java.util.ArrayList;

import tutu.exception.InvalidInputException;
import tutu.task.Task;

/** Represents a TaskList object to store the tasks. */
public class TaskList {
    /** Stores the tasks added as Task objects in an ArrayList object. */
    private ArrayList<Task> items;

    /** Minimum size of input for delete function */
    private static final int MINIMUM_DELETE_INPUT_LENGTH = 8;
    /** Minimum size of input for find function */
    private static final int MINIMUM_FIND_INPUT_LENGTH = 6;
    /** Response message for out of range index input */
    private static final String OUT_OF_RANGE = "Oops! Index is out of range";
    /** Response message for empty delete input */
    private static final String EMPTY_DELETE_INPUT = "☹ OOPS!!! Please include the task you want to delete.";
    /** Response message for empty find input */
    private static final String EMPTY_FIND_INPUT = "Please input a keyword!";
    /** Response message for 0 matches for find command */
    private static final String NO_TASKS_FOUND = "No matching tasks were found...";

    /**
     * Constructor to create a TaskList Object.
     */
    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the TaskList object.
     * @return Number of tasks.
     */
    public int taskNumber() {
        return items.size();
    }

    /**
     * Retrieves the task at a specified index.
     * @param i Index of task to be retrieved.
     * @return Task object representing the task at index i.
     */
    public Task retrieve(int i) {
        return items.get(i - 1);
    }

    /**
     * Adds a task to be stored in the TaskList object.
     * @param task Command input from user with description of task to be added.
     * @return String containing added task.
     */
    public String add(Task task) {
        items.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task%s in the list.",
                task.isDone(), items.size(), items.size() == 1 ? "" : "s");
    }

    /**
     * Removes a specified task from the TaskList object.
     * @param cmd Command input from user specifying index of task to be removed.
     * @return String containing deleted task.
     * @throws InvalidInputException If the index input from user is invalid.
     */
    public String delete(String cmd) throws InvalidInputException {
        if (cmd.length() < MINIMUM_DELETE_INPUT_LENGTH) {
            throw new InvalidInputException(EMPTY_DELETE_INPUT);
        }
        int i = Integer.parseInt(cmd.substring(7));
        if (i > items.size()) {
            throw new InvalidInputException(OUT_OF_RANGE);
        }
        String deleted = items.get(i - 1).isDone();
        items.remove(i - 1);
        return String.format("Noted. I have removed this task:\n%sYou now have %d task%s in the list.",
                deleted, items.size(), items.size() == 1 ? "" : "s");
    }

    /**
     * Marks a task as done.
     * @param i Index of task that user wants to mark as done.
     * @return String containing marked task.
     */
    public String mark(int i) {
        if (i > taskNumber()) {
            return OUT_OF_RANGE;
        }
        Task done = items.get(i - 1);
        done.setDone();
        return "Nice! I've marked this task as done:\n" + done.isDone();
    }

    /**
     * Marks a task as not done.
     * @param i Index of task that user wants to mark as not done.
     * @return String containing unmarked task.
     */
    public String unmark(int i) {
        if (i > taskNumber()) {
            return OUT_OF_RANGE;
        }
        Task undone = items.get(i - 1);
        undone.setNotDone();
        return "OK, I've marked this task as not done yet:\n" + undone.isDone();
    }

    /**
     * Finds the tasks matching a keyword.
     * @param cmd Command input by user containing keyword to match tasks.
     * @return String containing matching tasks.
     * @throws InvalidInputException If user input is invalid.
     */
    public String find(String cmd) throws InvalidInputException {
        if (cmd.length() < MINIMUM_FIND_INPUT_LENGTH) {
            throw new InvalidInputException(EMPTY_FIND_INPUT);
        }
        String key = cmd.substring(5);
        ArrayList<String> matches = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).isMatch(key)) {
                int index = i + 1;
                matches.add(index + ". " + items.get(i).isDone());
            }
        }
        if (matches.size() == 0) {
            return NO_TASKS_FOUND;
        } else {
            String list = "Here are the matching tasks in your list:";
            for (int j = 0; j < matches.size(); j++) {
                list += ("\n" + matches.get(j));
            }
            return list;
        }
    }
}
