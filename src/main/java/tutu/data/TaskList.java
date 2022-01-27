package tutu.data;

import java.util.ArrayList;
import tutu.task.Task;
import tutu.exception.InvalidInputException;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<Task>();
    }

    public int taskNumber() {
        return items.size();
    }

    public Task retrieve(int i) {
        return items.get(i - 1);
    }

    public void add(Task task) {
        items.add(task);
        System.out.println("Got it. I've added this task:\n" + task.isDone());
        System.out.println(String.format("Now you have %d task%s in the list."
                , items.size(), items.size() == 1 ? "" : "s"));
    }

    public void delete(String cmd) throws InvalidInputException {
        if (cmd.length() < 8) {
            throw new InvalidInputException("☹ OOPS!!! Please include the task you want to delete.");
        } else {
            int i = Integer.parseInt(cmd.substring(7));
            if (i > items.size()) {
                throw new InvalidInputException("☹ OOPS!!! This task does not exist.");
            } else {
                System.out.println("Noted. I have removed this task:");
                System.out.println(items.get(i - 1).isDone());
                items.remove(i - 1);
                System.out.println(String.format("You now have %d task%s in the list."
                        , items.size(), items.size() == 1 ? "" : "s"));
            }
        }
    }

    public void mark(int i) {
        Task done = items.get(i - 1);
        done.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + done.isDone());
    }

    public void unmark(int i) {
        Task undone = items.get(i - 1);
        undone.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + undone.isDone());
    }
}
