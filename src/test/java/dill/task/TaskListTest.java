package dill.task;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

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

    // Test if a task is marked successfully
    @Test
    void markTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        Task task1 = new ToDo("Study");

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(task1); // Manually add task into the list

        assertFalse(internalTaskList.get(0).getIsDone()); // Check default is undone

        taskList.markTask(0);

        assertTrue(internalTaskList.get(0).getIsDone()); // Verify task marked as done
    }

    // Test if a task is unmarked successfully
    @Test
    void unmarkTaskTest() throws NoSuchFieldException, IllegalAccessException {
        taskList = new TaskList();

        Task task1 = new ToDo("Study");
        task1.setIsDone(true); // Mark task as done initially

        Field tasksField = TaskList.class.getDeclaredField("tasks");
        tasksField.setAccessible(true); // Set internal list as accessible
        @SuppressWarnings("unchecked")
        List<Task> internalTaskList = (List<Task>) tasksField.get(taskList); // Get internal list
        internalTaskList.add(task1); // Manually add task into the list

        assertTrue(internalTaskList.get(0).getIsDone()); // Verify default is marked as done

        taskList.unmarkTask(0);

        assertFalse(internalTaskList.get(0).getIsDone()); // Verify task is now unmarked
    }
}
