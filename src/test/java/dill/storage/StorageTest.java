package dill.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import dill.task.TaskList;
import dill.task.Task;
import dill.exception.StorageException;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    private Storage storage;

    @TempDir
    Path tempDir;

    @Test
    void saveTasksValidFileTest() throws IOException {
        Path tempFile = tempDir.resolve("saveTasksTest.txt");
        storage = new Storage(tempFile.toString());

        TaskList mockTaskList = Mockito.mock(TaskList.class);
        Task mockTask1 = Mockito.mock(Task.class);
        Task mockTask2 = Mockito.mock(Task.class);

        when(mockTaskList.getSize()).thenReturn(2);
        when(mockTaskList.getTask(0)).thenReturn(mockTask1);
        when(mockTaskList.getTask(1)).thenReturn(mockTask2);
        when(mockTask1.toFileString()).thenReturn("T | 1 | read");
        when(mockTask2.toFileString()).thenReturn("D | 0 | assignment | 2026-03-14");

        assertDoesNotThrow(() -> storage.saveTasks(mockTaskList));
        List<String> lines = java.nio.file.Files.readAllLines(tempFile);
        assertEquals(2, lines.size());
        assertEquals("T | 1 | read", lines.get(0));
        assertEquals("D | 0 | assignment | 2026-03-14", lines.get(1));
    }

    @Test
    void saveTasksInvalidFileTest() {
        storage = new Storage("./nonexistentDir/test.txt");
        TaskList mockTaskList = Mockito.mock(TaskList.class);
        assertThrows(StorageException.class, () -> storage.saveTasks(mockTaskList));
    }
}
