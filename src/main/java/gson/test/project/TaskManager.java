package gson.test.project;

import com.google.gson.Gson;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskManager {
    private final Gson gson = new Gson();
    private List<TaskProperties> tasks;
    private List<TaskProperties> loadTasks() {
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)) {
            TasksCollection tasksCollection = gson.fromJson(fileReader, TasksCollection.class);
            List<TaskProperties> tasks;

            if (tasksCollection != null) {
                tasks = tasksCollection.getTasks();
            } else {
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

    public TaskManager() {

    }

    // Method that will allow the program to add a task to the JSON file
    public void addTask(String description) {

        // Getting previous tasks
        TasksCollection previousTasks = new TasksCollection(this.tasks);
        this.tasks = previousTasks.getTasks();

//        Error handling just in case, but I don't think it is really necessary since
//        this method will add tasks to the JSON file previous tasks or not
//        if (!this.tasks.isEmpty()){
//            System.out.println("No previous tasks listed");
//        }

        // Adding the new tasks to the list
        int id = getNextAvailableId();
        this.tasks.add(new TaskProperties (
                id,
                description,
                Status.Todo,
                Instant.now().toString(),
                Instant.now().toString()
        ));

        writeToJson();
    }

    // Method that will allow to list all the task
    public void listTasks() {
        this.tasks = loadTasks();

        for (TaskProperties t : this.tasks) {
            System.out.println(t);
        }
    }

    // Method that will allow to get a specific task display
    public void listTasksByStatus(Status status) {
        this.tasks = loadTasks();
        List<TaskProperties> tasksListedAsDone = this.tasks.stream()
                .filter(taskProperties -> taskProperties.getStatus() == status)
                .toList();

        if (tasksListedAsDone.isEmpty()) {
            System.out.println("No tasks as 'Done' listed");
        }else {
            tasksListedAsDone.forEach(System.out::println);
        }
    }

    // Method that is used to display a single task
    public void displayTask(int taskId) {
        this.tasks = loadTasks();
        TaskProperties taskToDisplay = this.tasks.stream()
                .filter(taskProperties -> taskProperties.getTaskID() == taskId)
                .findFirst()
                .orElse(null);

        if (taskToDisplay != null) {
            System.out.println(taskToDisplay);
        }else {
            System.out.println("Unable to find the task with the id : " + taskId);
        }
    }

    // Method that will allow the program to update the description of any task
    public void updateTask(String description, int taskId) {
        this.tasks = loadTasks();

        for (TaskProperties td : this.tasks) {
            if (td.getTaskID() == taskId) {
                td.setDescription(description);
                td.setUpdatedAt(Instant.now().toString());
                System.out.println("Task description has been updated");
                break;
            }
        }

        writeToJson();
    }

    // Method that will allow the program to update the status of any task
    public void updateTask(Status status, int taskId) {
        this.tasks = loadTasks();

        for (TaskProperties td : this.tasks) {
            if (td.getTaskID() == taskId) {
                td.setStatus(status);
                td.setUpdatedAt(Instant.now().toString());
                System.out.println("Task status has been updated");
                break;
            }
        }

        writeToJson();
    }

    // Method that will allow the program to delete any task
    public void deleteTask(int taskId) {
        this.tasks = loadTasks();
        boolean removed = this.tasks.removeIf(taskProperties -> taskProperties.getTaskID() == taskId);

        if (removed) {
            System.out.println("Task with id " + taskId + " has been deleted");
        }else {
            System.out.println("Unable to find the task");
        }

        writeToJson();
    }

    // Method that return the taskId according to the id already attributed in the JSON file
    public int getNextAvailableId() {
        this.tasks = loadTasks();
        List<TaskProperties> currentTasks;

        currentTasks = Objects.requireNonNullElseGet(tasks, ArrayList::new);

        return currentTasks.stream()
                .mapToInt(TaskProperties::getTaskID)
                .max()
                .orElse(0) + 1;
    }

    // Method used to write to the JSON file with a BufferWriter
    public void writeToJson() {
        TasksCollection tasksCollection = new TasksCollection(this.tasks);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.JSON_FILE_PATH))) {
            this.gson.toJson(tasksCollection, bufferedWriter);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
