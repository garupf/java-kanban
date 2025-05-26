import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int nextId = 0;

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Task task1 = new Task("Коляска", "Присоединить сумку к коляске", manager.generateId(), TaskStatus.NEW);
        manager.addTask(task1);
        Task task2 = new Task("Посуда", "Помыть посуду", manager.generateId(), TaskStatus.NEW);
        manager.addTask(task2);

        Epic epic1 = new Epic("Подготовка к поездке", "Собрать необходимую для поездки одежду", manager.generateId(), TaskStatus.NEW);
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Детская обувь", "Найти детскую обувь", manager.generateId(), TaskStatus.NEW, epic1.getId());
        manager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Взрослая обувь", "Найти взрослую обувь", manager.generateId(), TaskStatus.NEW, epic1.getId());
        manager.addSubtask(subtask2);

        Epic epic2 = new Epic("ЖКХ", "Оплатить коммунальные услуги", manager.generateId(), TaskStatus.NEW);
        manager.addEpic(epic2);

        Subtask subtask3 = new Subtask("ЖКХ", "Передать показания до 20 числа", manager.generateId(), TaskStatus.NEW, epic2.getId());
        manager.addSubtask(subtask3);

        for (Task tasks : manager.getAllTasks()) {
            System.out.println(tasks);
        }

        for (Epic epics : manager.getAllEpics()) {
            System.out.println(epics);
        }

        for (Subtask subtasks : manager.getAllSubtasks()) {
            System.out.println(subtasks);
        }

        task1.setStatus(TaskStatus.IN_PROGRESS);
        task2.setStatus(TaskStatus.IN_PROGRESS);

        subtask1.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);

        subtask3.setStatus(TaskStatus.DONE);

        for (Task tasks : manager.getAllTasks()) {
            System.out.println(tasks);
        }

        for (Epic epics : manager.getAllEpics()) {
            System.out.println(epics);
        }

        for (Subtask subtasks : manager.getAllSubtasks()) {
            System.out.println(subtasks);
        }

        manager.removeEpicById(11);

        manager.removeTaskById(1);
    }

    public int generateId() {
        return nextId++;
    }

    //----------TASK METHODS-------

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void clearTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void addTask(Task task) {
        task.setId(generateId());
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task updatedTask) {
        int id = updatedTask.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, updatedTask);
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

    public void removeTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

    //----------EPIC METHODS-------

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void clearEpics() {
        epics.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public void addEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
    }

    public void updateEpic(Epic updatedEpic) {
        int id = updatedEpic.getId();
        if (epics.containsKey(id)) {
            epics.put(id, updatedEpic);
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

    public void removeEpicById(int id) {
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            for (int subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
            System.out.println("Удалена задача с id " + id + " и все связанные с ней подзадачи");
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

    public List<Subtask> getSubtaskByEpicId(int epicId) {
        List<Subtask> result = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                result.add(subtask);
            }
        }
        return  result;
    }

    public TaskStatus calculateEpicStatus(int epicId) {
        List<Subtask> subtasksList = getSubtaskByEpicId(epicId);

        if (subtasksList.isEmpty()) {
            return TaskStatus.NEW;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : subtasksList) {
            TaskStatus status = subtask.getStatus();

            if (status != TaskStatus.NEW) {
                allNew = false;
            }
            if (status != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            return TaskStatus.DONE;
        } else if (allNew) {
            return TaskStatus.NEW;
        } else {
            return TaskStatus.IN_PROGRESS;
        }

    }

    //----------SUBTASK METHODS-------

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void addSubtask(Subtask subtask) {
        subtask.setId(generateId());
        subtasks.put(subtask.getId(), subtask);
    }

    public void updateSubtask(Subtask updatedSubtask) {
        int id = updatedSubtask.getId();
        if (subtasks.containsKey(id)) {
            subtasks.put(id, updatedSubtask);
            calculateEpicStatus(updatedSubtask.getEpicId());
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

    public void removeSubtaskById(int id) {
        if (subtasks.containsKey(id)) {
            subtasks.remove(id);
        } else {
            System.out.println("Задача с id " + id + " не найдена.");
        }
    }

}


