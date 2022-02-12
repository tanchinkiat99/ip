package tutu.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tutu.exception.InvalidInputException;

/** Represents a Deadline task. */
public class Deadline extends Task {
    /** Date and time of deadline of task */
    private LocalDateTime deadline;

    /** Minimum size of input */
    private static final int MINIMUM_INPUT_LENGTH = 10;
    /** Error message for empty task description */
    private static final String EMPTY_INPUT_RESPONSE = "☹ OOPS!!! The description of a " +
            "deadline cannot be empty.";
    /** Error message for missing deadline */
    private static final String DEADLINE_MISSING = "Please include the deadline!";

    /**
     * Constructor to create a Deadline object.
     * @param task Command input from user.
     * @throws InvalidInputException If input is invalid.
     */
    public Deadline(String task) throws InvalidInputException {
        super(task);
        if (super.task.length() < MINIMUM_INPUT_LENGTH) {
            throw new InvalidInputException(EMPTY_INPUT_RESPONSE);
        }
        String[] ss = super.task.split(" /by ");
        if (ss.length < 2) {
            throw new InvalidInputException(DEADLINE_MISSING);
        }
        super.task = ss[0];

        // Create a String array with String values of date and time as elements
        String[] dateTime = ss[1].split(" ");

        // Store the date
        LocalDate d = LocalDate.parse(dateTime[0]);

        // Parse and store the time
        int hour = Integer.parseInt(dateTime[1].substring(0, 2));
        int minutes = Integer.parseInt(dateTime[1].substring(2));
        LocalDateTime dt = d.atTime(hour, minutes);
        this.deadline = dt;
    }

    /**
     * Returns a description of the task, whether it is done and the deadline.
     * @return Description of task and deadline.
     */
    @Override
    public String isDone() {
        return String.format("[D][%s] %s (by: %s)", super.done ? "X" : " ", super.task,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
