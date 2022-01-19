public class Deadline extends Task {
    private String deadline;

    public Deadline(String task) {
        super(task);
        String[] ss = super.task.split(" /by ");
        super.task = ss[0];
        this.deadline = ss[1];
    }

    @Override
    public String isDone() {
        return String.format("[D][%s] %s (by: %s)", super.done ? "X" : " ", super.task, this.deadline);
    }
}
