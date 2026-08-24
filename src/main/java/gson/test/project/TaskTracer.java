package gson.test.project;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;


public class TaskTracer {
    public static void main(String[] args) throws IOException {
        final TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;


        System.out.println("Welcome to Task-Tracer");

        while (isRunning) {
            System.out.println("\nAll your tasks : ");
            taskManager.listTasks();
            System.out.println("\nEnter your command below : ");
            System.out.print("> ");
            String commandInput = scanner.nextLine();

            switch (commandInput.toLowerCase()) {
                case "exit" -> isRunning = false;

                case "task-cli add" -> {
                    System.out.println("Enter the description of the task");
                    System.out.print("> ");
                    String description = scanner.nextLine();
                    taskManager.addTask(description);
                }

                case "task-cli update" -> {
                    System.out.println("Enter the id of the task");
                    System.out.print("> ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the description of the task");
                    System.out.print("> ");
                    String description = scanner.nextLine();
                    taskManager.updateTask(description, taskId);
                }

                case "task-cli delete" -> {
                    System.out.println("Enter the id of the task");
                    System.out.print("> ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.deleteTask(taskId);
                }

                case "task-cli mark-in-progress" -> {
                    System.out.println("Enter the id of the task");
                    System.out.print("> ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.updateTask(Status.In_Progress, taskId);
                }

                case "task-cli mark-done" -> {
                    System.out.println("Enter the id of the task");
                    System.out.print("> ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.updateTask(Status.Done, taskId);
                }

                case "task-cli mark-not-done" -> {
                    System.out.println("Enter the id of the task");
                    System.out.print("> ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine();
                    taskManager.updateTask(Status.Not_Done, taskId);
                }

                case "task-cli list" -> taskManager.listTasks();
                case "task-cli list-todo" -> taskManager.listTasksByStatus(Status.Todo);
                case "task-cli list-in-progress" -> taskManager.listTasksByStatus(Status.In_Progress);
                case "task-cli list-done" -> taskManager.listTasksByStatus(Status.Done);

                default -> System.out.println("Unknown command : " + commandInput);
            }
        }
    }
}