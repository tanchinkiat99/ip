package tutu.task;

import tutu.exception.InvalidInputException;

/** Represents a ToDo task. */
public class ToDo extends Task {
    /**
     * Constructor to create a ToDo object
     * @param task Command input from user.
     * @throws InvalidInputException If input is invalid.
     */
    public ToDo(String task) throws InvalidInputException {
        super(task);
        if (super.task.length() < 6) {
            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            super.task = super.task.substring(5);
        }
    }

    /**
     * Returns a description of the task and whether it is done.
     * @return Description of task.
     */
    @Override
    public String isDone() {
        return String.format("[T][%s] %s", super.done ? "X" : " ", super.task);
    }
}
