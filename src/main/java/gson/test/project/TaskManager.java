package gson.test.project;

import com.google.gson.Gson;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private Instant instant = Instant.now();
    private Gson gson = new Gson();
    private List<TaskData> tasks;

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
    public void displayTask(TaskCollection tasks){

    }

    // Method that will allow the program to update the description of any task
    public void updateTask(String description, String updatedAt)throws IOException {

    }

    // Method that will allow the program to update the status of any task
    public void updateTask(Status status, String updatedAt) throws IOException {

    }

    // Method that will allow the program to delete any task
    public void deleteTask(TaskCollection tasks) throws IOException {

    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
