package tutu.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

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

    /**
     * Determines if task description contains keyword.
     * @param key Keyword input by user.
     * @return Boolean value indicating if task contains keyword.
     */
    public boolean isMatch(String key) {
        return this.task.contains(key);
    }

    /**
     * Gets date of task.
     * @return LocalDate object containing date of task.
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Gets time of task.
     * @return LocalTime object containing time of task.
     */
    public LocalTime getTime() {
        return null;
    }

    public static class TaskTimeComparator implements Comparator<Task> {
        @Override
        public int compare(Task t1, Task t2) {
            if (t1.getTime().isAfter(t2.getTime())) {
                return 1;
            } else if (t1.getTime().equals(t2.getTime())) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
