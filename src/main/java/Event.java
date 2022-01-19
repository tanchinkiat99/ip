public class Event extends Task {
    private String event;

    public Event(String task) {
        super(task);
        String[] ss = super.task.split(" /at ");
        super.task = ss[0];
        this.event = ss[1];
    }

    @Override
    public String isDone() {
        return String.format("[E][%s] %s (at: %s)", super.done ? "X" : " ", super.task, this.event);
    }
}
