import java.util.*;

public class Tutu {
    private static ArrayList<Task> items = new ArrayList<>();

    public enum Function {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
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

    public static void main(String[] args) throws InvalidInputException {
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
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case UNMARK:
                        int j = Integer.parseInt(cmd.substring(7)); // throw NumberFormatException if not valid
                        if (j <= items.size()) {
                            unmark(j);
                        } else {
                            System.out.println("Oops! Index is out of range");
                        }
                        break;
                    case TODO:
                        ToDo t = new ToDo(cmd);
                        add(t);
                        break;
                    case DEADLINE:
                        Deadline dl = new Deadline(cmd);
                        add(dl);
                        break;
                    case EVENT:
                        Event e = new Event(cmd);
                        add(e);
                        break;
                    default:
                        break;
                }
            } catch (InvalidInputException e) {
                System.err.println(e);
            }
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
