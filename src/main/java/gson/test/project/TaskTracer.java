package gson.test.project;

import com.google.gson.*;
import java.io.*;


public class TaskTracer {
    public static void main(String[] args) throws IOException {

        TaskManager taskManager = new TaskManager();
//        taskManager.addTask("Vendre colis");
//        taskManager.listTasks();
//        taskManager.displayTask(15);
//        taskManager.deleteTask(3);
        taskManager.updateTask("Acheter colis", 15);
        taskManager.updateTask(Status.InProgress, 15);


    }
}