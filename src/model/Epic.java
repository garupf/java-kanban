package model;

import java.util.ArrayList;
import java.util.List;


public class Epic extends Task {

    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String title, String description, int id, TaskStatus status) {
        super(title, description, id, status);
    }

    public Epic(String title, String description) {
        super(title, description, TaskStatus.NEW); // или другой статус по умолчанию
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subtaskId) {
        subtaskIds.add(subtaskId);
    }

    public void removeSubtaskId(int subtaskId) {
        subtaskIds.remove(Integer.valueOf(subtaskId));
    }

}
