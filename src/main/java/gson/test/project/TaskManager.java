package gson.test.project;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final Gson gson = new Gson();
    private List<TaskProperties> tasks;
    private List<TaskProperties> loadTasks(){
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)) {
            return gson.fromJson(fileReader, TasksCollection.class).getTasks();
        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public TaskManager() throws FileNotFoundException {

    }

    // Method that will allow the program to add a task to the JSON file
    public void addTask(String description) {
        // Getting previous tasks
        if (CommonConstant.JSON_FILE_PATH.exists() && CommonConstant.JSON_FILE_PATH.length() > 0) {
            try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
                TasksCollection previousTasks = this.gson.fromJson(fileReader, TasksCollection.class);
                if (previousTasks != null){
                    this.tasks = previousTasks.getTasks();
                }else {
                    this.tasks = new ArrayList<>();
                }

                for (TaskProperties t : this.tasks){
                    System.out.println(t);
                }
            }catch (IOException e){
                e.printStackTrace();
                this.tasks = new ArrayList<>();
            }
        }
        // Adding the new tasks to the list
        int id = getNextAvailableId();
        this.tasks.add(new TaskProperties(
                id,
                description,
                Status.Todo,
                Instant.now().toString(),
                Instant.now().toString()
        ));

        writeToJson();
    }

    // Method that will allow to list all the task
    public void listTasks(){
        if (CommonConstant.JSON_FILE_PATH.exists()) {
            this.tasks = loadTasks();

            for (TaskProperties t : this.tasks){
                System.out.println(t);
            }

        } else if (CommonConstant.JSON_FILE_PATH.length() <= 0) {
            System.out.println("'tasks.json' is empty");
            tasks = new ArrayList<>();
        } else {
            System.out.println("Unable to find 'tasks.json' file");
            tasks = new ArrayList<>();
        }
    }

    // Method that will allow to get a specific task display
    public void displayTask(int taskId){
        if (CommonConstant.JSON_FILE_PATH.exists() && CommonConstant.JSON_FILE_PATH.length() > 0){
            this.tasks = loadTasks();
            TaskProperties taskToDisplay = this.tasks.stream()
                    .filter(taskProperties -> taskProperties.getTaskID() == taskId)
                    .findFirst()
                    .orElse(null);

            if (taskToDisplay != null){
                System.out.println(taskToDisplay);
            }else {
                System.out.println("Unable to find the task with the id : " + taskId);
            }
        }else {
            System.out.println("Unable to find the 'task.json' file or the file is empty");
        }
    }

    // TODO : This is working fine but it prints all the tasks with the status asked in one line. Need to change the behavior of this method
    public void listTasksByStatus(Status status){
        this.tasks = loadTasks();
        List<TaskProperties> tasksDone = this.tasks.stream()
                .filter(taskProperties -> taskProperties.getStatus() == status)
                .toList();

        System.out.println(tasksDone);
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

    public int getNextAvailableId() {
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)) {
            TasksCollection existingTasks = gson.fromJson(fileReader, TasksCollection.class);
            List<TaskProperties> currentTasks;

            if (existingTasks != null){
                currentTasks = existingTasks.getTasks();
            }else {
                currentTasks = new ArrayList<>();
            }

            return  currentTasks.stream()
                    .mapToInt(TaskProperties::getTaskID)
                    .max()
                    .orElse(0) + 1;

        }catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public void writeToJson(){
        TasksCollection tasksCollection = new TasksCollection(this.tasks);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.JSON_FILE_PATH))){
            this.gson.toJson(tasksCollection, bufferedWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
