package gson.test.project;

import java.util.List;

public class TasksCollection {
    private List<TaskProperties> tasks;

    public TasksCollection() {

    }

    public TasksCollection(List<TaskProperties> tasks) {
        this.tasks = tasks;
    }

    public List<TaskProperties> getTasks() {
        return this.tasks;
    }
}
