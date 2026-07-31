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
        taskManager.listTasks();

//        // Adding the already existing tasks to the list
//        if(file.exists() && file.length() > 0) {
//            try(FileReader fileReader = new FileReader(file)) {
//                TaskCollection existingTask = gson.fromJson(fileReader, TaskCollection.class);
//                if(existingTask != null){
//                    tasks = existingTask.getTasks();
//                }else{
//                    tasks = new ArrayList<>();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                tasks = new ArrayList<>();
//            }
//        }else {
//            tasks = new ArrayList<>();
//        }
//
//        for(TaskData tp : tasks){
//            System.out.println(tp);
//        }
//
//        // Adding a task to the List
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("> ");
//        String description = scanner.nextLine();
//
//        tasks.add(new TaskData(
//                tasks.size() + 1,
//                description,
//                Status.Todo,
//                Instant.now().toString(),
//                Instant.now().toString()
//        ));
//
//        TaskCollection task = new TaskCollection(tasks);
//
//        // Write to the "tasks.json" file
//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tasks.json"))) {
//            gson.toJson(task, bufferedWriter);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Get a specific task from the "tasks.json" file
//        int iDRecherche = 2;
//        TaskData taskFound = tasks.stream()
//                .filter(td -> td.getTaskID() == iDRecherche)
//                .findFirst()
//                .orElse(null);
//
//        if (taskFound != null) {
//            System.out.println(taskFound);
//        } else {
//            System.out.println("Tache introuvable");
//        }
//
//
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