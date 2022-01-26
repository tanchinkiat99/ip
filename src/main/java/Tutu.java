import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Tutu {
    private static ArrayList<Task> items = new ArrayList<>();

    public enum Function {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Function checkFunction(String cmd) throws InvalidInputException {
        if (cmd.equals("list")) {
            return Function.LIST;
        } else if (cmd.length() > 3 && cmd.substring(0, 4).equals("mark")) {
            return Function.MARK;
        } else if (cmd.length() > 5 && cmd.substring(0, 6).equals("unmark")) {
            return Function.UNMARK;
        } else if (cmd.length() > 3 && cmd.substring(0, 4).equals("todo")) {
            return Function.TODO;
        } else if (cmd.length() > 7 && cmd.substring(0, 8).equals("deadline")) {
            return Function.DEADLINE;
        } else if (cmd.length() > 4 && cmd.substring(0, 5).equals("event")) {
            return Function.EVENT;
        } else if (cmd.length() > 5 && cmd.substring(0, 6).equals("delete")) {
            return Function.DELETE;
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
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
    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i - 1).isDone());
        }
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

    public static void update(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 1; i <= items.size(); i++) {
            fw.write(i + ". " + items.get(i - 1).isDone());
        }
        fw.close();
    }

    public static void main(String[] args) throws InvalidInputException, IOException {
        Path p = Paths.get("./data");
        System.out.println(Files.exists(p));
        File f = new File("./data/tut.txt");
        if (!Files.exists(p)) {
            File path = new File("./data");
            path.mkdir();
        }
        f.createNewFile();

        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            try {
                switch (checkFunction(cmd)) {
                    case LIST:
                        list();
                        break;
                    case MARK:
                        int i = Integer.parseInt(cmd.substring(5)); // throw NumberFormatException if not valid
                        if (i <= items.size()) {
                            mark(i);
                            update(f);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case UNMARK:
                        int j = Integer.parseInt(cmd.substring(7)); // throw NumberFormatException if not valid
                        if (j <= items.size()) {
                            unmark(j);
                            update(f);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case TODO:
                        ToDo t = new ToDo(cmd);
                        add(t);
                        update(f);
                        break;
                    case DEADLINE:
                        Deadline dl = new Deadline(cmd);
                        add(dl);
                        update(f);
                        break;
                    case EVENT:
                        Event e = new Event(cmd);
                        add(e);
                        update(f);
                        break;
                    case DELETE:
                        delete(cmd);
                        update(f);
                        break;
                    default:
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (InvalidInputException e) {
                System.err.println(e);
            }
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
