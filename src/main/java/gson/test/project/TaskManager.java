package gson.test.project;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private Instant instant = Instant.now();
    private Gson gson = new Gson();
    private List<TaskData> tasks;
    private Scanner scanner;

    public TaskManager()  {

    }

    // Method that will allow the program to add a task to the JSON file
    public void addTask(String description) {
        // Getting previous tasks
        if (CommonConstant.JSON_FILE_PATH.exists() && CommonConstant.JSON_FILE_PATH.length() > 0) {
            try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
                TaskCollection previousTasks = this.gson.fromJson(fileReader, TaskCollection.class);
                if (previousTasks != null){
                    this.tasks = previousTasks.getTasks();
                }else {
                    this.tasks = new ArrayList<>();
                }

                for (TaskData t : this.tasks){
                    System.out.println(t);
                }
            }catch (IOException e){
                e.printStackTrace();
                this.tasks = new ArrayList<>();
            }
        }

        // Adding the new tasks to the list
        this.tasks.add(new TaskData(
                this.tasks.size() + 1,
                description,
                Status.Todo,
                Instant.now().toString(),
                Instant.now().toString()
        ));

        TaskCollection taskCollection = new TaskCollection(this.tasks);

        // Write the previous tasks and the new tasks into the file
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.JSON_FILE_PATH))){
            this.gson.toJson(taskCollection, bufferedWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Method that will allow to list all the task
    public void listTasks(){
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
            if (CommonConstant.JSON_FILE_PATH.exists()) {
                this.tasks = gson.fromJson(fileReader, TaskCollection.class).getTasks();

                for (TaskData t : this.tasks){
                    System.out.println(t);
                }

            } else if (CommonConstant.JSON_FILE_PATH.length() <= 0) {
                System.out.println("'tasks.json' is empty");
                tasks = new ArrayList<>();
            } else {
                System.out.println("Unable to find 'tasks.json' file");
                tasks = new ArrayList<>();
            }
        }catch (IOException e){
            e.printStackTrace();
            tasks = new ArrayList<>();
        }
    }


    // Method that will allow to get a specific task display
    public void displayTask(int taskId){
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
            if (CommonConstant.JSON_FILE_PATH.exists() && CommonConstant.JSON_FILE_PATH.length() > 0){
                this.tasks = gson.fromJson(fileReader, TaskCollection.class).getTasks();
                TaskData taskToDisplay = this.tasks.stream()
                        .filter(taskData -> taskData.getTaskID() == taskId)
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
        }catch (IOException e){
            e.printStackTrace();
            tasks = new ArrayList<>();
        }
    }

    public String getSpecificValue(String value, int taskID){
        // TODO : work on the behavior of this method and add a selector of a specific value
        //  like a switch case that let you choose between getting the description, the id, or even
        //  the date where the task was created
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
            this.tasks = gson.fromJson(fileReader, TaskCollection.class).getTasks();
            this.scanner = new Scanner(System.in);

            value = this.tasks.stream()
                    .filter(taskData -> taskData.getTaskID() == taskID)
                    .findFirst()
                    .map(TaskData::getDescription)
                    .orElse("Unable to find the task");

            return value;
        }catch (IOException e){
            e.printStackTrace();
        }

        return "Unable to get the value asked";
    }

    // Method that will allow the program to update the description of any task
    public void updateTask(String description, String updatedAt) throws IOException {
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)) {

        }
    }

    // Method that will allow the program to update the status of any task
    public void updateTask(Status status, String updatedAt) throws IOException {

    }

    // Method that will allow the program to delete any task
    public void deleteTask(int taskId) throws IOException {
        try (FileReader fileReader = new FileReader(CommonConstant.JSON_FILE_PATH)){
            this.tasks = gson.fromJson(fileReader, TaskCollection.class).getTasks();
            boolean removed = this.tasks.removeIf(taskData -> taskData.getTaskID() == taskId);

            if (removed) {
                System.out.println("Task with id " + taskId + " has been deleted");
            }else {
                System.out.println("Unable to find the task");
            }


        }catch (IOException e) {
            e.printStackTrace();
        }

        TaskCollection taskCollection = new TaskCollection(this.tasks);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstant.JSON_FILE_PATH))){
            this.gson.toJson(taskCollection, bufferedWriter);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
