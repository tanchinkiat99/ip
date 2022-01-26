import java.io.File;
import java.io.IOException;
import java.util.*;

public class Tutu {
    private static ArrayList<Task> items = new ArrayList<>();

    public enum Function {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static void separator() {
        System.out.println("******************************************");
    }

    public static void add(Task task) {
        items.add(task);
        System.out.println("Got it. I've added this task:\n" + task.isDone());
        System.out.println(String.format("Now you have %d task%s in the list."
                , items.size(), items.size() == 1 ? "" : "s"));
        Tutu.separator();
    }

    public static void mark(int i) {
        Task done = items.get(i - 1);
        done.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + done.isDone());
        separator();
    }

    public static void unmark(int i) {
        Task undone = items.get(i - 1);
        undone.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n" + undone.isDone());
        separator();
    }

    public static void delete(String cmd) throws InvalidInputException {
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

    public static void main(String[] args) throws InvalidInputException, IOException {
        Storage store = new Storage("./data");
        File f = store.initialize();

        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();

        Ui ui = new Ui();
        String cmd = ui.read();
        while (!cmd.equals("bye")) {
            try {
                switch (Parser.checkFunction(cmd)) {
                    case LIST:
                        store.list();
                        break;
                    case MARK:
                        int i = Integer.parseInt(cmd.substring(5)); // throw NumberFormatException if not valid
                        if (i <= items.size()) {
                            mark(i);
                            store.update(items);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case UNMARK:
                        int j = Integer.parseInt(cmd.substring(7)); // throw NumberFormatException if not valid
                        if (j <= items.size()) {
                            unmark(j);
                            store.update(items);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case TODO:
                        ToDo t = new ToDo(cmd);
                        add(t);
                        store.update(items);
                        break;
                    case DEADLINE:
                        Deadline dl = new Deadline(cmd);
                        add(dl);
                        store.update(items);
                        break;
                    case EVENT:
                        Event e = new Event(cmd);
                        add(e);
                        store.update(items);
                        break;
                    case DELETE:
                        delete(cmd);
                        store.update(items);
                        break;
                    default:
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidInputException e) {
                ui.displayError(e);
            }
            cmd = ui.read();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
