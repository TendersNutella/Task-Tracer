package gson.test.project;
import java.io.*;
import java.util.Scanner;


public class TaskTracer {
    public static void main(String[] args) throws IOException {
        final TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String taskDescriptionInput;
        boolean isRunning = true;
        int taskId;

        taskManager.displayTask(9);

        while (isRunning) {
            System.out.println("All your tasks : ");
            taskManager.listTasks();
            System.out.println("\n");

            System.out.println("Welcome to Task-Tracer");
            System.out.println("Enter your command below : ");
            System.out.print(">  ");
            String commandInput = scanner.nextLine();

            if (commandInput.equalsIgnoreCase("exit")) {
                isRunning = false;
            }

            if (commandInput.equalsIgnoreCase("task-cli add")) {
                System.out.println("Enter the description of the task");
                System.out.print("> ");
                taskDescriptionInput = scanner.nextLine();
                taskManager.addTask(taskDescriptionInput);
            }

            if (commandInput.equalsIgnoreCase("task-cli update")) {
                System.out.println("Enter the id of the task");
                System.out.print("> ");
                taskId = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter the description of the task");
                System.out.print("> ");
                taskDescriptionInput = scanner.nextLine();
                taskManager.updateTask(taskDescriptionInput, taskId);
            }

            if (commandInput.equalsIgnoreCase("task-cli delete")) {
                System.out.println("Enter the id of the task");
                System.out.print("> ");
                taskId = scanner.nextInt();
                taskManager.deleteTask(taskId);
            }

            if (commandInput.equalsIgnoreCase("task-cli mark-in-progress")) {
                System.out.println("Enter the id of the task");
                System.out.print("> ");
                taskId = scanner.nextInt();
                taskManager.updateTask(Status.InProgress, taskId);
            }

            if (commandInput.equalsIgnoreCase("task-cli mark-done")) {
                System.out.println("Enter the id of the task");
                System.out.print("> ");
                taskId = scanner.nextInt();
                taskManager.updateTask(Status.Done, taskId);
            }

            if (commandInput.equalsIgnoreCase("task-cli list")) {
                taskManager.listTasks();
            }

            if (commandInput.equalsIgnoreCase("task-cli list-todo")) {
                taskManager.listTasksByStatus(Status.Todo);
            }

            if (commandInput.equalsIgnoreCase("task-cli list-in-progress")) {
                taskManager.listTasksByStatus(Status.InProgress);
            }

            if (commandInput.equalsIgnoreCase("task-cli list-done")) {
                taskManager.listTasksByStatus(Status.Done);
            }
        }
    }
}