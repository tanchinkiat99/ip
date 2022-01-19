public class Task {
    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public void setDone() {
        this.done = true;
    }

    public void setNotDone() {
        this.done = false;
    }

    public String isDone() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.task);
    }

    public void added() {

    }
}
