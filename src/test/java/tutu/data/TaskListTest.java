package tutu.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tutu.exception.InvalidInputException;
import tutu.task.ToDo;

public class TaskListTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void addAndRemoveTest() throws InvalidInputException {
        TaskList taskList = new TaskList();
        ToDo t = new ToDo("todo say hello");
        taskList.add(t);
        assertEquals(1, taskList.taskNumber());

        String task = taskList.retrieve(1).isDone();
        assertEquals("[T][ ] say hello", task);

        taskList.delete("delete 1");
        assertEquals(0, taskList.taskNumber());
    }

    @Test
    public void markAndUnmarkTest() throws InvalidInputException {
        TaskList taskList = new TaskList();
        ToDo t = new ToDo("todo say hello");
        taskList.add(t);

        String task = taskList.retrieve(1).isDone();
        assertEquals("[T][ ] say hello", task);

        taskList.mark(1);
        String marked = taskList.retrieve(1).isDone();
        assertEquals("[T][X] say hello", marked);

        taskList.unmark(1);
        String unmarked = taskList.retrieve(1).isDone();
        assertEquals("[T][ ] say hello", unmarked);
    }
}
