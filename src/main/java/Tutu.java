import java.util.*;

public class Tutu {
    public static void separator() {
        System.out.println("******************************************");
    }
    public static void main(String[] args) {
        Tutu.separator();
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Tutu.separator();
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(cmd);
            Tutu.separator();
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
