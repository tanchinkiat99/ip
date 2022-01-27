package tutu.user;

import tutu.exception.InvalidInputException;

public class Parser {

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
}
