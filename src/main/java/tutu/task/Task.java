package tutu.task;

public class Task {
    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
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

    public boolean isMatch(String key) {
        return this.task.contains(key);
    }
}
