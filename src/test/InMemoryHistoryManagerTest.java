package test;

import manager.HistoryManager;
import manager.InMemoryHistoryManager;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryHistoryManagerTest {

    @Test
    void shouldPreserveTaskStateAtAdditionTime() {
        HistoryManager history = new InMemoryHistoryManager();

        Task task = new Task("Original", "Initial description", TaskStatus.NEW.NEW);
        history.add(task);

        // Изменяем исходный объект задачи
        task.setTitle("Changed name");
        task.setDescription("Updated description");
        task.setStatus(TaskStatus.DONE);

        // Получаем задачу из истории
        List<Task> historyList = history.getHistory();
        Task storedTask = historyList.get(0);

        // Проверяем, что история сохранила прежние значения
        assertEquals("Original", storedTask.getTitle(), "Имя задачи в истории должно остаться неизменным");
        assertEquals("Initial description", storedTask.getDescription(), "Описание должно остаться прежним");
        assertEquals(TaskStatus.NEW, storedTask.getStatus(), "Статус должен остаться прежним");
    }
}
