package tutu.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import tutu.exception.InvalidInputException;
import tutu.task.Task;

/** Represents a TaskList object to store the tasks. */
public class TaskList {
    /** Minimum size of input for delete function */
    private static final int MINIMUM_DELETE_INPUT_LENGTH = 8;
    /** Minimum size of input for find function */
    private static final int MINIMUM_FIND_INPUT_LENGTH = 6;
    /** Response message for out of range index input */
    private static final String OUT_OF_RANGE = "Oops! Index is out of range";
    /** Response message for empty delete input */
    private static final String EMPTY_DELETE_INPUT = "OOPS!!! Please include the task you want to delete.";
    /** Response message for empty find input */
    private static final String EMPTY_FIND_INPUT = "Please input a keyword!";
    /** Response message for 0 matches for find command */
    private static final String NO_TASKS_FOUND = "No matching tasks were found...";
    /** Response message for 0 matches for find command */
    private static final String INVALID_SCHEDULE_FORMAT = "Please follow the format: schedule for YYYY-MM-DD";
    /** Response message for 0 matches for empty schedule */
    private static final String EMPTY_SCHEDULE = "You have no tasks on this day! Take a break!";

    /** Stores the tasks added as Task objects in an ArrayList object. */
    private ArrayList<Task> items;

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
        assert (i > 0);
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
        return String.format("Noted. I have removed this task: %s\nYou now have %d task%s in the list.",
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

    /**
     * Check if date of task matches the input date.
     * @param task Task object to check.
     * @param date Date to match with date of task.
     * @return boolean value showing if date of task matches input date.
     */
    public boolean dateMatches(Task task, LocalDate date) {
        if (task.getDate() == null) {
            return false;
        }
        return task.getDate().isEqual(date);
    }

    /**
     * Gets the schedule for a specified date.
     * @param cmd Input command by user containing specified date.
     * @return Schedule containing tasks with specified date.
     */
    public String getScheduleFor(String cmd) {
        // Parse input command in the form of "schedule for yyyy-MM-DD"
        String[] inputs = cmd.split(" ");

        // If input command does not follow format
        if (inputs.length != 3) {
            return INVALID_SCHEDULE_FORMAT;
        }

        // Parse date and creating ArrayList of tasks with that date
        LocalDate key = LocalDate.parse(inputs[2]);
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (dateMatches(items.get(i), key)) {
                matches.add(items.get(i));
            }
        }

        // If no tasks on that date
        if (matches.size() == 0) {
            return EMPTY_SCHEDULE;
        }
        String schedule = String.format("Here is your schedule for %s:\n", key.toString());
        Collections.sort(matches, new Task.TaskTimeComparator());
        for (int j = 0; j < matches.size(); j++) {
            schedule += String.format("%s: %s\n", matches.get(j).getTime(), matches.get(j).isDone());
        }
        return schedule;
    }
}
