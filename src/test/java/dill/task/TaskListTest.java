package dill.task;

import java.lang.reflect.Field;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @Test
    void addTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class);   // Create mock Task to isolate TaskList
        taskList.addTask(mockTask);

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true);  // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        assertEquals(1, internalTaskList.size());
        assertEquals(mockTask, internalTaskList.get(0));
    }

    @Test
    void deleteTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class);   // Create mock Task to isolate TaskList

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true);  // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        internalTaskList.add(mockTask); // add mock Task to internal list
        Task removed = taskList.deleteTask(0);

        assertEquals(0, internalTaskList.size());
        assertEquals(mockTask, removed);
    }
}
