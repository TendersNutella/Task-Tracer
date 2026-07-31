package gson.test.project;

import java.util.List;

public class TaskCollection {
    private List<TaskData> tasks;

    public TaskCollection() {

    }

    public TaskCollection(List<TaskData> tasks) {
        this.tasks = tasks;
    }

    public List<TaskData> getTasks() {
        return this.tasks;
    }
}
