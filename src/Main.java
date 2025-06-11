public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager manager = Managers.getDefault();
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

        manager.getTaskById(task1.getId());
        manager.getTaskById(task2.getId());
        manager.getEpicById(epic1.getId());
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask2.getId());
        manager.getEpicById(epic2.getId());
        manager.getSubtaskById(subtask3.getId());

        printAllTasks(manager);

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

    private static void printAllTasks(InMemoryTaskManager manager) {
    }
}
