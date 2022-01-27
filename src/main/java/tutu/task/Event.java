package tutu.task;

import tutu.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime event;
    private LocalDateTime end;

    public Event(String task) throws InvalidInputException {
        super(task);
        if (task.length() < 6) {
            throw new InvalidInputException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            String[] ss = super.task.split(" /at ");
            if (ss.length < 2) {
                throw new InvalidInputException("Please include the date and time of event!");
            } else {
                super.task = ss[0];

                String[] dateTime = ss[1].split(" ");

                LocalDate d = LocalDate.parse(dateTime[0]);
                LocalDateTime dt = d.atTime(Integer.parseInt(dateTime[1].substring(0, 2)),
                        Integer.parseInt(dateTime[1].substring(2)));
                this.event = dt;
                this.end = d.atTime(Integer.parseInt(dateTime[3].substring(0, 2)),
                        Integer.parseInt(dateTime[3].substring(2)));
            }
        }
    }

    @Override
    public String isDone() {
        return String.format("[E][%s] %s (at: %s, from %s to %s)", super.done ? "X" : " ", super.task,
                this.event.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")),
                this.event.format(DateTimeFormatter.ofPattern("h:mm a")),
                this.end.format(DateTimeFormatter.ofPattern("h:mm a")));
    }
}
