package gson.test.project;
import java.io.*;
import java.util.Scanner;


public class TaskTracer {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] commands = {
          "task-cli add",
          "task-cli update",
          "task-cli delete",
          "task-cli mark-in-progress",
          "task-cli mark-done",
          "task-cli list",
          "task-cli list done",
          "task-cli list todo",
          "task-cli list in-progress",
        };

//        String commandInput = scanner.nextLine();
//        int idInput = scanner.nextInt();

        TaskManager taskManager = new TaskManager();
        taskManager.deleteTask(6);

    }
}