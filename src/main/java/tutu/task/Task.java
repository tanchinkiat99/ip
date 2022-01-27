package tutu.task;

/** Represents a task. */
public class Task {
    /** Description of task */
    protected String task;
    /** Boolean to represent if the task is done */
    protected boolean done;

    /**
     * Constructor to create a Task object.
     * @param task String command input from user.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.done = false;
    }

    /**
     * Returns a description of the task and showing if it is done.
     * @return Description of task.
     */
    public String isDone() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.task);
    }

}
