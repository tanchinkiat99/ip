public class ToDo extends Task {

    public ToDo(String task) throws InvalidInputException {
        super(task);
        if (super.task.length() < 6) {
            throw new InvalidInputException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else super.task = super.task.substring(5);
    }

    @Override
    public String isDone() {
        return String.format("[T][%s] %s", super.done ? "X" : " ", super.task);
    }
}
