package manager;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.List;

public interface TaskManagerImplementation {
    int generateId();

    List<Task> getAllTasks();

    void clearTasks();

    Task getTaskById(int id);

    void addTask(Task task);

    void updateTask(Task updatedTask);

    void removeTaskById(int id);

    List<Epic> getAllEpics();

    void clearEpics();

    Epic getEpicById(int id);

    void addEpic(Epic epic);

    void updateEpic(Epic updatedEpic);

    void removeEpicById(int id);

    List<Subtask> getSubtaskByEpicId(int epicId);

    TaskStatus calculateEpicStatus(int epicId);

    List<Subtask> getAllSubtasks();

    void clearSubtasks();

    Subtask getSubtaskById(int id);

    void addSubtask(Subtask subtask);

    void updateSubtask(Subtask updatedSubtask);

    void removeSubtaskById(int id);

    List<Task> getHistory();
}
