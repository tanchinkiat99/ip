public class Event extends Task {
    private String event;

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
                this.event = ss[1];
            }
        }
    }

    @Override
    public String isDone() {
        return String.format("[E][%s] %s (at: %s)", super.done ? "X" : " ", super.task, this.event);
    }
}
