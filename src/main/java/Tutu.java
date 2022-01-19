import java.util.*;

public class Tutu {
    private static ArrayList<Task> items = new ArrayList<>();

    public enum Function {
        LIST, ADD, MARK, UNMARK
    }

    public static Function checkFunction(String cmd) {
        if (cmd.equals("list")) {
            return Function.LIST;
        } else if (cmd.length() > 5 && cmd.substring(0, 5).equals("mark ")) {
            return Function.MARK;
        } else if (cmd.length() > 7 && cmd.substring(0, 7).equals("unmark ")) {
            return Function.UNMARK;
        } else {
            return Function.ADD;
        }
    }

    public static void separator() {
        System.out.println("******************************************");
    }
    public static void add(String cmd) {
        Task task = new Task(cmd);
        items.add(task);
        System.out.println("added: " + task.getTask());
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

    public static class Task {
        private String task;
        private boolean done;

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
    }
    public static void main(String[] args) {
        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            switch (checkFunction(cmd)) {
                case LIST:
                    list();
                    break;
                case ADD:
                    add(cmd);
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
                default:
                    break;
            }
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
