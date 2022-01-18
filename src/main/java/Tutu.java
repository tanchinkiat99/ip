import java.util.*;

public class Tutu {
    private static ArrayList<String> items = new ArrayList<>();
    public static void separator() {
        System.out.println("******************************************");
    }
    public static void add(String cmd) {
        items.add(cmd);
        System.out.println("added: " + cmd);
        Tutu.separator();
    }
    public static void list() {
        for (int i = 1; i <= items.size(); i++) {
            System.out.println(i + ". " + items.get(i - 1));
        }
        Tutu.separator();
    }
    public static void main(String[] args) {
        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                list();
            } else {
                add(cmd);
            }
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
