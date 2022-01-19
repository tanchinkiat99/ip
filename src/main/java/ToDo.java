public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String isDone() {
        return String.format("[T][%s] %s", super.done ? "X" : " ", super.task);
    }
}
