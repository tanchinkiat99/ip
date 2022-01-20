public class Deadline extends Task {
    private String deadline;

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
                this.deadline = ss[1];
            }
        }
    }

    @Override
    public String isDone() {
        return String.format("[D][%s] %s (by: %s)", super.done ? "X" : " ", super.task, this.deadline);
    }
}
