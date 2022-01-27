package tutu.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import tutu.exception.InvalidInputException;

/** Represents a Deadline task. */
public class Deadline extends Task {
    /** Date and time of deadline of task */
    private LocalDateTime deadline;

    /**
     * Constructor to create a Deadline object.
     * @param task Command input from user.
     * @throws InvalidInputException If input is invalid.
     */
    public Deadline(String task) throws InvalidInputException {
        super(task);
        if (super.task.length() < 10) {
            throw new InvalidInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String[] ss = super.task.split(" /by ");
            if (ss.length < 2) {
                throw new InvalidInputException("Please include the deadline!");
            } else {
                super.task = ss[0];

                String[] dateTime = ss[1].split(" ");

                LocalDate d = LocalDate.parse(dateTime[0]);
                LocalDateTime dt = d.atTime(Integer.parseInt(dateTime[1].substring(0, 2)),
                        Integer.parseInt(dateTime[1].substring(2)));
                this.deadline = dt;
            }
        }
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
