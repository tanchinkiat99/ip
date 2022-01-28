package tutu.data;

import java.util.ArrayList;
import tutu.task.Task;
import tutu.exception.InvalidInputException;

/** Represents a TaskList object to store the tasks. */
public class TaskList {
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
        return items.get(i - 1);
    }

    /**
     * Adds a task to be stored in the TaskList object.
     * @param task Command input from user with description of task to be added.
     */
    public void add(Task task) {
        items.add(task);
        System.out.println("Got it. I've added this task:\n" + task.isDone());
        System.out.println(String.format("Now you have %d task%s in the list."
                , items.size(), items.size() == 1 ? "" : "s"));
    }

    /**
     * Removes a specified task from the TaskList object.
     * @param cmd Command input from user specifying index of task to be removed.
     * @throws InvalidInputException If the index input from user is invalid.
     */
    public void delete(String cmd) throws InvalidInputException {
        if (cmd.length() < 8) {
            throw new InvalidInputException("☹ OOPS!!! Please include the task you want to delete.");
        } else {
            int i = Integer.parseInt(cmd.substring(7));
            if (i > items.size()) {
                throw new InvalidInputException("☹ OOPS!!! This task does not exist.");
            } else {
                System.out.println("Noted. I have removed this task:");
                System.out.println(items.get(i - 1).isDone());
                items.remove(i - 1);
                System.out.println(String.format("You now have %d task%s in the list."
                        , items.size(), items.size() == 1 ? "" : "s"));
            }
        }
    }

    /**
     * Marks a task as done.
     * @param i Index of task that user wants to mark as done.
     */
    public void mark(int i) {
        Task done = items.get(i - 1);
        done.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + done.isDone());
    }

    /**
     * Marks a task as not done.
     * @param i Index of task that user wants to mark as not done.
     */
    public void unmark(int i) {
        Task undone = items.get(i - 1);
        undone.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + undone.isDone());
    }

    /**
     * Finds the tasks matching a keyword.
     * @param cmd Command input by user containing keyword to match tasks.
     * @throws InvalidInputException If user input is invalid.
     */
    public void find(String cmd) throws InvalidInputException {
        if (cmd.length() < 6) {
            throw new InvalidInputException("Please input a keyword!");
        } else {
            String key = cmd.substring(5);
            ArrayList<String> matches = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).isMatch(key)) {
                    int index = i + 1;
                    matches.add(index + ". " + items.get(i).isDone());
                }
            }
            if (matches.size() == 0) {
                System.out.println("No matching tasks were found...");
            } else {
                System.out.println("Here are the matching tasks in your list:");
                for (int j = 0; j < matches.size(); j++) {
                    System.out.println(matches.get(j));
                }
            }
        }
    }
}
