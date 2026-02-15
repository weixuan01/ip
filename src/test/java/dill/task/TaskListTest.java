package dill.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TaskListTest {
    private TaskList taskList;

    // Test if a task is added successfully into the list.
    @Test
    void addTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class); // Create mock Task to isolate TaskList
        taskList.addTask(mockTask);

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        assertEquals(1, internalTaskList.size());
        assertEquals(mockTask, internalTaskList.get(0));
    }

    // Test if a task is successfully deleted from the list.
    @Test
    void deleteTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();
        Task mockTask = Mockito.mock(Task.class); // Create mock Task to isolate TaskList

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list

        internalTaskList.add(mockTask); // add mock Task to internal list
        Task removed = taskList.deleteTask(0);

        assertEquals(0, internalTaskList.size());
        assertEquals(mockTask, removed);
    }

    // Test if the task list is correctly displayed.
    @Test
    void listTasksTest() {
        taskList = new TaskList();

        // Verify output for empty list.
        assertEquals("There are no tasks in the list!", taskList.listTasks().get(0));

        Task mockTask1 = Mockito.mock(Task.class);
        Task mockTask2 = Mockito.mock(Task.class);
        taskList.addTask(mockTask1);
        taskList.addTask(mockTask2);

        when(mockTask1.toString()).thenReturn("list test 1");
        when(mockTask2.toString()).thenReturn("list test 2");

        // Verify output for non-empty list.
        assertEquals("Here are the tasks in your list:", taskList.listTasks().get(0));
        assertEquals("  1.list test 1", taskList.listTasks().get(1));
        assertEquals("  2.list test 2", taskList.listTasks().get(2));
    }
}
