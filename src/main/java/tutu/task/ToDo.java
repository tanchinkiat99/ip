package tutu.task;

import tutu.exception.InvalidInputException;

/** Represents a ToDo task. */
public class ToDo extends Task {
    /** Minimum size of input */
    private static final int MINIMUM_INPUT_LENGTH = 6;
    /** Error message for empty task description */
    private static final String EMPTY_INPUT_RESPONSE = "☹ OOPS!!! The description of a " +
            "todo cannot be empty.";

    /**
     * Constructor to create a ToDo object
     * @param task Command input from user.
     * @throws InvalidInputException If input is invalid.
     */
    public ToDo(String task) throws InvalidInputException {
        super(task);
        if (super.task.length() < MINIMUM_INPUT_LENGTH) {
            throw new InvalidInputException(EMPTY_INPUT_RESPONSE);
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
