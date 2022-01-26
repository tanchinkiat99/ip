import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

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

    @Override
    public String isDone() {
        return String.format("[D][%s] %s (by: %s)", super.done ? "X" : " ", super.task,
                this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
