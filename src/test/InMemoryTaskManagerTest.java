package test;

import manager.InMemoryTaskManager;
import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    void shouldAddAndFindTasksById() {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        // Обычная задача
        Task task = new Task("Task 1", "Description 1", TaskStatus.NEW);
        manager.addTask(task);
        assertEquals(task, manager.getTaskById(task.getId()), "Task должна быть найдена по id");

        // Эпик
        Epic epic = new Epic("Epic 1", "Epic description");
        manager.addEpic(epic);
        assertEquals(epic, manager.getEpicById(epic.getId()), "Epic должен быть найден по id");

        // Подзадача
        Subtask subtask = new Subtask("Subtask 1", "Subtask description", TaskStatus.IN_PROGRESS, epic.getId());
        manager.addSubtask(subtask);
        assertEquals(subtask, manager.getSubtaskById(subtask.getId()), "Subtask должен быть найден по id");

        // Проверка, что подзадача добавлена в эпик
        assertTrue(manager.getSubtaskByEpicId(epic.getId()).contains(subtask), "Subtask должен быть связан с Epic");
    }

    @Test
    void taskShouldRemainUnchangedAfterAddition() {
        InMemoryTaskManager manager = new InMemoryTaskManager();

        Task originalTask = new Task("Task A", "Task Description", TaskStatus.NEW);
        manager.addTask(originalTask);

        Task retrievedTask = manager.getTaskById(originalTask.getId());

        assertNotNull(retrievedTask, "Задача должна быть найдена в менеджере");

        // Проверка всех полей
        assertEquals(originalTask.getId(), retrievedTask.getId(), "ID должен совпадать");
        assertEquals(originalTask.getTitle(), retrievedTask.getTitle(), "Название должно совпадать");
        assertEquals(originalTask.getDescription(), retrievedTask.getDescription(), "Описание должно совпадать");
        assertEquals(originalTask.getStatus(), retrievedTask.getStatus(), "Статус должен совпадать");
        assertEquals(originalTask.getClass(), retrievedTask.getClass(), "Тип задачи должен совпадать");
    }
}