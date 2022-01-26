public class Parser {

    public static Tutu.Function checkFunction(String cmd) throws InvalidInputException {
        if (cmd.equals("list")) {
            return Tutu.Function.LIST;
        } else if (cmd.length() > 3 && cmd.substring(0, 4).equals("mark")) {
            return Tutu.Function.MARK;
        } else if (cmd.length() > 5 && cmd.substring(0, 6).equals("unmark")) {
            return Tutu.Function.UNMARK;
        } else if (cmd.length() > 3 && cmd.substring(0, 4).equals("todo")) {
            return Tutu.Function.TODO;
        } else if (cmd.length() > 7 && cmd.substring(0, 8).equals("deadline")) {
            return Tutu.Function.DEADLINE;
        } else if (cmd.length() > 4 && cmd.substring(0, 5).equals("event")) {
            return Tutu.Function.EVENT;
        } else if (cmd.length() > 5 && cmd.substring(0, 6).equals("delete")) {
            return Tutu.Function.DELETE;
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
