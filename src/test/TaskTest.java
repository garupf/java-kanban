package test;

import model.TaskStatus;
import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    void tasksAreEqualIfIdIsSame() {
        Task task1 = new Task("model.Task", "Desc", 1, TaskStatus.NEW);
        Task task2 = new Task("model.Task", "Desc", 1, TaskStatus.DONE);
        assertEquals(task1, task2);
    }

    @Test
    void epicsAreEqualIfIdIsSame() {
        Epic epic1 = new Epic("model.Epic", "desc", 2, TaskStatus.NEW);
        Epic epic2 = new Epic("model.Epic", "another", 2, TaskStatus.DONE);
        assertEquals(epic1, epic2);
    }

    @Test
    void epicCannotContainItselfAsSubtask() {
        Epic epic = new Epic("model.Epic", "desc", 3, TaskStatus.NEW);
        epic.addSubtaskId(epic.getId());
        assertFalse(epic.getSubtaskIds().contains(epic.getId()));
    }

    @Test
    void subtaskCannotBeEpicOfItself() {
        Subtask subtask = new Subtask("Sub", "desc", 4, TaskStatus.NEW, 4);
        assertNotEquals(subtask.getId(), subtask.getEpicId());
    }
}