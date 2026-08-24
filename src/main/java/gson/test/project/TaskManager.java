package gson.test.project;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final Gson gson = new Gson();
    private List<Tasks> tasks;
    private static final Type TASK_LIST_TYPE = new TypeToken<List<Tasks>>(){}.getType();

    public TaskManager() {

    }

    private List<Tasks> loadTasks() {
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)) {
            List<Tasks> tasks = gson.fromJson(fileReader, TASK_LIST_TYPE);

            if (tasks == null) {
                tasks = new ArrayList<>();
            }

            if (tasks.isEmpty()) {
                System.out.println("No task listed in the JSON file");
            }

            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void addTask(String description) {
        this.tasks = loadTasks();

        int id = getNextAvailableId();
        this.tasks.add(new Tasks(
                id,
                description,
                Status.Todo,
                Instant.now().toString(),
                Instant.now().toString()
        ));

        writeToJson();
    }

    public void listTasks() {
        this.tasks = loadTasks();
        this.tasks.forEach(System.out::println);
    }

    public void listTasksByStatus(Status status) {
        this.tasks = loadTasks();
        List<Tasks> filtered = this.tasks.stream()
                .filter(t -> t.getStatus() == status)
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No tasks with status '" + status + "' listed");
        } else {
            filtered.forEach(System.out::println);
        }
    }

    public void displayTask(int taskId) {
        this.tasks = loadTasks();
        this.tasks.stream()
                .filter(task -> task.getTaskID() == taskId)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Unable to find the task with the id : " + taskId)
                );
    }

--

    public void updateTask(String description, int taskId) {
        this.tasks = loadTasks();

        for (Tasks td : this.tasks) {
            if (td.getTaskID() == taskId) {
                td.setDescription(description);
                td.setUpdatedAt(Instant.now().toString());
                System.out.println("Task description has been updated");
                break;
            }
        }

        writeToJson();
    }

    public void updateTask(Status status, int taskId) {
        this.tasks = loadTasks();

        for (Tasks task : this.tasks) {
            if (task.getTaskID() == taskId) {
                task.setStatus(status);
                task.setUpdatedAt(Instant.now().toString());
                System.out.println("Task status has been updated");
                break;
            }
        }

        writeToJson();
    }

    public void deleteTask(int taskId) {
        this.tasks = loadTasks();
        boolean removed = this.tasks.removeIf(task -> task.getTaskID() == taskId);

        System.out.println(removed ? "Task with id " + taskId + " has been deleted" : "Unable to find the task");

        writeToJson();
    }

    public int getNextAvailableId() {
        List<Tasks> currentTasks = loadTasks();

        return currentTasks.stream()
                .mapToInt(Tasks::getTaskID)
                .max()
                .orElse(0) + 1;
    }

    public void writeToJson() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.JSON_FILE_PATH))) {
            this.gson.toJson(this.tasks, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}