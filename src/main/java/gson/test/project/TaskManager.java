package gson.test.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class TaskManager {
    private Instant instant = Instant.now();

    public TaskManager(){

    }

    // Method that will allow the program to add a task to the JSON file
    public static void addTask(String description) throws IOException {

    }

    // Method that will allow to list all the task
    public void listTasks(TaskCollection tasks){

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
