package gson.test.project;

import com.google.gson.*;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskTracer {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        taskManager.addTask("Vendre colis");
        taskManager.listTasks();
        taskManager.displayTask(15);


//        // Get a specific value from the tasks
//        String getDescription = tasks.stream()
//                .filter(td -> td.getTaskID() == iDRecherche)
//                .findFirst()
//                .map(TaskData::getDescription)
//                .orElse("Tache introuvable");
//
//        System.out.println(getDescription);
//
//        // Update a specific value
//        int idToUpdate = 6;
//        String newValue = "Nouvelle valeur";
//
//        for(TaskData td : tasks){
//            if (td.getTaskID() == idToUpdate){
//                td.setDescription(newValue);
//                td.setUpdatedAt(Instant.now().toString());
//                break;
//            }
//        }
//
//        TaskCollection taskCollection = new TaskCollection(tasks);
//
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tasks.json"))) {
//            gson.toJson(taskCollection, bufferedWriter);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Delete an object
//        int idToDelete = 9;
//        boolean removed = tasks.removeIf(td -> td.getTaskID() == idToDelete);
//
//        if (removed) {
//            System.out.println("Tache supprimée");
//        }else {
//            System.out.println("Tache introuvable");
//        }
//
//       taskCollection = new TaskCollection(tasks);
//
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tasks.json"))) {
//            gson.toJson(taskCollection, bufferedWriter);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}