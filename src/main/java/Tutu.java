import java.util.*;

public class Tutu {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Tutu\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(cmd);
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
