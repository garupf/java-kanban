package test;

import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import manager.InMemoryTaskManager;
import manager.Managers;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void shouldReturnInitializedInMemoryTaskManager() {
        InMemoryTaskManager taskManager = Managers.getDefault();

        assertNotNull(taskManager, "TaskManager не должен быть null");
        assertInstanceOf(InMemoryTaskManager.class, taskManager, "Ожидался InMemoryTaskManager");

        // Проверка базовой работоспособности
        Task task = new Task("Test Task", "Test description", TaskStatus.NEW);
        taskManager.addTask(task);
        Task retrieved = taskManager.getTaskById(task.getId());

        assertEquals(task, retrieved, "Задача должна быть найдена после добавления");
    }

    @Test
    void shouldReturnInitializedInMemoryHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        assertNotNull(historyManager, "HistoryManager не должен быть null");
        assertInstanceOf(InMemoryHistoryManager.class, historyManager, "Ожидался InMemoryHistoryManager");

        // Проверка базовой работоспособности
        Task task = new Task("History Task", "History check", TaskStatus.DONE);
        historyManager.add(task);

        assertTrue(historyManager.getHistory().contains(task), "История должна содержать добавленную задачу");
    }
}